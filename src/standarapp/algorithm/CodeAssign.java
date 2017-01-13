/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standarapp.algorithm;

import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Niki
 */
public class CodeAssign {

    private static Hashtable<String, Hashtable<String, Hashtable<String, Integer>>> listOfStandarNames;
    private static Hashtable<Integer, String> allCodes;

    public CodeAssign() throws IOException {
        //Logica de la aplicacion
        listOfStandarNames = new Hashtable<String, Hashtable<String, Hashtable<String, Integer>>>();
        allCodes = new Hashtable<>();
        String nameExcel = "C:\\Users\\Niki\\Documents\\NetBeansProjects\\Standarapp NVyCP\\src\\database\\LocalidadesConCodigo.xlsx";
        XSSFWorkbook xwb = lectureXLSX(nameExcel);
        XSSFSheet xsheet = xwb.getSheetAt(0);
        //int repeated = 0;
        //int codigoCP = 0;
        double codigoTemporal = 0;
        for (Row row : xsheet) {
            if (row.getRowNum() > 0) {
                String departamento = "", municipio = "", localidad = "";
                int codigoVereda = 0;
                for (Cell cell : row) {
                    if (cell.getColumnIndex() == 1) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                codigoTemporal = Double.valueOf(cell.getStringCellValue());
                                codigoVereda = (int) codigoTemporal;
                                continue;
                            case Cell.CELL_TYPE_NUMERIC:
                                codigoVereda = (int) cell.getNumericCellValue();
                                continue;
                        }
                    }
                    if (cell.getColumnIndex() == 2) {
                        departamento = cell.getStringCellValue();
                    }
                    if (cell.getColumnIndex() == 3) {
                        municipio = cell.getStringCellValue();
                    }
                    if (cell.getColumnIndex() == 4) {
                        localidad = cell.getStringCellValue();
                    }
                    /*
                This was made for assignCode from dbf edited file
                if (cell.getColumnIndex() == 1) {
                    System.out.println(cell.getColumnIndex() + "" + cell.getStringCellValue());
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            String contenidoCodigo = cell.getStringCellValue();
                            if(!contenidoCodigo.equals("0")){
                                double temporal = Double.valueOf(contenidoCodigo);
                                codigoVereda = (int) temporal;
                            }
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            codigoVereda = (int) cell.getNumericCellValue();
                            break;
                    }
                }
                     */
                }

                if (!listOfStandarNames.containsKey(departamento)) {
                    Hashtable<String, Hashtable<String, Integer>> primerMunicipio = new Hashtable<String, Hashtable<String, Integer>>();
                    Hashtable<String, Integer> primerLocalidad = new Hashtable<>();
                    /*
                This was made for assignCode from dbf edited file
                if (codigoVereda != 0) {
                    codigoTemporal = codigoVereda;
                } else {
                    codigoCP++;
                    codigoTemporal = codigoCP;
                }
                primerLocalidad.put(localidad, codigoTemporal);
                     */
                    primerLocalidad.put(localidad, codigoVereda);
                    primerMunicipio.put(municipio, primerLocalidad);
                    listOfStandarNames.put(departamento, primerMunicipio);
                    
                    try{
                        allCodes.put(codigoVereda, localidad);
                    }catch(NullPointerException e){
                        System.err.println(codigoVereda + ": " + localidad);
                    }
                } else if (!listOfStandarNames.get(departamento).containsKey(municipio)) {
                    Hashtable<String, Integer> primerGeo = new Hashtable<String, Integer>();
                    /*
                This was made for assignCode from dbf edited file
                if (codigoVereda != 0) {
                    codigoTemporal = codigoVereda;
                } else {
                    codigoCP++;
                    codigoTemporal = codigoCP;
                }
                     */
                    primerGeo.put(localidad, codigoVereda);
                    listOfStandarNames.get(departamento).put(municipio, primerGeo);
                    allCodes.put(codigoVereda, localidad);
                } else if (!listOfStandarNames.get(departamento).get(municipio).containsKey(localidad)) {
                    /*
                This was made for assignCode from dbf edited file
                if (codigoVereda != 0) {
                    codigoTemporal = codigoVereda;
                } else {
                    /*codigoCP++;
                    codigoTemporal = codigoCP;
                }
                     */
                    listOfStandarNames.get(departamento).get(municipio).put(localidad, codigoVereda);
                    allCodes.put(codigoVereda, localidad);
                } else {
                    /*
                This was made for assignCode from dbf edited file
                if (codigoVereda != 0) {
                    codigoTemporal = codigoVereda;
                } else {
                    codigoTemporal = listOfStandarNames.get(departamento).get(municipio).get(localidad);
                }
                System.err.println(localidad);
                repeated++;
                     */
                }
            }
        }
    }

    public void generateExcel(String nameDirectory) throws IOException {
        /**
         * Write all localidades with codes, with his departamentos and
         * municipios Copiar en un excel todas las localidades con codigos en
         * departamentos y municipios
         */
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("StandarCodes");

        int rowCount = 0;
        int columnCount = 0;
        Row row = sheet.createRow(rowCount);
        Cell cell = row.createCell(columnCount);
        cell.setCellValue("Municipio");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Departamento");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Localidad");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Codigo");
        for (String municipio : listOfStandarNames.keySet()) {
            for (String departamento : listOfStandarNames.get(municipio).keySet()) {
                for (String localidad : listOfStandarNames.get(municipio).get(departamento).keySet()) {
                    row = sheet.createRow(++rowCount);
                    columnCount = 0;
                    cell = row.createCell(columnCount);
                    cell.setCellValue(municipio);
                    cell = row.createCell(++columnCount);
                    cell.setCellValue(departamento);
                    cell = row.createCell(++columnCount);
                    cell.setCellValue(localidad);
                    cell = row.createCell(++columnCount);
                    cell.setCellValue(listOfStandarNames.get(municipio).get(departamento).get(localidad));
                }

            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(nameDirectory)) {
            workbook.write(outputStream);
        }

    }

    public String findByDepartamento(String departamento) {
        String answer = "";
        String departamentoCorrecto = departamento;
        if (!listOfStandarNames.containsKey(departamentoCorrecto)) {
            int majorLev = 50;
            for (String dpto : listOfStandarNames.keySet()) {
                int partialLev = FuzzySearch.ratio(dpto, departamento);
                if (partialLev > majorLev) {
                    departamentoCorrecto = dpto;
                    majorLev = partialLev;
                }
                if (majorLev >= 80) {
                    break;
                }
            }
        }

        answer += "El departamento " + departamentoCorrecto + " tiene: \n";
        for (String mncp : listOfStandarNames.get(departamentoCorrecto).keySet()) {
            answer += mncp + "\n";
        }

        return answer;
    }

    public String findByMunicipio(String municipio) {
        String answer = "";
        String municipioCorrecto = municipio;
        answer += "El municicpio " + municipioCorrecto + " esta en: \n";
        int majorLev = 50;
        for (String dpto : listOfStandarNames.keySet()) {
            for (String mncp : listOfStandarNames.get(dpto).keySet()) {
                int partialLev = FuzzySearch.ratio(mncp, municipioCorrecto);
                if (partialLev > majorLev) {
                    //municipioCorrecto = mncp;
                    //majorLev = partialLev;
                    answer += dpto;
                    if(partialLev >= 100) answer += " *MAS POSIBLE* ";
                    answer += "\n";
                    break;
                }
                
                }
            }
        
        return answer;
    }

    public String findByLocalidad(String localidad) {
        String answer = "La localidad " + localidad + " esta en: \n";
        String localidadCorrecta = localidad;
        int majorLev = 50;
        int percent = 80;
        if(allCodes.contains(localidad))
            percent = 100;
        
        for (String dpto : listOfStandarNames.keySet()) {
            for (String mncp : listOfStandarNames.get(dpto).keySet()) {
                for (String local : listOfStandarNames.get(dpto).get(mncp).keySet()) {
                    int partialLev = 0;
                    if(percent == 100)
                        partialLev = FuzzySearch.ratio(local, localidadCorrecta);
                    else
                        partialLev = FuzzySearch.tokenSetRatio(local, localidadCorrecta);
                    if (partialLev >= majorLev && partialLev >= percent) {
                        localidadCorrecta = local;
                        majorLev = partialLev;
                    }
                    if (majorLev >= percent) {
                        majorLev = 0;
                        answer += "Dpto: " + dpto + ", municipio: " + mncp + " encontro " + local + ": " + listOfStandarNames.get(dpto).get(mncp).get(localidadCorrecta) +"\n";
                        //break;
                    }
                }
            }
        }

        return answer;
    }

    public String findByMunicipioAndDepartamento(String municipio, String departamento) {
        String answer = "";
        String municipioCorrecto = municipio;
        String departamentoCorrecto = departamento;
        if (!listOfStandarNames.containsKey(departamentoCorrecto)) {
            int majorLev = 50;
            for (String dpto : listOfStandarNames.keySet()) {
                int partialLev = FuzzySearch.ratio(dpto, departamento);
                if (partialLev > majorLev) {
                    departamentoCorrecto = dpto;
                    majorLev = partialLev;
                }
                if (majorLev >= 50) {
                    break;
                }
            }
        }

        if (!listOfStandarNames.get(departamentoCorrecto).containsKey(municipioCorrecto)) {
            int majorLev = 0;
            for (String mncp : listOfStandarNames.get(departamentoCorrecto).keySet()) {
                int partialLev = FuzzySearch.ratio(mncp, municipioCorrecto);
                if (partialLev > majorLev) {
                    municipioCorrecto = mncp;
                    majorLev = partialLev;
                }
                if (majorLev >= 50) {
                    break;
                }
            }
        }

        answer += "El departamento " + departamentoCorrecto + " con municipio " + municipioCorrecto + " tiene: \n";
        for (String localidades : listOfStandarNames.get(departamentoCorrecto).get(municipioCorrecto).keySet()) {
            answer += localidades + "\n";
        }

        return answer;
    }

    public String findByLocalidadAndMunicipio(String localidad, String municipio) {
        String answer = "";
        String municipioCorrecto = municipio;
        String localidadCorrecta = localidad;
        String departamentoCorrecto = "";
        int majorLev = 0;
        for (String dpto : listOfStandarNames.keySet()) {
            if (!listOfStandarNames.get(dpto).containsKey(municipioCorrecto)) {
                for (String mncp : listOfStandarNames.get(dpto).keySet()) {
                    int partialLev = FuzzySearch.ratio(mncp, municipioCorrecto);
                    if (partialLev > majorLev) {
                        municipioCorrecto = mncp;
                        majorLev = partialLev;
                        departamentoCorrecto = dpto;
                    }
                    if (majorLev >= 50) {
                        break;
                    }
                }
            } else {
                departamentoCorrecto = dpto;
            }

            if (!listOfStandarNames.get(dpto).get(municipioCorrecto).containsKey(localidadCorrecta)) {
                majorLev = 0;
                for (String local : listOfStandarNames.get(dpto).get(municipioCorrecto).keySet()) {
                    int partialLev = FuzzySearch.tokenSetRatio(local, localidadCorrecta);
                    if (partialLev > majorLev) {
                        localidadCorrecta = local;
                        majorLev = partialLev;
                    }
                    if (majorLev >= 80) {
                        break;
                    }
                }
            }
            if (majorLev >= 80) {
                break;
            }
        }

        answer += "Departamento " + departamentoCorrecto + " con municipio " + municipioCorrecto + " con " + localidadCorrecta + " tiene codigo: " + listOfStandarNames.get(departamentoCorrecto).get(municipioCorrecto).get(localidadCorrecta);
        return answer;
    }

    public String finbByLocalidadAndDepartamento(){
        String answer = "";
        return answer;
    }
    
    public String findByAll(String departamento, String municipio, String localidad) {
        String answer = "";
        String municipioCorrecto = municipio;
        String localidadCorrecta = localidad;
        String departamentoCorrecto = departamento;
        int majorLev = 0;

        if (!listOfStandarNames.containsKey(departamentoCorrecto)) {
            for (String dpto : listOfStandarNames.keySet()) {
                int partialLev = FuzzySearch.ratio(dpto, departamentoCorrecto);
                if (partialLev > majorLev) {
                    majorLev = partialLev;
                    departamentoCorrecto = dpto;
                }
                if (majorLev >= 50) {
                    break;
                }
            }
            majorLev = 0;
        }

        if (!listOfStandarNames.get(departamentoCorrecto).containsKey(municipioCorrecto)) {
            for (String mncp : listOfStandarNames.get(departamentoCorrecto).keySet()) {
                int partialLev = FuzzySearch.ratio(mncp, municipioCorrecto);
                if (partialLev > majorLev) {
                    municipioCorrecto = mncp;
                    majorLev = partialLev;
                }
                if (majorLev >= 50) {
                    break;
                }
            }
            majorLev = 0;
        }

        if (!listOfStandarNames.get(departamentoCorrecto).get(municipioCorrecto).containsKey(localidadCorrecta)) {
            for (String local : listOfStandarNames.get(departamentoCorrecto).get(municipioCorrecto).keySet()) {
                int partialLev = FuzzySearch.tokenSetRatio(local, localidadCorrecta);
                if (partialLev > majorLev) {
                    localidadCorrecta = local;
                    majorLev = partialLev;
                }
                if (majorLev >= 80) {
                    break;
                }
            }
        }

        answer += "Departamento " + departamentoCorrecto + " con municipio " + municipioCorrecto + " con " + localidadCorrecta + " tiene codigo: " + listOfStandarNames.get(departamentoCorrecto).get(municipioCorrecto).get(localidadCorrecta);
        return answer;
    }

    public String findByCode(int code) {
        String answer = "El codigo " + code + " corresponde a: \n";
        String localidadCorrecta = allCodes.get(code);
        String departamentoCorrecto = "", municipioCorrecto = "";
        for (String dpto : listOfStandarNames.keySet()) {
            for (String mncp : listOfStandarNames.get(dpto).keySet()) {
                if (listOfStandarNames.get(dpto).get(mncp).containsKey(localidadCorrecta)) {
                    departamentoCorrecto = dpto;
                    municipioCorrecto = mncp;
                    break;
                }
            }
            if (!departamentoCorrecto.equals("")) {
                break;
            }
        }

        answer += "Departamento " + departamentoCorrecto + " con municipio " + municipioCorrecto + " con localidad " + localidadCorrecta;
        return answer;
    }

    public Hashtable<String, Hashtable<String, Hashtable<String, Integer>>> getListOfStandarNames() {
        return listOfStandarNames;
    }

    public Hashtable<Integer, String> getAllCodes() {
        return allCodes;
    }

    private static XSSFWorkbook lectureXLSX(String nameFile) {
        FileInputStream file;
        XSSFWorkbook excelFile = new XSSFWorkbook();
        //Reading the file which contains registries
        //Lectura del archivo xls de registros
        try {
            file = new FileInputStream(new File(nameFile));
            excelFile = new XSSFWorkbook(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        return excelFile;
    }
    
    /*
    private static String fixWords(String message) {
        String info = message;
        //info = info.replace(" ", "");
        info = info.replace("Á", "A");
        info = info.replace("É", "E");
        info = info.replace("Í", "I");
        info = info.replace("Ó", "O");
        info = info.replace("Ú", "U");
        info = info.replace("Ñ", "N");

        //Fixing errors generated by dbf to xls
        info = info.replace("═", "I");
        info = info.replace("ß", "a");
        info = info.replace("Ú", "e");

        //Agregados de ultimas
        info = info.replace("·", "u");
        info = info.replace("┌", "U");
        info = info.replace("┴", "A");
        info = info.replace("¾", "o");
        info = info.replace("Ý", "i");
        info = info.replace("Ë", "O");
        info = info.replace("╔", "O");
        info = info.replace("├âÔÇÿ", "N");
        info = info.replace("├â┬ü", "A");
        info = info.replace("├âÔÇ░", "E");
        info = info.replace("├â┬ì", "I");
        info = info.replace("├âÔÇ£", "O");
        info = info.replace("├â┼í", "U");
        return info;
    }
    */
}
