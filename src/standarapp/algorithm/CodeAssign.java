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

    private static Hashtable<String, Hashtable<String, Hashtable<String, Double>>> diccionario_UbicacionLocalidad;
    private static Hashtable<Integer, String> codigo_Dpto;
    private static Hashtable<Integer, String> codigo_Municipio;
    private static Hashtable<Integer, Hashtable<Integer, String>> dpto_Municipio;
    private static Hashtable<Double, String> codigo_localidad;
    private static Hashtable<Double, Double> localidad_X;
    private static Hashtable<Double, Double> localidad_Y;
    private static Hashtable<Integer, Hashtable<Double, String>> codigo_municipioLocalidad;

    public static Hashtable<Integer, String> getCodigo_Dpto() {
        return codigo_Dpto;
    }

    public static Hashtable<Integer, String> getCodigo_Municipio() {
        return codigo_Municipio;
    }

    public static Hashtable<Integer, Hashtable<Integer, String>> getDpto_Municipio() {
        return dpto_Municipio;
    }

    public static Hashtable<Double, String> getCodigo_localidad() {
        return codigo_localidad;
    }

    public static Hashtable<String, Hashtable<String, Hashtable<String, Double>>> getDiccionario_UbicacionLocalidad() {
        return diccionario_UbicacionLocalidad;
    }

    public static Hashtable<Double, Double> getLocalidad_X() {
        return localidad_X;
    }

    public static Hashtable<Double, Double> getLocalidad_Y() {
        return localidad_Y;
    }

    public static Hashtable<Integer, Hashtable<Double, String>> getCodigo_municipioLocalidad() {
        return codigo_municipioLocalidad;
    }

    public CodeAssign(String nameExcel) throws IOException {
        //Logica de la aplicacion
        diccionario_UbicacionLocalidad = new Hashtable<>();
        codigo_Dpto = new Hashtable<>();
        codigo_Municipio = new Hashtable<>();
        dpto_Municipio = new Hashtable<>();
        codigo_localidad = new Hashtable<>();
        localidad_X = new Hashtable<>();
        localidad_Y = new Hashtable<>();
        codigo_municipioLocalidad = new Hashtable<>();

        XSSFWorkbook xwb = Lecture.lectureXLSX(nameExcel);
        XSSFSheet xsheet = xwb.getSheetAt(0);
        double codigoTemporal = 0;
        
        for (Row row : xsheet) {
            if (row.getRowNum() > 0) {
                String departamento = "", municipio = "", localidad = "";
                int cod_departamento = 0, cod_municipio = 0;
                double cod_localidad = 0, x = 0, y = 0;
                for (Cell cell : row) {
                    if (cell.getColumnIndex() == 0) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                codigoTemporal = Double.valueOf(cell.getStringCellValue());
                                cod_departamento = (int) codigoTemporal;
                                continue;
                            case Cell.CELL_TYPE_NUMERIC:
                                cod_departamento = (int) cell.getNumericCellValue();
                                continue;
                        }
                    }
                    
                    if (cell.getColumnIndex() == 1)
                        departamento = cell.getStringCellValue();
                   
                    if (cell.getColumnIndex() == 2) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                codigoTemporal = Double.valueOf(cell.getStringCellValue());
                                cod_municipio = (int) codigoTemporal;
                                continue;
                            case Cell.CELL_TYPE_NUMERIC:
                                cod_municipio = (int) cell.getNumericCellValue();
                                continue;
                        }
                    }

                    if (cell.getColumnIndex() == 3) 
                        municipio = cell.getStringCellValue();

                    if (cell.getColumnIndex() == 4) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                cod_localidad = Double.valueOf(cell.getStringCellValue());
                                continue;
                            case Cell.CELL_TYPE_NUMERIC:
                                cod_localidad = (double) cell.getNumericCellValue();
                                continue;
                        }
                    }

                    if (cell.getColumnIndex() == 5)
                        localidad = cell.getStringCellValue();
        
                    if (cell.getColumnIndex() == 6) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                x = Double.valueOf(cell.getStringCellValue());
                                continue;
                            case Cell.CELL_TYPE_NUMERIC:
                                x = (double) cell.getNumericCellValue();
                                continue;
                        }
                    }

                    if (cell.getColumnIndex() == 7) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                y = Double.valueOf(cell.getStringCellValue());
                                continue;
                            case Cell.CELL_TYPE_NUMERIC:
                                y = (double) cell.getNumericCellValue();
                                continue;
                        }
                    }
                }

                if (!diccionario_UbicacionLocalidad.containsKey(departamento)) {
                    Hashtable<String, Hashtable<String, Double>> primerMunicipio = new Hashtable<>();
                    Hashtable<String, Double> primerLocalidad = new Hashtable<>();
                    Hashtable<Double, String> primerLocalidadInv = new Hashtable<>();
                    codigo_Dpto.put(cod_departamento, departamento);
                    codigo_Municipio.put(cod_municipio, municipio);
                    codigo_localidad.put(cod_localidad, localidad);
                    localidad_X.put(cod_localidad, x);
                    localidad_Y.put(cod_localidad, y);
                    
                    primerLocalidadInv.put(cod_localidad, localidad);
                    codigo_municipioLocalidad.put(cod_municipio, primerLocalidadInv);
                    primerLocalidad.put(localidad, cod_localidad);
                    primerMunicipio.put(municipio, primerLocalidad);
                    diccionario_UbicacionLocalidad.put(departamento, primerMunicipio);

                } else if (!diccionario_UbicacionLocalidad.get(departamento).containsKey(municipio)) {
                    Hashtable<String, Double> primerLocalidad = new Hashtable<>();
                    codigo_Municipio.put(cod_municipio, municipio);
                    codigo_localidad.put(cod_localidad, localidad);
                    localidad_X.put(cod_localidad, x);
                    localidad_Y.put(cod_localidad, y);
                    primerLocalidad.put(localidad, cod_localidad);
                    
                    Hashtable<Double, String> primerLocalidadInv = new Hashtable<>();
                    
                    primerLocalidadInv.put(cod_localidad, localidad);
                    codigo_municipioLocalidad.put(cod_municipio, primerLocalidadInv);
                    
                    diccionario_UbicacionLocalidad.get(departamento).put(municipio, primerLocalidad);
                    
                } else if (!diccionario_UbicacionLocalidad.get(departamento).get(municipio).containsKey(localidad)) {
                    codigo_localidad.put(cod_localidad, localidad);
                    localidad_X.put(cod_localidad, x);
                    localidad_Y.put(cod_localidad, y);
                    codigo_municipioLocalidad.get(cod_municipio).put(cod_localidad, localidad);
                    diccionario_UbicacionLocalidad.get(departamento).get(municipio).put(localidad, cod_localidad);
                } 
            }
        }
        
    }
    
    public void showDictionary(){
        for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
            for(String mncp : diccionario_UbicacionLocalidad.get(dpto).keySet()){
                for(String local : diccionario_UbicacionLocalidad.get(dpto).get(mncp).keySet()){
                    System.out.println("Dpto: " + dpto + " | Mncp: " + mncp + " | Local: " + local + " | Cod: " + diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(local));
                }
            }
        }
    }
    
    public void showMncpLocalidad(){
        int cont = 0;
        for (Integer mncp : codigo_municipioLocalidad.keySet()) {
            for(Double local : codigo_municipioLocalidad.get(mncp).keySet()){
                System.out.println(cont++ + "Mncp: " + mncp + " | Local: " + local + " | " + codigo_municipioLocalidad.get(mncp).get(local));
            }
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
                    if (partialLev > majorLev) {
                        dptoCorrecto = dpto;
                        majorLev = partialLev;
                    }
                    answer += dpto;
                    if (partialLev >= 100) {
                        answer += " *MAS POSIBLE* ";
                    } else if (partialLev >= 80) {
                        answer += "*muy probable*";
                    }
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
        if (codigo_localidad.contains(localidad)) {
            percent = 100;
        }

        for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
            for (String mncp : diccionario_UbicacionLocalidad.get(dpto).keySet()) {
                for (String local : diccionario_UbicacionLocalidad.get(dpto).get(mncp).keySet()) {
                    int partialLev = 0;
                    if (percent == 100) {
                        partialLev = FuzzySearch.ratio(local, localidadCorrecta);
                    } else {
                        partialLev = FuzzySearch.tokenSetRatio(local, localidadCorrecta);
                    }
                    if (partialLev >= majorLev && partialLev >= percent) {
                        localidadCorrecta = local;
                        majorLev = partialLev;
                    }
                    if (majorLev >= percent) {
                        majorLev = 0;
                        answer += "Dpto: " + dpto + ", municipio: " + mncp + " encontro " + local + ": " + diccionario_UbicacionLocalidad.get(dpto).get(mncp).get(localidadCorrecta) + "\n";
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

    public String finbByLocalidadAndDepartamento(String localidad, String departamento) {
        String answer = "En " + departamento + " se encontro " + localidad + " en: \n";
        String localidadCorrecta = localidad;
        String departamentoCorrecto = departamento;
        int majorLev = 50;
        if (!diccionario_UbicacionLocalidad.containsKey(departamento)) {
            for (String dpto : diccionario_UbicacionLocalidad.keySet()) {
                if (FuzzySearch.ratio(departamentoCorrecto, dpto) < 50) {
                    continue;
                }
                for (String mncp : diccionario_UbicacionLocalidad.get(dpto).keySet()) {
                    for (String local : diccionario_UbicacionLocalidad.get(dpto).get(mncp).keySet()) {
                        int temporalLev = FuzzySearch.ratio(local, localidadCorrecta);
                        if (temporalLev >= majorLev) {
                            majorLev = temporalLev;
                            answer += "El municipio que tiene a " + local + " y pertenece a " + dpto + " es: " + mncp + "\n";
                        }
                        //if (majorLev >= 100) break;
                    }
                    //if (majorLev >= 100) break;
                }
            }
        } else {
            for (String mncp : diccionario_UbicacionLocalidad.get(departamentoCorrecto).keySet()) {
                for (String local : diccionario_UbicacionLocalidad.get(departamentoCorrecto).get(mncp).keySet()) {
                    int temporalLev = FuzzySearch.ratio(local, localidadCorrecta);
                    if (temporalLev >= majorLev) {
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

    /*public String findByCode(int code) {
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
    }*/

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


   /*
    public static void main(String args[]) {  
    diccionario_UbicacionLocalidad = new Hashtable<>();
    codigo_Dpto = new Hashtable<>();
    codigo_Municipio = new Hashtable<>();
    dpto_Municipio = new Hashtable<>();
    codigo_localidad = new Hashtable<>();
    codigo_municipioLocalidad = new Hashtable<>();
    localidad_X = new Hashtable<>();
    localidad_Y = new Hashtable<>();
    
        XSSFSheet xsheet = Lecture.lectureXLSX("C:\\Users\\Dell\\Downloads\\codigosDaneArreglados.xlsx", 0);
        for (Row row : xsheet) {
            if(row.getRowNum() >4){
            String departamento = "";
            int codigoMunicipio = 0;
            int codigoDpto = 0;
            String codigoMun = "";
            String municipio = "";
            
            for (Cell cell : row) {
                if (cell.getColumnIndex() == 0) 
                    departamento = cell.getStringCellValue();
                
                if (cell.getColumnIndex() == 2) 
                    municipio = cell.getStringCellValue();
                
                if (cell.getColumnIndex() == 1) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            codigoMun = cell.getStringCellValue();
                            codigoMunicipio = Integer.parseInt(codigoMun);
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            codigoMunicipio = (int) cell.getNumericCellValue();
                            codigoMun = String.valueOf(codigoMunicipio);
                            break;
                    }
                    
                    String temporal = codigoMun.charAt(0) + "" + codigoMun.charAt(1);
                    codigoDpto = Integer.parseInt(temporal);
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
                    Hashtable<String, Hashtable<String, Double>> primerMunicipio = new Hashtable<>();
                    Hashtable<String, Double> primerLocalidad = new Hashtable<>();
                    Hashtable<Integer, String> primerMncp = new Hashtable<>();
                    Hashtable<Double, String> primerLocalidadporNumero = new Hashtable<>();
                    
                    codigo_Dpto.put(codigoDpto, departamento);
                    
                    primerMunicipio.put(municipio, primerLocalidad);
                    diccionario_UbicacionLocalidad.put(departamento, primerMunicipio);
                    codigo_Municipio.put(codigoMunicipio, municipio);
                    codigo_municipioLocalidad.put(codigoMunicipio, primerLocalidadporNumero);
                    primerMncp.put(codigoMunicipio, municipio);
                    dpto_Municipio.put(codigoDpto, primerMncp);
                    
            } else if (!diccionario_UbicacionLocalidad.get(departamento).containsKey(municipio)) {
                    Hashtable<String, Double> primerLocalidad = new Hashtable<>();
                    Hashtable<Double, String> primerLocalidadporNumero = new Hashtable<>();
                    
                    diccionario_UbicacionLocalidad.get(departamento).put(municipio, primerLocalidad);
                    codigo_Municipio.put(codigoMunicipio, municipio);
                    codigo_municipioLocalidad.put(codigoMunicipio, primerLocalidadporNumero);
                    dpto_Municipio.get(codigoDpto).put(codigoMunicipio, municipio);
            } else {
            }
        }
        }
        
        xsheet = Lecture.lectureXLSX("C:\\Users\\Dell\\Downloads\\veredasCodigosArreglados.xlsx", 0);
        for (Row row : xsheet) {
            if(row.getRowNum() >0){
            String departamento = "";
            String municipio = "";
            String vereda = "";
            
            int codigoMunicipio = 0;
            int codigoDpto = 0;
            double codigoVereda = 0;
            String codigoMun = "";
            String codigoVer = "";
            double veredaX = 0;
            String verX = "";
            double veredaY = 0;
            String verY = "";
            
            
            System.out.print(row.getRowNum() + "| ");
            for (Cell cell : row) {
                if (cell.getColumnIndex() == 5) 
                    vereda = cell.getStringCellValue();
                
                if (cell.getColumnIndex() == 2) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            System.out.print("Vereda String: ");
                            codigoVer = cell.getStringCellValue();
                            codigoVereda = Integer.parseInt(codigoVer);
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print("numerico: ");
                            codigoVereda = (int) cell.getNumericCellValue();
                            //codigoVer = String.valueOf(codigoVereda);
                            break;
                    }
                }
                
                if (cell.getColumnIndex() == 12) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            System.out.print("X String: ");
                            verX = cell.getStringCellValue();
                            veredaX = Double.parseDouble(verX);
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print("X numerico: ");
                            veredaX = cell.getNumericCellValue();
                            //codigoVer = String.valueOf(codigoVereda);
                            break;
                    }
                }
                
                if (cell.getColumnIndex() == 13) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            System.out.print("Y String: ");
                            verY = cell.getStringCellValue();
                            veredaY = Double.parseDouble(verY);
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print("Y numerico: ");
                            veredaY = cell.getNumericCellValue();
                            break;
                    }
                }
                
                
                if (cell.getColumnIndex() == 1) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            System.out.print("Dpto String: ");
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
            
            departamento = codigo_Dpto.get(codigoDpto);
            municipio = codigo_Municipio.get(codigoMunicipio);
            
            if(!diccionario_UbicacionLocalidad.get(departamento).get(municipio).containsKey(vereda)){
                codigo_localidad.put(codigoVereda, vereda);
                diccionario_UbicacionLocalidad.get(departamento).get(municipio).put(vereda, codigoVereda);
                codigo_municipioLocalidad.get(codigoMunicipio).put(codigoVereda, vereda);
                localidad_X.put(codigoVereda, veredaX);
                localidad_Y.put(codigoVereda, veredaY);
            }
        }
        }
        
        
        xsheet = Lecture.lectureXLSX("C:\\Users\\Dell\\Downloads\\centrospobladosArreglados.xlsx", 0);
        for (Row row : xsheet) {
            if(row.getRowNum() >0){
            String departamento = "";
            String municipio = "";
            String vereda = "";
            
            int codigoMunicipio = 0;
            int codigoDpto = 0;
            double codigoVereda = 0;
            String codigoMun = "";
            String codigoVer = "";
            double veredaX = 0;
            String verX = "";
            double veredaY = 0;
            String verY = "";
            
            
            System.out.print(row.getRowNum() + "| ");
            for (Cell cell : row) {
                if (cell.getColumnIndex() == 7) 
                    vereda = cell.getStringCellValue();
                
                if (cell.getColumnIndex() == 8) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            System.out.print("Vereda String: ");
                            codigoVer = cell.getStringCellValue();
                            codigoVereda = Double.parseDouble(codigoVer);
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print("numerico: ");
                            codigoVereda = (int) cell.getNumericCellValue();
                            //codigoVer = String.valueOf(codigoVereda);
                            break;
                    }
                }
                
                if (cell.getColumnIndex() == 16) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            System.out.print("X String: ");
                            verX = cell.getStringCellValue();
                            veredaX = Double.parseDouble(verX);
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print("X numerico: ");
                            veredaX = cell.getNumericCellValue();
                            //codigoVer = String.valueOf(codigoVereda);
                            break;
                    }
                }
                
                if (cell.getColumnIndex() == 17) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            System.out.print("Y String: ");
                            verY = cell.getStringCellValue();
                            veredaY = Double.parseDouble(verY);
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print("Y numerico: ");
                            veredaY = cell.getNumericCellValue();
                            break;
                    }
                }
                
                
                if (cell.getColumnIndex() == 1) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            System.out.print("Dpto String: ");
                            String codigoDepto = cell.getStringCellValue();
                            codigoDpto = Integer.parseInt(codigoDepto);
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print("numerico: ");
                            codigoDpto = (int) cell.getNumericCellValue();
                            break;
                    }
                }
                
                if (cell.getColumnIndex() == 2) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            System.out.print("Mncp String: ");
                            codigoMun = cell.getStringCellValue();
                            codigoMunicipio = Integer.parseInt(codigoMun);
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print("numerico: ");
                            codigoMunicipio = (int) cell.getNumericCellValue();
                            codigoMun = String.valueOf(codigoMunicipio);
                            break;
                    }
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
            
            codigoMunicipio += (codigoDpto*1000);
            departamento = codigo_Dpto.get(codigoDpto);
            municipio = codigo_Municipio.get(codigoMunicipio);
            
            if(!diccionario_UbicacionLocalidad.get(departamento).get(municipio).containsKey(vereda)){
                codigo_localidad.put(codigoVereda, vereda);
                diccionario_UbicacionLocalidad.get(departamento).get(municipio).put(vereda, codigoVereda);
                codigo_municipioLocalidad.get(codigoMunicipio).put(codigoVereda, vereda);
                localidad_X.put(codigoVereda, veredaX);
                localidad_Y.put(codigoVereda, veredaY);
            }
        }
        }
        
        XSSFWorkbook xwb = new XSSFWorkbook();
        XSSFSheet sheet = xwb.createSheet("StandarCodes");

        int rowCount = 0;
        int columnCount = 0;
        Row row = sheet.createRow(rowCount);
        Cell cell = row.createCell(columnCount);
        cell.setCellValue("Cod_Departamento");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Departamento");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Cod_Municipio");
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
        
        for(int codDpto: codigo_Dpto.keySet()){
            for(int codMncp: dpto_Municipio.get(codDpto).keySet()){
                for(double codLoc: codigo_municipioLocalidad.get(codMncp).keySet()){
                    row = sheet.createRow(++rowCount);
                    columnCount = 0;
                    cell = row.createCell(columnCount);
                    cell.setCellValue(codDpto);
                    cell = row.createCell(++columnCount);
                    cell.setCellValue(codigo_Dpto.get(codDpto));
                    
                    
                    cell = row.createCell(++columnCount);
                    cell.setCellValue(codMncp);
                    cell = row.createCell(++columnCount);
                    cell.setCellValue(codigo_Municipio.get(codMncp));
                    cell = row.createCell(++columnCount);
                    cell.setCellValue(codLoc);
                    cell = row.createCell(++columnCount);
                    cell.setCellValue(codigo_localidad.get(codLoc));
                    cell = row.createCell(++columnCount);
                    cell.setCellValue(localidad_X.get(codLoc));
                    cell = row.createCell(++columnCount);
                    cell.setCellValue(localidad_Y.get(codLoc));
                }
            }
        }
        
        try (FileOutputStream outputStream = new FileOutputStream("C:\\St.xlsx")) {
            xwb.write(outputStream);
        } catch(Exception e){}
        
        
    }
     */