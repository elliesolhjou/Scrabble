// Name: 
// USC NetID: 
// CS 455 PA4
// Spring 2025

import java.util.*;

public class WordFinder{

    public static void main(String[] args){

        System.out.println("Type . to quit.");
        Scanner in = new Scanner(); 

        while(in.hasNext() && (in.next() != '.' || in.next() != "\n")){
            try{
                System.out.print("Rack?");
                Rack rack = in.next();
                //read rack
                //process rack
            }
            catch()
            {

            }

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