
package crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import util.ExceptionHelper;
import util.UrlHelper;


/**
 * This class crawls the given webpages and extracts all words
 * and pass it to DenistyAnalysis class to get the most common 
 * topics or words. 
 *  
 * @author    Vivek sridhar
 * @version   1.0
 * @created   10/31/2015
 * 
 */

public class WebCrawler {
  
  String Url;
  
 //Constructor to initialze class variables
  public WebCrawler(){
   Url="";
 }
  
  //Get the URL
  private String getUrl() {
    return Url;
  }

  //Set the url passed by user
  public void setUrl(String url) {
      Url = url;
  }

   
  /**
   * Below method is used to crawl the webpage for results
   */
  public void parseURL()
  {
    //To store all the words returned  at a time as got from htmltext
    List<String> getWords= new ArrayList<String>();
    
    String pureWord=""; 
    
    //Use the default system proxy server to connect to the site
    System.setProperty("java.net.useSystemProxies", "true");
    
    Document doc = null;
    
    try {
      
      //Crawl the website
      doc = Jsoup.connect(getUrl()).timeout(UrlHelper.CONNECTION_TIMEOUT).get();
 
      // Scanner for parsing each individual string in document
      Scanner sc = new Scanner(doc.text());
      
      System.out.println(UrlHelper.TOPICS + "\n");
      
      //Get the HTML content as words and trim special characters
      //and store them in a array.
      while (sc.hasNext()) {
        String temp = sc.next();
        pureWord = removeSpecialChars(temp);
        getWords.add(pureWord);
      } 
      
      //To avoid resource leak
      sc.close();
      
      //Start the density analysis
      startAnalysis(getWords);
      
    } catch (IOException e) {
      System.out.println(ExceptionHelper.CONNECTION_TIMEOUT + "\n");
    }catch (Exception e) {
      System.out.println(ExceptionHelper.ERROR_MSG + "\n");
    }//End of Try-Catch
    
  }//End of method

  
  /*
   * Returns a string with no special characters in it.
   */
  private static String removeSpecialChars(String textToTrim) {
    
    // Trim extra whitespace
    String pureText = textToTrim.trim();
    
    // Remove plurals
    pureText = pureText.replace("'s", "");
    
    // Remove other symbols
    pureText = pureText.replace("&", "");
    pureText = pureText.replace("“", "");
    pureText = pureText.replace("”", "");
    pureText = pureText.replace("", "");
    pureText = pureText.replace("?", "");
    pureText = pureText.replace("©", "");
    pureText = pureText.replace(":", "");
    pureText = pureText.replace("!", "");
    pureText = pureText.replace("\"", "");
    pureText = pureText.replace(",", "");
    pureText = pureText.replace(":’", "");
    pureText = pureText.replace("’›", "");
    pureText = pureText.replace("›", "");
    pureText = pureText.replace("(", "");
    pureText = pureText.replace(")", "");
    pureText = pureText.replace(">", "");
    pureText = pureText.replace("<", "");
    pureText = pureText.replace("+", "");
    pureText = pureText.replace("$", "");
    pureText = pureText.replace("_", "");
    pureText = pureText.replace("%", "");
    
    // Remove apostrophes
    if (pureText.startsWith("'") || pureText.endsWith("'") || pureText.endsWith("’"))
      pureText = pureText.replace("'", "");
    
    // Remove periods
    if (pureText.endsWith(".") && !checkUpper(pureText.substring(pureText.length()-2, pureText.length()-1)))
      pureText = pureText.replace(".", "");
    
    return pureText;
    
  }//End of method
  
  /*
   * Returns true for upper case character
  */ 
  private static boolean checkUpper(String temp)
  {
      for (char c : temp.toCharArray()) {
          if(!Character.isUpperCase(c))
              return false;
      }
      return true;
  }//End of method
  
  
  //Calls to start the word density analysis 
  private static void startAnalysis(List<String> wordList){
    
    DensityAnalysis density = new DensityAnalysis();
    
    density.removeStopWords(wordList);
    
  }//End of method
}//End of class
