package kram.advent.day9;

import kram.advent.utils.ReadFromFile;

import java.util.ArrayList;
import java.util.List;

public class DiskFragmenter {

    public static final List<Memory> files = new ArrayList<>();
    public static final List<Memory> spaces = new ArrayList<>();

    public static void main(String[] args) {
        String input = ReadFromFile.readFileString("diskmap");
        fillLists(input);

        List<Memory> blocks = turnToBlocks();
        rearrange(blocks);

        long result = calculateChecksum(blocks);
        System.out.println(result);
    }

    public static long calculateChecksum(List<Memory> blocks) {
        long result = 0;
        List<Object> distributed = distribute(blocks);
        for (int i = 0; i < distributed.size(); i++) {
            Object o = distributed.get(i);
            if (o instanceof File file) {
                result += (long) file.getId() * i;
            }
        }
        return result;
    }

    private static List<Object> distribute(List<Memory> blocks) {
        List<Object> distributed = new ArrayList<>();
        for (Memory block : blocks) {
            if (block.getFiles().isEmpty()) {
                for (int j = 0; j < block.getSize(); j++) {
                    distributed.add(block);
                }
            } else {
                List<File> fileList = block.getFiles();
                distributed.addAll(fileList);
                int leftover = block.getSize() - fileList.size();
                for (int j = 0; j < leftover; j++) {
                    distributed.add(block);
                }
            }
        }
        return distributed;
    }

    public static void rearrange(List<Memory> blocks) {
        for (int i = blocks.size() - 1; i >= 0; i--) {
            Memory file = blocks.get(i);
            if (!file.getFiles().isEmpty()) {
                for (int j = 0; j < i; j++) {
                    Memory space = blocks.get(j);
                    if (space.getSize() - space.getFiles().size() >= file.getFiles().size()) {
                        space.addMultipleFiles(file.getFiles());
                    }
                }
            }
        }
    }

    public static List<Memory> turnToBlocks() {
        List<Memory> memory = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            memory.add(files.get(i));
            addSpace(i, memory);
        }
        return memory;
    }

    private static void addSpace(int i, List<Memory> memory) {
        if (i >= spaces.size()) {
            return;
        }
        memory.add(spaces.get(i));
    }

    public static void fillLists(String input) {
        int counter = 0;
        for (int i = 0; i < input.length(); i++) {
            int currentNumber = Integer.parseInt(String.valueOf(input.charAt(i)));
            if (i % 2 == 0) {
                Memory m = new Memory(currentNumber);
                if (currentNumber != 0) {
                    m.addFile(new File(counter++, currentNumber));
                }
                files.add(m);
            } else {
                spaces.add(new Memory(currentNumber));
            }
        }
    }

}
