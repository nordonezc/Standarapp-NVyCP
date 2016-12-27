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

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //Logica de la aplicacion
        listOfStandarNames = new Hashtable<String, Hashtable<String, Hashtable<String, Integer>>>();

        String nameExcel = "C:\\Users\\Niki\\Downloads\\Localidades.xlsx";
        XSSFWorkbook xwb = lectureXLSX(nameExcel);
        XSSFSheet xsheet = xwb.getSheetAt(0);
        int repeated = 0;
        int codigoCP = 0;
        int codigoTemporal = 0;

        for (Row row : xsheet) {
            if (row.getRowNum() > 0) {
            String departamento = "", municipio = "", localidad = "";
            int codigoVereda = 0;

            System.out.println(row.getRowNum() + " = ");
            
            for (Cell cell : row) {
                /*switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            System.err.print(cell.getColumnIndex() + "\t\t" + cell.getStringCellValue() + "\t\t");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.err.print(cell.getColumnIndex() + "\t\t" + cell.getNumericCellValue() + "\t\t");
                            break;
                    }*/
                //System.err.print(cell.getColumnIndex() + "\t\t" + cell.getStringCellValue() + "\t\t");
                if (cell.getColumnIndex() == 2) {
                    departamento = cell.getStringCellValue();
                }
                if (cell.getColumnIndex() == 3) {
                    municipio = cell.getStringCellValue();
                }
                if (cell.getColumnIndex() == 4) {
                    localidad = cell.getStringCellValue();
                }
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
            }

            /*
                departamento = departamento.toUpperCase();
                departamento = fixWords(departamento);

                municipio = municipio.toUpperCase();
                municipio = fixWords(municipio);

                localidad = localidad.toUpperCase();
                localidad = fixWords(localidad);
             */
            if (!listOfStandarNames.containsKey(departamento)) {
                Hashtable<String, Hashtable<String, Integer>> primerMunicipio = new Hashtable<String, Hashtable<String, Integer>>();
                Hashtable<String, Integer> primerLocalidad = new Hashtable<>();
                if (codigoVereda != 0) {
                    codigoTemporal = codigoVereda;
                } else {
                    codigoCP++;
                    codigoTemporal = codigoCP;
                }
                primerLocalidad.put(localidad, codigoTemporal);
                primerMunicipio.put(municipio, primerLocalidad);
                listOfStandarNames.put(departamento, primerMunicipio);
            } else if (!listOfStandarNames.get(departamento).containsKey(municipio)) {
                Hashtable<String, Integer> primerGeo = new Hashtable<String, Integer>();
                if (codigoVereda != 0) {
                    codigoTemporal = codigoVereda;
                } else {
                    codigoCP++;
                    codigoTemporal = codigoCP;
                }
                primerGeo.put(localidad, codigoTemporal);
                listOfStandarNames.get(departamento).put(municipio, primerGeo);
            } else if (!listOfStandarNames.get(departamento).get(municipio).containsKey(localidad)) {
                if (codigoVereda != 0) {
                    codigoTemporal = codigoVereda;
                } else {
                    codigoCP++;
                    codigoTemporal = codigoCP;
                }
                listOfStandarNames.get(departamento).get(municipio).put(localidad, codigoTemporal);
            } else {
                if (codigoVereda != 0) {
                    codigoTemporal = codigoVereda;
                } else {
                    codigoTemporal = listOfStandarNames.get(departamento).get(municipio).get(localidad);
                }
                repeated++;
                System.err.println(localidad);
            }

            row.getCell(1).setCellValue(codigoTemporal);

            /*switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(row.getRowNum() + cell.getColumnIndex() + ":" + cell.getStringCellValue() + "\t\t");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(row.getRowNum() + cell.getColumnIndex() + ":" + cell.getNumericCellValue() + "\t\t");
                            break;
                    }
                }
                System.out.println("");*/
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Niki\\Downloads\\LocalidadesConCodigo.xlsx")) {
            xwb.write(outputStream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }

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
}

/**
 * Muestra la lista de localidades estandarizados for(String dep:
 * listOfStandarNames.keySet()){ for(String mun:
 * listOfStandarNames.get(dep).keySet()){ for(String local:
 * listOfStandarNames.get(dep).get(mun).keySet()){ System.out.println(dep +
 * "\t\t" + mun + "\t\t" + local); } } }
 */
/*
        for (Row row : xsheet) {
            if (row.getRowNum() >= 1) {
                int codigo = 0;
                String departamento = "", municipio = "", localidad = "";
                for (Cell cell : row) {
                    if (cell.getColumnIndex() == 3) {
                        codigo = (int) cell.getNumericCellValue();
                    }
                    if (cell.getColumnIndex() == 0) {
                        departamento = cell.getStringCellValue();
                    }
                    if (cell.getColumnIndex() == 1) {
                        municipio = cell.getStringCellValue();
                    }
                    if (cell.getColumnIndex() == 2) {
                        localidad = cell.getStringCellValue();
                    }
                }

                departamento = departamento.toUpperCase();
                municipio = municipio.toUpperCase();
                localidad = localidad.toUpperCase();

                if (!listOfStandarNames.containsKey(departamento)) {
                    Hashtable<String, Hashtable<String, Integer>> primerMunicipio = new Hashtable<String, Hashtable<String, Integer>>();
                    Hashtable<String, Integer> primerLocalidad = new Hashtable<>();
                    primerLocalidad.put(localidad, codigo);
                    primerMunicipio.put(municipio, primerLocalidad);
                    listOfStandarNames.put(departamento, primerMunicipio);

                } //Add municipio if it isn't exist
                //Añade municipio a la tabla hash si no existe
                //Add vycp if municipio doesnt contained it
                //Añade vycp si no se encuentra ubicado en el municipio
                else if (!listOfStandarNames.get(departamento).containsKey(municipio)) {
                    Hashtable<String, Integer> primerGeo = new Hashtable<String, Integer>();
                    primerGeo.put(localidad, codigo);
                    listOfStandarNames.get(departamento).put(municipio, primerGeo);
                } //Add vycp_codigo if it isn't exist
                //Añade el municipio y su codigo si aun no se ha agregado
                else if (!listOfStandarNames.get(departamento).get(municipio).contains(codigo)) {
                    listOfStandarNames.get(departamento).get(municipio).put(localidad, codigo);
                } else {
                    repeated++;
                    System.err.println(localidad);
                }
                /*
                switch (cell.getCellType()) {

                    case Cell.CELL_TYPE_STRING:

                        System.out.print(cell.getColumnIndex() + ":" + cell.getStringCellValue() + "\t\t");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getColumnIndex() + ":" + cell.getNumericCellValue() + "\t\t");
                        break;
                }

            }
        }
 */

 /*
                int mejorLevenstein = 0;
                String nombre_mejorLevenstein = "";*/
 /*
                if (!listOfStandarNames.containsKey(departamento)) {
                    for (String departamentoOficial : listOfStandarNames.keySet()) {
                        int levenstein = FuzzySearch.ratio(departamento, departamentoOficial);
                        if (levenstein > mejorLevenstein) {
                            nombre_mejorLevenstein = departamentoOficial;
                            mejorLevenstein = levenstein;
                        }
                    }

                    departamento = nombre_mejorLevenstein;
                    nombre_mejorLevenstein = "";
                    mejorLevenstein = 0;
                }

                System.out.println(row.getRowNum() + "= departamento: " + departamento + "| municipio: " + municipio);
                if (!listOfStandarNames.get(departamento).containsKey(municipio)) {
                    for (String municipioOficial : listOfStandarNames.get(departamento).keySet()) {
                        int levenstein = FuzzySearch.ratio(municipio, municipioOficial);
                        if (levenstein > mejorLevenstein) {
                            nombre_mejorLevenstein = municipioOficial;
                            mejorLevenstein = levenstein;
                        }
                    }

                    municipio = nombre_mejorLevenstein;
                    nombre_mejorLevenstein = "";
                    mejorLevenstein = 0;
                }
 */

 /*
                System.out.println(departamento + "\t\t" + municipio + "\t\t" + String.valueOf(localidad) + "\t\t");
                System.out.println(listOfStandarNames.get(departamento).containsKey(municipio));
                System.out.println(listOfStandarNames.get(departamento).get(municipio).containsKey(localidad));
                if (!listOfStandarNames.get(departamento).get(municipio).containsKey(localidad)) {
                    for (String municipioOficial : listOfStandarNames.get(departamento).get(municipio).keySet()) {
                        int levenstein = FuzzySearch.ratio(municipio, municipioOficial);
                        if (levenstein > mejorLevenstein) {
                            nombre_mejorLevenstein = municipioOficial;
                            mejorLevenstein = levenstein;
                        }
                    }

                    System.out.println("I: " + localidad + "\\t" + "O: " + nombre_mejorLevenstein);
                    localidad = nombre_mejorLevenstein;
                    nombre_mejorLevenstein = "";
                    mejorLevenstein = 0;
                }
                 */
