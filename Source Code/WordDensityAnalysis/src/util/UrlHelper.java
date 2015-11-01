package util;
/**
 * This Class consists of all error message contants
 * @author Vivek Sridhar
 * @since 10/31/2015
 *
 */

public class UrlHelper {

  String[] urls = new String[3];
  
  public static final String SUCCESS = "Connection is established to the website...";
  public static final String FINISHED = "Finished fetching common keywords...";
  public static final String STARTED = "Started Crawling into WebSite...";
  public static final String TOPICS = "Fetching Common Keywords...";
  public static final int CONNECTION_TIMEOUT = 5000;
  public static final int MIN_THRESHOLD = 1;
  public static final int MAX_THRESHOLD = 10;
  
  
  //This method is used to return the url to main class
  public String fetchUrl(String index){
    urls[0] = "http://www.amazon.com/Cuisinart-CPT-122-Compact-2-Slice-Toaster/dp/B009GQ034C/ref=sr_1_1?s=kitchen&ie=UTF8&qid=1431620315&sr=1-1&keywords=toaster";
    urls[1] = "http://blog.rei.com/camp/how-to-introduce-your-indoorsy-friend-to-the-outdoors/";
    urls[2] = "http://www.cnn.com/2013/06/10/politics/edward-snowden-profile/";
    
    return urls[Integer.parseInt(index)];
  }
  
}
