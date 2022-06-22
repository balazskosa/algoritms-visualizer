package com.application.complexity.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TournamentSortComplexity extends SortComplexity {

    public List<Integer> tournamentTree;
    private int n;

    public TournamentSortComplexity(int size) {
        super(size, "Tournament Sort");
    }

    @Override
    protected void sort() {
        setTournamentTree();

        buildTournamentTree();
        write();
        int r = n - 1;
        while (!less(r, 1)) {
            tournamentTreeMax();
            write();
            r = r - 1;
        }
    }

    private void setTournamentTree() {
        n = numbers.size();
        tournamentTree = new ArrayList<>(2 * n - 1);
        IntStream.range(0, n - 1).map(i -> 0).forEach(tournamentTree::add);
        tournamentTree.addAll(numbers);
        numbers.clear();
    }

    private void buildTournamentTree() {
        for (int j = n - 1; j >= 1; j--) {

            int max = max(tournamentTree.get(2 * j - 1), tournamentTree.get(2 * j));
            set(j-1, max);
        }
    }

    private void write() {
        swapCounter++;
        numbers.add(0, tournamentTree.get(0));
    }
    private void tournamentTreeMax() {
        int j = 1;

        while (!greater(j, n - 1)) {
            if (!notEqual(tournamentTree.get(j - 1), tournamentTree.get(2 * j - 1))) {
                j = 2 * j;
            } else {
                j = 2 * j + 1;
            }
        }

        set(j-1, Integer.MIN_VALUE);
        j = j / 2;

        while (!less(j, 1)) {
            int max = max(tournamentTree.get(2 * j - 1), tournamentTree.get(2 * j));
            set(j-1, max);
            j = j / 2;
        }
    }

    @Override
    protected void set(int index, int value) {
        swapCounter++;
        tournamentTree.set(index, value);
    }

    private int max(int first, int second) {
        comparisonCounter++;
        return Math.max(first, second);
    }

}
