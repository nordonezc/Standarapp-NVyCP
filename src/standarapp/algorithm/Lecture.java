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

/**
 *
 * @author Niki Ordoñez
 */
public abstract class Lecture {

    private static boolean determineExtensionFile(String nameFile){
        char lastPosition = nameFile.charAt(nameFile.length());
        boolean answer = false;
        if(lastPosition == 'x')
            return true;
        
        return answer;
    }
    
    public static void fixFile(String nameFile){
        int temp[] = {};
        if(determineExtensionFile(nameFile))
            fixXLSX(nameFile, nameFile, "", temp);
        else
            fixXLS(nameFile, nameFile, "", temp);
    }
    
    public static void fixFile(String nameFile, int col[]){
        if(determineExtensionFile(nameFile))
            fixXLSXwithoutChangeName(nameFile, nameFile, col);
        else
            fixXLS(nameFile, nameFile, "fixed Sheet", col);
    }
    
    public static void fixFile(String nameFile, String nameFileExit){
        int temp[] = {};
        if(determineExtensionFile(nameFile))
            fixXLSX(nameFile, nameFileExit, "", temp);
        else
            fixXLS(nameFile, nameFile, "fixed Sheet", temp);
    }
    
    public static void fixFile(String nameFile, String nameFileExit, int col[]){
        if(determineExtensionFile(nameFile))
            fixXLSXwithoutChangeName(nameFile, nameFileExit, col);
        else
            fixXLS(nameFile, nameFile, "fixed Sheet", col);
    }
    
