// Name: Fatemeh Ellie Solhjou
// USC NetID: 1424729265
// CS 455 PA4
// Spring 2025

import java.util.*;

/**

 * Encapsulates the logic for scoring words based on Scrabble letter values.
 * Supports both uppercase and lowercase letters. Non-letter characters are
 * silently ignored during scoring.
 *
 * This class is used by the WordFinder program to compute the score of
 * valid words generated from a Scrabble rack.
 *
 * Letter values:
 * - 1 point: A, E, I, O, U, L, N, S, T, R
 * - 2 points: D, G
 * - 3 points: B, C, M, P
 * - 4 points: F, H, V, W, Y
 * - 5 points: K
 * - 8 points: J, X
 * - 10 points: Q, Z

 */
public class ScoreTable{
    // Representation Invariant:
    // - charScoreMap != null
    // - All keys in charScoreMap are single alphabetic characters (both cases)
    // - All values are valid Scrabble point values (1, 2, 3, 4, 5, 8, 10)
    private Map<Character, Integer> charScoreMap;

    /**
    * Constructs a ScoreTable with predefined Scrabble letter scores.
    * Initializes both uppercase and lowercase mappings for each letter.
    */
    public ScoreTable(){
        charScoreMap = new HashMap<>();

        addScores(1, "AEIOULNSTR");
        addScores(2, "DG");
        addScores(3, "BCMP");
        addScores(4, "FHVWY");
        addScores(5, "K");
        addScores(8, "JX");
        addScores(10, "QZ");

        assert isValid();
    }
    
    /**
    * Helper method to map a group of letters to a score value.
    * Populates both uppercase and lowercase variants.
    * @param score the Scrabble point value to assign
    * @param letters a string of uppercase letters to assign that score to
    */
    private void addScores(int score, String letters) {
        for (int i = 0; i < letters.length(); i++) {
            char ch = letters.charAt(i);
            charScoreMap.put(ch, score);  // Uppercase
            charScoreMap.put(Character.toLowerCase(ch), score);  // Lowercase
        }
    }
    /**
    * Computes the Scrabble score of the given word.
    * Ignores any characters that are not letters present in the score table.
    * @param word the word to compute the score for (case-insensitive)
    * @return the total score of the word based on individual letter values
    */
    public int getScore(String word) {
        int total = 0;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (charScoreMap.containsKey(ch)) {
                total += charScoreMap.get(ch);
            }
            // If the character isn't a letter (like @), skip it.
        }

        return total;
    }
    /**
    * Checks whether the ScoreTable satisfies the representation invariant.
    * @return true if charScoreMap is non-null and contains only valid Scrabble entries
    */
    private boolean isValid() {
        if (charScoreMap == null) return false;

        for (Map.Entry<Character, Integer> entry : charScoreMap.entrySet()) {
            char c = entry.getKey();
            int score = entry.getValue();

            if (!Character.isLetter(c)) return false;
            if (!(score == 1 || score == 2 || score == 3 || score == 4 ||
                score == 5 || score == 8 || score == 10)) {
                return false;
            }
        }

        return true;
    }
}
