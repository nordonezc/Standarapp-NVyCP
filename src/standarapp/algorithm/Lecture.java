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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell;

/**
 *
 * @author Niki Ordoñez
 */
public class Lecture{

    /**
     * 
     * @param nameFile
     * @return true if last is X, false in otherwise
     */
    public static boolean determineExtensionFile(String nameFile){
        char lastPosition = nameFile.charAt(nameFile.length()-1);
        boolean answer = false;
        if(lastPosition == 'x')
            return true;
        
        return answer;
    }
    
    public void fixFile(String nameFile){
        int temp[] = {};
        if(determineExtensionFile(nameFile))
            fixXLSX(nameFile, nameFile, 0, temp);
        else
            fixXLS(nameFile, nameFile, 0, temp);
    }
    
    public void fixFile(String nameFile, int col[]){
        if(determineExtensionFile(nameFile))
            fixXLSX(nameFile, nameFile, 0,col);
        else
            fixXLS(nameFile, nameFile, 0, col);
    }
    
    public void fixFile(String nameFile, int sheet, int col[]){
        if(determineExtensionFile(nameFile))
            fixXLSX(nameFile, nameFile, sheet,col);
        else
            fixXLS(nameFile, nameFile, sheet, col);
    }

    public void fixFile(String nameFile, String nameFileExit){
        int temp[] = {};
        if(determineExtensionFile(nameFile))
            fixXLSX(nameFile, nameFileExit, 0, temp);
        else
            fixXLS(nameFile, nameFileExit, 0, temp);
    }
    
    public void fixFile(String nameFile, String nameFileExit, int col[]){
        if(determineExtensionFile(nameFile))
            fixXLSX(nameFile, nameFileExit, 0,col);
        else
            fixXLS(nameFile, nameFileExit, 0, col);
    }
    
    public void fixFile(String nameFile, String nameFileExit, int sheet, int col[]) throws Exception, IOException{
        if(determineExtensionFile(nameFile))
            fixXLSX(nameFile, nameFileExit, sheet,col);
        else
            fixXLS(nameFile, nameFileExit, sheet, col);
    }
    
