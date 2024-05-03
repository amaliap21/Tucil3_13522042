package Algorithms;

import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;

public class WordLadder {
     // ANSI escape codes for colors
     public static final String RESET = "\u001B[0m";
     public static final String GREEN = "\u001B[32m";
     public static final String BLUE = "\u001B[34m";
     public static final String RED = "\u001B[31m";
     public static final String YELLOW = "\u001B[33m";
     public static final String CYAN = "\u001B[36m";
 
    protected HashSet<String> dictionary;

    /**
     * Constructor kelas WordLadder
     * @param filename nama file yang berisi kamus kata
     */
    public WordLadder(String filename) throws FileNotFoundException {
        dictionary = new HashSet<>();
        loadDictionary(filename);
    }

    /**
     * Method untuk memuat dictionary dari file
     * @param filename nama file dictionary
     */
    private void loadDictionary(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();
            dictionary.add(word);
        }
        scanner.close();
    }

    /**
     * Method untuk mengecek apakah dua kata berbeda satu huruf
     * @param word1 kata pertama
     * @param word2 kata kedua
     */
    public boolean isOneLetterDifferent(String word1, String word2) {
        if (word1.length() != word2.length())
            return false;

        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                if (++count > 1)
                    return false;
            }
        }
        return count == 1;
    }

    /**
     * Method untuk mendapatkan tetangga dari suatu kata
     * @param word kata yang akan dicari tetangganya
     */
    protected List<String> getNeighbors(String word) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char original = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == original)
                    continue;
                chars[i] = c;
                String newWord = new String(chars);

                // Jika kata baru ada di dictionary, maka kata tersebut adalah tetangga
                if (dictionary.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }
            chars[i] = original;
        }

        return neighbors;
    }

    /**
     * Method untuk menghitung jarak antara dua kata
     * @param current kata pertama
     * @param goal kata kedua
     */
    protected int heuristic(String current, String goal) {
        int distance = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != goal.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    /**
     * Method untuk merekonstruksi path dari node akhir
     * @param endNode node akhir
     */
    protected List<String> reconstructPath(Node endNode) {
        List<String> path = new LinkedList<>();
        Node current = endNode;
        while (current != null) {
            path.add(0, current.word);
            current = current.parent;
        }
        return path;
    }

    /**
     * Kelas Node untuk merepresentasikan node dalam algoritma
     */
    protected class Node {
        String word;
        Node parent;
        int heuristic; // Used for GBFS -> mencari jarak dari node saat ini ke goal: h(n)
        int costFromStart; // Used for A* -> mencari jarak dari start ke node saat ini: g(n)
        int priority; // Used for A* -> costFromStart + heuristic: f(n) = g(n) + h(n)

        // User-defined Constructor GBFS
        public Node(String word, Node parent, int heuristic) {
            this.word = word;
            this.parent = parent;
            this.heuristic = heuristic;
        }

        // User-defined Constructor A*
        public Node(String word, Node parent, int costFromStart, int priority) {
            this.word = word;
            this.parent = parent;
            this.costFromStart = costFromStart;
            this.priority = priority;
        }
    }
}
