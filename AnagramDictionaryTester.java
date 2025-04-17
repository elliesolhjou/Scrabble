// Name: Fatemeh Ellie Solhjou
// USC NetID: Solhjouk
// CS 455 PA4
// Spring 2025

import java.util.*;

public class AnagramDictionaryTester {

    public static void main(String[] args) {
        System.out.println("---- Testing AnagramDictionary ----");

        // Test 1: Normal dictionary with known anagram groups
        try {
            AnagramDictionary dict = new AnagramDictionary("testFiles/simpleDict.txt");

            System.out.println("Test 1: Anagrams of 'act'");
            ArrayList<String> result = dict.getAnagramsOf("act");
            System.out.println("Expected: [act, cat, tac]");
            System.out.println("Actual  : " + result);
            System.out.println();

            System.out.println("Test 2: Anagrams of 'listen'");
            result = dict.getAnagramsOf("listen");
            System.out.println("Expected: [listen, silent, enlist]");
            System.out.println("Actual  : " + result);
            System.out.println();

            System.out.println("Test 3: Anagrams of 'zzz' (no match)");
            result = dict.getAnagramsOf("zzz");
            System.out.println("Expected: []");
            System.out.println("Actual  : " + result);
            System.out.println();

        } catch (IllegalDictionaryException e) {
            System.out.println("FAILED Test 1-3: Unexpected IllegalDictionaryException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("FAILED Test 1-3: Unexpected Exception: " + e.getMessage());
        }

        // Test 4: Dictionary with duplicates
        try {
            System.out.println("Test 4: Should trigger IllegalDictionaryException for duplicates");
            AnagramDictionary dictWithDup = new AnagramDictionary("testFiles/duplicate.txt");
            System.out.println("FAILED: Expected IllegalDictionaryException but didn't get one");
        } catch (IllegalDictionaryException e) {
            System.out.println("PASSED: Caught expected IllegalDictionaryException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("FAILED: Unexpected exception: " + e.getMessage());
        }

        // Test 5: Empty dictionary
        try {
            System.out.println("\nTest 5: Empty dictionary file");
            AnagramDictionary emptyDict = new AnagramDictionary("testFiles/empty.txt");
            ArrayList<String> result = emptyDict.getAnagramsOf("anything");
            System.out.println("Expected: []");
            System.out.println("Actual  : " + result);
        } catch (Exception e) {
            System.out.println("FAILED Test 5: Unexpected Exception: " + e.getMessage());
        }

        System.out.println("\n---- End of Tests ----");
    }
}
