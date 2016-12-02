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

    private static String nameExcel1, nameExcel2;
    private static Hashtable<String, ArrayList<String>> bar_ver, dir_res, nmun_resi, ndep_notif;
    private static ArrayList<ArrayList<String>> registry; //21, 22, 97, 99 each 4 its a different registry
    private static FileInputStream file;
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        //Name of excel Files
        nameExcel1 = "Book1.xlsx";
        nameExcel2 = "C:\\Users\\Niki\\Downloads\\LEISHMANIASI.xlsx";

        //Files where will be located the names of each cell
        registry = new ArrayList<>();

        bar_ver = new Hashtable<String, ArrayList<String>>();
        dir_res = new Hashtable<String, ArrayList<String>>();
        nmun_resi = new Hashtable<String, ArrayList<String>>();
        ndep_notif = new Hashtable<String, ArrayList<String>>();

        try {
            file = new FileInputStream(new File(nameExcel2));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //Create Workbook instance holding reference to .xlsx file
            workbook = new XSSFWorkbook(file);
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Get first/desired sheet from the workbook
        sheet = workbook.getSheetAt(0);

        //Iterate through each rows one by one
        for (Row row : sheet) {
            ArrayList<String> cellsWI = new ArrayList<>();
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        //cellsWI = cells with information
                        if(!cell.getStringCellValue().contains("1") && !cell.getStringCellValue().contains("2") && !cell.getStringCellValue().contains("0") && !cell.getStringCellValue().contains("3") && !cell.getStringCellValue().contains("4") && !cell.getStringCellValue().contains("5") && !cell.getStringCellValue().contains("6") && !cell.getStringCellValue().contains("7") && !cell.getStringCellValue().contains("8") && !cell.getStringCellValue().contains("9")){
                        if (cell.getColumnIndex() == 21 || cell.getColumnIndex() == 22 || cell.getColumnIndex() == 97 || cell.getColumnIndex() == 99) {
                            //System.out.print(cell.getColumnIndex() + ":" + cell.getStringCellValue() + "\t\t");
                            String info = cell.getStringCellValue().toUpperCase();
                            //Eliminacion de palabras sobrantes y tildes
                            info = info.replace(" ", "");
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
                            info = info.replace("(", "");
                            info = info.replace(")", "");
                            info = info.replace("#", "");
                            cellsWI.add(info);
                            System.out.print(cell.getColumnIndex() + ":" + info + "\t\t");
                        }}
                        break;
                }
            }
            System.out.println("");
            if(!cellsWI.isEmpty())
                registry.add(cellsWI);
        }

        try {
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        System.out.println("Cantidad de registros: " + registry.size());
        //System.out.println("Cantidad de registros: " + registry.get(8).size());
        //Levenstein distance applied to two random words
        String s1 = "Test";
        String s2 = "Testo";
        int lvd = FuzzySearch.tokenSetRatio(s1, s2);
        System.out.println("Levenstein: " + lvd);
    }

}
