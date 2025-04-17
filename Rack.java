// Name: Fatemeh Ellie Solhjou
// USC NetID: Solhjouk
// CS 455 PA4
// Spring 2025

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
   Represents a player's Scrabble tile rack as a multiset of characters.
   Provides functionality to compute all possible subsets (including repeated characters)
   that can be formed from the rack. These subsets are used to find all valid words
   a player can make during a Scrabble game.
   This class encapsulates both the input and processing logic needed to 
   transform the tile rack into a structured frequency map and generate 
   combinations using a recursive subset generator.
   The rack is case-sensitive and assumes all characters match the dictionary format.
 */

public class Rack {
   /** 
      Representation Invariant:
      - originalRack != null
      - originalRack contains only non-whitespace characters
      - originalRack may contain duplicate letters
      - originalRack is case-sensitive (but must match dictionary case)
   */

   private String originalRack;

   /**
      Constructs an empty Rack with no tiles.
      Initializes the originalRack to an empty string.
    */
   public Rack(){
      originalRack= "";
   }

   /**
      Constructs a Rack with the specified tile letters.
      Stores the original rack string to be processed later.
      @param input the string of tiles entered for the rack.
    */
   public Rack(String input){
      originalRack = input;
   }

   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
      each subset is represented as a String that can have repeated characters in it.
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();

      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }

   /**
      Generates all subsets of the rack represented by this Rack object.
      This method processes the original rack string to compute the unique characters
      and their multiplicities, then delegates to the recursive allSubsets helper method.      
      Each subset is a String that may include repeated characters, and the
      full list includes all possible combinations that can be formed from
      the multiset of characters in the original rack.
      PRE: originalRack != null && originalRack contains only non-whitespace characters
      @return an ArrayList of all subsets of the rack, including the empty string.
   */
   public ArrayList<String> getSubsets(){
      Map<Character, Integer> charFrequency = new TreeMap<>();
      
      // Count Freq of each character
      for (int i = 0; i < originalRack.length(); i++){
         char character = originalRack.charAt(i);
         if (!charFrequency.containsKey(character)){
            charFrequency.put(character, 1);
         }
         else{
            charFrequency.put(character, charFrequency.get(character)+1);
         }
      }

      // Building unique and corresponding mult[]
      String unique = "";
      int[] mult = new int[charFrequency.size()];
      int index = 0;

      for (Map.Entry<Character, Integer> entry: charFrequency.entrySet()){
         unique += entry.getKey();
         mult[index] = entry.getValue();
         index++;
      }
      return allSubsets(unique, mult, 0);
   } 
   
}
