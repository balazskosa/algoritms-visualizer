package com.application.complexity.data;

public class MaximumSelectionSortComplexity extends SortComplexity {
    public MaximumSelectionSortComplexity(int size) {
        super(size, "Maximum Selection Sort");
    }

    @Override
    protected void sort() {
        int j = numbers.size();

        while (greater(j, 1)) {
            //findMax
            int max = 0;
            for (int i = 1; i < j; i++) {
                if (less(numbers.get(max), numbers.get(i))) {
                    max = i;
                }
            }

            //swap
            if (notEqual(max, j-1)) {
                swap(max, j-1);
            }
            j = j - 1;
        }
        //end while
    }
}