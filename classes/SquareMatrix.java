package classes;

/**
 * Interface for a square matrix.
 */
public interface SquareMatrix {
  public void populateMatrix(int rowNumber, int colNumber, float ratingValue);
  public void printMatrix();
  public int getNumRows();
}
