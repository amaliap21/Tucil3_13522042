package Algorithms;

import java.io.FileNotFoundException;
import java.util.*;

public class GBFSWordLadder extends WordLadder {
    private Map<String, Integer> heuristicCache = new HashMap<>();

    /**
     * Constructor kelas GBFSWordLadder
     * @param filename nama file yang berisi kamus kata
    */
    public GBFSWordLadder(String filename) throws FileNotFoundException {
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

        // Priority queue untuk menyimpan node yang akan dikunjungi
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.heuristic));
        priorityQueue.add(new Node(start, null, calculateHeuristic(start, end)));
        
        // Set untuk menyimpan kata yang sudah dikunjungi
        Set<String> visited = new HashSet<>();
        visited.add(start); // Menandai kata awal sebagai sudah dikunjungi

        // Jumlah node yang dikunjungi
        int nodesVisited = 0;

        while (!priorityQueue.isEmpty()) {
            // Mengambil node dengan heuristic terkecil
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

            // Menambahkan tetangga kata saat ini ke priority queue
            for (String neighbor : getNeighbors(current.word)) {
                // Jika tetangga belum dikunjungi, tambahkan ke priority queue
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

    // Method untuk menghitung heuristic dari kata saat ini ke kata akhir
    private int calculateHeuristic(String word, String end) {
        // Jika kata sudah pernah dihitung heuristicnya, maka gunakan hasil sebelumnya
        if (heuristicCache.containsKey(word)) {
            return heuristicCache.get(word);
        }

        // Jika panjang kata tidak sama, maka heuristic = MAX_VALUE
        if (word.length() != end.length()) {
            return Integer.MAX_VALUE;
        }

        // Menghitung jarak antara kata saat ini dengan kata akhir
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
