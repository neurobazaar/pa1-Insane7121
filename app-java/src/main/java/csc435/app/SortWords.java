package csc435.app;

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
            String content = new String(Files.readAllBytes(inputFile));
            content = content.replaceAll("\r", ""); // Remove '\r' characters
            content = content.replaceAll("(\r\n|\n\r|\r|\n|\t| )+", "$1"); // Replace repeating delimiters with the last one

            // Remove separators and handle alphanumerical characters
            content = content.replaceAll("[^0-9a-zA-Z\\t\\n\\r\\x0B\\f\\x85\\p{javaWhitespace}]", "");

            String[] words = content.split("\\p{javaWhitespace}+"); // Split by whitespace

            // Sort the words
            String sortedWords = Arrays.stream(words)                            // Stream of words
                                        .filter(word -> !word.isEmpty())              // Filter out empty words
                                        .sorted()                                    // Sort the words
                                        .collect(Collectors.joining(" "));             // Join the words with spaces

            Path relativePath = inputFile.relativize(inputFile); // get relative path
            Path outputFile = outputDir.resolve(relativePath);  // get output file path

            Files.createDirectories(outputFile.getParent());     // create output directory
            Files.write(outputFile, sortedWords.getBytes());    // write file content

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
