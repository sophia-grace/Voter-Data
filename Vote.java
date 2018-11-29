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

public class Vote {


  public static void main(String[] args) throws IOException {
    // produce a stream of strings where each string is a line from the file
    Stream<String> lines = new BufferedReader(new FileReader("voterdata.csv")).lines();

    // get the relevant lines
    // and split them by constituent pieces
    // (the lines are organized s.t. columns are separated by semicolons)
    Stream<String[]> relevantStringArray = lines
          .filter(e -> e.contains("Governor"))
          .filter(e -> e.contains("Democratic"))
      //    .filter(e -> (e.contains("Democratic") || (e.contains("Republican"))))
          .map(e -> e.split(";"));


    int vote = 0;
    vote = relevantStringArray.mapToInt(e -> Integer.parseInt(e[6])).sum();
    System.out.println(vote);
  }
}
