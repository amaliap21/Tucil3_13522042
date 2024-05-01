import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import java.io.FileNotFoundException;

public class WordLadder {
    protected HashSet<String> dictionary;

    public WordLadder(String filename) throws FileNotFoundException {
        dictionary = new HashSet<>();
        loadDictionary(filename);
    }

    private void loadDictionary(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();
            dictionary.add(word);
        }
        scanner.close();
    }

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
     * 
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

    protected List<String> reconstructPath(Node endNode) {
        List<String> path = new LinkedList<>();
        Node current = endNode;
        while (current != null) {
            path.add(0, current.word);
            current = current.parent;
        }
        return path;
    }

    protected class Node {
        String word;
        Node parent;
        int heuristic; // Used for GBFS
        int costFromStart; // Used for A*
        int priority; // Used for A*

        // Use-defined Constructor GBFS
        public Node(String word, Node parent, int heuristic) {
            this.word = word;
            this.parent = parent;
            this.heuristic = heuristic;
        }

        // Use-defined Constructor A*
        public Node(String word, Node parent, int costFromStart, int priority) {
            this.word = word;
            this.parent = parent;
            this.costFromStart = costFromStart;
            this.priority = priority;
        }
    }
}
