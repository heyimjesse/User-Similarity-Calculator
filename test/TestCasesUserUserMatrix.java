package test;

import static org.junit.Assert.*;
import org.junit.Test;
import classes.UserMovieMatrix;
import classes.UserUserMatrix;

public class TestCasesUserUserMatrix {
  UserMovieMatrix umMatrix = new UserMovieMatrix(2, 2);
  UserUserMatrix uuMatrix;
  
  @Test
  public void testMatrixVariableRows() {
    uuMatrix = new UserUserMatrix(umMatrix);
    assertEquals(2, uuMatrix.getNumRows());
  }
  
  @Test
  public void testMatrixAllSameValues() {
    uuMatrix = new UserUserMatrix(umMatrix);
    assertEquals(1, uuMatrix.getScore(0, 0), 0.1);
    assertEquals(1, uuMatrix.getScore(0, 1), 0.1);
  }

  @Test
  public void testMatrixDifferentValues() {
    umMatrix.populateMatrix(0, 0, 1);
    umMatrix.populateMatrix(0, 1, 2);
    umMatrix.populateMatrix(1, 0, 3);
    umMatrix.populateMatrix(1, 1, 4);
    uuMatrix = new UserUserMatrix(umMatrix);
    assertEquals(0.26, uuMatrix.getScore(0, 1), 0.1);
  }
  
  @Test
  public void testMatrixDifferentValuesReversedUsers() {
    umMatrix.populateMatrix(0, 0, 1);
    umMatrix.populateMatrix(0, 1, 2);
    umMatrix.populateMatrix(1, 0, 3);
    umMatrix.populateMatrix(1, 1, 4);
    uuMatrix = new UserUserMatrix(umMatrix);
    assertEquals(uuMatrix.getScore(0, 1), uuMatrix.getScore(1, 0), 0.1);
  }
  
  @Test
  public void testMatrixFindSim() {
    umMatrix.populateMatrix(0, 0, 1);
    umMatrix.populateMatrix(0, 1, 2);
    umMatrix.populateMatrix(1, 0, 3);
    umMatrix.populateMatrix(1, 1, 4);
    uuMatrix = new UserUserMatrix(umMatrix);
    assertEquals(0.26, uuMatrix.findMaxSimilarityScore(), 0.1);
  }
  
  @Test
  public void testMatrixFindDisimilar() {
    umMatrix.populateMatrix(0, 0, 1);
    umMatrix.populateMatrix(0, 1, 2);
    umMatrix.populateMatrix(1, 0, 3);
    umMatrix.populateMatrix(1, 1, 4);
    uuMatrix = new UserUserMatrix(umMatrix);
    assertEquals(0.26, uuMatrix.findMaxSimilarityScore(), 0.1);
  }
}
