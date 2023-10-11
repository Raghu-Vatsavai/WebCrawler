// importing relevant libraries
// java.io for BufferedReader and InputStreamReader
// java.net for URL and URLConnection
import java.io.*;
import java.net.*;

public class WebCrawler {
  // throwing IOException to prevent the user from giving invalid input
  public static void main(String[] args) throws IOException {

    // user inputs prompt for username
    System.out.println("Enter email username...");
    // using buffer object to read user input
    InputStreamReader userInputEmail = new InputStreamReader(System.in);
    BufferedReader buffer = new BufferedReader(userInputEmail);
    String emailUsername = buffer.readLine();

    // pageURL object store and concatenate the strings into 1 URL
    URL pageURL = new URL("https://www.ecs.soton.ac.uk/people/" + emailUsername); 
    
    // opens a connection to the URL
    URLConnection connection = pageURL.openConnection();
    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

    // boolean value to check whether user has been found
    boolean userFound = false;
    
    String line;
  
    while ((line = reader.readLine()) != null) {
      // checks for name
      if (line.indexOf("og:title") != -1) {
        int startIndex = line.lastIndexOf("=");
        int finishIndex = line.lastIndexOf("\"");
        System.out.println("NAME: " + line.substring(startIndex+2, finishIndex));
        userFound = true;
      }
      // checks for description of person
      else if (line.indexOf("og:description") != -1) {
        int startIndex = line.lastIndexOf("=");
        int finishIndex = line.lastIndexOf("\"");
        System.out.println("DESCRIPTION: " + line.substring(startIndex+2, finishIndex));
      }      
    }
    // closes the BufferedReader object
    reader.close();

    // checks whether user was found or not
    if (userFound == false) {
      System.out.println("User not found...");
    }
  }
}