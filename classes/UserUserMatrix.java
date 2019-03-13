package classes;

import java.text.DecimalFormat;

/**
 * The UserUserMatrix class is responsible for keeping track of a user user
 * matrix as well as performing operations related to it.
 */
public class UserUserMatrix implements SquareMatrix {
  private float userUserMatrix[][];
  private int numRows;
  
  /**
   * Constructor
   * @param userMovieMatrix The user movie matrix from which we will construct
   * our user user matrix.
   */
  public UserUserMatrix(UserMovieMatrix userMovieMatrix) {
    numRows = userMovieMatrix.getNumRows();
    userUserMatrix = new float[numRows][numRows];
    
    for (int x=1;x<=this.numRows;x++) {
      for (int y=x;y<=this.numRows;y++) {
        float simScore = userMovieMatrix.calculateSimilarityScore(x, y);
        this.populateMatrix(x-1, y-1, simScore);
        this.populateMatrix(y-1, x-1, simScore);
      }
    }
  }
  
  /**
   * The similarity number is inserted into the specified location in the user
   * user matrix.
   * 
   * @param rowNumber The row number in the userMovieMatrix.
   * @param colNumber The column number in the userMovieMatrix.
   * @param ratingValue The similarity number to be inserted.
   */
  public void populateMatrix(int rowNumber, int colNumber, float ratingValue) {
    userUserMatrix[rowNumber][colNumber] = ratingValue;
  }
  
  /**
   * This method goes through the userUserMatrix and find the lowest
   * similarity score. No redundant checks are performs.
   */
  public float findMinSimilarityScore() {
    // we can safely set the first item in the matrix as the starting minScore
    float minScore = this.userUserMatrix[0][0];
    for (int user1=0;user1<this.numRows;user1++) {
      // this never checks a user's similarity with him/herself
      for (int user2=user1+1;user2<this.numRows;user2++) {
        if (userUserMatrix[user1][user2] < minScore) {
          minScore = userUserMatrix[user1][user2];
        }
      }
    }
    return minScore;
  }
  
  /**
   * This method goes through the userUserMatrix and find the highest
   * similarity score. No redundant checks are performs.
   */
  public float findMaxSimilarityScore() {
    // we can safely set 0 as the starting maxScore
    float maxScore = 0f;
    for (int user1=0;user1<this.numRows;user1++) {
      // this never checks a user's similarity with him/herself
      for (int user2=user1+1;user2<this.numRows;user2++) {
        if (userUserMatrix[user1][user2] > maxScore) {
          maxScore = userUserMatrix[user1][user2];
        }
      }
    }
    return maxScore;
  }
  
  /**
   * Prints out the similarity scores of the userUserMatrix, with each row and
   * column representing each/single user and the cell position (i,j)
   * representing the similarity score between user i and user j.
   */
  public void printMatrix() {
    System.out.println("userUserMatrix is:");
    // we use DecimalFormat to force each number to have 4 decimals
    DecimalFormat df = new DecimalFormat("0.0000");
    // every loop will print a row of the matrix
    for (int row=0;row<this.numRows;row++) {
      // add each item on current row to pString
      String pString = "[";
      for (int col=0;col<this.numRows;col++) {
        String temp = df.format(this.userUserMatrix[row][col]);
        pString = pString + temp + ", ";
      }
      pString = pString.substring(0, pString.length()-2);
      pString = pString + "]";
      System.out.println(pString);
    }
  }
  
  /**
   * Get the score at the specified location.
   * 
   * @param x The x coordinate of the matrix.
   * @param y The y coordinate of the matrix.
   * @return The score at the x, y location.
   */
  public float getScore(int x, int y) {
    return this.userUserMatrix[x][y];
  }
  
  /**
   * Getter for row variable.
   */
  public int getNumRows() {
    return this.numRows;
  }
}
