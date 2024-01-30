package csc435.app;

import java.io.*;
import java.nio.file.*;

public class CleanDataSet
{
    public long dataset_size = 0;
    public double execution_time = 0.0;

    public void clean_dataset(String input_dir, String output_dir) {
        try {
            Path inputPath = Paths.get(input_dir);   // input path
            Path outputPath = Paths.get(output_dir); // output path

            Files.walk(inputPath)
                .filter(Files::isRegularFile)
                .forEach(file -> cleanFile(file, inputPath, outputPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cleanFile(Path inputFile, Path inputDir, Path outputDir) {
        try {
            String content = new String(Files.readAllBytes(inputFile)); // read file content
            content = content.replaceAll("\r", ""); // remove  "\r" characters
            content = content.replaceAll("(\r\n|\n\r|\r\n|\t| )+", "$1"); // Replace repeating delimiters with the last one
            // Remove separators and handle alphanumerical characters
            content = content.replaceAll("[^0-9a-zA-Z\\t\\n\\r\\x0B\\f\\x85\\p{javaWhitespace}]", " ");


            Path relativePath = inputDir.relativize(inputFile); // get relative path
            Path outputFile = outputDir.resolve(relativePath); // get output file path

            Files.createDirectories(outputFile.getParent()); // create output directory
            Files.write(outputFile, content.getBytes()); // write file content

            dataset_size += Files.size(inputFile); // update dataset size
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

    public static void main(String[] args)
    {
        if (args.length != 2) {
            System.err.println("improper number of arguments");
            System.exit(1);
        }

        CleanDataSet cleanDataset = new CleanDataSet();

        long startTime = System.currentTimeMillis();    // start time
        cleanDataset.clean_dataset(args[0], args[1]);
        long endTime = System.currentTimeMillis();      // end time

        cleanDataset.execution_time = (endTime - startTime) / 1000.0; // calculate execution time

        System.out.print("Finished cleaning " + cleanDataset.dataset_size + " MiB of data");
        System.out.println(" in " + cleanDataset.execution_time + " milliseconds");
    }
}
