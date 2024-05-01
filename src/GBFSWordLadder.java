import java.util.*;
import java.io.FileNotFoundException;

public class GBFSWordLadder extends WordLadder {

    public GBFSWordLadder(String filename) throws FileNotFoundException {
        super(filename);
    }

    public Map<String, Object> findLadder(String start, String end) {
        long startTime = System.currentTimeMillis(); 

        if (!dictionary.contains(start) || !dictionary.contains(end)) {
            return Collections.emptyMap(); 
        }

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.heuristic));
        Map<String, Boolean> visited = new HashMap<>();
        priorityQueue.add(new Node(start, null, heuristic(start, end)));

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

            if (!visited.containsKey(current.word)) {
                visited.put(current.word, true);

                for (String neighbor : dictionary) {
                    if (!visited.containsKey(neighbor) && isOneLetterDifferent(current.word, neighbor)) {
                        priorityQueue.add(new Node(neighbor, current, heuristic(neighbor, end)));
                    }
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
