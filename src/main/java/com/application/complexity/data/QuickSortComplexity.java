package com.application.complexity.data;

public class QuickSortComplexity extends SortComplexity{
    public QuickSortComplexity(int size) {
        super(size, "Quicksort");
    }

    @Override
    protected void sort() {
        quickSort(0, numbers.size()-1);
    }

    public void quickSort(int begin, int end) {
        if (less(begin, end)) {
            int partitionIndex = partition(begin, end);

            quickSort(begin, partitionIndex - 1);
            quickSort(partitionIndex + 1, end);
        }
    }

    private int partition(int begin, int end) {
        int pivot = numbers.get(end);

        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (less(numbers.get(j), pivot)) {
                i++;
                swap(i, j);
            }
        }
        swap(i+1, end);

        return i + 1;
    }
}
