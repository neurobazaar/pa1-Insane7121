package csc435.app;

import java.io.*;
import java.nio.file.*;
import java.util.Arrays;
import java.util.regex.*;

public class CountWords
{
    public long dataset_size = 0;
    public double execution_time = 0.0;

    public void count_words(String input_dir, String output_dir) {
        try {
            Path inputPath = Paths.get(input_dir);
            Path outputPath = Paths.get(output_dir);

            Files.walk(inputPath)
                 .filter(Files::isRegularFile)
                 .forEach(file -> countWordsInFile(file, outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void countWordsInFile(Path inputFile, Path outputDir) {
        try {
            String content = new String(Files.readAllBytes(inputFile));
            content = content.replaceAll("\r", ""); // Remove '\r' characters
            content = content.replaceAll("(\r\n|\n\r|\r|\n|\t| )+", "$1"); // Replace repeating delimiters with the last one

            // Remove separators and handle alphanumerical characters
            content = content.replaceAll("[^0-9a-zA-Z\\t\\n\\r\\x0B\\f\\x85\\p{javaWhitespace}]", "");

            String[] words = content.split("\\p{javaWhitespace}+"); // Split by whitespace

            // Count the number of words
            long wordCount = Arrays.stream(words)                            // Stream of words
                                    .filter(word -> !word.isEmpty())              // Filter out empty words
                                    .count();                                     // Count the number of words

            dataset_size += wordCount;   // Update dataset size
        } catch (IOException e) {
            e.printStackTrace();        // Print stack trace
        }
    }

    public static void main(String[] args)
    {
        if (args.length != 2) {
            System.err.println("improper number of arguments");
            System.exit(1);
        }

        CountWords countWords = new CountWords();   // create count words object

        long startTime = System.currentTimeMillis();    // start time
        countWords.count_words(args[0], args[1]);     // count words
        long endTime = System.currentTimeMillis();      // end time

        countWords.execution_time = (endTime - startTime) / 1000.0; // calculate execution time
        
        System.out.print("Finished counting " + countWords.dataset_size + " MiB of words");
        System.out.println(" in " + countWords.execution_time + " milliseconds");
    }
}
