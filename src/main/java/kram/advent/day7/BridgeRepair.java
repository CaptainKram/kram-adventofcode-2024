package kram.advent.day7;

import kram.advent.utils.ReadFromFile;

import java.util.*;

public class BridgeRepair {

    public static Map<Long, List<Long>> calibrationMap = new HashMap<>();

    public static void main(String[] args) {
        List<String> lines = ReadFromFile.readFileList("bridgeRepair");
        fillCalibrationMap(lines);

        long sum = 0;
        for (Map.Entry<Long, List<Long>> entry : calibrationMap.entrySet()) {
            long result = entry.getKey();
            List<Long> values = entry.getValue();
            if (canBeCalibrated(result, values)) {
                sum += result;
            }
        }
        System.out.println(sum);
    }

    public static void fillCalibrationMap(List<String> lines) {
        for (String s : lines) {
            String[] splitLine = s.split(": ");
            long result = Long.parseLong(splitLine[0]);
            List<Long> values = Arrays.stream(splitLine[1].split(" ")).map(Long::parseLong).toList();
            calibrationMap.put(result, values);
        }
    }

    public static boolean canBeCalibrated(long result, List<Long> values) {
        List<Long> results = new ArrayList<>();
        results.add(values.get(0));
        for (int i = 1; i < values.size(); i++) {
            if (i == values.size() - 1) {
                List<Long> finalList = calibrate(results, values.get(i), result);
                return finalList.contains(result);
            } else {
                results = calibrate(results, values.get(i), result);
            }
        }
        return false;
    }

    private static List<Long> calibrate(List<Long> results , long value, long result) {
        List<Long> temp = new ArrayList<>();
        for (Long num : results) {
            if (num > result) {
                continue;
            }
            temp.add(num * value);
            temp.add(num + value);
            temp.add(concatNumbers(num, value));
        }
        results.addAll(temp);
        return temp;
    }

    private static long concatNumbers(long one, long two) {
        String valuesAsString = String.valueOf(one).concat(String.valueOf(two));
        return Long.parseLong(valuesAsString);
    }

}
