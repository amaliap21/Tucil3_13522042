package Algorithms;

import java.io.FileNotFoundException;
import java.util.*;

public class UCSWordLadder extends WordLadder {
    /**
     * Constructor kelas UCSWordLadder
     * @param filename nama file yang berisi kamus kata
    */
    public UCSWordLadder(String filename) throws FileNotFoundException {
        super(filename);
    }

    /**
     * Method untuk mencari ladder dari kata awal ke kata akhir
     * @param start kata awal
     * @param end kata akhir
     */
    public Map<String, Object> findLadder(String start, String end) {
        long startTime = System.currentTimeMillis();

        // Jika kata awal atau akhir tidak ada di kamus atau panjang kata awal tidak sama dengan panjang kata akhir
        if (!dictionary.contains(start) || !dictionary.contains(end) || start.length() != end.length()) {
            return Collections.emptyMap();
        }

        // Priority Queue untuk menyimpan node yang akan dikunjungi
        Queue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.costFromStart));
        priorityQueue.add(new Node(start, null, 0, 0)); // Menambahkan node awal ke priority queue

        // Set untuk menyimpan kata yang sudah dikunjungi
        Set<String> visited = new HashSet<>(); 

        // Jumlah node yang dikunjungi
        int nodesVisited = 0;

        while (!priorityQueue.isEmpty()) {
            // Mengambil node dengan cost terkecil
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
                // Jika tetangga belum dikunjungi, tambahkan ke priority queue
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
