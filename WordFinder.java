// Name: 
// USC NetID: 
// CS 455 PA4
// Spring 2025

import java.util.*;

public class WordFinder{

    public static void main(String[] args){
        AnagramDictionary anagramDict = new AnagramDictionary();
        Scanner in = new Scanner(System.in); 
        System.out.println("Enter a dictionary file name: ");

        String dictionaryFileName = in.nextLine().trime();
        if (dictionaryFileName.equals("")){
            dictionaryFileName = "sowpods.txt";
        }
        try{
            dictionary = new AnagramDictionary(dictionaryFileName);
        }
        catch(FileNotFoundException e){
            System.out.println("ERROR: Dictionary file \"" + fileName + "\" does not exist.");
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
                break;
            }
            Rack rack = new Rack(rackInput);

        }
        //classname class = readInput(in);
        //validateinput(class);
        //validate is static private method here
        
        //after validation
        if (in == '.'){
            exit 0;
        }
        System.out.printf("We can make %d words from "%s"",count, in );
        System.out.println("All of the words with their scores (sorted by score):");
        //System.out.println(//score : scrablled word);
    }
}