/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standarapp.algorithm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
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

    private static Hashtable<String, Hashtable<String, Hashtable<String, Integer>>> diccionario_UbicacionLocalidad;
    private static Hashtable<Integer, String> codigo_Dpto;
    private static Hashtable<Integer, String> codigo_Municipio;
    private static Hashtable<Integer, String> codigo_localidad;
    private static Hashtable<Integer, Hashtable<Integer, String>> codigo_municipioLocalidad;

    public static void main(String args[]) {
        
    diccionario_UbicacionLocalidad = new Hashtable<>();
    codigo_Dpto = new Hashtable<>();
    codigo_Municipio = new Hashtable<>();
    codigo_localidad = new Hashtable<>();
    codigo_municipioLocalidad = new Hashtable<>();
    
        XSSFSheet xsheet = Lecture.lectureXLSX("C:\\Users\\Niki\\Documents\\codigosDaneArreglados.xlsx", 0);
        for (Row row : xsheet) {
            if(row.getRowNum() >4){
            String departamento = "";
            int codigoMunicipio = 0;
            int codigoDpto = 0;
            String codigoMun = "";
            String municipio = "";
            System.out.print(row.getRowNum() + "| ");
            for (Cell cell : row) {
                if (cell.getColumnIndex() == 0) 
                    departamento = cell.getStringCellValue();
                
                if (cell.getColumnIndex() == 2) 
                    municipio = cell.getStringCellValue();
                
                if (cell.getColumnIndex() == 1) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            System.out.print("String: ");
                            codigoMun = cell.getStringCellValue();
                            codigoMunicipio = Integer.parseInt(codigoMun);
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print("numerico: ");
                            codigoMunicipio = (int) cell.getNumericCellValue();
                            codigoMun = String.valueOf(codigoMunicipio);
                            break;
                    }
                    
                    System.out.print(codigoMunicipio);
                    String temporal = codigoMun.charAt(0) + "" + codigoMun.charAt(1);
                    codigoDpto = Integer.parseInt(temporal);
                    System.out.print(" dpto: " + temporal + "\t\t");
                    
                    System.out.println("Municipio: " + municipio);
                }
                
                try{
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            String contenido = cell.getStringCellValue();
                            System.out.print(cell.getColumnIndex() + ": " + contenido + "\t\t");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            double contenido_Numerico = cell.getNumericCellValue();
                            System.out.print(cell.getColumnIndex() + ": " + contenido_Numerico + "\t\t");
                    }
                } catch(Exception e){}
            }
            System.out.println();
            
            if (!diccionario_UbicacionLocalidad.containsKey(departamento)) {
                    Hashtable<String, Hashtable<String, Integer>> primerMunicipio = new Hashtable<>();
                    Hashtable<String, Integer> primerLocalidad = new Hashtable<>();
                    Hashtable<Integer, String> primerLocalidadporNumero = new Hashtable<>();
                    
                    codigo_Dpto.put(codigoDpto, departamento);
                    
                    primerMunicipio.put(municipio, primerLocalidad);
                    diccionario_UbicacionLocalidad.put(departamento, primerMunicipio);
                    codigo_Municipio.put(codigoMunicipio, municipio);
                    codigo_municipioLocalidad.put(codigoMunicipio, primerLocalidadporNumero);
                    
            } else if (!diccionario_UbicacionLocalidad.get(departamento).containsKey(municipio)) {
                    Hashtable<String, Integer> primerLocalidad = new Hashtable<String, Integer>();
                    Hashtable<Integer, String> primerLocalidadporNumero = new Hashtable<>();
                    
                    diccionario_UbicacionLocalidad.get(departamento).put(municipio, primerLocalidad);
                    codigo_Municipio.put(codigoMunicipio, municipio);
                    codigo_municipioLocalidad.put(codigoMunicipio, primerLocalidadporNumero);
            } else {
            }
        }
        }
        
        /*for(int codigo: codigo_Dpto.keySet())
            System.out.println(codigo + ": " + codigo_Dpto.get(codigo));
        
        System.out.println("Tamaño departamentos: " + codigo_Dpto.size());
        */
        
        for(int codigo: codigo_Municipio.keySet())
            System.out.println(codigo + ": " + codigo_Municipio.get(codigo));
        
        System.out.println("Tamaño municipios: " + codigo_Municipio.size());
    }
    
    
    public CodeAssign(String nameExcel) throws IOException {
        //Logica de la aplicacion
        diccionario_UbicacionLocalidad = new Hashtable<>();
        codigo_localidad = new Hashtable<>();
        XSSFWorkbook xwb = Lecture.lectureXLSX(nameExcel);
        XSSFSheet xsheet = xwb.getSheetAt(0);
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
                    if (cell.getColumnIndex() == 2) 
                        departamento = cell.getStringCellValue();
                    if (cell.getColumnIndex() == 3) 
                        municipio = cell.getStringCellValue();
                    if (cell.getColumnIndex() == 4) 
                        localidad = cell.getStringCellValue();
                }

                if (!diccionario_UbicacionLocalidad.containsKey(departamento)) {
                    Hashtable<String, Hashtable<String, Integer>> primerMunicipio = new Hashtable<String, Hashtable<String, Integer>>();
                    Hashtable<String, Integer> primerLocalidad = new Hashtable<>();
                    primerLocalidad.put(localidad, codigoVereda);
                    primerMunicipio.put(municipio, primerLocalidad);
                    diccionario_UbicacionLocalidad.put(departamento, primerMunicipio);
                    
                    try{
                        codigo_localidad.put(codigoVereda, localidad);
                    }catch(NullPointerException e){
                        System.err.println(codigoVereda + ": " + localidad);
                    }
                } else if (!diccionario_UbicacionLocalidad.get(departamento).containsKey(municipio)) {
                    Hashtable<String, Integer> primerGeo = new Hashtable<String, Integer>();
                    primerGeo.put(localidad, codigoVereda);
                    diccionario_UbicacionLocalidad.get(departamento).put(municipio, primerGeo);
                    codigo_localidad.put(codigoVereda, localidad);
                } else if (!diccionario_UbicacionLocalidad.get(departamento).get(municipio).containsKey(localidad)) {
                    diccionario_UbicacionLocalidad.get(departamento).get(municipio).put(localidad, codigoVereda);
                    codigo_localidad.put(codigoVereda, localidad);
                } else {
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
        for (String municipio : diccionario_UbicacionLocalidad.keySet()) {
            for (String departamento : diccionario_UbicacionLocalidad.get(municipio).keySet()) {
                for (String localidad : diccionario_UbicacionLocalidad.get(municipio).get(departamento).keySet()) {
                    row = sheet.createRow(++rowCount);
                    columnCount = 0;
                    cell = row.createCell(columnCount);
                    cell.setCellValue(municipio);
                    cell = row.createCell(++columnCount);
                    cell.setCellValue(departamento);
                    cell = row.createCell(++columnCount);
                    cell.setCellValue(localidad);
                    cell = row.createCell(++columnCount);
                    cell.setCellValue(diccionario_UbicacionLocalidad.get(municipio).get(departamento).get(localidad));
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
        if (!diccionario_UbicacionLocalidad.containsKey(departamentoCorrecto)) {
            int majorLev = 50;
            for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
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
        for (String mncp : diccionario_UbicacionLocalidad.get(departamentoCorrecto).keySet()) {
            answer += mncp + "\n";
        }

        return answer;
    }

    public String findByMunicipio(String municipio) {
        String answer = "";
        String dptoCorrecto = "";
        answer += "El municicpio " + dptoCorrecto + " esta en: \n";
        int majorLev = 50;
        
        for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
            for (String mncp : diccionario_UbicacionLocalidad.get(dpto).keySet()) {
                int partialLev = FuzzySearch.ratio(mncp, municipio);
                if (partialLev > 50) {
                    if(partialLev > majorLev){
                        dptoCorrecto = dpto;
                        majorLev = partialLev;
                    }
                    answer += dpto;
                    if(partialLev >= 100) answer += " *MAS POSIBLE* ";
                    else if(partialLev >= 80) answer += "*muy probable*";
                    answer += "\n";
                    break;
                }
            }
        }
        answer += dptoCorrecto + " *mayor posibilidad";
        return answer;
    }

    public String findByLocalidad(String localidad) {
        String answer = "La localidad " + localidad + " esta en: \n";
        String localidadCorrecta = localidad;
        int majorLev = 50;
        int percent = 80;
        if(codigo_localidad.contains(localidad))
            percent = 100;
        
        for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
            for (String mncp : diccionario_UbicacionLocalidad.get(dpto).keySet()) {
                for (String local : diccionario_UbicacionLocalidad.get(dpto).get(mncp).keySet()) {
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
                        answer += "Dpto: " + dpto + ", municipio: " + mncp + " encontro " + local + ": " + diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(localidadCorrecta) +"\n";
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
        if (!diccionario_UbicacionLocalidad.containsKey(departamentoCorrecto)) {
            int majorLev = 50;
            for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
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

        if (!diccionario_UbicacionLocalidad.get(departamentoCorrecto).containsKey(municipioCorrecto)) {
            int majorLev = 0;
            for (String mncp : diccionario_UbicacionLocalidad.get(departamentoCorrecto).keySet()) {
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
        for (String localidades : diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(municipioCorrecto).keySet()) {
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
        for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
            if (!diccionario_UbicacionLocalidad.get(dpto).containsKey(municipioCorrecto)) {
                for (String mncp : diccionario_UbicacionLocalidad.get(dpto).keySet()) {
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

            if (!diccionario_UbicacionLocalidad.get(dpto).get(municipioCorrecto).containsKey(localidadCorrecta)) {
                majorLev = 0;
                for (String local : diccionario_UbicacionLocalidad.get(dpto).get(municipioCorrecto).keySet()) {
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

        answer += "Departamento " + departamentoCorrecto + " con municipio " + municipioCorrecto + " con " + localidadCorrecta + " tiene codigo: " + diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(municipioCorrecto).get(localidadCorrecta);
        return answer;
    }

    public String finbByLocalidadAndDepartamento(String localidad, String departamento){
        String answer = "En " + departamento + " se encontro " + localidad + " en: \n";
        String localidadCorrecta = localidad;
        String departamentoCorrecto = departamento;
        int majorLev = 50;
        if(!diccionario_UbicacionLocalidad.containsKey(departamento)){
        for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
            if(FuzzySearch.ratio(departamentoCorrecto, dpto)<50)
                continue;
            for (String mncp : diccionario_UbicacionLocalidad.get(dpto).keySet()) {
                for(String local: diccionario_UbicacionLocalidad.get(dpto).get(mncp).keySet()){
                    int temporalLev = FuzzySearch.ratio(local, localidadCorrecta);
                    if(temporalLev>=majorLev){
                        majorLev = temporalLev;
                        answer += "El municipio que tiene a " + local + " y pertenece a " + dpto + " es: " + mncp + "\n";
                    }
                    //if (majorLev >= 100) break;
                }
                //if (majorLev >= 100) break;
            }
        }}
        else{
            for (String mncp : diccionario_UbicacionLocalidad.get(departamentoCorrecto).keySet()) {
                for(String local: diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(mncp).keySet()){
                    int temporalLev = FuzzySearch.ratio(local, localidadCorrecta);
                    if(temporalLev>=majorLev){
                        majorLev = temporalLev;
                        answer += "El municipio que tiene a " + local + " y pertenece a " + departamentoCorrecto + " es: " + mncp + "\n";
                    }
                    //if (majorLev >= 100) break;
                }
                //if (majorLev >= 100) break;
            }
        }
        
        return answer;
    }
    
    public String findByAll(String departamento, String municipio, String localidad) {
        String answer = "";
        String municipioCorrecto = municipio;
        String localidadCorrecta = localidad;
        String departamentoCorrecto = departamento;
        int majorLev = 0;

        if (!diccionario_UbicacionLocalidad.containsKey(departamentoCorrecto)) {
            for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
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

        if (!diccionario_UbicacionLocalidad.get(departamentoCorrecto).containsKey(municipioCorrecto)) {
            for (String mncp : diccionario_UbicacionLocalidad.get(departamentoCorrecto).keySet()) {
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

        if (!diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(municipioCorrecto).containsKey(localidadCorrecta)) {
            for (String local : diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(municipioCorrecto).keySet()) {
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

        answer += "Departamento " + departamentoCorrecto + " con municipio " + municipioCorrecto + " con " + localidadCorrecta + " tiene codigo: " + diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(municipioCorrecto).get(localidadCorrecta);
        return answer;
    }

    public String findByCode(int code) {
        String answer = "El codigo " + code + " corresponde a: \n";
        String localidadCorrecta = codigo_localidad.get(code);
        String departamentoCorrecto = "", municipioCorrecto = "";
        for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
            for (String mncp : diccionario_UbicacionLocalidad.get(dpto).keySet()) {
                if (diccionario_UbicacionLocalidad.get(dpto).get(mncp).containsKey(localidadCorrecta)) {
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
        return diccionario_UbicacionLocalidad;
    }

    public Hashtable<Integer, String> getAllCodes() {
        return codigo_localidad;
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
