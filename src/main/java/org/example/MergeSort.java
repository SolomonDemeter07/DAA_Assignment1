package org.example;

public class MergeSort {
    public static void sort(int[] arr){
        if (arr == null || arr.length <= 1) {
            return;
        }
        int[] temp = new int[arr.length];
        mergeSortRecursive(arr, temp, 0, arr.length - 1);
    }

    private static void mergeSortRecursive(int[] arr, int[] temp, int left, int right){
        if (right - left + 1 <= 15){
            insertionSort(arr, left, right);
        }

        int mid = left + (right - left)/2;
        mergeSortRecursive(arr, temp, left, mid);
        mergeSortRecursive(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
    }
    private static void insertionSort(int[] arr, int left, int right){
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
            }
        }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right){
        int i = left;
        int j = mid +1;
        int k = left;

        while (i <= mid && j <= right) {
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
