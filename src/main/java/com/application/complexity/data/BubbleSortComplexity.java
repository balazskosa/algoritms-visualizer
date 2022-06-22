package com.application.complexity.data;

public class BubbleSortComplexity extends SortComplexity {
    public BubbleSortComplexity(int size) {
        super(size, "Bubble Sort");
    }

    @Override
    protected void sort() {
        int n = numbers.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (greater(numbers.get(j), numbers.get(j + 1))) {
                    swap(j, j + 1);
                }
            }
        }
    }
}
