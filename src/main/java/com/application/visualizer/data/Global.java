package com.application.visualizer.data;

import com.vaadin.flow.internal.Pair;

import java.util.List;

public class Global {
    public static final String BASE_BACKGROUND_COLOR = "tomato";
    public static final String SELECTED_BACKGROUND_COLOR = "#3fadd9";
    public static final String SECOND_SELECTED_BACKGROUND_COLOR = "#ccb23d";
    public static final String THIRD_SELECTED_BACKGROUND_COLOR = "#6927db";
    public static final String SORTED_BACKGROUND_COLOR = "#329456";
    public static final String BASE_BORDER = "2px solid black";
    public static final String SELECTED_BORDER = "2px solid white";
    public static final String SORTED_BORDER = "2px solid #013317";

    public static final List<String> ALGORITHMS = List.of("Maximum Selection Sort",
            "Tournament Sort", "Insertion Sort", "Bubble Sort", "Mergesort", "Quicksort", "Heapsort");
    public static final String algorithm = ALGORITHMS.get(0);
    public static final List<Pair<String, Integer>> SIZES = List.of(
            new Pair<>("Small", 8),
            new Pair<>("Medium",12),
            new Pair<>("Large", 16),
        new Pair<>("Custom", null));

    public static final Pair<String, Integer> size = SIZES.get(0);

    private Global() {}
}
