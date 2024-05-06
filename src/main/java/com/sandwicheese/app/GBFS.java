package com.sandwicheese.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class GBFS {
    // Definisi h(n) pada Greedy Best First Search (GBFS) adalah jarak hamming dari
    // node saat ini ke node tujuan. Jarak hamming adalah jumlah huruf yang berbeda
    // pada posisi yang sama antara kedua node tersebut. Himpunan calon solusi yang
    // di-generate oleh GBFS adalah himpunan yang berisi node-node yang memiliki
    // nilai h(n) yang paling kecil.

    private String start;
    private String goal;

    private int hamming(String word1, String word2) {
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    private int heuristic(ArrayList<String> path) {
        String current = path.get(path.size() - 1);
        return hamming(current, goal);
    }

    public GBFS(String start, String goal) {
        this.start = start;
        this.goal = goal;
    }

    public Tuple<ArrayList<String>, Integer> search(File wordlist) {
        PriorityQueue<ArrayList<String>> queue = new PriorityQueue<>(Comparator.comparingInt(this::heuristic));
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();

        ArrayList<String> initialPath = new ArrayList<>();
        initialPath.add(start);
        queue.add(initialPath);
        visited.add(start);
        parent.put(start, null);

        int currentDepth = 0;

        while (!queue.isEmpty()) {
            ArrayList<String> currentPath = queue.poll();
            String current = currentPath.get(currentPath.size() - 1);

            if (currentPath.size() > currentDepth) {
                currentDepth = currentPath.size();
                System.out.println("Searching depth " + currentDepth);
            }

            if (current.equals(goal)) {
                Tuple<ArrayList<String>, Integer> result = new Tuple<>(currentPath, visited.size());
                return result;
            }

            ArrayList<String> neighbors = NodeUtil.getNeighbors(current, wordlist);
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    ArrayList<String> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbor);
                    queue.add(newPath);
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                }
            }
        }

        return null;
    }
}
