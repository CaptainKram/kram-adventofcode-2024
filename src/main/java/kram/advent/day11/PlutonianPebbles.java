package kram.advent.day11;

import kram.advent.utils.ReadFromFile;

import java.util.*;

public class PlutonianPebbles {

    private static final String plutonianPebblesTestInput1 = "125 17";
    private static final List<String> stones = new ArrayList<>();

    private static final int blinkTimes = 75;

    public static void main(String[] args) {
        String input = ReadFromFile.readFileString("stones");
        fillInitialStonesList(input);

        long result = blinkXTimes();
        System.out.println(result);
    }

    private static long blinkXTimes() {
        List<String> nextStones = new ArrayList<>(stones);
        Map<String, Long> currentBadge = new HashMap<>();
        for (String s : nextStones) {
            currentBadge.put(s, currentBadge.getOrDefault(s, 0L) + 1);
        }
        for (int i = 0; i < blinkTimes; i++) {
            Map<String, Long> nextBadge = new HashMap<>();
            for (Map.Entry<String, Long> entry : currentBadge.entrySet()) {
                nextStones = blink(entry.getKey());
                for (String s : nextStones) {
                    nextBadge.put(s, nextBadge.getOrDefault(s, 0L) + entry.getValue());
                }
            }
            currentBadge = nextBadge;
        }
        return currentBadge.values().stream().mapToLong(l -> l).sum();
    }

    private static List<String> blink(String stone) {
        List<String> list = new ArrayList<>();
        int length = stone.length();
        if (stone.equals("0")) {
            stone = "1";
        } else if (length % 2 == 0) {
            String stone2 = stone.substring(length / 2);
            while (stone2.charAt(0) == '0' && stone2.length() > 1) {
                stone2 = stone2.substring(1);
            }
            stone = stone.substring(0, length / 2);
            list.add(stone2);
        } else {
            long engravingNum = Long.parseLong(stone);
            engravingNum *= 2024;
            stone = String.valueOf(engravingNum);
        }
        list.add(stone);
        return list;
    }

    private static void fillInitialStonesList(String input) {
        String[] initialStones = input.split(" ");
        Collections.addAll(stones, initialStones);
    }

}
