package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Benchmark {

    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 100000, 1000000};
        String[] types = {"random", "sorted", "duplicates"};
        String[] algorithms = {"MergeSort", "QuickSort", "QuickSelect"};

        try (FileWriter writer = new FileWriter("results.csv")) {
            writer.write("algorithm,input,n,time_ms,comparisons,max_depth\n");

            for (String algo : algorithms) {
                for (String type : types) {
                    for (int n : sizes) {
                        System.out.println("Launch: " + algo + " | " + type + " | n=" + n);
                        runAndRecord(writer, algo, type, n);
                    }
                }
            }
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] generateRandom(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt();
        }
        return arr;
    }

    private static int[] generateSorted(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    private static int[] generateDuplicates(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(10);
        }
        return arr;
    }


    private static void runAndRecord(FileWriter writer, String algo, String type, int n) throws IOException {
        int[] originalArray;

        if (type.equals("random")) {
            originalArray = generateRandom(n);
        } else if (type.equals("sorted")) {
            originalArray = generateSorted(n);
        } else {
            originalArray = generateDuplicates(n);
        }

        Metrics[] results = new Metrics[5];

        for (int i = 0; i < 5; i++) {
            int[] arr = Arrays.copyOf(originalArray, originalArray.length);
            Metrics metrics = new Metrics();

            metrics.startTimer();
            if (algo.equals("MergeSort")) {
                MergeSort.sort(arr, metrics);
            } else if (algo.equals("QuickSort")) {
                QuickSort.sort(arr, metrics);
            } else if (algo.equals("QuickSelect")) {
                QuickSelect.select(arr, arr.length / 2, metrics);
            }
            metrics.stopTimer();

            results[i] = metrics;
        }
        Arrays.sort(results, (m1, m2) -> Double.compare(m1.getTimeMs(), m2.getTimeMs()));

        Metrics median = results[2];
        String line = algo + "," + type + "," + n + "," + median.getTimeMs() + "," +
                median.getComparisons() + "," + median.getMaxDepth() + "\n";

        writer.write(line);
    }
}