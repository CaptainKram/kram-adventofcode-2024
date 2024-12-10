package kram.advent.day9;

public class File {

    private final int id;
    private final int size;

    File(int id, int size){
        this.size = size;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
