package com.application.visualizer.data.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TournamentSort {
    public List<Integer> numbers;
    public List<Integer> tournamentTree;
    public int n;
    public TournamentSort(List<Integer> list) {
        n = list.size();
        this.numbers = new ArrayList<>();
        this.tournamentTree = new ArrayList<>(2 * list.size() -1);
        IntStream.range(0, list.size() -1).map(i -> 0).forEach(tournamentTree::add);
        tournamentTree.addAll(list);
    }


    public void sort() {
        tournamentTreeFillOut();
        write();

        int r = n-1;
        while(r >= 1) {
            tournamentTreeMax();
            write();
            r = r-1;
        }

    }

    public void tournamentTreeFillOut() {
        for(int j = n - 1; j >= 1; j--) {
            int max = Math.max(tournamentTree.get(2*j  -1 ), tournamentTree.get(2*j));
            tournamentTree.set(j-1, max);
        }

    }

    public void write() {
        numbers.add(0, tournamentTree.get(0));
    }

    public void tournamentTreeMax() {
        int j = 1;
        while(j <= n -1) {
            if(tournamentTree.get(j -1).intValue() == tournamentTree.get(2*j - 1).intValue()) {
                j = 2*j;
            } else {
                j = 2*j+1;
            }
        }
        tournamentTree.set(j-1, Integer.MIN_VALUE);

        j = j/2;

        while(j>=1) {
            int max = Math.max(tournamentTree.get(2*j-1), tournamentTree.get(2*j));
            tournamentTree.set(j-1, max);
            j = j/2;
        }
    }
}
