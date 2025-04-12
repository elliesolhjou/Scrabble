// Name: 
// USC NetID: 
// CS 455 PA4
// Spring 2025

import java.util.*;
import java.io.*;


//add class comments here
public class WordFinder{

    public static void main(String[] args){

        AnagramDictionary anagramDict;
        Scanner in = new Scanner(System.in); 
        System.out.println("Enter a dictionary file name: ");

        String dictionaryFileName = in.nextLine().trim();
        if (dictionaryFileName.equals("")){
            dictionaryFileName = "sowpods.txt";
        }
        try{
            anagramDict = new AnagramDictionary(dictionaryFileName);
        }
        catch(FileNotFoundException e){
            System.out.println("ERROR: Dictionary file \"" + dictionaryFileName + "\" does not exist.");
            System.out.println("Exiting program.");
            return;
        }
        catch(IllegalDictionaryException e){
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("Exiting program.");
            return;
        }

        ScoreTable scoreTable = new ScoreTable();

        System.out.println("Type . to quit.");
        boolean done = false;
        while(!done){
            System.out.print("Rack? ");
            String rackInput = in.nextLine().trim();
            if (rackInput.equals(".")){
                System.exit(0);
            }
            Rack rack = new Rack(rackInput);
            ArrayList<String> subsets = rack.getSubsets();

            // For each subset, get anagrams from dictionary
            Set<String> seenWord = new HashSet<>();
            ArrayList<WordScore> wordList = new ArrayList<>();
            for (String subset : subsets){
                ArrayList<String> anagram = anagramDict.getAnagramsOf(subset);
                for (String word : anagram){
                    if(!seenWord.contains(word)){
                        seenWord.add(word);
                        int score = scoreTable.getScore(word);
                        wordList.add(new WordScore(word, score));
                    }
                }
            }
            // Sort list by score
            Collections.sort(wordList);

            
            System.out.println("We can make " + wordList.size() + " words from \"" + rackInput + "\"");
            System.out.println("All of the words with their scores (sorted by score):");

            for (WordScore ws : wordList){
                System.out.println(ws.getScore() + ": " + ws.getWord());
            }

            done = true;
        }


        in.close();
    }
}



//add method class variant rep comments
class WordScore implements Comparable<WordScore>{
    //add method class variant rep comments
    private String word;
    private int score;

    public WordScore(String word, int score){
        this.word = word;
        this.score = score;
    }
      //add method comments
    public String getWord() {
        return word;
    }
      //add method comments
    public int getScore() {
        return score;
    }

    @Override
    //add method comments
    public int compareTo(WordScore other) {
      // Sort by score descending
        if (this.score != other.score) {
            return other.score - this.score;
        }
      // If scores are equal, sort alphabetically
        return this.word.compareTo(other.word);
    }
}