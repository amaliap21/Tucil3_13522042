import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DictionaryExtract extends WordLadder {

    private static final int THREAD_COUNT = 16;

    public DictionaryExtract(String filename) throws FileNotFoundException {
        super(filename);
    }

    public static void main(String[] args) {
        try {
            // Load already processed words
            Set<String> processedWords = new HashSet<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("extracted-word.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    processedWords.add(line.trim());
                }
            }

            DictionaryExtract dict = new DictionaryExtract("dictionary.txt");
            DictionaryAPI dictionaryAPI = new DictionaryAPI();

            FileWriter fileWriter = new FileWriter("extracted-word.txt", true); // Append mode
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
            boolean startProcessing = false;

            for (String word : dict.dictionary) {
                // Start processing from the word "flat" "rewinding"
                if (!startProcessing && word.equals("response")) {
                    startProcessing = true;
                }

                if (startProcessing) {
                    executor.submit(() -> {
                        try {
                            String wordData = dictionaryAPI.getOnlineData(word);
                            // Check if word data was successfully retrieved
                            if (!wordData.startsWith("Error:")) {
                                synchronized (bufferedWriter) {
                                    bufferedWriter.write(word);
                                    bufferedWriter.newLine();
                                }
                                System.err.println("Extracted data for " + word + "...");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }

            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.HOURS);

            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
