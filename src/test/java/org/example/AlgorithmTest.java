package org.example;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmTest {

    private final Random random = new Random();
    @Test
    public void testSortsCorrectness() {
        for (int i = 0; i < 100; i++) {
            int[] original = generateRandomArray(random.nextInt(1000) + 10);

            int[] expected = Arrays.copyOf(original, original.length);
            Arrays.sort(expected);

            int[] mergeArr = Arrays.copyOf(original, original.length);
            MergeSort.sort(mergeArr, new Metrics());
            assertArrayEquals(expected, mergeArr, "MergeSort failed on array " + i);

            int[] quickArr = Arrays.copyOf(original, original.length);
            QuickSort.sort(quickArr, new Metrics());
            assertArrayEquals(expected, quickArr, "QuickSort failed on array " + i);
        }
    }

    @Test
    public void testEdgeCases() {
        int[] empty = {};
        MergeSort.sort(empty, new Metrics());
        assertArrayEquals(new int[]{}, empty);

        int[] single = {67};
        QuickSort.sort(single, new Metrics());
        assertArrayEquals(new int[]{67}, single);

        int[] equals = {7, 7, 7, 7, 7};
        QuickSort.sort(equals, new Metrics());
        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, equals);

        int[] sorted = {1, 2, 3, 4, 5};
        MergeSort.sort(sorted, new Metrics());
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sorted);
    }

    @Test
    public void testQuickSortDepth() {
        int n = 100000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        Metrics metrics = new Metrics();
        QuickSort.sort(arr, metrics);

        int maxAllowedDepth = (int) (2 * (Math.log(n) / Math.log(2)));

        assertTrue(metrics.getMaxDepth() <= maxAllowedDepth,
                "Recursion depth exceeded! Current:" + metrics.getMaxDepth() + ", max allowed:" + maxAllowedDepth);
    }

    @Test
    public void testQuickSelectCorrectness() {
        for (int i = 0; i < 100; i++) {
            int[] original = generateRandomArray(random.nextInt(1000) + 10);
            int k = random.nextInt(original.length);

            int[] sorted = Arrays.copyOf(original, original.length);
            Arrays.sort(sorted);
            int expected = sorted[k];

            int actual = QuickSelect.select(Arrays.copyOf(original, original.length), k, new Metrics());

            assertEquals(expected, actual, "QuickSelect found incorrect value");
        }
    }

    private int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(10000);
        }
        return arr;
    }
}