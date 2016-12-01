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
    private static Hashtable<String,ArrayList<String>> contenedor;
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
        contenedor=new Hashtable<String,ArrayList<String>>();
        
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
            for(Row row: sheet){
                for(Cell cell:row){
                    /*
                        
                        if(cell.getColumnIndex() == 21 || cell.getColumnIndex() == 22){
                        String indice = "" + cell.getStringCellValue().substring(0, 1);
                        if(contenedor.containsKey(indice)){
                            contenedor.get(indice).add(cell.getStringCellValue());
                        }
                        else{
                            ArrayList<String> nuevo = new ArrayList<>();
                            nuevo.add(cell.getStringCellValue());
                            contenedor.put(indice, nuevo);
                        }
                    }*/
                    
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getColumnIndex() + ":" + cell.getNumericCellValue() + "\t\t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getColumnIndex() + ":" +  cell.getStringCellValue() + "\t\t");
                            break;
                    }
                }
                System.out.println("");
            }
            
        try {
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        //Levenstein distance applied to two random words
        String s1 = "Test";
        String s2 = "Testo";
        int lvd = FuzzySearch.tokenSetRatio(s1, s2);
        System.out.println("Levenstein: " + lvd);
    }

}
