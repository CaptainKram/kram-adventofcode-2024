package kram.advent.day5;

import kram.advent.utils.ReadFromFile;

import java.util.*;

public class PrintQueue {

    private static final Map<Integer, List<Integer>> pageOrderingRules = new HashMap<>();
    private static final List<List<Integer>> updates = new ArrayList<>();

    public static void main(String[] args) {

        List<String> input = ReadFromFile.readFileList("printques");

        parseInputs(input);

        int sum = 0;
        int incorrectSum = 0;
        for (List<Integer> update : updates) {
            boolean correct = checkIfCorrect(update);
            if (correct) {
                sum += update.get(update.size() / 2);
            } else {
                makeCorrect(update);
                incorrectSum += update.get(update.size() / 2);
            }
        }

        System.out.println(incorrectSum);
    }

    private static void parseInputs(List<String> input) {
        boolean rules = true;
        for (String s : input) {
            if (s.isEmpty()) {
                rules = false;
                continue;
            }
            if (rules) {
                int pipe = s.indexOf('|');
                int key = Integer.parseInt(s.substring(0, pipe));
                int value = Integer.parseInt(s.substring(pipe + 1));
                if (pageOrderingRules.containsKey(key)) {
                    pageOrderingRules.get(key).add(value);
                } else {
                    pageOrderingRules.put(key, new ArrayList<>());
                    pageOrderingRules.get(key).add(value);
                }
            } else {
                String[] updateStringArr = s.split(",");
                List<Integer> update = new ArrayList<>();
                for (int i = 0; i < updateStringArr.length; i++) {
                    update.add(Integer.parseInt(updateStringArr[i]));
                }
                updates.add(update);
            }
        }
    }

    private static void makeCorrect(List<Integer> update) {
        List<Integer> order = new ArrayList<>();
        boolean correct = true;
        for (int i = 0; i < update.size(); i++) {
            for (int j = 0; j < order.size(); j++) {
                if (pageOrderingRules.get(update.get(i)) != null && pageOrderingRules.get(update.get(i)).contains(order.get(j))) {
                    Collections.swap(update, i, i - 1);
                    correct = false;
                    break;
                }
            }
            order.add(update.get(i));
        }
        if (!correct) {
            makeCorrect(update);
        }
    }

    private static boolean checkIfCorrect(List<Integer> update) {
        List<Integer> order = new ArrayList<>();
        for (Integer i : update) {
            for (int j = 0; j < order.size(); j++) {
                if (pageOrderingRules.get(i) != null && pageOrderingRules.get(i).contains(order.get(j))) {
                    return false;
                }
            }
            order.add(i);
        }
        return true;
    }

}
