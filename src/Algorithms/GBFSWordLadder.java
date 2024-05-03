package Algorithms;

import java.io.FileNotFoundException;
import java.util.*;

public class GBFSWordLadder extends WordLadder {
    // Move heuristicCache to the class level
    private Map<String, Integer> heuristicCache = new HashMap<>();

    public GBFSWordLadder(String filename) throws FileNotFoundException {
        super(filename);
    }

    public Map<String, Object> findLadder(String start, String end) {
        long startTime = System.currentTimeMillis();

        if (!dictionary.contains(start) || !dictionary.contains(end) || start.length() != end.length()) {
            return Collections.emptyMap();
        }

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.heuristic));
        Set<String> visited = new HashSet<>();

        priorityQueue.add(new Node(start, null, calculateHeuristic(start, end)));
        visited.add(start);

        int nodesVisited = 0;

        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();
            nodesVisited++;

            if (current.word.equals(end)) {
                long endTime = System.currentTimeMillis();
                Map<String, Object> result = new HashMap<>();
                result.put("Execution Time", (endTime - startTime) + " ms");
                result.put("Nodes Visited", nodesVisited);
                result.put("Path", reconstructPath(current));
                return result;
            }

            for (String neighbor : getNeighbors(current.word)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    priorityQueue.add(new Node(neighbor, current, calculateHeuristic(neighbor, end)));
                }
            }
        }

        long endTime = System.currentTimeMillis();
        Map<String, Object> result = new HashMap<>();
        result.put("Execution Time", (endTime - startTime) + " ms");
        result.put("Nodes Visited", nodesVisited);
        result.put("Path", Collections.emptyList());
        return result; // No path found
    }

    private int calculateHeuristic(String word, String end) {
        if (heuristicCache.containsKey(word)) {
            return heuristicCache.get(word);
        }

        if (word.length() != end.length()) {
            return Integer.MAX_VALUE;
        }

        int heuristic = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != end.charAt(i)) {
                heuristic++;
            }
        }

        heuristicCache.put(word, heuristic);
        return heuristic;
    }
}
