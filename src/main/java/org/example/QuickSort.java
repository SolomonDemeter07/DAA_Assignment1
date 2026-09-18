package org.example;
import java.util.Random;

public class QuickSort {
    private static final Random random = new Random();

    private static void sort(int arr[], Metrics metrics) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1, metrics);
    }


    private static void quickSort(int[] arr, int left, int right, Metrics metrics) {
        metrics.enterRecursion();
        while (left < right) {
            int[] bounds = partition(arr, left, right, metrics);
            int lt = bounds[0];
            int gt = bounds[1];
            int leftSize = lt - left;
            int rightSize = right - gt;

            if (leftSize < rightSize) {
                quickSort(arr, left, lt - 1, metrics);
                left = gt + 1;
            } else {
                quickSort(arr, gt + 1, right, metrics);
                right = lt - 1;
            }
        }
        metrics.exitRecursion();

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int[] partition(int[] arr, int left, int right, Metrics metrics) {
        int pivotIndex = left + random.nextInt(right - left + 1);
        int pivot = arr[pivotIndex];
        swap(arr, left, pivotIndex);
        int lt = left;
        int i = left + 1;
        int gt = right;

        while (i <= gt) {
            metrics.addComparison();
            if (arr[i] < pivot) {
                swap(arr, i, lt);
                lt++;
                i++;
            } else {
                metrics.addComparison();
                if (arr[i] > pivot) {
                    swap(arr, i, gt);
                    gt--;
                } else {
                    i++;
                }
            }
        }
        return new int[]{lt, gt};
    }
}




