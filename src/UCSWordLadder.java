import java.io.FileNotFoundException;
import java.util.*;

public class UCSWordLadder extends WordLadder {
    public UCSWordLadder(String filename) throws FileNotFoundException {
        super(filename);
    }

    public Map<String, Object> findLadder(String start, String end) {
        long startTime = System.currentTimeMillis(); // Start timing

        if (!dictionary.contains(start) || !dictionary.contains(end)) {
            return Collections.emptyMap();
        }

        Queue<List<String>> queue = new LinkedList<>();
        queue.add(Arrays.asList(start));

        Set<String> visited = new HashSet<>();
        int nodesVisited = 0;

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String lastWord = path.get(path.size() - 1);

            if (lastWord.equals(end)) {
                long endTime = System.currentTimeMillis();
                Map<String, Object> result = new HashMap<>();
                result.put("Execution Time", (endTime - startTime) + " ms");
                result.put("Nodes Visited", nodesVisited);
                result.put("Path", path);
                return result;
            }

            for (String word : dictionary) {
                if (!visited.contains(word) && isOneLetterDifferent(lastWord, word)) {
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(word);
                    queue.add(newPath);
                    visited.add(word);
                    nodesVisited++;
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
