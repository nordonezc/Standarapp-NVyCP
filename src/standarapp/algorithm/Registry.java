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
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Niki
 */
public class Registry {
    
    private static ArrayList<String[]> registry; //21, 22, 97, 99 each 4 its a different registry
    private static FileInputStream file;
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static HSSFWorkbook hworkbook;
    private static HSSFSheet hsheet;
    private static String nameFile;
    private static int repeated;
    private static int TOF;

    public Registry(String nameRegistry, int typeOfFile) throws FileNotFoundException, IOException{
        registry = new ArrayList<>();
        file = new FileInputStream(new File(nameFile));
        nameFile = nameRegistry;
        TOF = typeOfFile;
        switch(typeOfFile){
            case 1:
                hworkbook = new HSSFWorkbook(file);
                break;
            case 2:
                break;
            default:
                break;
                
        }
    }

    public static String getNameFile() {
        return nameFile;
    }

    public static void setNameFile(String nameFile) {
        Registry.nameFile = nameFile;
    }

    public static ArrayList<String[]> getRegistry() {
        return registry;
    }
    
    public static String[] getRegistryAt(int numero_registro){
        return registry.get(numero_registro);
    }
    
    public static String getRegistryAt(int numero_registro, int numero_campo){
        return registry.get(numero_registro)[numero_campo];
    }

    public static XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public static XSSFSheet getSheet() {
        return sheet;
    }

    public static HSSFWorkbook getHworkbook() {
        return hworkbook;
    }

    public static HSSFSheet getHsheet() {
        return hsheet;
    }

    public static void addRegistry(String[] regis) {
        registry.add(regis);
    }

    public static void setFile(FileInputStream file) {
        Registry.file = file;
    }

    public static void setWorkbook(XSSFWorkbook workbook) {
        Registry.workbook = workbook;
    }

    public static void setSheet(XSSFSheet sheet) {
        Registry.sheet = sheet;
    }

    public static void setHworkbook(HSSFWorkbook hworkbook) {
        Registry.hworkbook = hworkbook;
    }

    public static void setHsheet(HSSFSheet hsheet) {
        Registry.hsheet = hsheet;
    }

    public static void setRepeated(int repeated) {
        Registry.repeated = repeated;
    }
}
