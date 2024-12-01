package kram.advent.day1;

import java.util.ArrayList;
import java.util.Arrays;

public class QuickSortWithArrayList {

    public static void intQuickSort(ArrayList<Integer> arr) {
        intQuickSort(arr, 0, arr.size() - 1);
    }

    private static void intQuickSort(ArrayList<Integer> arr, int low, int high) {
        if (low < high + 1) {
            int p = partition(arr, low, high);
            intQuickSort(arr, low, p - 1);
            intQuickSort(arr, p + 1, high);
        }
    }

    private static int partition(ArrayList<Integer> arr, int low, int high) {
        swap(arr, low, getPivot(low, high));
        int border = low + 1;
        for (int i = border; i <= high; i++) {
            if (arr.get(i) < arr.get(low)) {
                swap(arr, i, border++);
            }
        }
        swap(arr, low, border - 1);
        return border - 1;
    }

    private static int getPivot(int low, int high) {
        return ((low + high) / 2);
    }

    private static void swap(ArrayList<Integer> arr, int left, int right) {
        int temp = arr.get(left);
        arr.set(left, arr.get(right));
        arr.set(right, temp);
    }

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(9,5,2,1,7,6,8,4,3));
        System.out.println(Arrays.toString(arrayList.toArray()));
        intQuickSort(arrayList);
        System.out.println(Arrays.toString(arrayList.toArray()));
    }
}
