package com.application.complexity.data;

public class QuickSortComplexity extends SortComplexity{
    public QuickSortComplexity(int size) {
        super(size);
    }

    @Override
    protected void sort() {
        quickSort(0, numbers.size()-1);
    }

    public void quickSort(int begin, int end) {
        comparisonCounter++;
        if (begin < end) {
            int partitionIndex = partition(begin, end);

            quickSort(begin, partitionIndex - 1);
            quickSort(partitionIndex + 1, end);
        }
    }

    private int partition(int begin, int end) {
        int pivot = this.numbers.get(end);

        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            comparisonCounter++;
            if (this.numbers.get(j) < pivot) {
                i++;
                swapCounter++;
                int swapTemp = this.numbers.get(i);
                this.numbers.set(i, this.numbers.get(j));
                this.numbers.set(j, swapTemp);
            }
        }

        swapCounter++;
        int swapTemp = this.numbers.get(i + 1);
        this.numbers.set(i + 1, this.numbers.get(end));
        this.numbers.set(end, swapTemp);

        return i + 1;
    }
}
