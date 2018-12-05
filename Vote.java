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

  public static void main(String[] args) throws IOException {
    // produce a stream of strings where each string is a line from the file
    Stream<String> lines = new BufferedReader(new FileReader("voterdata.csv")).lines();

    // split the lines and filter for Governor and relevant party
    // (the lines are organized s.t. columns are separated by semicolons)
    Stream<String[]> split = lines.map(e -> e.split(";"))
                                  .filter(e -> e[2].equals("Governor"))
                                  .filter(e -> e[4].equals("Democratic") || e[4].equals("Republican"));

    // sum the vote counts and store them in a tuple
    // i.e., Tuple(Dem vote sum, Rep vote sum)
    // source for if/else idea: https://stackoverflow.com/questions/38021061/how-to-use-if-else-logic-in-java-8-stream-foreach

    Tuple<Integer,Integer> result =
      split.map(e -> {
              if(e[4].equals("Democratic")) {
                  return new Tuple<Integer,Integer>(0,Integer.parseInt(e[6]));
              }
              else {
                  return new Tuple<Integer,Integer>(1,Integer.parseInt(e[6]));
              }
           })
           .reduce(new Tuple<Integer,Integer>(0,0), (a,b) -> {
              if(b.fst == 0) { //democrat
                  return a.changeFirst(a.fst + b.snd);
              }
              else { //republican
                  return a.changeSecond(a.snd + b.snd);
              }
           });

    // output the result
    System.out.println("Democratic votes: " + result.fst);
    System.out.println("Republican votes: " + result.snd);
  }
}
