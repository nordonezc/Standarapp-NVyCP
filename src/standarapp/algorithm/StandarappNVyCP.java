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
public class StandarappNVyCP {

    /**
     * English Explanation of each variable nameExcelX: name of direction where
     * is located each excel file. dptoMncp: Hashtable where is located each
     * 'departamento' with all his 'municipios' mncpVyCp: Has the relation
     * between each mncp ('municipio') with VyCU(Veredas y Centros Poblados)
     * vycp_codigo: Has all the VyCU (Veredas y centros poblados) with all his
     * codes registry: ArrayList with an arraylist indexed which contains
     * registers given before by doctors and specialist file: Auxiliar variable
     * for save xls files workbook: Contains the workbook of the file sheet:
     * Contains the sheet that has the workbook
     * ---------+------------+--------------+----------------+-------------+-----------------+-------------+
     * Explicación en español de cada varaible nameExcelX: Direccion exacta
     * donde se encuentra cada archivo de excel. Ej: C:\\Users\\User1\\file.xls
     * dptoMncp: Tabla hash que tiene cada departamento en relación de
     * pertenencia con cada municipio mncpVyCp: Tabla hash que tiene la
     * pertenencia entre cada mncp ('municipio') con VyCU(Veredas y Centros
     * Poblados) vycp_codigo: Tiene todos los codigos de VyCU (Veredas y centros
     * poblados) registry: Lista que tiene los registros dados por los medicos
     * asi como los 4 campos que se analizan file: Variable auxiliar que
     * almacena los archivos xls workbook: Contiene el libro de el archivo de
     * excel almacenado en file sheet: Almacena cada una de las hojas que
     * existen en el workbook
     */
    private static String nameExcel;
    private static Hashtable<String, Hashtable<String, Hashtable<String, Integer>>> listOfStandarNames; // relacion de departamento con municipio
    private static Hashtable<String, String> dptoMncp;
    private static Hashtable<String, Hashtable<String, Integer>> mncpVyCP; // relacion de centro poblado y vereda con municipio
    private static Hashtable<Integer, String> vycp_codigo; //codigo de cada vereda con centro poblado
    private static ArrayList<String[]> registry; //21, 22, 97, 99 each 4 its a different registry

    private static FileInputStream file;
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static HSSFWorkbook hworkbook;
    private static HSSFSheet hsheet;
    private static int repeated;

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //Logica de la aplicacion

        nameExcel = "";
        listOfStandarNames = new Hashtable<String, Hashtable<String, Hashtable<String, Integer>>>();
        registry = new ArrayList<String[]>();
        dptoMncp = new Hashtable<>();
        mncpVyCP = new Hashtable<String, Hashtable<String, Integer>>();
        vycp_codigo = new Hashtable<Integer, String>();
        repeated = 0;
        
        //Name of excel Files, initialize variables
        //Nombre de los archivos de excel, inicializar variables
        nameExcel = "C:\\Users\\Niki\\Downloads\\LEISHMANIASI.xlsx";

        //Reading the file which contains registries
        //Lectura del archivo xls de registros
        workbook = lectureXLSX(nameExcel);

        //Obtiene la primera oja del archivo de excel.
        //Get first/desired sheet from the workbook
        sheet = workbook.getSheetAt(0);

