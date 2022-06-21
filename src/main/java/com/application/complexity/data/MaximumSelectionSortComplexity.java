package com.application.complexity.data;

public class MaximumSelectionSortComplexity extends SortComplexity {
    public MaximumSelectionSortComplexity(int size) {
        super(size, "Maximum Selection Sort");
    }

    @Override
    protected void sort() {
        int j = numbers.size();

        while (j > 1) {
            comparisonCounter++;
            //--------------findMax
            int max = 0;
            for (int i = 1; i < j; i++) {
                comparisonCounter++;
                if (numbers.get(max) < numbers.get(i)) {
                    max = i;
                }
            }
            //--------------swap
            comparisonCounter++;
            if (max != j - 1) {
                swapCounter++;
                int swap = this.numbers.get(max);
                this.numbers.set(max, this.numbers.get(j - 1));
                this.numbers.set(j - 1, swap);
            }
            j = j - 1;
        }
        //end while
        comparisonCounter++;
    }
}