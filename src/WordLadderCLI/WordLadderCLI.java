package WordLadderCLI;

import Algorithms.*;
import java.util.*;
import java.io.FileNotFoundException;

public class WordLadderCLI {
    // ANSI escape codes for colors
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";

    // ASCII Art Title
    public static final String ASCII_ART = CYAN
            + "                                                       ,--,                                                        \n"
            +
            CYAN
            + "                                                    ,---.'|                                                        \n"
            +
            CYAN
            + "           .---.                                    |   | :                                                        \n"
            +
            CYAN
            + "          /. ./|                       ,---,        :   : |                    ,---,      ,---,                    \n"
            +
            CYAN
            + "      .--'.  ' ;   ,---.    __  ,-.  ,---.'|        |   ' :                  ,---.'|    ,---.'|            __  ,-. \n"
            +
            CYAN
            + "     /__./ \\ : |  '   ,'\\ ,' ,'/ /|  |   | :        ;   ; '                  |   | :    |   | :          ,' ,'/ /| \n"
            +
            CYAN
            + " .--'.  '   \\' . /   /   |'  | |' |  |   | |        '   | |__   ,--.--.      |   | |    |   | |   ,---.  '  | |' | \n"
            +
            CYAN
            + "/___/ \\ |    ' '.   ; ,. :|  |   ,',--.__| |        |   | :.'| /       \\   ,--.__| |  ,--.__| |  /     \\ |  |   ,' \n"
            +
            CYAN
            + ";   \\  \\;      :'   | |: :'  :  / /   ,'   |        '   :    ;.--.  .-. | /   ,'   | /   ,'   | /    /  |'  :  /   \n"
            +
            CYAN
            + " \\   ;  `      |'   | .; :|  | ' .   '  /  |        |   |  ./  \\__\\/: . ..   '  /  |.   '  /  |.    ' / ||  | '    \n"
            +
            CYAN
            + "  .   \\    .\\  ;|   :    |;  : | '   ; |:  |        ;   : ;    ,\" .--.; |'   ; |:  |'   ; |:  |'   ;   /|;  : |    \n"
            +
            CYAN
            + "   \\   \\   ' \\ | \\   \\  / |  , ; |   | '/  '        |   ,/    /  /  ,.  ||   | '/  '|   | '/  ''   |  / ||  , ;    \n"
            +
            CYAN
            + "    :   '  |--'   `----'   ---'  |   :    :|        '---'    ;  :   .'   \\   :    :||   :    :||   :    | ---'     \n"
            +
            CYAN
            + "     \\   \\ ;                      \\   \\  /                   |  ,     .-./\\   \\  /   \\   \\  /   \\   \\  /           \n"
            +
            CYAN
            + "      '---'                        `----'                     `--`---'     `----'     `----'     `----'            \n"
            + RESET;

    public static void main(String[] args) {
        System.out.println(ASCII_ART);

        try {
            UCSWordLadder ladderUCS = new UCSWordLadder("Dictionary/dictionary.txt");
            GBFSWordLadder ladderGBFS = new GBFSWordLadder("Dictionary/dictionary.txt");
            AStarWordLadder ladderAStar = new AStarWordLadder("Dictionary/dictionary.txt");

            Scanner scanner = new Scanner(System.in);

            // Masukan Start and End Word
            System.out.print(GREEN + "Masukkan Start Word: " + RESET);
            String start = scanner.nextLine().trim().toLowerCase();

            System.out.print(GREEN + "Masukkan End Word: " + RESET);
            String end = scanner.nextLine().trim().toLowerCase();

            // Masukan Algorithm: UCS, GBFS, atau A-Star
            System.out.print(GREEN + "Masukkan Algoritma (" + YELLOW + "UCS" + GREEN + ", " + YELLOW + "GBFS" + GREEN
                    + ", " + YELLOW + "A*" + GREEN + "): " + RESET);
            String algorithm = scanner.nextLine().trim().toUpperCase();

            scanner.close();

            Map<String, Object> result = null;

            // Mengeksekusi Algoritma yang dipilih
            switch (algorithm) {
                case "UCS":
                    System.out.println(BLUE + "\nMenjalankan Algoritma Uniform Cost Search...\n" + RESET);
                    result = ladderUCS.findLadder(start, end);
                    break;
                case "GBFS":
                    System.out.println(BLUE + "\nMenjalankan Algoritma Greedy Best First Search...\n" + RESET);
                    result = ladderGBFS.findLadder(start, end);
                    break;
                case "A*":
                case "A-STAR":
                case "A_STAR":
                    System.out.println(BLUE + "\nMenjalankan Algoritma A-Star...\n" + RESET);
                    result = ladderAStar.findLadder(start, end);
                    break;
                default:
                    System.out.println(RED + "\nMasukan invalid. Masukkan algoritma: " + YELLOW + "UCS" + RED + ", "
                            + YELLOW + "GBFS" + RED
                            + ", atau " + YELLOW + "A*" + RED + "." + RESET);
                    return;
            }

            // Tamplikan hasil
            if (result == null || result.isEmpty()) {
                System.out.println(RED + "Tidak ada solusi yang ditemukan." + RESET);
            } else {
                System.out.println(YELLOW + "Path:" + RESET);
                result.forEach((key, value) -> {
                    if (key.equals("Path")) {
                        List<String> path = (List<String>) value;
                        for (String word : path) {
                            StringBuilder coloredWord = new StringBuilder();
                            for (int i = 0; i < word.length(); i++) {
                                if (i < end.length() && word.charAt(i) == end.charAt(i)) {
                                    coloredWord.append(GREEN).append(word.charAt(i)).append(RESET);
                                } else {
                                    coloredWord.append(word.charAt(i));
                                }
                            }
                            System.out.println(coloredWord);
                        }
                    } else {
                        System.out.println(YELLOW + key + ": " + RESET + value);
                    }
                });
            }

        } catch (FileNotFoundException e) {
            System.out.println(RED + "Kamus tidak ditemukan." + RESET);
        }
    }
}