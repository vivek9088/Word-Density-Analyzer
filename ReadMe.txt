WORD DENSITY ANALYZER
---------------------

This application is designed and developed to perform "Word Density Analysis" 
for a given URL and output the list of most common keywords that best describes
the given URL. This is an java based application that can be executed via the
command prompt with the help of jar file or run via the Eclipse IDE.

----------------
FOLDER STRUCTURE 
----------------

-src
 -crawler
     -WordDensity.java     ->   Main class of the application which takes URL number as input and calls the parseUrl().
     -WebCrawler.java      ->   This class crawls the given URL and retrieves all the words and removes all special characters in each word. This class is used to start the keyword
											density analysis for the retrieved keyword list.                       
     -DensityAnalysis.java ->   This class contains methods that get stopwords and remove them from the keyword list. It also performs the calculation of keyword density calculation and 
											prints the output.
     -StopWords.txt        ->   A text file that contains the list of stop words.
      
 -util
    -ExceptionHelper.java  ->   This class contains all the error message constants.
    -UrlHelper.java        ->   This class contains all the user-friendly message constants.

    
Note: The density calculation is performed based on the Threshold values given manually which are not optimal. The weights can be experimented with to achieve good results in all these cases.
 
------------
REQUIREMENTS
------------

-Java SDK 1.7
-JRE 1.6+


-----
USAGE
-----

To run the jar file, type the below command in the command prompt:

Syntax:  java -jar Assignment.jar <your_url>

Example: java -jar Assignment.jar "http://www.cnn.com/2013/06/10/politics/edward-snowden-profile/"
 

---------------
DEPENDENCIES:
---------------

Ensure that the "StopWords.txt" file is in the crawler package always.

JSoup library is required to be installed. 

 
---------------
BASIC ALGORITHM
---------------

The general algorithm which I followed for keyword extraction is as follows:

1) Get the HTML page as text using one of the available java webparser library.
2) Get the results as a Document and store in a ArrayList by normalizing them one by one.
3) Read the stopwords from a file and remove all the stop words from the ArrayList.
4) Store the non-stop words in a Map with its key and value. Key being the word itself
and the value being the "Frequency". 
5) Perform the word density calculation based on a Threshold value.
6) Print the number of keywords as required.








