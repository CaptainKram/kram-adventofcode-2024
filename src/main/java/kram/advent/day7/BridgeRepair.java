package kram.advent.day7;

import kram.advent.utils.ReadFromFile;

import java.util.*;

public class BridgeRepair {

    public static final Map<Long, List<Long>> calibrationMap = new HashMap<>();

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
            String[] splitLine = s.split(":");
            long result = Long.parseLong(splitLine[0]);
            List<Long> values = Arrays.stream(splitLine[1].trim().split(" ")).map(Long::parseLong).toList();
            calibrationMap.put(result, values);
        }
    }

    public static boolean canBeCalibrated(long result, List<Long> values) {
        List<Long> results = new ArrayList<>();
        if (values.size() > 1) {
            results.add(values.get(0) + values.get(1));
            results.add(values.get(0) * values.get(1));
        }
        for (int i = 2; i < values.size(); i++) {
            calibrate(results, results.size(), values.get(i));
        }
        return results.contains(result);
    }

    private static void calibrate(List<Long> results , int limit, long value) {
        List<Long> temp = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            temp.add(results.get(i) * value);
            temp.add(results.get(i) + value);
        }
        results.addAll(temp);
    }

}
