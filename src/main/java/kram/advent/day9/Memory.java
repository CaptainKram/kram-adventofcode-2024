package kram.advent.day9;

import java.util.ArrayList;
import java.util.List;

public class Memory {

    protected final List<File> files;
    protected int size;

    public Memory(int size) {
        this.files = new ArrayList<>();
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void addFile(File file) {
        int fileSize = file.getSize();
        for (int i = 0; i < fileSize; i++) {
            this.files.add(file);
        }
    }

    public void addMultipleFiles(List<File> files) {
        this.files.addAll(files);
        files.clear();
    }

    public List<File> getFiles() {
        return files;
    }

    @Override
    public String toString() {
        String s = "";
        if (!files.isEmpty()) {
            for (File file : files) {
                s = s.concat(String.valueOf(file.getId()));
            }
            int leftover = size - files.size();
            for (int i = 0; i < leftover; i++) {
                s = s.concat(".");
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                s = s.concat(".");
            }
        }
        return s;
    }
}
