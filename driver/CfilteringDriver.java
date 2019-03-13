// **********************************************************
// Assignment0:
// UTORID: zhang568
// UT Student #: 1000125170
// Author: Jesse Zhang
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences. In this semester
// we will select any three of your assignments from total of 5 and run it
// for plagiarism check. 
// *********************************************************
package driver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import classes.DissimilarPairs;
import classes.SimilarPairs;
import classes.UserMovieMatrix;
import classes.UserUserMatrix;

public class CfilteringDriver {
  /**
   * Reads user movie ratings from a text file, calculates similarity scores
   * and prints a score matrix.
   */
  public static void main(String[] args) {
    try {
      // open file to read
      String fileName;
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the name of input file? ");
      fileName = in.nextLine();
      FileInputStream fStream = new FileInputStream(fileName);
      BufferedReader br = new BufferedReader(new InputStreamReader(fStream));

      // Read dimensions: number of users and number of movies
      int numberOfUsers = Integer.parseInt(br.readLine());
      int numberOfMovies = Integer.parseInt(br.readLine());

      
      // Create a user movie matrix
      UserMovieMatrix userMovieMatrix = new UserMovieMatrix(numberOfUsers, 
          numberOfMovies);
      
      
      // this is a blankline being read
      br.readLine();

      // read each line of movie ratings and populate the
      // userMovieMatrix
      String row;
      // keeps track of the row number
      int m = 0;
      while ((row = br.readLine()) != null) {
        // allRatings is a list of all String numbers on one row
        String allRatings[] = row.split(" ");
        for (int n=0;n<allRatings.length;n++) {
          // make the String number into an integer
          int tempInt = Integer.parseInt(allRatings[n]);
          // populate userMovieMatrix
          userMovieMatrix.populateMatrix(m, n, tempInt);   
        }
        m++;
      }
      // close the file
      fStream.close();
      // close the scanner to prevent resource leak
      in.close();

      System.out.println();
      System.out.println();
      
      // create a user user matrix
      UserUserMatrix userUserMatrix = new UserUserMatrix(userMovieMatrix);
      
      // Print out the userUserMatrix
      userUserMatrix.printMatrix();
      System.out.println();
      System.out.println();
      // print the most similar pairs of users
      SimilarPairs.findAndprintMostSimilarPairOfUsers(userUserMatrix);
      System.out.println();
      System.out.println();
      // print the least similar pairs of users
      DissimilarPairs.findAndprintMostDissimilarPairOfUsers(userUserMatrix);
      System.out.println();
      System.out.println();
   
    } catch (FileNotFoundException e) {
      System.err.println("Do you have the input file in the root folder "
          + "of your project?");
      System.err.print(e.getMessage());
    } catch (IOException e) {
      System.err.print(e.getMessage());
    }
  }
}
