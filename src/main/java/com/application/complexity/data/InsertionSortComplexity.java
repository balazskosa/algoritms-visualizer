package com.application.complexity.data;

public class InsertionSortComplexity extends SortComplexity{
    public InsertionSortComplexity(int size) {
        super(size, "Insertion Sort");
    }

    @Override
    public void sort() {
        int n = numbers.size();
        for (int i = 1; i < n; ++i) {
            int key = numbers.get(i);
            int j = i - 1;
            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (!less(j, 0) && greater(numbers.get(j), key)) {
                andOperator();

                set(j+1, numbers.get(j));
                j = j - 1;
            }
            set(j+1, key);
        }
    }
}
