package org.example;
import java.util.Random;

public class QuickSort {
    private static final Random random = new Random();

    private static void sort(int arr[]) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }


    private static void quickSort(int[] arr, int left, int right) {
        while (left < right) {
            int[] bounds = partition(arr, left, right);
            int lt = bounds[0];
            int gt = bounds[1];
            int leftSize = lt - left;
            int rightSize = right - gt;

            if (leftSize < rightSize) {
                quickSort(arr, left, lt - 1);
                left = gt + 1;
            } else {
                quickSort(arr, gt + 1, right);
                right = lt - 1;
            }
        }

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int[] partition(int[] arr, int left, int right) {
        int pivotIndex = left + random.nextInt(right - left + 1);
        int pivot = arr[pivotIndex];
        swap(arr, left, pivotIndex);
        int lt = left;
        int i = left + 1;
        int gt = right;

        while (i <= gt) {
            if (arr[i] < pivot) {
                swap(arr, i, lt);
                lt++;
                i++;
            } else if(arr[i] > pivot){
                swap(arr, i, gt);
                gt--;
            } else {
                i++;
            }

        }
        return new int[]{lt, gt};
    }
}




