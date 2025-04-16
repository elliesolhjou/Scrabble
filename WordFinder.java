// Name: Fatemeh Ellie Solhjou
// USC NetID: 1424729265
// CS 455 PA4
// Spring 2025

import java.util.*;
import java.io.*;

/**
 * This program helps players find all valid Scrabble words that can be formed from a given rack of tiles.
 *
 * 1. The program first loads a Scrabble dictionary from a file. If no file is provided, it uses "sowpods.txt" by default.
 * 2. The user is prompted to enter a rack (any sequence of letters). Typing "." will exit the program.
 * 3. For each rack:
 *    - All subsets of the rack are generated.
 *    - All valid dictionary words (anagrams) formed from those subsets are found.
 *    - Each word is scored based on Scrabble letter values.
 *    - The words are displayed, sorted by descending score and then alphabetically.
 *
 * If the dictionary file is missing or contains duplicate words, the program prints an error message and exits.
 *
 * Input/Output is done through the console.
 */
public class WordFinder {

    /**
    * Entry point for the WordFinder program.
    * Loads the dictionary and starts the user command loop.
    *
    * @param args optional command-line argument specifying the dictionary file name
    */
    public static void main(String[] args) {
        AnagramDictionary anagramDict = loadDictionary(args);
        if (anagramDict == null) return;

        ScoreTable scoreTable = new ScoreTable();
        runCommandLoop(anagramDict, scoreTable);
    }

    /**
    * Loads the Scrabble dictionary from the provided file name.
    * If no file name is given, defaults to "sowpods.txt".
    * Prints an error and returns null if the file is missing or contains duplicates.
    *
    * @param args the command-line arguments passed to the program
    * @return an initialized AnagramDictionary if successful; null otherwise
    */
    private static AnagramDictionary loadDictionary(String[] args) {
        String dictionaryFileName = "sowpods.txt";
        if (args.length > 0) {
            dictionaryFileName = args[0];
        }

        try {
            return new AnagramDictionary(dictionaryFileName);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Dictionary file \"" + dictionaryFileName + "\" does not exist.");
        } catch (IllegalDictionaryException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("Exiting program.");
        return null;
    }

    /**
    * Continuously prompts the user for racks, processes them,
    * and prints valid words until the user enters "." or EOF is reached.
    *
    * @param anagramDict the initialized dictionary used to look up valid words
    * @param scoreTable the scoring table used to compute word scores
    */
    private static void runCommandLoop(AnagramDictionary anagramDict, ScoreTable scoreTable) {
        Scanner in = new Scanner(System.in);
        System.out.println("Type . to quit.");

        while (true) {
            System.out.print("Rack? ");
            if (!in.hasNextLine()) break;

            String rackInput = in.nextLine().trim();
            if (rackInput.equals(".")) break;

            processRack(rackInput, anagramDict, scoreTable);
        }
    }

    /**
    * Processes a single rack input from the user:
    * - Computes all subsets of the rack
    * - Finds valid dictionary words from those subsets
    * - Scores and sorts the words
    * - Displays the results in the required format
    *
    * @param rackInput the rack string entered by the user
    * @param anagramDict the dictionary to look up anagrams
    * @param scoreTable the scoring system for words
    */
    private static void processRack(String rackInput, AnagramDictionary anagramDict, ScoreTable scoreTable) {
        Rack rack = new Rack(rackInput);
        ArrayList<String> subsets = rack.getSubsets();

        Set<String> seenWord = new HashSet<>();
        ArrayList<WordScore> wordList = new ArrayList<>();

        for (String subset : subsets) {
            List<String> anagram = anagramDict.getAnagramsOf(subset);
            if (anagram != null) {
                for (String word : anagram) {
                if (seenWord.add(word)) {
                    int score = scoreTable.getScore(word);
                    wordList.add(new WordScore(word, score));
                }
                }
            }
        }

        Collections.sort(wordList);

        System.out.println("We can make " + wordList.size() + " words from \"" + rackInput + "\"");
        if (!wordList.isEmpty()) {
            System.out.println("All of the words with their scores (sorted by score):");
        }
        for (WordScore ws : wordList) {
            System.out.println(ws.getScore() + ": " + ws.getWord());
        }
    }
}

/**
 * WordScore class
 *
 * Stores a word and its corresponding Scrabble score.
 * Supports comparison by score in descending order and alphabetical order as a tiebreaker.
 * Used for sorting word results in the WordFinder program.
 *
 * Representation Invariant:
 * - word != null
 * - score >= 0
 */
class WordScore implements Comparable<WordScore> {

    private String word;
    private int score;

    /**
        * Constructs a WordScore object with a given word and score.
        *
        * @param word the dictionary word
        * @param score the Scrabble score for that word
        */
    public WordScore(String word, int score) {
        this.word = word;
        this.score = score;
    }

    /**
    * Returns the word stored in this WordScore.
    * @return the word string
    */
    public String getWord() {
        return word;
    }

    /**
        * Returns the score associated with this WordScore.
        * @return the Scrabble score
        */
    public int getScore() {
        return score;
    }

    /**
    * Compares this WordScore with another for sorting.
    * Sorts in descending order of score. Breaks ties alphabetically.
    *
    * @param other the other WordScore to compare to
    * @return a negative number if this < other, 0 if equal, a positive number if this > other
    */
    @Override
    public int compareTo(WordScore other) {
        if (this.score != other.score) {
            return other.score - this.score;
        }
        return this.word.compareTo(other.word);
    }
}
