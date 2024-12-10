import kram.advent.day9.DiskFragmenter;
import kram.advent.day9.Memory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiskFragmenterTest {

    private static final String diskFragmenterTestInput1 = "2333133121414131402";
    private static final String diskFragmenterTestInput2 = "12345";
    private static final String diskFragmenterTestInput3 = "597689906";
    private static final String diskFragmenterTestInput4 = "354631466260";
    private static final String diskFragmenterTestInput5 = "2333133121414131499";
    private static final String diskFragmenterTestInput6 = "714892711";
    private static final String diskFragmenterTestInput7 = "12101";
    private static final String diskFragmenterTestInput8 = "1313165";
    private static final String diskFragmenterTestInput9 = "12143";
    private static final String diskFragmenterTestInput10 = "14113";
    private static final String diskFragmenterTestInput11 = "121";
    private static final String diskFragmenterTestInput12 = "22222228282828222222282829212324252627282920";
    private static final String diskFragmenterTestInput13 = "23222120202525282820202020272722212121";
    private static final String diskFragmenterTestInput14 = "673253833464635054191677274350925861527651788483";

    @BeforeEach
    void setUp(){
        DiskFragmenter.files.clear();
        DiskFragmenter.spaces.clear();
    }

    @Test
    public void diskFragmenterTest1() {
        long result = doWork(diskFragmenterTestInput1);
        assertEquals(2858, result);
    }

    @Test
    public void diskFragmenterTest2() {
        long result = doWork(diskFragmenterTestInput2);
        assertEquals(132, result);
    }

    @Test
    public void diskFragmenterTest3() {
        long result = doWork(diskFragmenterTestInput3);
        assertEquals(1840, result);
    }

    @Test
    public void diskFragmenterTest4() {
        long result = doWork(diskFragmenterTestInput4);
        assertEquals(1325, result);
    }

    @Test
    public void diskFragmenterTest5() {
        long result = doWork(diskFragmenterTestInput5);
        assertEquals(6204, result);
    }

    @Test
    public void diskFragmenterTest6() {
        long result = doWork(diskFragmenterTestInput6);
        assertEquals(813, result);
    }

    @Test
    public void diskFragmenterTest7() {
        long result = doWork(diskFragmenterTestInput7);
        assertEquals(4, result);
    }

    @Test
    public void diskFragmenterTest8() {
        long result = doWork(diskFragmenterTestInput8);
        assertEquals(169, result);
    }

    @Test
    public void diskFragmenterTest9() {
        long result = doWork(diskFragmenterTestInput9);
        assertEquals(31, result);
    }

    @Test
    public void diskFragmenterTest10() {
        long result = doWork(diskFragmenterTestInput10);
        assertEquals(16, result);
    }

    @Test
    public void diskFragmenterTest11() {
        long result = doWork(diskFragmenterTestInput11);
        assertEquals(1, result);
    }

    @Test
    public void diskFragmenterTest12() {
        long result = doWork(diskFragmenterTestInput12);
        assertEquals(9447, result);
    }

    @Test
    public void diskFragmenterTest13() {
        long result = doWork(diskFragmenterTestInput13);
        assertEquals(7705, result);
    }

    @Test
    public void diskFragmenterTest14() {
        long result = doWork(diskFragmenterTestInput14);
        assertEquals(149706, result);
    }

    private static long doWork(String input) {
        DiskFragmenter.fillLists(input);
        List<Memory> blocks = DiskFragmenter.turnToBlocks();
        DiskFragmenter.rearrange(blocks);
        return DiskFragmenter.calculateChecksum(blocks);
    }

}
