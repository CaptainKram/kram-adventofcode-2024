package kram.advent.day9;

public class File extends Memory {

    private final int id;

    File(int id, int size){
        super(size);
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
