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

    // split the relevant lines and store their vote sums in a Tuple
    // (the lines are organized s.t. columns are separated by semicolons)
    // source for if/else idea: https://stackoverflow.com/questions/38021061/how-to-use-if-else-logic-in-java-8-stream-foreach
    // source for array wrapper idea: https://stackoverflow.com/questions/31851892/java-changing-the-value-of-a-final-variable-from-within-a-lambda
    final Integer[] sum = new Integer[2];
    sum[0] = 0;
    sum[1] = 0;
    lines.map(e -> e.split(";"))
         .filter(e -> e[2].equals("Governor"))
         .forEach(e -> {
            if(e[4].equals("Democratic")) {
              sum[0] += Integer.parseInt(e[6]);
            }
            else if(e[4].equals("Republican")) {
              sum[1] += Integer.parseInt(e[6]);
            }
         });

    // output the result
    System.out.println("Democratic votes: " + sum[0]);
    System.out.println("Republican votes: " + sum[1]);
  }
}
