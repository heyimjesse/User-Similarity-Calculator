package test;

import static org.junit.Assert.*;
import org.junit.Test;
import classes.UserMovieMatrix;

public class TestCasesUserMovieMatrix {
  UserMovieMatrix umMatrix;
  
  @Test
  public void testMatrixVariableRows() {
    umMatrix = new UserMovieMatrix(3, 3);
    assertEquals(3, umMatrix.getNumRows());
  }
  
  @Test
  public void testMatrixVariableColumns() {
    umMatrix = new UserMovieMatrix(3, 7);
    assertEquals(7, umMatrix.getNumCols());
  }
  
  @Test
  public void testPopulateMatrix() {
    umMatrix = new UserMovieMatrix(2, 2);
    umMatrix.populateMatrix(0, 0, 1);
    umMatrix.populateMatrix(0, 1, 2);
    umMatrix.populateMatrix(1, 0, 3);
    umMatrix.populateMatrix(1, 1, 4);
    
    assertEquals(1, umMatrix.getRating(0, 0));
    assertEquals(2, umMatrix.getRating(0, 1));
    assertEquals(3, umMatrix.getRating(1, 0));
    assertEquals(4, umMatrix.getRating(1, 1));
  }
  
  @Test
  public void testSimilarityScoreSameScore() {
    umMatrix = new UserMovieMatrix(2, 2);
    assertEquals(1, umMatrix.calculateSimilarityScore(1, 2), 0.1);
  }
  
  @Test
  public void testSimilarityScoreDifferentScore() {
    umMatrix = new UserMovieMatrix(2, 2);
    umMatrix.populateMatrix(0, 0, 0);
    umMatrix.populateMatrix(0, 1, 0);
    umMatrix.populateMatrix(1, 0, 5);
    umMatrix.populateMatrix(1, 1, 5);
    assertEquals(0.1, umMatrix.calculateSimilarityScore(1, 2), 0.1);
  }
}
