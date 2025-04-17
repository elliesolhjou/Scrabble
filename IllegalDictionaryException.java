// Name: Fatemeh Ellie Solhjou
// USC NetID: 1424729265
// CS 455 PA4
// Spring 2025

import java.io.IOException;

/**
 * This class reports problems with the dictionary file.
 */
public class IllegalDictionaryException extends IOException {
   public IllegalDictionaryException() {}
   public IllegalDictionaryException(String message)
   {
      super(message);
   }
}
