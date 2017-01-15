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

    private static CodeAssign ca;
    private static Hashtable<String, Hashtable<String, Hashtable<String, Integer>>> listOfStandarNames;
    private static ArrayList<String[]> registry; //21, 22, 97, 99 each 4 its a different registry
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static HSSFWorkbook hworkbook;
    private static HSSFSheet hsheet;

    public ReadRegistry() throws IOException {
        ca = new CodeAssign();
        listOfStandarNames = ca.getListOfStandarNames();
        registry = new ArrayList<>();
    }

    //95 96 procedencia | 97 98 residencia | 99 100 notificacion | 21 22 23 entrada
    // Percent: 80 dpto | 80 mncp | 50 localidad
    public static String lectureRegistry(String nameFile, int[] col, int[] percent) throws IOException {
        String answer = "";
        nameFile = "C:\\Users\\Niki\\Downloads\\LEISHMANIASI.xlsx";
        int quantityFound = 0;
        double percentFound = 0;

        boolean xlsx = Lecture.determineExtensionFile(nameFile);
        if (xlsx) {
            workbook = Lecture.lectureXLSX(nameFile);
            sheet = workbook.getSheetAt(0);
        } else {
            hworkbook = Lecture.lectureXLS(nameFile);
            hsheet = hworkbook.getSheetAt(0);
        }

        for (Row row : sheet) {
            String[] cellsWI = new String[col.length];
            for (int i = 0; i < cellsWI.length; i++) {
                cellsWI[i] = Lecture.fixWords(row.getCell(col[i]).getStringCellValue().toUpperCase());
            }
            registry.add(cellsWI);
        }

        if (xlsx) {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("StandarCodes");
        } else {
            hworkbook = new HSSFWorkbook();
            hsheet = hworkbook.createSheet("StandarCodes");
        }

        int rowCount = 0;
        int columnCount = 0;

        Row row = sheet.createRow(rowCount);
        Cell cell = row.createCell(columnCount);
        cell.setCellValue("Departamento Procedencia");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Municipio Procedencia");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Municipio Residencia");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Municipio Residencia");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Municipio Notificacion");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Municipio Notificacion");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Entrada 1");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Entrada 2");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Entrada 3");
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

        for (int i = 1; i < registry.size(); i++) {
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
            int localMajorLevenstein = 50;

            while (localFound && dptoInicial <= 2) {
                dptoInicial += 2;
                mncpInicial += 2;
                mncpMajorLev = 0;
                dptoMajorLevenstein = 0;
                localMajorLevenstein = 0;
                //1. Procedencia 2. Residencia 3. Notificación
                //Busqueda del Departamento 
                for (String mncp : listOfStandarNames.keySet()) {
                    int levMncp = FuzzySearch.ratio(registro[dptoInicial], mncp);
                    if (levMncp > mncpMajorLev) {
                        mncpWithBestLevenstein = mncp;
                        mncpMajorLev = levMncp;
                        if (mncpMajorLev >= 100) 
                            break;
                    }
                }
                
                if(mncpMajorLev<percent[0]) continue;

                //Busqueda del Municipio
                for (String value : listOfStandarNames.get(mncpWithBestLevenstein).keySet()) {
                    int levDpto = FuzzySearch.ratio(registro[mncpInicial], value);
                    if (levDpto > dptoMajorLevenstein) {
                        dptoWithBestLevenstein = value;
                        dptoMajorLevenstein = levDpto;
                        if (dptoMajorLevenstein >= 100) 
                            break;
                    }
                }

                if(dptoMajorLevenstein<percent[1]) continue;
                
                //Busqueda localidad
                for (String value : listOfStandarNames.get(mncpWithBestLevenstein).get(dptoWithBestLevenstein).keySet()) {
                    if(findWords(registro[6]))
                        registro[6] = registro[mncpInicial];
                    
                    int levVyCP = FuzzySearch.tokenSetRatio(registro[6], value);
                    if (levVyCP > localMajorLevenstein) {
                        localWithTheBestLev = value;
                        localMajorLevenstein = levVyCP;
                    }

                    if (localMajorLevenstein >= 100)
                        break;
                    if(findWords(registro[7]))
                        registro[7] = registro[mncpInicial];
                    
                    levVyCP = FuzzySearch.tokenSetRatio(registro[7], value);
                    if (levVyCP > localMajorLevenstein) {
                        localWithTheBestLev = value;
                        localMajorLevenstein = levVyCP;
                    }

                    if (localMajorLevenstein >= 100)
                        break;
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
                cell.setCellValue(listOfStandarNames.get(dptoMajorLevenstein).get(mncpMajorLev).get(localMajorLevenstein));
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

        percentFound = quantityFound/registry.size();
        answer = "Se rescato un " + percentFound + "% de la información.";
        try (FileOutputStream outputStream = new FileOutputStream("standarizedRegistries.xlsx")) {
            workbook.write(outputStream);
        } catch (IOException ex) {
            Logger.getLogger(ReadRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }

        return answer;
    }

    
    private static String deleteTrash(String message){
        String info = message;
        info = info.replace("VEREDA", "");
        info = info.replace("V ", "");
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
