// Name: 
// USC NetID: 
// CS 455 PA4
// Spring 2025


import java.util.*;

//class descriptiomn
public class ScoreTable{
// repr variant
    private Map<Character, Integer> charScoreMap;

    public ScoreTable(){
        charScoreMap = new HashMap<>();

        addScores(1, "AEIOULNSTR");
        addScores(2, "DG");
        addScores(3, "BCMP");
        addScores(4, "FHVWY");
        addScores(5, "K");
        addScores(8, "JX");
        addScores(10, "QZ");
    }
    
    //add method commenst
    // Helper method to add scores for a group of letters
    private void addScores(int score, String letters) {
        for (int i = 0; i < letters.length(); i++) {
            char ch = letters.charAt(i);
            charScoreMap.put(ch, score);  // Uppercase
            charScoreMap.put(Character.toLowerCase(ch), score);  // Lowercase
        }
    }

    //add method commenst
    // Computes the score for a word (case-insensitive)
    public int getScore(String word) {
        int total = 0;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (charScoreMap.containsKey(ch)) {
                total += charScoreMap.get(ch);
            }
            // If the character isn't a letter (like @), skip it silently
        }

        return total;
    }
}
