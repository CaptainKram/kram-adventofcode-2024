package kram.advent.day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class HistorianHysteria {

    public static void main(String[] args) {
        try {
            ClassLoader cl = HistorianHysteria.class.getClassLoader();
            String s = Files.readString(Path.of(cl.getResource("pairs").toURI()));
            String[] pairsArr = s.split("[ ]{3}|\n");
            ArrayList<Integer> arr1 = new ArrayList<>();
            ArrayList<Integer> arr2 = new ArrayList<>();
            for (int i = 0; i < pairsArr.length -1; i+=2) {
                arr1.add(Integer.parseInt(pairsArr[i]));
                arr2.add(Integer.parseInt(pairsArr[i+1]));
            }

            QuickSortWithArrayList.intQuickSort(arr1);
            QuickSortWithArrayList.intQuickSort(arr2);

            System.out.println(Arrays.toString(arr1.toArray()));

            int totalDist = 0;
            for (int i = 0; i < arr1.size(); i++) {
                int left = arr1.get(i);
                int right = arr2.get(i);
                if (left > right) {
                    totalDist += left - right;
                } else {
                    totalDist += right - left;
                }
            }
            System.out.printf("Total distance: %s\n", totalDist); // For task 1

            int similarityScore = 0;
            for (int i = 0; i < arr1.size(); i++) {
                int leftCount = 0;
                int left = arr1.get(i);
                for (int j = 0; j < arr1.size(); j++) {
                    if (left == arr2.get(j)) {
                        leftCount++;
                    }
                }
                similarityScore += left * leftCount;
            }
            System.out.printf("Similarity score: %s\n", similarityScore);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

}
