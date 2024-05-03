package Algorithms;

import java.io.FileNotFoundException;
import java.util.*;

public class AStarWordLadder extends WordLadder {
    /**
     * Constructor untuk kelas AStarWordLadder
     * 
     * @param filename nama file yang berisi kamus kata
     */
    public AStarWordLadder(String filename) throws FileNotFoundException {
        super(filename);
    }

    /**
     * Method untuk mencari ladder dari kata awal ke kata akhir
     * 
     * @param start kata awal
     * @param end   kata akhir
     */
    public Map<String, Object> findLadder(String start, String end) {
        long startTime = System.currentTimeMillis();

        // Jika kata awal atau akhir tidak ada di kamus atau panjang kata awal tidak
        // sama dengan panjang kata akhir
        if (!dictionary.contains(start) || !dictionary.contains(end) || start.length() != end.length()) {
            return Collections.emptyMap();
        }

        // Priority queue untuk menyimpan node yang akan dikunjungi
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.priority));
        priorityQueue.add(new Node(start, null, 0, heuristic(start, end)));

        // Map untuk menyimpan cost terbaik dari node awal ke node tertentu
        Map<String, Integer> bestCosts = new HashMap<>();
        bestCosts.put(start, 0);

        // Set untuk menyimpan kata yang sudah dikunjungi
        Set<String> visited = new HashSet<>();

        // Jumlah node yang dikunjungi
        int nodesVisited = 0;

        while (!priorityQueue.isEmpty()) {
            // Mengambil node dengan priority terkecil
            Node current = priorityQueue.poll();
            nodesVisited++;

            // Jika kata saat ini sama dengan kata akhir -> selesai
            if (current.word.equals(end)) {
                long endTime = System.currentTimeMillis();
                Map<String, Object> result = new HashMap<>();
                result.put("Execution Time", (endTime - startTime) + " ms");
                result.put("Nodes Visited", nodesVisited);
                result.put("Path", reconstructPath(current));
                return result;
            }

            visited.add(current.word); // Menandai kata saat ini sebagai sudah dikunjungi

            // Menambahkan tetangga kata saat ini ke priority queue
            for (String neighbor : getNeighbors(current.word)) {
                if (visited.contains(neighbor)) {
                    continue;
                }

                int newCost = current.costFromStart + 1; // Cost dari node awal ke tetangga
                // Jika cost baru lebih kecil dari cost terbaik sebelumnya, update cost terbaik
                if (!bestCosts.containsKey(neighbor) || newCost < bestCosts.get(neighbor)) {
                    bestCosts.put(neighbor, newCost); // Update cost terbaik
                    int priority = newCost + heuristic(neighbor, end);  // Priority = cost + heuristic : f(n) = g(n) + h(n)
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
