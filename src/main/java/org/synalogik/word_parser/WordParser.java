package org.synalogik.word_parser;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.util.*;
import java.util.stream.Collectors;

public class WordParser {

    /* Method to split a string into a List of words
     * This logic is encapsulated in a method to allow for quick refactoring
     *
     * @param String content, the string split
     * @return List<String>, a list containing each individual word parsed from the string
     *
     * @author Finn Torbet
     * @date 24/11/21
     */
    private static List<String> parse(String content) {
        return new ArrayList<>(Arrays.asList(content.split("[?!\\s.,;]")))
                //
                .stream().filter(s -> !s.equals("")).collect(Collectors.toList());
    }


    /* Method to collect all lengths that share a given number of occurrences
     * This logic is encapsulated in a method to allow for quick refactoring
     *
     * @param Map<Integer, Integer> word_lengths, a map where each key is a length of a word and the value
     *                                  is the number of occurrences of that given length
     * @return List<Integer>, a list of the extracted lengths that have n number of occurrences
     *
     * @author Finn Torbet
     * @date 24/11/21
     */
    private static List<Integer> lengthsWithOccurrences(Map<Integer, Integer> word_lengths, int occurrence_number) {
        // Filter the  word lengths that have the highest number of
        // occurrences and return them as a list
        return word_lengths.entrySet().stream()
                .filter(entry -> entry.getValue() == occurrence_number)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

    }


    /* Method to calculate the average word length
     * This logic is encapsulated in a method to allow for quick refactoring
     *
     * @param Map<Integer, Integer> word_lengths, a map where each key is a length of a word and the value
     *                                  is the number of occurrences of that given length
     * @return double, the calculated average
     *
     * @author Finn Torbet
     * @date 24/11/21
     */
    private static double calculateAverage(Map<Integer, Integer> word_lengths) {
        double n = 0;
        double total = 0;
        // Iterate over the word lengths in the map to calculate the
        // sum of all the lengths (total) and the sum of the number
        // of occurrences (n)
        for(Integer length : word_lengths.keySet()) {
            int occurrences_of_length = word_lengths.get(length);
            total += length * occurrences_of_length;
            n += word_lengths.get(length);
        }

        // Calculate Average and round to 3 decimal places as shown in the example
        return Math.round((total / n)*1000d)/1000d; // Average
    }


    /* This classes' primary method to parse a text file into all its words then assemble
     * a multi-line string to display all the information
     *
     * @param String filePath, the filepath to retrieve the file from
     * @throws WordParserException, a custom exception with a custom message attached for clear fail feedback
     * @return String, a String detailing all the data from the parsed words
     *
     * @author Finn Torbet
     * @date 24/11/21
     */
    public static String parseFile(String filePath) throws WordParserException {

        if(!filePath.endsWith(".txt")) { throw new WordParserException("Given file is not a .txt file!"); }

        try {

            // Read the file
            FileReader in_reader = new FileReader(filePath);
            BufferedReader b_reader = new BufferedReader(in_reader);

            StringBuilder data = new StringBuilder();
            String line;
            while ((line = b_reader.readLine()) != null) {
                data.append(line);
            }
            b_reader.close();
            in_reader.close();

            if(data.length() == 0) {
                throw new WordParserException("Empty File: " + filePath);
            }

            Map<Integer, Integer> word_lengths = new HashMap<>();

            // Split the String into individual words
            List<String> split_words = parse(data.toString());

            // Store each length of a word in a Map
            for(String s : split_words) {
                int length = s.length();
                if(!word_lengths.containsKey(length)) {
                    word_lengths.put(length, 1);
                } else {
                    word_lengths.put(length, word_lengths.get(length) + 1);
                }
            }

            // Calculate average length by iterating over each length in the map
            double average = calculateAverage(word_lengths);

            // Calculate all the lengths that have the most occurrences
            // Retrieve the word length(s) from the map with the largest occurrences
            int max = Collections.max(word_lengths.values());
            List<Integer> max_lengths = lengthsWithOccurrences(word_lengths, max);



            // Start assembling the output string
            StringBuilder output = new StringBuilder();

            // Display the word count and the average word length
            output.append("Word count = ").append(split_words.size()).append("\n");
            output.append("Average word length = ").append(average).append("\n");

            for(Map.Entry<Integer, Integer> l : word_lengths.entrySet()) {
                output.append("Number of words of length ").append(l.getKey()).append(" is ").append(l.getValue()).append("\n");
            }

            // Construct the string to display all the lengths that share the max length
            output.append("The most frequently occurring word length is ").append(max).append(", for word length").append((max_lengths.size() > 1) ? "s" : "").append(" of ");;

            for(Integer i : max_lengths) {
                output.append(i);
                if(max_lengths.indexOf(i) != max_lengths.size() -1) {
                    output.append(" & ");
                }
            }
            return output.toString();


        } catch (InvalidPathException | IOException e) {
            throw new WordParserException("Invalid File Path: " + filePath);
        }
    }
}
