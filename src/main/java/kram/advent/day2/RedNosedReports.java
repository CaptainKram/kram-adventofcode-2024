package kram.advent.day2;

import kram.advent.utils.ReadFromFile;

import java.util.ArrayList;
import java.util.List;

public class RedNosedReports {

    private static boolean isSafe = true;
    private static int safe = 0;

    public static void main(String[] args) {

        List<String> list = ReadFromFile.readFileList("reports");

        Unsafe unsafe = new Unsafe();
        for (String s : list) {
            String[] report = s.split(" ");
            isSafe = true;
            falling(report);
            if (isSafe) {
                safe++;
            } else {
                unsafe.falling = true;
            }
            isSafe = true;
            rising(report);
            if (isSafe) {
                safe++;
            } else {
                unsafe.rising = true;
            }
            if (unsafe.falling && unsafe.rising) {
                unsafe.unsafeReports.add(report);
            }
            unsafe.falling = false;
            unsafe.rising = false;
        }

        for (int j = 0; j < unsafe.unsafeReports.size(); j++) {
            String[] report = unsafe.unsafeReports.get(j);
            for (int i = 0; i < report.length; i++) {
                String[] chewedReport = chewedArray(report, i);
                isSafe = true;
                falling(chewedReport);
                if (isSafe) {
                    safe++;
                    break;
                }
                isSafe = true;
                rising(chewedReport);
                if (isSafe) {
                    safe++;
                    break;
                }
            }
        }

        System.out.println(safe);

    }

    private static void rising(String[] report) {
        int dampener = 0;
        for (int j = 0; j < report.length - 1; j++) {
            int current = Integer.parseInt(report[j]);
            int next = Integer.parseInt(report[j + 1]);
            if (current <= next) {
                isSafe = false;
                break;
            }
            if (current - next > 3) {
                isSafe = false;
                break;
            }
        }
    }

    private static void falling(String[] report) {
        int dampener = 0;
        for (int j = 0; j < report.length - 1; j++) {
            int current = Integer.parseInt(report[j]);
            int next = Integer.parseInt(report[j + 1]);
            if (current >= next) {
                isSafe = false;
                break;
            }
            if (next - current > 3) {
                isSafe = false;
                break;
            }
        }
    }

    private static String[] chewedArray(String[] arr, int i) {
        String[] arr2 = new String[arr.length - 1];
        for (int j = 0, k = 0; j < arr.length; j++) {
            if (j == i) {
                continue;
            }
            arr2[k++] = arr[j];
        }
        return arr2;
    }

    private static class Unsafe {
        boolean rising;
        boolean falling;
        List<String[]> unsafeReports = new ArrayList<>();
    }

}
