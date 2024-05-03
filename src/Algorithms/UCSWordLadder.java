package Algorithms;

import java.io.FileNotFoundException;
import java.util.*;

public class UCSWordLadder extends WordLadder {
    public UCSWordLadder(String filename) throws FileNotFoundException {
        super(filename);
    }

    public Map<String, Object> findLadder(String start, String end) {
        long startTime = System.currentTimeMillis(); // Start timing

        if (!dictionary.contains(start) || !dictionary.contains(end) || start.length() != end.length()) {
            return Collections.emptyMap();
        }

        Queue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.costFromStart));
        Set<String> visited = new HashSet<>();
        priorityQueue.add(new Node(start, null, 0, 0));

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

            visited.add(current.word);

            for (String neighbor : getNeighbors(current.word)) {
                if (!visited.contains(neighbor)) {
                    priorityQueue.add(new Node(neighbor, current, current.costFromStart + 1, 0));
                    visited.add(neighbor);
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
}
