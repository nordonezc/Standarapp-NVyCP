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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        String nameExcel1 = "Book1.xlsx";
        String nameExcel2 = "C:\\Users\\Niki\\Downloads\\LEISHMANIASI.xlsx";

        /**
         * Try-catch block where is readed the .xlsx file
         */
        try {
            FileInputStream file = new FileInputStream(new File(nameExcel2));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            for(Row row: sheet){
                for(Cell cell:row){
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t\t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + "\t\t");
                            break;
                    }
                }
                System.out.println("");
            }
            file.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        String s1 = "Test";
        String s2 = "Testo";
        int lvd = FuzzySearch.tokenSetRatio(s1, s2);
        System.out.println("Levenstein: " + lvd);
    }

}
