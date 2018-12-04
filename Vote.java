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

  public static int add(int first, int second) {
    return first + second;
  }

  public static void main(String[] args) throws IOException {
    // produce a stream of strings where each string is a line from the file
    Stream<String> lines = new BufferedReader(new FileReader("voterdata.csv")).lines();

    // split the relevant lines and store their vote sums in a Tuple
    // (the lines are organized s.t. columns are separated by semicolons)
    // source for if/else idea: https://stackoverflow.com/questions/38021061/how-to-use-if-else-logic-in-java-8-stream-foreach

    final ArrayList<String> demVotes = new ArrayList<String>();
    final ArrayList<String> repVotes = new ArrayList<String>();

    lines.parallel().map(e -> e.split(";"))
         .filter(e -> e[2].equals("Governor"))
         .forEach(e -> {
            if(e[4].equals("Democratic")) {
              demVotes.add(e[6]);
            }
            else if(e[4].equals("Republican")) {
              repVotes.add(e[6]);
            }
         });

    // put the values in the tuple
    Tuple<Integer,Integer> result = new Tuple<Integer,Integer>
    (demVotes.stream().mapToInt(e -> Integer.parseInt(e)).sum()
    ,repVotes.stream().mapToInt(e -> Integer.parseInt(e)).sum());

    // output the result
    System.out.println("Democratic votes: " + result.fst);
    System.out.println("Republican votes: " + result.snd);
  }
}
