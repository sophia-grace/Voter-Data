/*
Name: Sophia Trump
File: Vote.java
Description: Counts the number of votes cast for Democratic and Republican
             governors in the PA 2018 election.
Date: 29/11/18
*/

import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Vote {

  // returns the sum of the votes (index 6) for the given input
  public static int countVotes(Stream<String[]> relevantStringArray) {
    return relevantStringArray.mapToInt(e -> Integer.parseInt(e[6])).sum();
  }

  // used to group the elements of the stream
  public static boolean isDemocratic(String[] ln) {
    if(ln[4].equals("Democratic")) {
      return true;
    }
    else {
      return false;
    }
  }

  public static void main(String[] args) throws IOException {
    // produce a stream of strings where each string is a line from the file
    Stream<String> lines = new BufferedReader(new FileReader("voterdata.csv")).lines();

    // get the relevant lines
    // and split them by constituent pieces
    // (the lines are organized s.t. columns are separated by semicolons)

  //  Tuple<Integer, Integer> demrepResult;
  /*  Stream<String[]> relevantStringArray = lines
          .map(e -> e.split(";"))
          .filter(e -> e[2].equals("Governor"))
          .filter(e -> (e[4].equals("Democratic")) || (e[4].equals("Republican"))); */

    Tuple<Integer, Integer> result = new Tuple<Integer,Integer>(0,0);
    lines.map(e -> e.split(";"))
         .filter(e -> e[2].equals("Governor"))
         .forEach(e -> {
            if(e[4].equals("Democratic")) {
              //  demVotes += Integer.parseInt(e[6]);
              // System.out.println("D");
              result.changeFirst(result.fst + Integer.parseInt(e[6]));
            }
            else if(e[4].equals("Republican")) {
              //  repVotes += Integer.parseInt(e[6]);
              // System.out.println("R");
              result.changeSecond(result.snd + Integer.parseInt(e[6]));
            }
         });

    // create tuple with dem and rep lines
    System.out.println(result.fst);
  }
}
