package com.application.complexity.data;

public class BubbleSortComplexity extends SortComplexity {
    public BubbleSortComplexity(int size) {
        super(size, "Bubble Sort");
    }

    @Override
    protected void sort() {
        int n = this.numbers.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparisonCounter++;
                if (this.numbers.get(j) > this.numbers.get(j + 1)) {
                    swapCounter++;
                    int temp = this.numbers.get(j);
                    this.numbers.set(j, this.numbers.get(j + 1));
                    this.numbers.set(j + 1, temp);
                }
            }
        }
    }
}