    private static void fixXLS(String nameIn, String nameOut, String nameSheet, int columnas[]){
        //String nameExcel = "C:\\Users\\Niki\\Downloads\\Original.xlsx";
        HSSFWorkbook xwb = lectureXLS(nameIn);
        HSSFSheet xsheet = xwb.getSheetAt(0);
        HSSFSheet xsheet_WRITE;
        if(nameSheet.equals("")){
            xsheet_WRITE = xwb.getSheetAt(0);
        }
            
        else{
            xsheet_WRITE = xwb.createSheet("Fixed Sheet");
        }
        try (FileOutputStream outputStream = new FileOutputStream(nameOut)) {
            xwb.write(outputStream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void fixXLSX(String nameIn, String nameOut, String nameSheet, int columnas[]){
        //String nameExcel = "C:\\Users\\Niki\\Downloads\\Original.xlsx";
        XSSFWorkbook xwb = lectureXLSX(nameIn);
        XSSFSheet xsheet = xwb.getSheetAt(0);
        XSSFSheet xsheet_WRITE = xwb.createSheet();
       
        for (Row row : xsheet) {
            xsheet_WRITE.createRow(row.getRowNum());
            for (Cell cell : row) {
                //System.out.println("");
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            //System.out.print(cell + "\t\t");
                            String contenido = cell.getStringCellValue();
                            if (columnas.length==0 ||containsInColumns(columnas, cell.getColumnIndex()))
                                contenido = fixWords(contenido);
                            xsheet_WRITE.getRow(row.getRowNum()).createCell(cell.getColumnIndex()).setCellValue(contenido);
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            //System.out.print(cell + "\t\t");
                            double contenido_Numerico = cell.getNumericCellValue();
                            xsheet_WRITE.getRow(row.getRowNum()).createCell(cell.getColumnIndex()).setCellValue(contenido_Numerico);
                            break;
                        /*default:
                            System.err.print(cell + "\t\t");
                            xsheet_WRITE.getRow(row.getRowNum()).createCell(cell.getColumnIndex()).setCTCell((CTCell) cell);
                            break;*/
                    }
            }
        }

        if(nameSheet.equals("")){
            //xwb.removeSheetAt(xsheet.getSheetName());
        }
        try (FileOutputStream outputStream = new FileOutputStream(nameOut)) {
            xwb.write(outputStream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    private static void fixXLSXwithoutChangeName(String nameIn, String nameOut, int columnas[]){
        XSSFWorkbook xwb = lectureXLSX(nameIn);
        XSSFSheet xsheet = xwb.getSheetAt(0);
        String temporal;
        
        for (Row row : xsheet) {
            temporal = "";
            //xsheet.createRow(row.getRowNum());
            for (Cell cell : row) {
                //System.out.println("");
                if (columnas.length==0 ||containsInColumns(columnas, cell.getColumnIndex())){
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            //System.out.print(cell + "\t\t");
                            String contenido = cell.getStringCellValue();
                            /*if(cell.getColumnIndex()==1)
                                temporal = contenido;*/
                            
                            if(!cell.getStringCellValue().equals("")){
                                contenido = fixWords(contenido);
                                xsheet.getRow(row.getRowNum()).getCell(cell.getColumnIndex()).setCellValue(contenido);
                            }
                            else{
                                xsheet.getRow(row.getRowNum()).getCell(cell.getColumnIndex()).setCellValue("");
                            }
                            break;
                        /*case Cell.CELL_TYPE_NUMERIC:
                            //System.out.print(cell + "\t\t");
                            double contenido_Numerico = cell.getNumericCellValue();
                            xsheet.getRow(row.getRowNum()).createCell(cell.getColumnIndex()).setCellValue(contenido_Numerico);
                            break;
                        default:
                            System.err.print(cell + "\t\t");
                            xsheet_WRITE.getRow(row.getRowNum()).createCell(cell.getColumnIndex()).setCTCell((CTCell) cell);
                            break;*/
                    }
                }
            }
            
            /*
            if(!temporal.equals("") && row.getRowNum()>0){
                temporal = fixWords(temporal);
                xsheet.getRow(row.getRowNum()).getCell(7).setCellValue(temporal);
            }*/
            
        }
        
        try (FileOutputStream outputStream = new FileOutputStream(nameOut)) {
            xwb.write(outputStream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //Logica de la aplicacion
        int col[] = {1,2,3,4,5,6,7};
        fixFile("C:\\Users\\Niki\\Downloads\\prueba.xlsx", "C:\\Users\\Niki\\Downloads\\pruebaCorregido.xlsx", col, true);
    }*/

    private static boolean containsInColumns(int columnas[], int num) {
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
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StandarappNVyCP.class.getName()).log(Level.SEVERE, null, ex);
        }

        return excelFile;
    }

    public static String fixWords(String message) {
        String info = message;
        /*Caracteres especiales
        info = info.replace(",", "");
        info = info.replace("-", "");
        info = info.replace("\"", "");
        info = info.replace("\n", "");
        info = info.replace("'", "");
        */

        //Cambios caracteres especiales en veredas
        info = info.replace("├â┬ü", "A");
        info = info.replace("├âÔÇ░", "E");
        info = info.replace("├â┬ì", "I");
        info = info.replace("├âÔÇ£", "O");
        info = info.replace("├â┼í", "U");
        info = info.replace("├Â┼Ô", "U");
        
        info = info.replace("├âÔÇÿ", "N");
        
        /* U CON DIERESIS
        info = info.replace("ÃƒÅ“", "U");
        
        Cambio caracteres csv lectura_ok
        info = info.replace("Ãƒâ€˜", "N");
        info = info.replace("ÃƒÂ", "A");
        info = info.replace("Ãƒâ€°", "E");
        info = info.replace("ÃƒÂ", "I");
        info = info.replace("Ãƒâ€œ", "O");
        */
        
        info = info.replace("├Ü", "A");
        info = info.replace("├Ô", "O");

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

        info = info.replace("Ñ", "N");
        
        
        
        
        //Tildes raras
        info = info.replace("Á", "A");
        info = info.replace("É", "E");
        info = info.replace("Í", "I");
        info = info.replace("Ú", "U");
        
        info = info.toUpperCase();
        return info;
    }
}
