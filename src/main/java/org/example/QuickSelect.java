package org.example;
import java.util.Random;

public class QuickSelect {
    private static final Random random = new Random();

    public static int select(int[] a, int k, Metrics metrics){
        if(a == null || k < 0 || k >= a.length){
            throw new IllegalArgumentException("array is empty or index k is out of bounds");
        }

        int left = 0;
        int right = a.length - 1;

        while(left <= right){
            int[] bounds = partition(a, left, right, metrics);
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
