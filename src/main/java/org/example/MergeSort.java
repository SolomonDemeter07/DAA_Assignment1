package org.example;

public class MergeSort {
    public static void sort(int[] arr, Metrics metrics){
        if (arr == null || arr.length <= 1) {
            return;
        }
        int[] temp = new int[arr.length];
        mergeSortRecursive(arr, temp, 0, arr.length - 1, metrics);
    }

    private static void mergeSortRecursive(int[] arr, int[] temp, int left, int right, Metrics metrics){
        metrics.enterRecursion();

        if (right - left + 1 <= 15){
            insertionSort(arr, left, right, metrics);
            metrics.exitRecursion();
            return;
        }

        int mid = left + (right - left)/2;
        mergeSortRecursive(arr, temp, left, mid, metrics);
        mergeSortRecursive(arr, temp, mid + 1, right, metrics);
        merge(arr, temp, left, mid, right, metrics);
    }
    private static void insertionSort(int[] arr, int left, int right, Metrics metrics){
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                metrics.addComparison();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
            }
        }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right, Metrics metrics){
        int i = left;
        int j = mid +1;
        int k = left;

        while (i <= mid && j <= right) {
            metrics.addComparison();
            if (arr[i] <= arr[j]) {
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            temp[k] = arr[i];
            i++;
            k++;
        }
        while (j <= right) {
            temp[k] = arr[j];
            j++;
            k++;
        }
        for (int m = left; m <= right; m++) {
            arr[m] = temp[m];
        }
    }
}
