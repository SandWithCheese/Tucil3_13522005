package com.sandwicheese.app;

import java.io.File;

public class UCS {
    // Pada UCS permainan word ladder, definisi dari g(n) adalah jarak dari node
    // awal ke node saat ini. Karena pada permainan word ladder kita hanya
    // diperbolehkan untuk mengubah satu huruf pada setiap langkahnya, maka jarak
    // dari node awal ke node saat ini adalah jumlah huruf yang berbeda antara kedua
    // node tersebut. Oleh karena itu, essentially UCS pada permainan word ladder
    // adalah sama dengan BFS.

    private String start;
    private String goal;

    public UCS(String start, String goal) {
        this.start = start;
        this.goal = goal;
    }

    public void search(File wordlist) {
        BFS bfs = new BFS(start, goal);
        bfs.search(wordlist);
    }
}
