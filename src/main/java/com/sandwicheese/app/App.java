package com.sandwicheese.app;

import java.io.File;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter starting word: ");
        String start = scanner.next();
        System.out.print("Enter goal word: ");
        String goal = scanner.next();

        if (start.length() != goal.length()) {
            System.out.println("Words must have the same length");
            scanner.close();
            return;
        }

        String wordListFile = "./wordlist/" + start.length() + ".txt";
        File wordList = new File(wordListFile);

        if (!NodeUtil.isInDictionary(start, wordList) || !NodeUtil.isInDictionary(goal, wordList)) {
            System.out.println("Words must be in the dictionary");
            scanner.close();
            return;
        }

        System.out.print("Enter method (ucs/gbfs/astar): ");
        String method = scanner.next();

        if (method.equals("ucs")) {
            UCS ucs = new UCS(start, goal);
            ucs.search(wordList);
        } else if (method.equals("gbfs")) {
            GBFS gbfs = new GBFS(start, goal);
            gbfs.search(wordList);
        } else if (method.equals("astar")) {
            AStar astar = new AStar(start, goal);
            astar.search(wordList);
        } else {
            System.out.println("Invalid method");
            scanner.close();
            return;

        }

        scanner.close();
    }
}