        //Iteración de cada una de las filas y celdas del archivo cargado
        //Iterate through each rows one by one
        for (Row row : sheet) {
            //cellsWI = cells with important information
            //celdas donde esta la información que se compara con levenstein
            String[] cellsWI = new String[5];
            for (int i = 0; i < cellsWI.length; i++) {
                cellsWI[i] = "";
            }

            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        //System.out.print(cell.getColumnIndex() + ":" + cell.getStringCellValue() + "\t\t");
                        if (!cell.getStringCellValue().contains("1") && !cell.getStringCellValue().contains("2") && !cell.getStringCellValue().contains("0") && !cell.getStringCellValue().contains("3") && !cell.getStringCellValue().contains("4") && !cell.getStringCellValue().contains("5") && !cell.getStringCellValue().contains("6") && !cell.getStringCellValue().contains("7") && !cell.getStringCellValue().contains("8") && !cell.getStringCellValue().contains("9")) {
                            if (cell.getColumnIndex() == 20) {
                                String info = cell.getStringCellValue().toUpperCase();

                                //Eliminacion de palabras sobrantes y tildes
                                info = fixWords(info);
                                cellsWI[0] = info;
                                //System.out.print(cell.getColumnIndex() + ":" + info + "\t\t");
                            }
                            if (cell.getColumnIndex() == 21) {
                                String info = cell.getStringCellValue().toUpperCase();

                                //Eliminacion de palabras sobrantes y tildes
                                info = fixWords(info);
                                cellsWI[1] = info;
                                //System.out.print(cell.getColumnIndex() + ":" + info + "\t\t");
                            }
                            if (cell.getColumnIndex() == 22) {
                                String info = cell.getStringCellValue().toUpperCase();

                                //Eliminacion de palabras sobrantes y tildes
                                info = fixWords(info);
                                cellsWI[2] = info;
                                //System.out.print(cell.getColumnIndex() + ":" + info + "\t\t");
                            }
                            if (cell.getColumnIndex() == 97) {
                                String info = cell.getStringCellValue().toUpperCase();

                                //Eliminacion de palabras sobrantes y tildes
                                info = fixWords(info);
                                cellsWI[3] = info;
                                //System.out.print(cell.getColumnIndex() + ":" + info + "\t\t");
                            }
                            if (cell.getColumnIndex() == 99) {
                                String info = cell.getStringCellValue().toUpperCase();

                                //Eliminacion de palabras sobrantes y tildes
                                info = fixWords(info);
                                cellsWI[4] = info;
                                //System.out.print(cell.getColumnIndex() + ":" + info + "\t\t");
                            }
                        }
                        break;
                }
            }
            //System.out.println("");
            //Se añade a la lista de registros leidos correctamente.
            System.err.println(cellsWI[4] + "\t " + cellsWI[3] + "\t " + cellsWI[2] + "\t " + cellsWI[1] + "\t " + cellsWI[0]);
            registry.add(cellsWI);
        }

        //Se cierra el archivo leido.
        //Close file readed
        try {
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
        System.out.println("Cantidad de registros: " + registry.size());
        for (ArrayList<String> registro : registry) {
            for (String celda : registro) {
                System.out.print(celda + "\t\t");
            }
            System.out.println("");
        }
         */
        //Lectura de archivo que contiene nombres estandar de veredas
        //Reading the file which contains stantard names and codes of 'Veredas'
        nameExcel = "C:\\Users\\Niki\\Downloads\\VyCPcorregido.xlsx";
        try {
            file = new FileInputStream(new File(nameExcel));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Create Workbook instance holding reference to .xlsx file
        //Analogamente al anterior, creación de la instancia del libro del archivo xlsx.
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Get first/desired sheet from the workbook
        //Se obtiene la primera hoja del libro
        sheet = workbook.getSheetAt(0);

        //Iterate through each rows one by one
        //Iteración en cada celda y fila
        for (Row row : sheet) {
            //Auxiliar variables which contains important information of each row
            //Variables auxiliares donde se almacena temporalmente la información de cada fila
            int codigo = 0;
            String departamento = "";
            String municipio = "";
            String vycp = "";

            /**
             * If Sentence where specifies that: First row doesnt have
             * information Fourth row is different to zero
             *
             * Sentencia condicional donde especifica que haga analice la fila
             * solo si: La fila no es la primera del archivo .xlsx (Es decir el
             * titulo de cada columna La celda numero 4 es diferente de cero
             */
            if (row.getRowNum() != 0 && !row.getCell(4).getStringCellValue().equals(String.valueOf(0))) {
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            if (cell.getColumnIndex() == 4) {
                                codigo = Integer.parseInt(cell.getStringCellValue());
                            }
                            if (cell.getColumnIndex() == 5) {
                                departamento = cell.getStringCellValue();
                            }
                            if (cell.getColumnIndex() == 6) {
                                municipio = cell.getStringCellValue();
                            }
                            if (cell.getColumnIndex() == 7) {
                                vycp = cell.getStringCellValue();
                            }
                            //System.out.print(cell.getColumnIndex() + ":" + cell.getStringCellValue() + "\t\t");
                            break;
                    }
                }

                departamento = departamento.toUpperCase();
                departamento = fixWords(departamento);
                municipio = municipio.toUpperCase();
                municipio = fixWords(municipio);
                vycp = vycp.toUpperCase();
                vycp = fixWords(vycp);

                //Add departamento if it isn't exist
                //Añade deparmenteo a la tabla hash si no existe
                //Add municipio if departamento doesnt contained it
                //Añade municipio si no se encuentra ubicado en el deparamento
                if (!listOfStandarNames.containsKey(departamento)) {
                    Hashtable<String, Hashtable<String, Integer>> primerMunicipio = new Hashtable<String, Hashtable<String, Integer>>();
                    Hashtable<String, Integer> primerLocalidad = new Hashtable<>();
                    primerLocalidad.put(vycp, codigo);
                    primerMunicipio.put(municipio, primerLocalidad);
                    listOfStandarNames.put(departamento, primerMunicipio);

                    dptoMncp.put(departamento, municipio);
                    mncpVyCP.put(municipio, primerLocalidad);
                    vycp_codigo.put(codigo, vycp);

                } //Add municipio if it isn't exist
                //Añade municipio a la tabla hash si no existe
                //Add vycp if municipio doesnt contained it
                //Añade vycp si no se encuentra ubicado en el municipio
                else if (!listOfStandarNames.get(departamento).containsKey(municipio)) {
                    Hashtable<String, Integer> primerGeo = new Hashtable<String, Integer>();
                    primerGeo.put(vycp, codigo);
                    listOfStandarNames.get(departamento).put(municipio, primerGeo);

                    mncpVyCP.put(municipio, primerGeo);
                    vycp_codigo.put(codigo, vycp);
                } //Add vycp_codigo if it isn't exist
                //Añade el municipio y su codigo si aun no se ha agregado
                else if (!listOfStandarNames.get(departamento).get(municipio).contains(codigo)) {
                    listOfStandarNames.get(departamento).get(municipio).put(vycp, codigo);

                    vycp_codigo.put(codigo, vycp);
                } else {
                    repeated++;
                    System.out.println(vycp);
                }

                //System.out.println();
            }
        }

        //Close file readed
        //Se cierra el archivo leido
        try {
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
        Muestra todos los departamentos en la base de datos
        Shows the departamentos and municipios located in the database
        System.out.println("Cantidad de registros: " + registry.size());
        for (String key : dptoMncp.keySet()) {
            System.out.println("Departamento: " + key);
        }
        for (String key : mncpVyCP.keySet()) {
            System.out.println("Municipio: " + key);
        }
         */
        //Reading the file which contains registries
        //Lectura del archivo xls de registros
        nameExcel = "C:\\Users\\Niki\\Downloads\\municipio de cada casco urbano.xls";
        try {
            file = new FileInputStream(new File(nameExcel));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Create Workbook instance holding reference to .xlsx file
        //Creando una instancia haciendo referencia al archivo xls ubicado en file
        try {
            hworkbook = new HSSFWorkbook(file);
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Obtiene la primera oja del archivo de excel.
        //Get first/desired sheet from the workbook
        hsheet = hworkbook.getSheetAt(0);
        int centroPobladoCodigo = 0;
//Iteración de cada una de las filas y celdas del archivo cargado
        //Iterate through each rows one by one

        for (Row row : hsheet) {
            //Auxiliar variables which contains important information of each row
            //Variables auxiliares donde se almacena temporalmente la información de cada fila
            centroPobladoCodigo += 1;
            String departamento = "";
            String municipio = "";
            String vycp = "";

            /**
             * If Sentence where specifies that: First row doesnt have
             * information Fourth row is different to zero
             *
             * Sentencia condicional donde especifica que haga analice la fila
             * solo si: La fila no es la primera del archivo .xlsx (Es decir el
             * titulo de cada columna La celda numero 4 es diferente de cero
             */
            if (row.getRowNum() != 0) {
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            if (cell.getColumnIndex() == 2) {
                                departamento = cell.getStringCellValue();
                            }
                            if (cell.getColumnIndex() == 3) {
                                municipio = cell.getStringCellValue();
                            }
                            if (cell.getColumnIndex() == 0) {
                                vycp = cell.getStringCellValue();
                            }
                            //System.out.print(cell.getColumnIndex() + ":" + cell.getStringCellValue() + "\t\t");
                            break;
                    }
                }

                departamento = departamento.toUpperCase();
                departamento = fixWords(departamento);
                municipio = municipio.toUpperCase();
                municipio = fixWords(municipio);
                vycp = vycp.toUpperCase();
                vycp = fixWords(vycp);

                //Add departamento if it isn't exist
                //Añade deparmenteo a la tabla hash si no existe
                //Add municipio if departamento doesnt contained it
                //Añade municipio si no se encuentra ubicado en el deparamento
                if (!listOfStandarNames.containsKey(departamento)) {
                    Hashtable<String, Hashtable<String, Integer>> primerMunicipio = new Hashtable<String, Hashtable<String, Integer>>();
                    Hashtable<String, Integer> primerLocalidad = new Hashtable<>();
                    primerLocalidad.put(vycp, centroPobladoCodigo);
                    primerMunicipio.put(municipio, primerLocalidad);
                    listOfStandarNames.put(departamento, primerMunicipio);

                    dptoMncp.put(departamento, municipio);
                    mncpVyCP.put(municipio, primerLocalidad);
                    vycp_codigo.put(centroPobladoCodigo, vycp);
                } //Add municipio if it isn't exist
                //Añade municipio a la tabla hash si no existe
                //Add vycp if municipio doesnt contained it
                //Añade vycp si no se encuentra ubicado en el municipio
                else if (!listOfStandarNames.get(departamento).containsKey(municipio)) {
                    Hashtable<String, Integer> primerGeo = new Hashtable<String, Integer>();
                    primerGeo.put(vycp, centroPobladoCodigo);
                    listOfStandarNames.get(departamento).put(municipio, primerGeo);

                    mncpVyCP.put(municipio, primerGeo);
                    vycp_codigo.put(centroPobladoCodigo, vycp);
                } //Add vycp_codigo if it isn't exist
                //Añade el municipio y su codigo si aun no se ha agregado
                else if (!listOfStandarNames.get(departamento).get(municipio).contains(centroPobladoCodigo)) {
                    listOfStandarNames.get(departamento).get(municipio).put(vycp, centroPobladoCodigo);

                    vycp_codigo.put(centroPobladoCodigo, vycp);
                } else {
                    repeated++;
                }
                //System.out.println();
            }
        }

        //Iteración de cada una de las filas y celdas del archivo cargado
        //Iterate through each rows one by one
        System.out.println("Numero de municipios: " + mncpVyCP.size());
        System.out.println("Numero de departamentos: " + listOfStandarNames.size());
        System.out.println("Numero de Localidades: " + vycp_codigo.size());
        System.out.println("Numero de Repeticiones: " + repeated);

        /**
         * Write all localidades with codes, with his departamentos and
         * municipios Copiar en un excel todas las localidades con codigos en
         * departamentos y municipios
         */
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("StandarCodes");

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

        try (FileOutputStream outputStream = new FileOutputStream("StandartFileWCodes.xlsx")) {
            workbook.write(outputStream);
        }

        /*Levenstein distance applied to two random words
        String s1 = "Test";
        String s2 = "Testo
        int lvd = FuzzySearch.partialRatio(s1, s2);
        System.out.println("Levenstein: " + lvd);
         */
        /**
         * Write all localidades with codes, with his departamentos and
         * municipios Copiar en un excel todas las localidades con codigos en
         * departamentos y municipios
         */
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("StandarCodes");

        rowCount = 0;
        columnCount = 0;
        row = sheet.createRow(rowCount);
        cell = row.createCell(columnCount);
        cell.setCellValue("Departamento Entrada");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Municipio Entrada");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Localidad Entrada 1");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Localidad Entrada 2");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Localidad Entrada 3");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Departamento Estandarizado");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Municipio Estandarizado");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Localidad Estandarizado");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Codigo Estandarizado");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Mayor Levenstein Localidad");
        cell = row.createCell(++columnCount);
        cell.setCellValue("Mayor Valor Levenstein Localidad");

        for (int i = 1; i < registry.size(); i++) { //revisar pos 1133
            String mncpWithBestLevenstein = "";
            int mncpMajorLev = 0;
            String[] registro = registry.get(i);

            //Create new row
            //Crea una nueva fila
            row = sheet.createRow(++rowCount);
            columnCount = 0;
            cell = row.createCell(columnCount);
            cell.setCellValue(registro[4]);
            cell = row.createCell(++columnCount);
            cell.setCellValue(registro[3]);
            cell = row.createCell(++columnCount);
            cell.setCellValue(registro[2]);
            cell = row.createCell(++columnCount);
            cell.setCellValue(registro[1]);
            cell = row.createCell(++columnCount);
            cell.setCellValue(registro[0]);

            //Departamento search
            //Busqueda del Departamento
            //System.out.println("**********************");
            //System.out.println(" **** Comparación Departamento con Ratio ****");
            for (String key : listOfStandarNames.keySet()) {
                int levMncp = FuzzySearch.ratio(registro[4], key);
                if (levMncp > mncpMajorLev) {
                    mncpWithBestLevenstein = key;
                    mncpMajorLev = levMncp;
                }
                //System.out.println("Comparación entre " + registro.get(4) + " y " + key + " presenta levenstein: " + levMncp);
            }
            cell = row.createCell(++columnCount);
            cell.setCellValue(mncpWithBestLevenstein);
            //System.out.println("Mayor levenstein de " + registro.get(4) + " es: " + mncpWithBestLevenstein + " con una distancia de: " + mncpMajorLev);

            //Municipio search
            //Busqueda del Municipio
            String dptoWithBestLevenstein = "";
            int dptoMajorLevenstein = 0;
            //System.out.println(" **** Comparación DEPARTAMENTO con Ratio ****");
            System.out.println(registro[4] + " resulta ser " + mncpWithBestLevenstein + " pos " + i);
            for (String value : listOfStandarNames.get(mncpWithBestLevenstein).keySet()) {
                int levDpto = FuzzySearch.ratio(registro[3], value);
                if (levDpto > dptoMajorLevenstein) {
                    dptoWithBestLevenstein = value;
                    dptoMajorLevenstein = levDpto;
                    //System.out.println("Comparación entre " + registro.get(3) + " y " + value + " presenta levenstein: " + levDpto);
                }
            }

            cell = row.createCell(++columnCount);
            if (dptoMajorLevenstein >= 50) {
                cell.setCellValue(dptoWithBestLevenstein);

                //System.out.println("Mayor levenstein de " + registro.get(3) + " es: " + dptoWithBestLevenstein + " con una distancia de: " + dptoMajorLevenstein);
                //Vereda y centro poblado search
                //Busqueda del Vereda y centro poblado
                String vycpWithTheBestLev = "";
                int vycpMajorLevenstein = 0;
                //System.out.println(" **** Comparación Vereda y Centro Poblado con Ratio ****");
                for (String value : listOfStandarNames.get(mncpWithBestLevenstein).get(dptoWithBestLevenstein).keySet()) {
                    int levVyCP = FuzzySearch.tokenSetRatio(registro[2], value);
                    if (levVyCP > vycpMajorLevenstein) {
                        vycpWithTheBestLev = value;
                        vycpMajorLevenstein = levVyCP;
                        //System.out.println("Comparación entre " + registro.get(2) + " y " + value + " presenta levenstein: " + levVyCP);
                    }

                    levVyCP = FuzzySearch.ratio(registro[1], value);
                    if (levVyCP > vycpMajorLevenstein) {
                        vycpWithTheBestLev = value;
                        vycpMajorLevenstein = levVyCP;
                        //System.out.println("Comparación entre " + registro.get(1) + " y " + value + " presenta levenstein: " + levVyCP);
                    }

                    levVyCP = FuzzySearch.ratio(registro[0], value);
                    if (levVyCP > vycpMajorLevenstein) {
                        vycpWithTheBestLev = value;
                        vycpMajorLevenstein = levVyCP;
                        //System.out.println("Comparación entre " + registro.get(0) + " y " + value + " presenta levenstein: " + levVyCP);
                    }
                }

                cell = row.createCell(++columnCount);
                if (vycpMajorLevenstein >= 80) {
                    cell.setCellValue(vycpWithTheBestLev);
                    cell = row.createCell(++columnCount);
                    cell.setCellValue(listOfStandarNames.get(mncpWithBestLevenstein).get(dptoWithBestLevenstein).get(vycpWithTheBestLev));
                } else {
                    cell.setCellValue("Indeterminado");
                    cell = row.createCell(++columnCount);
                }

                cell = row.createCell(++columnCount);
                cell.setCellValue("Localidad: " + vycpWithTheBestLev);
                cell = row.createCell(++columnCount);
                cell.setCellValue("Localidad: " + vycpMajorLevenstein);
                //System.out.println("Mayor levenstein de " + registro.get(2) + " o " + registro.get(1) + " o " + registro.get(0) + " es: " + vycpWithTheBestLev + " con una distancia de: " + vycpMajorLevenstein);
                //System.out.println("Mayor levenstein es: " + vycpWithTheBestLev + " y su codigo es: " + listOfStandarNames.get(mncpWithBestLevenstein).get(dptoWithBestLevenstein).get(vycpWithTheBestLev));

            } else {
                cell.setCellValue("Indeterminado");
                cell = row.createCell(++columnCount);
                cell = row.createCell(++columnCount);
                cell.setCellValue("Departamento: " + mncpWithBestLevenstein);
                cell = row.createCell(++columnCount);
                cell.setCellValue("Departamento: " + mncpMajorLev);
            }

        }

        //Se cierra el archivo leido.
        //Close file readed
        try {
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (FileOutputStream outputStream = new FileOutputStream("standarizedRegistries.xlsx")) {
            workbook.write(outputStream);
        }

    }

    /**
     * Recieved word and returns the same without special characters
     *
     * @param message
     * @return info
     */
    private static String fixWords(String message) {
        String info = message;
        //info = info.replace(" ", "");
        info = info.replace("Á", "A");
        info = info.replace("É", "E");
        info = info.replace("Í", "I");
        info = info.replace("Ó", "O");
        info = info.replace("Ú", "U");
        info = info.replace("Ñ", "N");
        info = info.replace("VEREDA", "");
        info = info.replace("CORREGIMIENTO", "");
        info = info.replace("FINCA", "");
        info = info.replace("CALLE", ""); // No se elimina , junto con avenidas, carreras, etc
        info = info.replace("-", "");
        info = info.replace("°", "");
        info = info.replace("BARRIO", ""); // No se elimina
        //info = info.replace("(", "");
        //info = info.replace(")", "");
        info = info.replace("#", ""); //Direccion y tambien N°

        //Fixing errors generated by dbf to xls
        info = info.replace("═", "I");
        info = info.replace("ß", "a");
        info = info.replace("Ú", "e");
        info = info.replace("├âÔÇÿ", "N");
        info = info.replace("├â┬ü", "A");
        info = info.replace("├âÔÇ░", "E");
        info = info.replace("├â┬ì", "I");
        info = info.replace("├âÔÇ£", "O");
        info = info.replace("├â┼í", "U");

        return info;
    }

    private static XSSFWorkbook lectureXLSX(String nameFile) {
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

    private static HSSFWorkbook lectureXLS(String nameFile) {
        
        HSSFWorkbook excelFile = new HSSFWorkbook();
        //Reading the file which contains registries
        //Lectura del archivo xls de registros
        try {
            file = new FileInputStream(new File(nameFile));
            excelFile = new HSSFWorkbook(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return excelFile;
    }

    /**
     * Write in a specific cell of excelFile
     *
     * @param cell
     * @param row
     * @param col
     * @param typeOfFile if is it equal to false, is xls file, in other case is
     * xlsx
     * @return If the excel was correctly written, return true, in other case,
     * return false
     */
    private static boolean writeExcel(String cell, int row, int col, boolean typeOfFile, String nameFile){
        boolean answer = false;

        if (typeOfFile == false) {
            XSSFWorkbook xworkbook = lectureXLSX(nameFile);
            XSSFSheet xsheet = xworkbook.getSheetAt(0);
            xsheet.createRow(row);
            xsheet.getRow(row).createCell(col).setCellValue(cell);
            
            try (FileOutputStream outputStream = new FileOutputStream(nameFile)) {
                xworkbook.write(outputStream);
                answer = true;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            HSSFWorkbook hwb = lectureXLS(nameFile);
            HSSFSheet hs = hwb.getSheetAt(0);
            hs.createRow(row);
            hs.getRow(row).createCell(col).setCellValue(cell);
            
            try (FileOutputStream outputStream = new FileOutputStream(nameFile)) {
                hwb.write(outputStream);
                answer = true;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return answer;
    }

    private static boolean lectureRegistry(){
        boolean answer = false;
        
        
        return true;
    }
    
}
