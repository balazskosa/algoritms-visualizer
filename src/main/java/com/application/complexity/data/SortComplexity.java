package com.application.complexity.data;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class SortComplexity {

    protected String algorithmName;
    protected int swapCounter = 0;
    protected int comparisonCounter = 0;
    protected final List<Integer> numbers;

    public SortComplexity(int size, String algorithmName) {
        this.numbers = generateList(size);
        this.algorithmName = algorithmName;
        sort();
    }

    protected abstract void sort();

    public int getSwapCounter() {
        return swapCounter;
    }

    public int getComparisonCounter() {
        return comparisonCounter;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    private List<Integer> generateList(int size) {
        int min = 1;
        int max = size * 10;
        Set<Integer> numbers = new LinkedHashSet<>(size);
        while (numbers.size() != size) {
            numbers.add(ThreadLocalRandom.current().nextInt(min, max + 1));
        }
        return new ArrayList<>(numbers);
    }

    protected boolean less(int first, int second) {
        comparisonCounter++;
        return first < second;
    }

    protected boolean greater(int first, int second) {
        comparisonCounter++;
        return first > second;
    }

    protected void andOperator() {
        comparisonCounter++;
    }

    protected boolean notEqual(int first, int second) {
        comparisonCounter++;
        return !(first == second);
    }

    protected void set(int index, int value) {
        swapCounter++;
        numbers.set(index, value);
    }

    protected void swap(int first, int second) {
        swapCounter++;
        int tmp = numbers.get(first);
        numbers.set(first, numbers.get(second));
        numbers.set(second, tmp);
    }
}
