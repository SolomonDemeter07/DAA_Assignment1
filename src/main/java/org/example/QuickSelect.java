package org.example;
import java.util.Random;

public class QuickSelect {
    private static final Random random = new Random();

    public static int select(int[] a, int k){
        if(a == null || k < 0 || k >= a.length){
            throw new IllegalArgumentException("array is empty or index k is out of bounds");
        }

        int left = 0;
        int right = a.length - 1;

        while(left <= right){
            int[] bounds = partition(a, left, right);
            int lt = bounds[0];
            int gt = bounds[1];

            if(k >= lt && k <= gt){
                return a[k];
            }else if(k < lt){
                right = lt - 1;
            }else{
                left = gt + 1;
            }

        }
        return a[k];
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
            } else if (arr[i] > pivot) {
                swap(arr, i, gt);
                gt--;
            } else {
                i++;
            }
        }
        return new int[]{lt, gt};
    }
}
