/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standarapp.algorithm;

import me.xdrop.diffutils.DiffUtils;
import me.xdrop.fuzzywuzzy.FuzzySearch;

/**
 * Credits
 * seatgeek 
 * Adam Cohen 
 * David Necas (python-Levenshtein) 
 * Mikko Ohtamaa (python-Levenshtein) 
 * Antti Haapala (python-Levenshtein)
 * 
 * This project is an implementation of levenstein distance development
 * by people called before. 
 * 
 * @author Niki Ordoñez
 */
public class StandarappNVyCP {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String s1 = "Test";
        String s2 = "Testo";
        int lvd = FuzzySearch.tokenSetRatio(s1, s2);
        System.out.println("Levenstein: " + lvd);
    }
    
}
