/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package standarapp.algorithm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.xdrop.fuzzywuzzy.FuzzySearch;
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
    private static String nameExcel1, nameExcel2;
    private static Hashtable<String, ArrayList<String>> dptoMncp; // relacion de departamento con municipio
    private static Hashtable<String, ArrayList<String>> mncpVyCP; // relacion de centro poblado y vereda con municipio
    private static Hashtable<Integer, String> vycp_codigo; //codigo de cada vereda con centro poblado
    private static ArrayList<ArrayList<String>> registry; //21, 22, 97, 99 each 4 its a different registry
    private static FileInputStream file;
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Logica de la aplicacion

        //Name of excel Files, initialize variables
        //Nombre de los archivos de excel, inicializar variables
        nameExcel1 = "C:\\Users\\Niki\\Downloads\\VyCPcorregido.xlsx";
        nameExcel2 = "C:\\Users\\Niki\\Downloads\\LEISHMANIASI.xlsx";
        registry = new ArrayList<>();
        dptoMncp = new Hashtable<String, ArrayList<String>>();
        mncpVyCP = new Hashtable<String, ArrayList<String>>();
        vycp_codigo = new Hashtable<Integer, String>();

        //Reading the file which contains registries
        //Lectura del archivo xls de registros
        try { 
            file = new FileInputStream(new File(nameExcel2)); 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Create Workbook instance holding reference to .xlsx file
        //Creando una instancia haciendo referencia al archivo xls ubicado en file
        try {
            workbook = new XSSFWorkbook(file);
        } 
        catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Obtiene la primera oja del archivo de excel.
        //Get first/desired sheet from the workbook
        sheet = workbook.getSheetAt(0);

        //Iteración de cada una de las filas y celdas del archivo cargado
        //Iterate through each rows one by one
        for (Row row : sheet) {
            //cellsWI = cells with important information
            //celdas donde esta la información que se compara con levenstein
            ArrayList<String> cellsWI = new ArrayList<>();
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        if (!cell.getStringCellValue().contains("1") && !cell.getStringCellValue().contains("2") && !cell.getStringCellValue().contains("0") && !cell.getStringCellValue().contains("3") && !cell.getStringCellValue().contains("4") && !cell.getStringCellValue().contains("5") && !cell.getStringCellValue().contains("6") && !cell.getStringCellValue().contains("7") && !cell.getStringCellValue().contains("8") && !cell.getStringCellValue().contains("9")) {
                            if (cell.getColumnIndex() == 21 || cell.getColumnIndex() == 22 || cell.getColumnIndex() == 97 || cell.getColumnIndex() == 99) {
                                String info = cell.getStringCellValue().toUpperCase();
                                
                                //Eliminacion de palabras sobrantes y tildes
                                info = fixWords(info);
                                cellsWI.add(info);
                                System.out.print(cell.getColumnIndex() + ":" + info + "\t\t");
                            }
                        }
                        break;
                }
            }
            System.out.println("");
            //Se añade a la lista de registros leidos correctamente.
            if (!cellsWI.isEmpty()) registry.add(cellsWI);
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
        try {
            file = new FileInputStream(new File(nameExcel1));
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
             * If Sentence where specifies that:
             * First row doesnt have information
             * Fourth row is different to zero
             * 
             * Sentencia condicional donde especifica que haga analice la fila solo si:
             * La fila no es la primera del archivo .xlsx (Es decir el titulo de cada columna
             * La celda numero 4 es diferente de cero 
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
                            System.out.print(cell.getColumnIndex() + ":" + cell.getStringCellValue() + "\t\t");
                            break;
                    }
                }

                departamento = fixWords(departamento);
                municipio = fixWords(municipio);
                vycp = fixWords(vycp);

                //Add departamento if it isn't exist
                //Añade deparmenteo a la tabla hash si no existe
                //Add municipio if departamento doesnt contained it
                //Añade municipio si no se encuentra ubicado en el deparamento
                if (!dptoMncp.containsKey(departamento)) {
                    ArrayList<String> primerMunicipio = new ArrayList<>();
                    primerMunicipio.add(municipio);
                    dptoMncp.put(departamento, primerMunicipio);
                } else if (!dptoMncp.get(departamento).contains(municipio)) {
                    dptoMncp.get(departamento).add(municipio);
                }
                
                //Add municipio if it isn't exist
                //Añade municipio a la tabla hash si no existe
                //Add vycp if municipio doesnt contained it
                //Añade vycp si no se encuentra ubicado en el municipio
                if (!mncpVyCP.containsKey(municipio)) {
                    ArrayList<String> primerGeoEnMunicipio = new ArrayList<>();
                    primerGeoEnMunicipio.add(vycp);
                    mncpVyCP.put(municipio, primerGeoEnMunicipio);
                } else if (!mncpVyCP.get(municipio).contains(vycp)) {
                    mncpVyCP.get(municipio).add(vycp);
                }

                //Add vycp_codigo if it isn't exist
                //Añade el municipio y su codigo si aun no se ha agregado
                if (!vycp_codigo.containsKey(codigo)) {
                    vycp_codigo.put(codigo, vycp);
                }

                System.out.println("");
            }
        }
        
        //Close file readed
        //Se cierra el archivo leido
        try {
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Cantidad de registros: " + registry.size());
        for (String key : dptoMncp.keySet())
            System.out.println("Departamento: " + key);
        for (String key : mncpVyCP.keySet())
            System.out.println("Municipio: " + key);
        
        System.out.println("Numero de municipios: " + mncpVyCP.size());
        System.out.println("Numero de departamentos: " + dptoMncp.size());
        System.out.println("Numero de veredas y CP: " + vycp_codigo.size());

        //Levenstein distance applied to two random words
        String s1 = "Test";
        String s2 = "Testo";
        int lvd = FuzzySearch.tokenSetRatio(s1, s2);
        System.out.println("Levenstein: " + lvd);
    }

    /**
     * Recieved word and returns the same without special characters
     * @param message
     * @return info
     */
    private static String fixWords(String message) {
        String info = message;
        //info = info.replace(" ", "");
        info = info.replace("Á", "");
        info = info.replace("É", "");
        info = info.replace("Í", "");
        info = info.replace("Ó", "");
        info = info.replace("Ú", "");
        info = info.replace("Ñ", "N");
        info = info.replace("VEREDA", "");
        info = info.replace("CORREGIMIENTO", "");
        info = info.replace("FINCA", "");
        info = info.replace("CALLE", "");
        info = info.replace("-", "");
        info = info.replace("°", "");
        info = info.replace("BARRIO", "");
        //info = info.replace("(", "");
        //info = info.replace(")", "");
        info = info.replace("#", "");

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

}