    private void fixXLS(String nameIn, String nameOut, int nameSheet, int columnas[]){
        HSSFWorkbook xwb = lectureXLS(nameIn);
        HSSFSheet xsheet = xwb.getSheetAt(0);
        HSSFSheet xsheet_WRITE = xwb.createSheet();
        for (Row row : xsheet) {
            xsheet_WRITE.createRow(row.getRowNum());
            for (Cell cell : row) {
                try{
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            String contenido = cell.getStringCellValue();
                            if (columnas.length==0 ||containsInColumns(columnas, cell.getColumnIndex()))
                                contenido = fixWords(contenido);
                            xsheet_WRITE.getRow(row.getRowNum()).createCell(cell.getColumnIndex()).setCellValue(contenido);
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            double contenido_Numerico = cell.getNumericCellValue();
                            xsheet_WRITE.getRow(row.getRowNum()).createCell(cell.getColumnIndex()).setCellValue(contenido_Numerico);
                            break;
                        /*default:
                            System.err.print(cell + "\t\t");
                            xsheet_WRITE.getRow(row.getRowNum()).createCell(cell.getColumnIndex()).setCTCell((CTCell) cell);
                            break;*/
                    }
                } catch(Exception e){}
            }
        }
        try (FileOutputStream outputStream = new FileOutputStream(nameOut)) {
            xwb.write(outputStream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadRegistry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    private void fixXLSX(String nameIn, String nameOut, int sheet, int columnas[]){
        XSSFWorkbook xwb = lectureXLSX(nameIn);
        XSSFSheet xsheet = xwb.getSheetAt(sheet);
        
        for (Row row : xsheet) {
            //xsheet.createRow(row.getRowNum());
            for (Cell cell : row) {
                try{
                if (columnas.length==0 ||containsInColumns(columnas, cell.getColumnIndex())){
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            String contenido = cell.getStringCellValue();
                            if(!cell.getStringCellValue().equals("")){
                                contenido = fixWords(contenido);
                                xsheet.getRow(row.getRowNum()).getCell(cell.getColumnIndex()).setCellValue(contenido);
                            }
                            else{
                                xsheet.getRow(row.getRowNum()).getCell(cell.getColumnIndex()).setCellValue("");
                            }
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            double contenido_Numerico = cell.getNumericCellValue();
                            xsheet.getRow(row.getRowNum()).createCell(cell.getColumnIndex()).setCellValue(contenido_Numerico);
                            break;
                        default:
                            System.err.print(cell + "\t\t");
                            xsheet.getRow(row.getRowNum()).createCell(cell.getColumnIndex()).setCTCell((CTCell) cell);
                            break;
                    }
                }
                } catch(Exception e){}
            }
        }
        
        try (FileOutputStream outputStream = new FileOutputStream(nameOut)) {
            xwb.write(outputStream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadRegistry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean containsInColumns(int columnas[], int num) {
        boolean answer = false;
        for (int i = 0; i < columnas.length; i++) {
            if (columnas[i] == num) {
                answer = true;
                break;
            }
        }
        return answer;
    }

    public static XSSFSheet lectureXLSX(String nameFile, int page) {
        FileInputStream file;
        XSSFWorkbook excelFile = new XSSFWorkbook();
        XSSFSheet xsheet = excelFile.createSheet();
        //Reading the file which contains registries
        //Lectura del archivo xls de registros
        try {
            file = new FileInputStream(new File(nameFile));
            excelFile = new XSSFWorkbook(file);
            xsheet = excelFile.getSheetAt(page);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadRegistry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }

        return xsheet;
    }

    public static XSSFWorkbook lectureXLSX(String nameFile) {
        FileInputStream file;
        XSSFWorkbook excelFile = new XSSFWorkbook();
        //Reading the file which contains registries
        //Lectura del archivo xls de registros
        try {
            file = new FileInputStream(new File(nameFile));
            excelFile = new XSSFWorkbook(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadRegistry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }

        return excelFile;
    }

    public static HSSFSheet lectureXLS(String nameFile, int page) {
        FileInputStream file;
        HSSFWorkbook excelFile = new HSSFWorkbook();
        HSSFSheet hsheet = excelFile.createSheet();
        //Reading the file which contains registries
        //Lectura del archivo xls de registros
        try {
            file = new FileInputStream(new File(nameFile));
            excelFile = new HSSFWorkbook(file);
            hsheet = excelFile.getSheetAt(page);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadRegistry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hsheet;
    }

    public static HSSFWorkbook lectureXLS(String nameFile) {
        FileInputStream file;
        HSSFWorkbook excelFile = new HSSFWorkbook();
        //Reading the file which contains registries
        //Lectura del archivo xls de registros
        try {
            file = new FileInputStream(new File(nameFile));
            excelFile = new HSSFWorkbook(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadRegistry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadRegistry.class.getName()).log(Level.SEVERE, null, ex);
        }

        return excelFile;
    }

    public static String fixWords(String message) {
        String info = message;
        //Cambios caracteres especiales en veredas
        info = info.replace("├â┬ü", "A");
        info = info.replace("├âÔÇ░", "E");
        info = info.replace("├â┬ì", "I");
        info = info.replace("├âÔÇ£", "O");
        info = info.replace("├â┼í", "U");
        info = info.replace("├Â┼Ô", "U");
        
        info = info.replace("├âÔÇÿ", "N");
        
        //Centros poblados
        info = info.replace("├Ü", "A");
        info = info.replace("├ü", "A");
        
        info = info.replace("├ë", "E");
        
        info = info.replace("├ì", "I");
        
        
        info = info.replace("├ô", "O");
        info = info.replace("├Ô", "O");
        
        info = info.replace("├Ô", "O");
        info = info.replace("├Ô", "O");
        
        
        info = info.replace("├æ", "N");
        info = info.replace("├Æ", "N");

        
        //Errores en centros poblados
        info = info.replace("ßÜ", "A");
        info = info.replace("ßü", "a");
        //Solo para antes de pasar centros poblados
        //info = info.replace("Ú", "e");
        info = info.replace("Ý", "i");
        info = info.replace("¾", "o");
        info = info.replace("š", "u");
        info = info.replace("³", "u");

        info = info.replace("┴", "A");
        info = info.replace("╔", "E");
        info = info.replace("╠", "I");
        info = info.replace("Ë", "O");
        info = info.replace("┌", "U");

        info = info.replace("ß", "a");
        info = info.replace("═", "I");
        info = info.replace("Ê", "O");
        info = info.replace("▄", "U");

        info = info.replace("±", "N");
        info = info.replace("Ð", "N");
        info = info.replace("·", "u");

        //Tildes normales
        info = info.replace("Á", "A");
        info = info.replace("É", "E");
        info = info.replace("Í", "I");
        info = info.replace("Ó", "O");
        info = info.replace("Ú", "U");
        info = info.replace("Ñ", "N");
        
        //Tildes invertidas
        info = info.replace("À", "A");
        info = info.replace("È", "E");
        info = info.replace("Ì", "I");
        info = info.replace("Ò", "O");
        info = info.replace("Ù", "U");

        info = info.toUpperCase();
        return info;
    }

}
