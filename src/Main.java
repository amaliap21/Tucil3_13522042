// import java.io.FileNotFoundException;
// // import java.util.List;
// import java.util.Map;
// import java.util.Scanner;

// public class Main {
//     public static void main(String[] args) {
//         try {
//             UCSWordLadder ladderUCS = new UCSWordLadder("dictionary.txt");
//             GBFSWordLadder ladderGBFS = new GBFSWordLadder("dictionary.txt");
//             AStarWordLadder ladderAStar = new AStarWordLadder("dictionary.txt");

//             Scanner scanner = new Scanner(System.in);
//             System.out.print("Enter start word: ");
//             String start = scanner.nextLine();

//             System.out.print("Enter end word: ");
//             String end = scanner.nextLine();

//             scanner.close();

//             System.out.println("Finding ladder from " + start + " to " + end + "...");

//             Map<String, Object> resultUCS = ladderUCS.findLadder(start, end);
//             Map<String, Object> resultGBFS = ladderGBFS.findLadder(start, end);
//             Map<String, Object> resultAStar = ladderAStar.findLadder(start, end);

//             if (resultUCS.isEmpty() || resultGBFS.isEmpty() || resultAStar.isEmpty()) {
//                 System.out.println("No path found.");
//             } else {
//                 System.out.println("UCS: ");
//                 resultUCS.forEach((key, value) -> System.out.println(key + ": " + value));
//                 System.out.println("\nGBFS: ");
//                 resultGBFS.forEach((key, value) -> System.out.println(key + ": " + value));
//                 System.out.println("\nA-Star: ");
//                 resultAStar.forEach((key, value) -> System.out.println(key + ": " + value));
//             }
//         } catch (FileNotFoundException e) {
//             System.out.println("Dictionary file not found.");
//         }
//     }
// }

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            DictionaryAPI dictionaryAPI = new DictionaryAPI();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter start word: ");
            String start = scanner.nextLine();

            System.out.print("Enter end word: ");
            String end = scanner.nextLine();

            scanner.close();

            // Fetch and print the online data for the start and end words
            String startWordData = dictionaryAPI.getOnlineData(start);
            String endWordData = dictionaryAPI.getOnlineData(end);

            System.out.println("Start word data: " + startWordData);
            System.out.println("End word data: " + endWordData);

            UCSWordLadder ladderUCS = new UCSWordLadder("dictionary.txt");
            GBFSWordLadder ladderGBFS = new GBFSWordLadder("dictionary.txt");
            AStarWordLadder ladderAStar = new AStarWordLadder("dictionary.txt");

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
