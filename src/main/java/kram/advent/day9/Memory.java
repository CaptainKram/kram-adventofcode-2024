package kram.advent.day9;

public abstract class Memory {

    private final int size;

    public Memory(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
