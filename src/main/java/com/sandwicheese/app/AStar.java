package com.sandwicheese.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar {
    // Pada A* permainan word ladder, definisi dari g(n) adalah jarak dari node
    // awal ke node saat ini. Definisi dari h(n) adalah jarak hamming dari node saat
    // ini ke node tujuan. Fungsi f(n) adalah g(n) + h(n). Himpunan calon solusi
    // yang di-generate oleh A* adalah himpunan yang berisi node-node yang memiliki
    // nilai f(n) yang paling kecil. A* adalah gabungan dari UCS dan GBFS.

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

    private int cost(ArrayList<String> path) {
        return path.size() - 1;
    }

    private int function(ArrayList<String> path) {
        return cost(path) + heuristic(path);
    }

    public AStar(String start, String goal) {
        this.start = start;
        this.goal = goal;
    }

    public Tuple<ArrayList<String>, Integer> search(File wordlist) {
        PriorityQueue<ArrayList<String>> queue = new PriorityQueue<>(Comparator.comparingInt(this::function));
        Set<String> visited = new HashSet<>();

        ArrayList<String> initialPath = new ArrayList<>();
        initialPath.add(start);
        queue.add(initialPath);
        visited.add(start);

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
                }
            }
        }

        return null;
    }
}
