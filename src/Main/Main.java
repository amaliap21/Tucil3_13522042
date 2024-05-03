package Main;

import Algorithms.*;
import java.util.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            UCSWordLadder ladderUCS = new UCSWordLadder("Dictionary/dictionary.txt");
            GBFSWordLadder ladderGBFS = new GBFSWordLadder("Dictionary/dictionary.txt");
            AStarWordLadder ladderAStar = new AStarWordLadder("Dictionary/dictionary.txt");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter start word: ");
            String start = scanner.nextLine();

            System.out.print("Enter end word: ");
            String end = scanner.nextLine();

            scanner.close();

            System.out.println("Finding ladder from " + start + " to " + end + "...");

            Map<String, Object> resultUCS = ladderUCS.findLadder(start, end);
            Map<String, Object> resultGBFS = ladderGBFS.findLadder(start, end);
            Map<String, Object> resultAStar = ladderAStar.findLadder(start, end);

            if (resultUCS.isEmpty() || resultGBFS.isEmpty() || resultAStar.isEmpty()) {
                System.out.println("No path found.");
            } else {
                System.out.println("UCS: ");
                resultUCS.forEach((key, value) -> System.out.println(key + ": " + value));
                System.out.println("\nGBFS: ");
                resultGBFS.forEach((key, value) -> System.out.println(key + ": " + value));
                System.out.println("\nA-Star: ");
                resultAStar.forEach((key, value) -> System.out.println(key + ": " + value));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Dictionary file not found.");
        }
    }
}