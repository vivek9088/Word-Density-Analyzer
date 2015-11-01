
package crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import util.ExceptionHelper;
import util.UrlHelper;

/**
 * URL Parser class that scrapes the given URL and outputs the common topics 
 * or words based upon the word density calculation.
 * 
 * @author    Vivek sridhar
 * @version   1.0
 * @created   10/31/2015
 * 
 */

public class WordDensity {

  /**
   * @param args - Gets the URL from User
   */
  public static void main(String[] args) {
   
    WebCrawler crawler = new WebCrawler();
    
    //Check for empty arguments
    if(args.length<1) {
      System.out.println(ExceptionHelper.ARGUMENTS_INVALID + "\n");
      return;
    }
    else{
      try{  
          
          //Check whether the given URL is valid or not by connecting to the site
          //String uri = new UrlHelper().fetchUrl(args[0]);
        
          URL url = new URL(args[0]);
          URLConnection conn = url.openConnection();
          conn.connect();
         
          //Connection is estblished
          System.out.println(UrlHelper.SUCCESS + "\n");
          
          //Set the given URL to start crawling
          crawler.setUrl(args[0]);
          
          //Started website crawling
          System.out.println(UrlHelper.STARTED + "\n");
          
          //Call the crawl function parse the URL
          crawler.parseURL();
          
          //Retrieved the common topics
          System.out.println("\n" + UrlHelper.FINISHED);
          
      } catch (MalformedURLException e) {
          System.out.println(ExceptionHelper.URL_INVALID + "\n");
      } catch (IOException e) {
        System.out.println(ExceptionHelper.CONNECTION_LOST + "\n");
      }//End of Try-Catch
    }//End of Else
  }//End of Main
}//End of Class
