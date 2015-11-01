
package crawler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import util.ExceptionHelper;
import util.UrlHelper;

/**
 * Density Analysis class that performs the word density of a given input 
 * and outputs the most common topics or words based upon a word density 
 * calculation. 
 * 
 * @author    Vivek sridhar
 * @version   1.0
 * @created   10/31/2015
 * 
 */


public class DensityAnalysis {

//Set containing unique words to ignore
 List<String> stopwords;
 
 //To store the word frequency
 Map<String, Integer> words;
 
 //Constructor
 public DensityAnalysis(){
   stopwords = new ArrayList<String>();
   words = new HashMap<String, Integer>();
 }
 
  /*
   * Generates the set of stopwords
   */
  private void getStopwords() {
    
    //Get stopwords file
    Scanner sc; 
    
    try {
      sc = new Scanner(getClass().getResourceAsStream("/crawler/StopWords.txt"));
      
      //Add unique stopwords to an Hashset
      while (sc.hasNext())
        stopwords.add(sc.next());
      
    //To avoid resource leak
      sc.close();
      
    }catch(Exception err){
      System.out.println(ExceptionHelper.ERROR_MSG + "\n");
    }
    
  }//End of Method
  
  /*
   * Main method to perform the word density calculation
   */
  public void removeStopWords(List<String> inputList){
    
    int indx=0,listSize=0,linkedWords=0;
    
    //Get the size of List
    listSize=inputList.size();
    
    //To get the stop words
    getStopwords();
    
    //Count the number of frequency for each word in the List 
    //which are not stopwords and put them in Map
    while (indx < listSize) {
      String word = inputList.get(indx);
      
      if(!stopwords.contains(word.toLowerCase()))
      {
        Integer count = words.get(word);
        words.put(word, (count == null) ? 1 : count + 1);
      }
      indx++;
    }
    
    //To determine the words that are linked to each other.
    for (Map.Entry<String,Integer> entry : words.entrySet()) {
      if(entry.getValue()>1)
      {
        linkedWords++;
      }
    }
    
    System.out.println("There are "+ listSize +" words in this page.");
    System.out.println("There are "+ words.size() +" non-stop words in this page.");
    System.out.println("Of those "+ words.size() +" words "+ linkedWords +" words are linked ones.");
    
    //Sort the words list based on fequency
    sortWords(words);
    
    //Call the function the keyword density analysis
    densityAnalysis(words,words.size());
    
  }//End of Method
  
  
  private void sortWords(Map<String,Integer> newList){
  
    List<Map.Entry<String, Integer>> entries =
            new ArrayList<Map.Entry<String, Integer>>(newList.entrySet());
    
    Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
      
      public int compare(Map.Entry<String, Integer> ent1, Map.Entry<String, Integer> ent2) {
            return (ent1.getValue() == ent2.getValue())
                    ? ent1.getKey().compareTo(ent2.getKey())
                    : ent2.getValue() - ent1.getValue();
        }
    });
  }//End of Method
  
  
  private void densityAnalysis(Map<String,Integer> newWords,float mapSize){
    
    if(mapSize>0) {
    
      Iterator<Map.Entry<String, Integer>> iterator = newWords.entrySet().iterator();
     
      float wordDensity=0.0f;
        
      System.out.println("\nMost Common Keywords based on the Threshold value are...");
      
      while (iterator.hasNext()) {
      
        Map.Entry<String, Integer> currWord = (Map.Entry<String, Integer>) iterator.next();
        
        wordDensity = (currWord.getValue() / mapSize) * 100;
        
        if (wordDensity >= UrlHelper.MIN_THRESHOLD) {
          if(!currWord.getKey().matches("\\d+"))
          {
            System.out.println(currWord.getKey());  
          }
        }
      }//End of While
    }//End of IF 
  }//End of method
  
}//End of class
