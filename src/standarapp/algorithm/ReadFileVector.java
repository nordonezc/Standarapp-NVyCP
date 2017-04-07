/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package standarapp.algorithm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Credits seatgeek Adam Cohen David Necas (python-Levenshtein) Mikko Ohtamaa
 * (python-Levenshtein) Antti Haapala (python-Levenshtein)
 *
 * This project is an implementation of levenstein distance development by
 * people called before.
 *
 * @author Niki Ordoñez
 */
public class ReadFileVector {

    private CodeAssign ca;
    private Hashtable<String, Hashtable<String, Hashtable<String, Double>>> listOfStandarNames;
    private Hashtable<Integer, Hashtable<Double, String>> mncp_localidad;
    private Hashtable<Double, Double> localidad_x;
    private Hashtable<Double, Double> localidad_y;
    private static Hashtable<Integer, String> codigo_Dpto;
    private static Hashtable<Integer, String> codigo_Municipio;
    private static Hashtable<Double, String> codigo_localidad;

    private ArrayList<String[]> registry;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ReadFileVector(String nameExcel) throws IOException {
        ca = new CodeAssign(nameExcel);
        listOfStandarNames = ca.getDiccionario_UbicacionLocalidad();
        mncp_localidad = ca.getCodigo_municipioLocalidad();
        localidad_x = ca.getLocalidad_X();
        localidad_y = ca.getLocalidad_Y();
        codigo_Dpto = ca.getCodigo_Dpto();
        codigo_Municipio = ca.getCodigo_Municipio();
        codigo_localidad = ca.getCodigo_localidad();
        registry = new ArrayList<String[]>();
    }

    public String lectureRegistry(String nameFile, String nameOut, int[] col, double percent, int rowBegin) {
        String answer = "";
        int quantityFound = 0;

        workbook = Lecture.lectureXLSX(nameFile);
        sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() < rowBegin) {
                continue;
            }

