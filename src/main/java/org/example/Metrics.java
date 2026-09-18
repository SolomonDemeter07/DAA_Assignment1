package org.example;

public class Metrics {
    private long comparisons = 0;
    private int maxDepth = 0;
    private int currentDepth = 0;

    private long startTime = 0;
    private long endTime = 0;

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
    }

    public double getTimeMs() {
        return (endTime - startTime) / 1000000.0;
    }

    public void addComparison() {
        comparisons++;
    }

    public long getComparisons() {
        return comparisons;
    }

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void exitRecursion() {
        currentDepth--;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}