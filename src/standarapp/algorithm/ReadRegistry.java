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
    private HSSFWorkbook hworkbook;
    private HSSFSheet hsheet;

    public ReadRegistry(String nameExcel) throws IOException {
        ca = new CodeAssign(nameExcel);
        listOfStandarNames = ca.getDiccionario_UbicacionLocalidad();
        mncp_localidad = ca.getCodigo_municipioLocalidad();
        localidad_x = ca.getLocalidad_X();
        localidad_y = ca.getLocalidad_Y();
        codigo_Dpto = ca.getCodigo_Dpto();
        codigo_Municipio = ca.getCodigo_Municipio();
        codigo_localidad = ca.getCodigo_localidad();

        /*
        for(int cod: mncp_localidad.keySet()){
            for(double codlc: mncp_localidad.get(cod).keySet()){
                System.err.println(cod + " " + codlc + " " + mncp_localidad.get(cod).get(codlc) + "");
            }
        }
        
        for(String dp: listOfStandarNames.keySet()){
            for(String mn: listOfStandarNames.keySet()){
                for(String lc: listOfStandarNames.keySet()){
                    System.out.println(dp + ", " + mn + ", " + lc);
                }
            }
        }
         */
        registry = new ArrayList<String[]>();
    }

    public String lectureRegistry(String nameFile, String nameOut, int[] col, double percent) {
        String answer = "";
        int quantityFound = 0;
        float percentFound = 0;

        /*boolean xlsx = Lecture.determineExtensionFile(nameFile);
        if (xlsx) {*/
            workbook = Lecture.lectureXLSX(nameFile);
            sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() < 1) {
                    continue;
                }

                String[] cellsWI = new String[col.length];
                for (int i = 0; i < col.length; i++) {
                    cellsWI[i] = "";
                    try {
                        Cell cell = row.getCell(col[i]);
                        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            cellsWI[i] = deleteTrash(cell.getStringCellValue());
                        }
                        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            cellsWI[i] = String.valueOf(cell.getNumericCellValue());
                        }
                    } catch (Exception e) {
                        continue;
                    }

                }
                registry.add(cellsWI);
            }
        
        System.out.println("size: " + registry.size());
        int rowCount = 0;
        int columnCount = 0;
        Row row;

        sheet = workbook.createSheet();
        row = sheet.createRow(rowCount);

        Cell cell = row.createCell(columnCount);
        cell.setCellValue("Cod_Dpto");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Departamento");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Cod_Mncp");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Municipio");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Cod_Localidad");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Localidad");
        cell = row.createCell(++columnCount);
        cell.setCellValue("X");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Y");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Levenstein");

        for (int i = 0; i < registry.size(); i++) {
            try {
                String[] registro = registry.get(i);
                columnCount = -1;
                int cod_Mncp = Integer.parseInt(registro[0]) * 1000 + Integer.parseInt(registro[1]);
                row = sheet.createRow(++rowCount);
                double levenstein = percent;
                double localidad_oficial = 0;
                
                for (int j = 2; j < 6; j++) {
                    for (Double cod_Loc : mncp_localidad.get(cod_Mncp).keySet()) {
                        String loc = mncp_localidad.get(cod_Mncp).get(cod_Loc);
                        try {
                            int levenstein_local = FuzzySearch.partialRatio(registro[j], loc);
                            if (determinarBarrio(registro[j])) {
                                j = 6;
                                registro[6] = registro[6].concat("Barrio");
                                levenstein = percent;
                                break;
                            }

                            if (levenstein_local > levenstein) {
                                localidad_oficial = cod_Loc;
                                levenstein = levenstein_local;
                            }
                        } catch (Exception e) {
                            continue;
                        }

                    }
                }

                if (levenstein == percent) {
                    
                    if (registro[8].equals(registro[7])) {
                        String direccion = registro[6];

                        if (findWords(direccion)) {
                            direccion = codigo_Municipio.get(cod_Mncp);
                            //System.err.println(cod_Mncp + " " + i + " Centro poblado " + direccion);
                        }

                        direccion = deleteTrash(direccion);

                        for (Double cod_Loc : mncp_localidad.get(cod_Mncp).keySet()) {
                            String loc = mncp_localidad.get(cod_Mncp).get(cod_Loc);

                            try {

                                int levenstein_local = FuzzySearch.partialRatio(direccion, loc);
                                if (levenstein_local > levenstein) {
                                    localidad_oficial = cod_Loc;
                                    levenstein = levenstein_local;
                                }
                            } catch (Exception e) {
                                break;
                            }

                        }
                    }
                }

                String dpto_oficial = codigo_Dpto.get(Integer.parseInt(registro[0]));
                String mncp_oficial = codigo_Municipio.get(cod_Mncp);
                String loc_oficial = "";
                double locX = 0;
                double locY = 0;

                if (levenstein == percent) {
                    loc_oficial = "Indeterminable";
                } else {
                    loc_oficial = codigo_localidad.get(localidad_oficial);
                    locX = localidad_x.get(localidad_oficial);
                    locY = localidad_y.get(localidad_oficial);
                    quantityFound++;
                }

                //System.out.println(dpto_oficial + ", " + mncp_oficial + ", " + loc_oficial);
                cell = row.createCell(++columnCount);
                cell.setCellValue(Integer.parseInt(registro[0]));
                cell = row.createCell(++columnCount);
                cell.setCellValue(dpto_oficial);
                cell = row.createCell(++columnCount);
                cell.setCellValue(cod_Mncp);
                cell = row.createCell(++columnCount);
                cell.setCellValue(mncp_oficial);
                cell = row.createCell(++columnCount);
                cell.setCellValue(localidad_oficial);
                cell = row.createCell(++columnCount);
                cell.setCellValue(loc_oficial);
                cell = row.createCell(++columnCount);
                cell.setCellValue(locX);
                cell = row.createCell(++columnCount);
                cell.setCellValue(locY);
                cell = row.createCell(++columnCount);
                cell.setCellValue(levenstein);
            } catch (Exception e) {
                continue;
            }
        }

        percentFound = ((quantityFound) * 100) / (registry.size());
        answer = "Se rescato un " + percentFound + "% de la información.";
        try (FileOutputStream outputStream = new FileOutputStream(nameOut)) {
            workbook.write(outputStream);
        } catch (IOException ex) {
            Logger.getLogger(ReadRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }

        return answer;
    }

    private static String deleteTrash(String message) {
        String info = message;
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

        return info;
    }

    /**
     * Recieved word that would test try it to replace some words
     *
     * @param message
     * @return if anything change in message
     */
    private static boolean determinarBarrio(String message) {
        boolean answer = false;
        String info = message;

        info = info.replace("BARRIO", "");
        info = info.replace("BAR", "");
        info = info.replace("BRIO", "");

        if (!info.equals(message)) {
            answer = true;
        }

        return answer;
    }

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
        
        info = info.replace("BARRIO", "");
        info = info.replace("BAR", "");
        info = info.replace("BRIO", "");
        
        info = info.replace("KM", "");
        info = info.replace("KDX", "");
        info = info.replace("LOTE", "");

        info = info.replace("#", ""); //Direccion y tambien N°
        info = info.replace("-", "");
        info = info.replace("°", "");

        if (!message.equals(info)) {
            answer = true;
        }

        return answer;
    }
}

//Si encuentra barrio, revisar direcccion
//SI no encuentra formato de direccion buscar con las localidades actuales tal cual como esta
//Prioridad corregimiento sobre direccion


/*
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
                double codigo = listOfStandarNames.get(dptoWithBestLevenstein).get(mncpWithBestLevenstein).get(localWithTheBestLev);
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
 */
