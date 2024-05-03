package Algorithms;

import java.io.FileNotFoundException;
import java.util.*;

public class AStarWordLadder extends WordLadder {

    public AStarWordLadder(String filename) throws FileNotFoundException {
        super(filename);
    }

    public Map<String, Object> findLadder(String start, String end) {
        long startTime = System.currentTimeMillis();

        if (!dictionary.contains(start) || !dictionary.contains(end) || start.length() != end.length()) {
            return Collections.emptyMap();
        }

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.priority));
        Map<String, Integer> bestCosts = new HashMap<>();
        Set<String> visited = new HashSet<>();
        priorityQueue.add(new Node(start, null, 0, heuristic(start, end)));
        bestCosts.put(start, 0);

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
                if (visited.contains(neighbor)) {
                    continue;
                }

                int newCost = current.costFromStart + 1;
                if (!bestCosts.containsKey(neighbor) || newCost < bestCosts.get(neighbor)) {
                    bestCosts.put(neighbor, newCost);
                    int priority = newCost + heuristic(neighbor, end);
                    priorityQueue.add(new Node(neighbor, current, newCost, priority));
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
