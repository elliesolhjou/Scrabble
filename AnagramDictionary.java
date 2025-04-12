// Name: 
// USC NetID: 
// CS 455 PA4
// Spring 2025

import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;



/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {
   // Representation Invariant:
   // - anagramMap != null
   // - No null keys or values in anagramMap
   // - All keys in anagramMap are sorted lowercase strings (canonical forms of words)
   // - All values are non-empty ArrayLists of real dictionary words (2 or more characters)
   // - wordSet != null
   // - wordSet contains no duplicates and only words of length >= 2

   private Map<String, ArrayList<String>> anagramMap;
   private Set<String> wordSet;

   // creat empty constructor?
   //put constructor comment
   public AnagramDictionary(){
      anagramMap = new HashMap<String, ArrayList<String>>();
      wordSet = new HashSet<String>();
   }

   
   /**
   Create an anagram dictionary from the list of words given in the file
   indicated by fileName.  
   @param fileName  the name of the file to read from
   @throws FileNotFoundException  if the file is not found
   @throws IllegalDictionaryException  if the dictionary has any duplicate words
   */
   public AnagramDictionary(String fileName) throws FileNotFoundException, IllegalDictionaryException{
      anagramMap = new HashMap<>();
      wordSet = new HashSet<>();

      File file = new File(fileName);
      Scanner in = new Scanner(file);

      while (in.hasNext()){
         String word = in.next();
         if (word.length() == 0){
            continue;
         }
         // Check for duplicates
         if (wordSet.contains(word)){
            throw new IllegalDictionaryException("ERROR: Illegal dictionary: dictionary file has a duplicate word:" + word);
         }
         wordSet.add(word);

         // Sort word in canonical form
         char[] chars = new char[word.length()];
         for(int i=0; i< word.length(); i++){
            chars[i] = word.charAt(i);
         }
         Arrays.sort(chars);
         // Convert to string
         String canonical = new String(chars);  

         // Add to anagramMap
         if(!anagramMap.containsKey(canonical)){
            ArrayList<String> keyList = new ArrayList<>();
            anagramMap.put(canonical, keyList);

            anagramMap.get(canonical).add(word);
         }
      }
      in.close();
   }


   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String string) {
      char[] chars = new char[string.length()];
      for (int i = 0; i< string.length(); i++){
         chars[i] = string.charAt(i);
      }
      Arrays.sort(chars);
      String canonical = new String(chars);
      ArrayList<String> anagramList = anagramMap.get(canonical);
      if(anagramList == null){
         return new ArrayList<String>();
      }
      return new ArrayList<String>(anagramList);
   }
   
   
}