            String[] cellsWI = new String[col.length + 1];
            for (int i = 0; i < col.length; i++) {
                cellsWI[i] = "";
                try {
                    Cell cell = row.getCell(col[i]);
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                        cellsWI[i] = deleteTrash(cell.getStringCellValue());
                    } else {
                        cellsWI[i] = cell.getDateCellValue().toString();
                        cellsWI[col.length] = String.valueOf(cell.getDateCellValue().getMonth());
                    }
                    if (i == 2) {
                        cellsWI[i] = cell.getStringCellValue();
                    }
                } catch (Exception e) {
                }
            }
            registry.add(cellsWI);
        }

        System.out.println("Total: " + registry.size());
        /*for (int i = 0; i < registry.size(); i++) {
            System.out.println(i +" Municipio: " + registry.get(i)[0] + " | Localidad: " + registry.get(i)[1] + " | Especie: " + registry.get(i)[2] + " | Fecha: " + registry.get(i)[3]);
        }*/

        int rowCount = 0;
        int columnCount = 0;

        CellStyle cs = workbook.createCellStyle();
        //Font font = workbook.createFont();
        cs.setAlignment(HorizontalAlignment.CENTER);
        cs.setVerticalAlignment(VerticalAlignment.CENTER);
        cs.setBorderRight(BorderStyle.THIN);
        cs.setBorderLeft(BorderStyle.THIN);
        cs.setBorderBottom(BorderStyle.THIN);
        cs.setBorderTop(BorderStyle.THIN);

        sheet = workbook.createSheet();
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        sheet.setColumnWidth(0, 20);

        Row row = sheet.createRow(0);
        Cell encabezado = row.createCell(rowCount);
        encabezado.setCellValue("Base de  datos coordenadas");
        encabezado.setCellStyle(cs);

        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 8);
        sheet.addMergedRegion(region);

        row = sheet.createRow(++rowCount);
        Cell cell = row.createCell(columnCount);
        cell.setCellValue("Especie");
        cell.setCellStyle(cs);
        cell = row.createCell(++columnCount);
        cell.setCellValue("Municipio");
        cell.setCellStyle(cs);
        cell = row.createCell(++columnCount);
        cell.setCellValue("Codigo Municipio");
        cell.setCellStyle(cs);
        cell = row.createCell(++columnCount);
        cell.setCellValue("Vereda");
        cell.setCellStyle(cs);
        cell = row.createCell(++columnCount);
        cell.setCellValue("Codigo Vereda");
        cell.setCellStyle(cs);
        cell = row.createCell(++columnCount);
        cell.setCellValue("Mes");
        cell.setCellStyle(cs);
        cell = row.createCell(++columnCount);
        cell.setCellValue("Año");
        cell.setCellStyle(cs);
        cell = row.createCell(++columnCount);
        cell.setCellValue("Latitud");
        cell.setCellStyle(cs);
        cell = row.createCell(++columnCount);
        cell.setCellValue("Longitud");
        cell.setCellStyle(cs);
        cell = row.createCell(++columnCount);
        cell.setCellValue("Fuente");
        cell.setCellStyle(cs);

        for (int i = 0; i < registry.size(); i++) {
            try {
                String[] registro = registry.get(i);
                columnCount = -1;
                int cod_Mncp = 0;
                row = sheet.createRow(++rowCount);
                double levenstein = 0;
                double localidad_oficial = 0;
                double levensteinActual = 0;

                for (Integer codMunicipio : codigo_Municipio.keySet()) {
                    if(registro[0].equals(codigo_Municipio.get(codMunicipio))){
                        cod_Mncp = codMunicipio;
                        break;
                    }
                    
                    try {
                        double levenstein_local = FuzzySearch.ratio(registro[0], codigo_Municipio.get(codMunicipio));
                        if (levenstein_local >= levensteinActual) {
                            cod_Mncp = codMunicipio;
                            levensteinActual = levenstein_local;
                        }

                        if (levensteinActual == 100) {
                            break;
                        }
                    } catch (Exception e) {
                    }
                }

                for (Double cod_Loc : mncp_localidad.get(cod_Mncp).keySet()) {
                    String loc = mncp_localidad.get(cod_Mncp).get(cod_Loc);
                    
                    if(registro[1].equals(loc)){
                        localidad_oficial = cod_Loc;
                        levenstein = 101;
                    }
                    
                    try {
                        double levenstein_local = FuzzySearch.ratio(registro[1], loc);
                        if (levenstein_local >= levenstein) {
                            localidad_oficial = cod_Loc;
                            levenstein = levenstein_local;
                        }
                        
                        if(levenstein == 100){
                            break;
                        }
                        
                    } catch (Exception e) {
                    }
                }

                String mncp_oficial = codigo_Municipio.get(cod_Mncp);
                String loc_oficial = codigo_localidad.get(localidad_oficial);
                String especie = registro[2];
                double locX = localidad_x.get(localidad_oficial);
                double locY = localidad_y.get(localidad_oficial);
                int year = 0;
                int month = 0;
                System.out.println();
                try{
                    year = Integer.parseInt(registro[3].split(" ")[5]);
                    month = Integer.parseInt(registro[registro.length-1]) + 1;
                }catch(Exception e){
                    year = Integer.parseInt(registro[3].substring(registro[3].length()-4));
                    month = Integer.parseInt(registro[3].substring(registro[3].length()-7, registro[3].length()-5));
                }
                
                quantityFound++;

                cell = row.createCell(++columnCount);
                cell.setCellValue(especie);
                cell.setCellStyle(cs);
                cell = row.createCell(++columnCount);
                cell.setCellValue(mncp_oficial);
                cell.setCellStyle(cs);
                cell = row.createCell(++columnCount);
                cell.setCellValue(cod_Mncp);
                cell.setCellStyle(cs);
                cell = row.createCell(++columnCount);
                cell.setCellValue(loc_oficial);
                cell.setCellStyle(cs);
                cell = row.createCell(++columnCount);
                cell.setCellValue(localidad_oficial);
                cell.setCellStyle(cs);
                cell = row.createCell(++columnCount);
                cell.setCellValue(month);
                cell.setCellStyle(cs);
                cell = row.createCell(++columnCount);
                cell.setCellValue(year);
                cell.setCellStyle(cs);
                cell = row.createCell(++columnCount);
                cell.setCellValue(locY);
                cell.setCellStyle(cs);
                cell = row.createCell(++columnCount);
                cell.setCellValue(locX);
                cell.setCellStyle(cs);
            } catch (Exception e) {
                continue;
            }
        }
        
        sheet.setColumnWidth(0, 5800);
        sheet.setColumnWidth(1, 5800);
        sheet.setColumnWidth(2, 3000);
        sheet.setColumnWidth(3, 5800);
        sheet.setColumnWidth(4, 3000);
        sheet.setColumnWidth(5, 3000);
        sheet.setColumnWidth(6, 3000);
        sheet.setColumnWidth(7, 6400);
        sheet.setColumnWidth(8, 6400);
        
        answer = "Se generaron " + quantityFound + " vector(es)";
        try (FileOutputStream outputStream = new FileOutputStream(nameOut)) {
            workbook.write(outputStream);
        } catch (IOException ex) {
            quantityFound = 0;
            answer = "Cerrar el archivo de entrada ";
        }
        return answer;
    }

    private static String deleteTrash(String message) {
        String info = message;
        info = info.toUpperCase();

        info = info.replace("VEREDA", "");
        info = info.replace("V ", "");
        info = info.replace("VDA ", "");

        info = info.replace("CORREGIMIENTO", "");
        info = info.replace("CORR", "");
        info = info.replace("COR", "");
        info = info.replace("CRTO", "");
        info = info.replace("CRRGTO", "");
        info = info.replace("CTO", "");

        info = info.replace("CASERIO", "");
        info = info.replace("CAS", "");
        info = info.replace("CRIO", "");

        info = info.replace("HACIENDA", "");
        info = info.replace("HCDA", "");
        info = info.replace("HDA", "");
        info = info.replace("H ", "");

        info = info.replace("FINCA", "");
        info = info.replace("FCA", "");
        info = info.replace("F ", "");

        info = info.replace("Ñ", "N");
        info = info.replace("Á", "A");
        info = info.replace("É", "E");
        info = info.replace("Í", "I");
        info = info.replace("Ó", "O");
        info = info.replace("Ú", "U");

        return info;
    }

}
