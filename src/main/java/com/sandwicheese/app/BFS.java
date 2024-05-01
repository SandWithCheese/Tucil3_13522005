package com.sandwicheese.app;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BFS {
    private String start;
    private String goal;

    public BFS(String start, String goal) {
        this.start = start;
        this.goal = goal;
    }

    public ArrayList<String> search(File wordlist) {
        Queue<ArrayList<String>> queue = new LinkedList<>();
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
                System.out.println("Path found: " + currentPath);
                return currentPath;
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

        System.out.println("No path found from " + start + " to " + goal);
        return null;
    }
}
