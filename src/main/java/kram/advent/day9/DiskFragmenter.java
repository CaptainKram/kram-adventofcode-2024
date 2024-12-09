package kram.advent.day9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiskFragmenter {

    private static final String diskFragmenterTestInput = "2333133121414131402";
    public static final List<File> ids = new ArrayList<>();
    public static final List<Space> spaces = new ArrayList<>();

    public static void main(String[] args) {
//        String input = ReadFromFile.readFileString("diskmap");
        String input = diskFragmenterTestInput;
        fillLists(input);

        List<Memory> blocks = turnToBlocks();
        rearrange(blocks);

        long result = calculateChecksum(blocks);
        System.out.println(result);
    }

    public static long calculateChecksum(List<Memory> blocks) {
        long result = 0;
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i) instanceof Space) {
                continue;
            }
            else if (blocks.get(i) instanceof File) {
                int currentNumber = ((File) blocks.get(i)).getId();
                result += (long) currentNumber * i;
            }
        }
        return result;
    }

    public static void rearrange(List<Memory> blocks) {
        int pointLeft = 0;
        int pointRight = blocks.size() - 1;
        while (pointLeft < pointRight) {
            if (!(blocks.get(pointLeft) instanceof Space)) {
                pointLeft++;
            } else if (!(blocks.get(pointRight) instanceof Space)) {
                Collections.swap(blocks, pointLeft, pointRight);
                pointLeft++;
                pointRight--;
            } else {
                pointRight--;
            }
        }
    }

    public static List<Memory> turnToBlocks() {
        List<Memory> memory = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            addFile(i, memory);
            addSpace(i, memory);
        }
        return memory;
    }

    private static void addFile(int id, List<Memory> memory) {
        File file = ids.get(id);
        for (int i = 0; i < file.getSize(); i++) {
            memory.add(file);
        }
    }

    private static void addSpace(int id, List<Memory> memory) {
        if (id >= spaces.size()) {
            return;
        }
        Space space = spaces.get(id);
        for (int i = 0; i < space.getSize(); i++) {
            memory.add(space);
        }
    }

    public static void fillLists(String input) {
        int counter = 0;
        for (int i = 0; i < input.length(); i++) {
            int currentNumber = Integer.parseInt(String.valueOf(input.charAt(i)));
            if (i % 2 == 0) {
                File file = new File(counter++, currentNumber);
                ids.add(file);
            } else {
                spaces.add(new Space(currentNumber));
            }
        }
    }

}
