package classes;

import java.util.Arrays;

/**
 * The UserMovieMatrix class is responsible for keeping track of a user
 * movie matrix object as well as performing operations related to it.
 */
public class UserMovieMatrix implements SquareMatrix{
  private int userMovieMatrix[][];
  private int numRows;
  private int numCols;
  
  /**
   * Constructs an object which contains a matrix of size
   * numberOfUsers x numberOfMovies, which will contain movie ratings
   * from users.
   * 
   * @param numberOfUsers Determines size of matrix variables.
   * @param numberOfMovies Determines size of matrix variables.
   */
  public UserMovieMatrix(int numberOfUsers, int numberOfMovies) {
    userMovieMatrix = new int[numberOfUsers][numberOfMovies];
    numRows = numberOfUsers;
    numCols = numberOfMovies;
  }
  
  /**
   * The rating number is inserted into the specified location in the user
   * movie matrix.
   * 
   * @param rowNumber The row number in the userMovieMatrix.
   * @param colNumber The column number in the userMovieMatrix.
   * @param ratingValue The rating number to be inserted.
   */
  public void populateMatrix(int rowNumber, int colNumber, float ratingValue) {
    userMovieMatrix[rowNumber][colNumber] = (int) ratingValue;
  }
  
  /**
   * Calculates the "distance" between two users through the formula defined
   * on the hand-out.
   * 
   * @param user1 A user being compared. (is an integer between 3 and 9)
   * @param user2 A user being compared. (is an integer between 3 and 9)
   */
  private float distance(int user1, int user2) {
    // the row index corresponding to each user is the user number - 1
    int user1Row = user1 - 1;
    int user2Row = user2 - 1;
    // keep track of the total, which we will eventually take the root of
    float retValue = 0f;
    for (int col=0;col<this.numCols;col++) {
      int user1Rating = userMovieMatrix[user1Row][col];
      int user2Rating = userMovieMatrix[user2Row][col];
      // add the square of the difference to the total
      retValue = (float) (retValue + Math.pow((user2Rating - user1Rating), 2));
    }
    retValue = (float) Math.sqrt(retValue);
    return retValue;
  }
  
  /**
   * Determines how similar each pair of users is based on their ratings. This
   * similarity value is represented with with a float value between 0 and 1,
   * where 1 is perfect/identical similarity. Stores these values in the
   * userUserMatrix.
   * 
   * @param user1 A user being compared. (is an integer between 3 and 9)
   * @param user2 A user being compared. (is an integer between 3 and 9)
   */
  public float calculateSimilarityScore(int user1, int user2) {
    float simScore = (float) (1.0/(1.0 + this.distance(user1, user2)));
    simScore = (float) (Math.round(simScore * 10000.0)/10000.0);
    return simScore;
  }
  
  /**
   * Getter for row variable.
   * 
   * @return Number of rows.
   */
  public int getNumRows() {
    return this.numRows;
  }
  
  /**
   * Getter for column variable.
   * 
   * @return Number of columns.
   */
  public int getNumCols() {
    return this.numCols;
  }
  
  /**
   * Prints the matrix for illustration purposes.
   */
  public void printMatrix() {
    for (int row=0;row<this.numRows;row++) {
      System.out.println(Arrays.toString(this.userMovieMatrix[row]));
    }
  }
  
  /**
   * Get the rating at the specified location.
   * 
   * @param x The x coordinate of the matrix.
   * @param y The y coordinate of the matrix.
   * @return The rating at the x, y location.
   */
  public int getRating(int x, int y) {
    return this.userMovieMatrix[x][y];
  }
}
