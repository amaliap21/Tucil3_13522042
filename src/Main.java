import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            UCSWordLadder ladderUCS = new UCSWordLadder("dictionary.txt");
            GBFSWordLadder ladderGBFS = new GBFSWordLadder("dictionary.txt");
            AStarWordLadder ladderAStar = new AStarWordLadder("dictionary.txt");

            System.out.println("Finding path from 'base' to 'root'...");

            // input start and end words here
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter start word: ");
            String start = scanner.nextLine();

            System.out.print("Enter end word: ");
            String end = scanner.nextLine();

            scanner.close();

            List<String> resultUCS = ladderUCS.findLadder(start, end);
            List<String> resultGBFS = ladderGBFS.findLadder(start, end);
            List<String> resultAStar = ladderAStar.findLadder(start, end);

            if (resultUCS.isEmpty() || resultGBFS.isEmpty() || resultAStar.isEmpty()) {
                System.out.println("No path found.");
            } else {
                System.out.println("Path-UCS: " + resultUCS);
                System.out.println("Path-GBFS: " + resultGBFS);
                System.out.println("Path-A*: " + resultAStar);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Dictionary file not found.");
        }
    }
}
