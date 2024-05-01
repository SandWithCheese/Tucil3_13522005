package com.sandwicheese.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NodeUtil {
    public static boolean isNeighbor(String word1, String word2) {
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count++;
            }
        }
        return count == 1;
    }

    public static ArrayList<String> getNeighbors(String word, File wordList) {
        ArrayList<String> neighbors = new ArrayList<String>();
        try {
            Scanner myReader = new Scanner(wordList);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (isNeighbor(word, data)) {
                    neighbors.add(data);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        return neighbors;
    }

    public static Boolean isInDictionary(String word, File wordList) {
        try {
            Scanner myReader = new Scanner(wordList);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (word.equals(data)) {
                    myReader.close();
                    return true;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return false;
    }
}
