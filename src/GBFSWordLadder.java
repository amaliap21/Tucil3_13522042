import java.util.*;
import java.io.FileNotFoundException;

public class GBFSWordLadder extends WordLadder {

    public GBFSWordLadder(String filename) throws FileNotFoundException {
        super(filename);
    }

    public List<String> findLadder(String start, String end) {
        long startTime = System.currentTimeMillis();

        if (!dictionary.contains(start) || !dictionary.contains(end)) {
            return Collections.emptyList();
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
                System.out.println("Execution Time: " + (endTime - startTime) + " ms");
                System.out.println("Nodes Visited: " + nodesVisited);
                return reconstructPath(current);
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

        long endTime = System.currentTimeMillis(); // End the timer
        System.out.println("Execution Time: " + (endTime - startTime) + " ms");
        System.out.println("Nodes Visited: " + nodesVisited);
        return Collections.emptyList(); // No path found
    }
}
