import kram.advent.day9.DiskFragmenter;
import kram.advent.day9.Memory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiskFragmenterTest {

    private static final String diskFragmenterTestInput = "2333133121414131402";

    @BeforeEach
    void setUp(){
        DiskFragmenter.ids.clear();
        DiskFragmenter.spaces.clear();
    }

    @Test
    public void diskFragmenterTest() {
        DiskFragmenter.fillLists(diskFragmenterTestInput);
        List<Memory> blocks = DiskFragmenter.turnToBlocks();
        DiskFragmenter.rearrange(blocks);
        long result = DiskFragmenter.calculateChecksum(blocks);
        assertEquals(1928, result);
    }
}
