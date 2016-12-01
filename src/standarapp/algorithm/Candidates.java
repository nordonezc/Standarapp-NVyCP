/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package standarapp.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Niki
 */
public class Candidates {
    private ArrayList<String> words;
    
    public Candidates(String[] word){
        words = new ArrayList<>();
        for(String s:word){
            words.add(s);
        }
    }
    
    
    
}
