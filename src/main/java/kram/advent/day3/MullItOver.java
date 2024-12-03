package kram.advent.day3;

import kram.advent.day2.ReadFromFile;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MullItOver {

    public static void main(String[] args) {

        String input = ReadFromFile.readFileString("corruptedmul");

        Pattern pattern = Pattern.compile("(mul\\([0-9]+,[0-9]+\\))|(do\\(\\))|(don't\\(\\))");
        Matcher matcher = pattern.matcher(input);

        ArrayList<String> muls = new ArrayList<>();
        boolean add = true;
        while (matcher.find()) {
            String matched = matcher.group();
            if (matched.equals("do()")) {
                add = true;
            } else if (matched.equals("don't()")) {
                add = false;
            } else {
                if (add) {
                    muls.add(matched);
                }
            }
        }

        int result = 0;
        for (String s : muls) {
            int num1 = Integer.parseInt(s.substring(4, s.indexOf(',')));
            int num2 = Integer.parseInt(s.substring(s.indexOf(',') + 1, s.length() - 1));
            result += num1*num2;
        }
        System.out.println(result);

    }

}
