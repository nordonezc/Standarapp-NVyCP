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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
public class ReadRegistry {

    private CodeAssign ca;
    private Hashtable<String, Hashtable<String, Hashtable<String, Integer>>> listOfStandarNames;
    private ArrayList<String[]> registry;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private HSSFWorkbook hworkbook;
    private HSSFSheet hsheet;

    public ReadRegistry(String nameExcel) throws IOException {
        ca = new CodeAssign(nameExcel);
        listOfStandarNames = ca.getListOfStandarNames();
        registry = new ArrayList<String[]>();
    }

    public String lectureRegistry(String nameFile, String nameOut, int[] col, int[] percent){
        String answer = "";
        int quantityFound = 0;
        float percentFound = 0;

        boolean xlsx = Lecture.determineExtensionFile(nameFile);
        if (xlsx) {
            workbook = Lecture.lectureXLSX(nameFile);
            sheet = workbook.getSheetAt(0);
            
        for (Row row : sheet) {
            if(row.getRowNum()<1)
                continue;
            
            String[] cellsWI = new String[col.length];
            for (int i = 0; i < col.length; i++) {
                cellsWI[i] = "";
                try{
                    Cell cell = row.getCell(col[i]);
                if(cell.getCellType() == Cell.CELL_TYPE_STRING)
                        cellsWI[i] = deleteTrash(cell.getStringCellValue());
                } catch(Exception e){
                    continue;
                }
                
            }
            registry.add(cellsWI);
        }
        } else {
            hworkbook = Lecture.lectureXLS(nameFile);
            hsheet = hworkbook.getSheetAt(0);
            
        for (Row row : hsheet) {
            if(row.getRowNum()<1)
                continue;
            
            String[] cellsWI = new String[col.length];
            for (int i = 0; i < col.length; i++) {
                cellsWI[i] = "";
                try{
                    Cell cell = row.getCell(col[i]);
                if(cell.getCellType() == Cell.CELL_TYPE_STRING)
                        cellsWI[i] = deleteTrash(cell.getStringCellValue());
                } catch(Exception e){
                    continue;
                }
                
            }
            registry.add(cellsWI);
        }
        }

        
        int rowCount = 0;
        int columnCount = 0;
        Row row;
        
        if (xlsx) {
            sheet = workbook.createSheet();
            row = sheet.createRow(rowCount);
        }
        else {
            hsheet = hworkbook.createSheet();
            row = hsheet.createRow(rowCount);
        }
        
        
        
        Cell cell = row.createCell(columnCount);
        cell.setCellValue("Departamento Procedencia");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Municipio Procedencia");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Departamento Residencia");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Municipio Residencia");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Departamento Notificacion");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Municipio Notificacion");
        cell = row.createCell(++columnCount);
        cell.setCellValue("vereda");
        cell = row.createCell(++columnCount);
        cell.setCellValue("bar_ver");
        cell = row.createCell(++columnCount);
        cell.setCellValue("dir_res");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Departamento Salida");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Municipio Salida");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Localidad Salida");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Codigo Salida");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Mayor Levenstein Localidad");

        
        for (int i = 0; i < registry.size(); i++) {
            String[] registro = registry.get(i);
            columnCount = -1;

            row = sheet.createRow(++rowCount);
            for (String reg : registro) {
                cell = row.createCell(++columnCount);
                cell.setCellValue(reg);
            }

            int dptoInicial = -2, mncpInicial = -1;
            boolean localFound = false;

            String mncpWithBestLevenstein = "INDETERMINADO";
            int mncpMajorLev = 0;

            String dptoWithBestLevenstein = "INDETERMINADO";
            int dptoMajorLevenstein = 0;

            String localWithTheBestLev = "INDETERMINADO";
            int localMajorLevenstein = 0;

            while (!localFound && dptoInicial <= 2) {
                dptoInicial += 2;
                mncpInicial += 2;
                mncpMajorLev = 0;
                dptoMajorLevenstein = 0;
                localMajorLevenstein = 0;
                //1. Procedencia 2. Residencia 3. Notificación
                //Busqueda del Departamento 
                for (String dpto : listOfStandarNames.keySet()) {
                    int levDpto = FuzzySearch.ratio(registro[dptoInicial], dpto);
                    if (levDpto > dptoMajorLevenstein) {
                        dptoWithBestLevenstein = dpto;
                        dptoMajorLevenstein = levDpto;
                        if (mncpMajorLev >= 100) 
                            break;
                    }
                }
                
                if(dptoMajorLevenstein<percent[0]) continue;

                
                //Busqueda del Municipio
                for (String value : listOfStandarNames.get(dptoWithBestLevenstein).keySet()) {
                    int levMncp = FuzzySearch.ratio(registro[mncpInicial], value);
                    if (levMncp > mncpMajorLev) {
                        mncpWithBestLevenstein = value;
                        mncpMajorLev = levMncp;
                        if (mncpMajorLev >= 100) 
                            break;
                    }
                }

                if(mncpMajorLev<percent[1]) continue;
                
                //Busqueda localidad
                for (String value : listOfStandarNames.get(dptoWithBestLevenstein).get(mncpWithBestLevenstein).keySet()) {
                    int levVyCP = 0;
                    try{
                        if(findWords(registro[6]))
                            registro[6] = registro[mncpInicial];
                        
                        levVyCP = FuzzySearch.tokenSetRatio(registro[6], value);
                        if (levVyCP > localMajorLevenstein) {
                            localWithTheBestLev = value;
                            localMajorLevenstein = levVyCP;
                    }

                    if (localMajorLevenstein >= 100)
                        break;
                    } catch(Exception e){}
                    
                    try{
                    if(findWords(registro[7]))
                        registro[7] = registro[mncpInicial];
                    
                    levVyCP = FuzzySearch.tokenSetRatio(registro[7], value);
                    if (levVyCP > localMajorLevenstein) {
                        localWithTheBestLev = value;
                        localMajorLevenstein = levVyCP;
                    }

                    if (localMajorLevenstein >= 100)
                        break;
                    
                    } catch(Exception e){}
                    
                    try{
                    if(registro[8].equals("")){
                    if(findWords(registro[8]))
                        registro[8] = registro[mncpInicial];
                    
                    levVyCP = FuzzySearch.tokenSetRatio(registro[8], value);
                    if (levVyCP > localMajorLevenstein) {
                        localWithTheBestLev = value;
                        localMajorLevenstein = levVyCP;
                    }

                    if (localMajorLevenstein >= 100)
                        break;
                    }
                    } catch(Exception e){}
                    
                    
                }

                if (localMajorLevenstein > percent[2])
                    localFound = true;
            }

            if (localFound) {
                cell = row.createCell(++columnCount);
                cell.setCellValue(dptoWithBestLevenstein);
                cell = row.createCell(++columnCount);
                cell.setCellValue(mncpWithBestLevenstein);
                cell = row.createCell(++columnCount);
                cell.setCellValue(localWithTheBestLev);
                cell = row.createCell(++columnCount);
                int codigo = listOfStandarNames.get(dptoWithBestLevenstein).get(mncpWithBestLevenstein).get(localWithTheBestLev);
                cell.setCellValue(codigo);
                cell = row.createCell(++columnCount);
                cell.setCellValue(localMajorLevenstein);
                quantityFound++;
            } else {
                cell = row.createCell(++columnCount);
                cell.setCellValue(dptoWithBestLevenstein);
                cell = row.createCell(++columnCount);
                cell.setCellValue(mncpWithBestLevenstein);
                cell = row.createCell(++columnCount);
                cell.setCellValue("INDETERMINADO");
                cell = row.createCell(++columnCount);
                cell.setCellValue("INDETERMINADO");
                cell = row.createCell(++columnCount);
                cell.setCellValue(localMajorLevenstein);
            }
        }

        percentFound = ((quantityFound)*100)/(registry.size());
        answer = "Se rescato un " + percentFound + "% de la información.";
        try (FileOutputStream outputStream = new FileOutputStream(nameOut)) {
            if(xlsx)
                workbook.write(outputStream);
            else
                hworkbook.write(outputStream);
        } catch (IOException ex) {
            Logger.getLogger(ReadRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }

        return answer;
    }

    
    private static String deleteTrash(String message){
        String info = message;
        info = info.replace("VEREDA", "");
        info = info.replace("V ", "");
        info = info.replace("VDA ", "");
        info = info.replace("CORREGIMIENTO", "");
        info = info.replace("FINCA", "");
        
        return info;
    }
    /**
     * Recieved word that would test try it to replace some words
     *
     * @param message
     * @return if anything change in message
     */
    private static boolean findWords(String message) {
        boolean answer = false;
        String info = message;
        
        info = info.replace("AVENIDA", "");
        info = info.replace("AV", "");
        info = info.replace("CARRERA", "");
        info = info.replace("KRA", "");
        info = info.replace("KR", "");
        info = info.replace("CALLE", "");
        info = info.replace("CLL", "");
        info = info.replace("CL", "");
        
        info = info.replace("KM", "");
        info = info.replace("KDX", "");
        info = info.replace("LOTE", "");

        info = info.replace("BARRIO", ""); // No se elimina
        info = info.replace("#", ""); //Direccion y tambien N°
        info = info.replace("-", "");
        info = info.replace("°", "");

        if (message.equals(info)) 
            answer = true;
        
        return answer;
    }
}
