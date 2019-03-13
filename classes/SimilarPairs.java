package classes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The SimilarPairs class is responsible for calculating the most similar pair
 * of users.
 */
public class SimilarPairs {
  /**
   * This function finds and prints the most similar pair of users in the
   * userUserMatrix.
   */
  public static void findAndprintMostSimilarPairOfUsers(UserUserMatrix 
      uuMatrix) {
    float maxScore = uuMatrix.findMaxSimilarityScore();
    // We'll store pairs with the same maxScore here
    List<String> retPairs = new ArrayList<String>();
    // No redundant checks are performed
    for (int user1=0;user1<uuMatrix.getNumRows();user1++) {
      for (int user2=user1+1;user2<uuMatrix.getNumRows();user2++) {
        if (uuMatrix.getScore(user1, user2)==maxScore) {
          String pair = String.format("User%s and User%s,", user1+1, user2+1);
          retPairs.add(pair);
        }
      }
    }
    // remove the comma from the last pair in the list of pairs
    String noComma = (String) retPairs.get(retPairs.size()-1);
    noComma = noComma.substring(0, noComma.length()-1);
    retPairs.set(retPairs.size()-1, noComma);

    System.out.println("The most similar pairs of users from above"
        + " userUserMatrix are:");
    // loop through list and print each
    for (int i=0;i<retPairs.size();i++) {
      System.out.println(retPairs.get(i));
    }
    DecimalFormat df = new DecimalFormat("0.0000");
    // makes sure we print with 4 decimal spaces
    System.out.println("with a similarity score of " + df.format(maxScore));
  }
}
