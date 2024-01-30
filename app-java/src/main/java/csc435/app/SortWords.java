package csc435.app;

import java.io.*;
import java.nio.file.*;

public class SortWords
{
    public long num_words = 0;
    public double execution_time = 0.0;

    public void sort_words(String input_dir, String output_dir) {
        try {
            Path inputPath = Paths.get(input_dir);
            Path outputPath = Paths.get(output_dir);

            Files.walk(inputPath)
                .filter(Files::isRegularFile)
                .forEach(file -> sortWordsInFile(file, outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sortWordsInFile(Path inputFile, Path outputDir) {
        try {
            String input_dir = "path/to/input/directory"; // Replace "path/to/input/directory" with the actual input directory path

            Path inputPath = Paths.get(input_dir); // Initialize inputPath variable with input_dir parameter
            Path relativePath = inputPath.relativize(inputFile); // get relative path

            String output_dir = "path/to/output/directory"; // Replace "path/to/output/directory" with the actual output directory path

            Path outputPath = Paths.get(output_dir); // Declare and initialize outputPath variable
            Path outputFile = outputPath.resolve(relativePath);  // get output file path

            Files.createDirectories(outputFile.getParent());     // create output directory

            String sortedWords = ""; // Declare and initialize sortedWords variable
            Files.write(outputFile, sortedWords.getBytes());    // write file content

            String[] words = sortedWords.split("\\s+"); // Declare and initialize words variable by splitting sortedWords

            num_words += words.length;   // Update dataset size
        } catch (IOException e) {     // Print stack trace
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        if (args.length != 2) {
            System.err.println("improper number of arguments");
            System.exit(1);
        }

        SortWords sortWords = new SortWords();

        long startTime = System.currentTimeMillis();    // start time
        sortWords.sort_words(args[0], args[1]);
        long endTime = System.currentTimeMillis();      // end time

        sortWords.execution_time = (endTime - startTime) / 1000.0; // calculate execution time
        
        System.out.print("Finished sorting " + sortWords.num_words + " words");
        System.out.println(" in " + sortWords.execution_time + " milliseconds");
    }
}
