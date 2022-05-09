package com.application.complexity;

public class InsertionSortComplexity extends SortComplexity{
    public InsertionSortComplexity(int size) {
        super(size);
    }

    @Override
    public void sort() {
        for (int i = 0; i < this.numbers.size(); i++) {

            int current = this.numbers.get(i);
            int j = i - 1;

            while (j >= 0) {
                comparisonCounter = comparisonCounter + 2;
                if(this.numbers.get(j) > current) {
                    swapCounter++;
                    this.numbers.set(j + 1, this.numbers.get(j));
                    j--;
                } else {
                    comparisonCounter--;
                    break;
                }
            }
            comparisonCounter++;

            swapCounter++;
            this.numbers.set(j + 1, current);
        }
    }
}
