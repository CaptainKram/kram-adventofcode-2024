package kram.advent.day1;

import java.util.Arrays;

public class QuickSortWIthArray {

    public static void main(String[] args) {
        int[] arr = {9,5,2,1,7,6,8,4,3};
        System.out.println(Arrays.toString(arr));
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high + 1) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        swap(arr, low, getPivot(low, high));
        int border = low + 1;
        for (int i = border; i <= high; i++) {
            if (arr[i] < arr[low]) {
                swap(arr, i, border++);
            }
        }
        swap(arr, low, border - 1);
        return border - 1;
    }

    private static int getPivot(int low, int high) {
        return (low + high) / 2;
    }

    private static void swap(int[] arr, int low, int high) {
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
    }

}
