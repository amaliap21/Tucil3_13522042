import java.io.FileNotFoundException;
import java.util.*;

public class UCSWordLadder extends WordLadder {
    public UCSWordLadder(String filename) throws FileNotFoundException {
        super(filename);
    }

    public List<String> findLadder(String start, String end) {
        long startTime = System.currentTimeMillis();

        if (!dictionary.contains(start) || !dictionary.contains(end)) {
            return Collections.emptyList();
        }

        Queue<List<String>> queue = new LinkedList<>();
        queue.add(Arrays.asList(start));

        Set<String> visited = new HashSet<>();
        int nodesVisited = 0;

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String lastWord = path.get(path.size() - 1);

            if (lastWord.equals(end)) {
                long endTime = System.currentTimeMillis(); // End timing
                System.out.println("Execution Time: " + (endTime - startTime) + " ms");
                System.out.println("Nodes visited: " + nodesVisited);
                return path;
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
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
        System.out.println("Nodes visited: " + nodesVisited);
        return Collections.emptyList();
    }
}
