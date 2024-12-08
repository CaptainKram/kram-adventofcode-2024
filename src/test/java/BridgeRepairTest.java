import kram.advent.utils.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kram.advent.day7.BridgeRepair.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BridgeRepairTest {

    private static final String bridgeTestInput = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
            """;

    private static final String bridgeTestInput2 = """
            40656: 26 37 33 56 79 42 60 33
            20592: 46 62 81 97 72
            39022201: 23 47 69 82 69 83 41
            536008016: 73 91 24 78 75 93 11 36 42 86 25 24 66 26
            81422384: 57 64 82 84 84 43 17 16
            5575106866126011: 94 43 54 52 36 29 26 94 55 84 27 87 97
            5172: 66 71 36 25 74 50 91
            206506492: 21 31 19 67 87 16 86 69 38 12 13 10
            1030269780930: 75 50 16 78 66 41 39 54 11 83 54 34 87
            2731294098: 91 27 19 32 27 15 94 18
            """;

    private static final String bridgeTestInput3 = """
            13: 6 7 3
            42: 6 7 3
            10: 6 7 3
            21: 6 7 3
            5: 2 2
            100: 50 50 2
            """;

    @BeforeEach
    void setUp() {
        calibrationMap = new HashMap<>();
    }

    @Test
    public void day7Part2Test1() {
        List<String> lines = StringUtil.stringToList(bridgeTestInput);
        fillCalibrationMap(lines);

        long sum = 0;
        for (Map.Entry<Long, List<Long>> entry : calibrationMap.entrySet()) {
            long result = entry.getKey();
            List<Long> values = entry.getValue();
            if (canBeCalibrated(result, values)) {
                sum += result;
            }
        }
        assertEquals(11387, sum);
    }

    @Test
    public void day7Part2Test2() {
        List<String> lines = StringUtil.stringToList(bridgeTestInput2);
        fillCalibrationMap(lines);

        long sum = 0;
        for (Map.Entry<Long, List<Long>> entry : calibrationMap.entrySet()) {
            long result = entry.getKey();
            List<Long> values = entry.getValue();
            if (canBeCalibrated(result, values)) {
                sum += result;
            }
        }
        assertEquals(5576140730226552L, sum);
    }

    @Test
    public void day7Part2Test3() {
        List<String> lines = StringUtil.stringToList(bridgeTestInput3);
        fillCalibrationMap(lines);

        long sum = 0;
        for (Map.Entry<Long, List<Long>> entry : calibrationMap.entrySet()) {
            long result = entry.getKey();
            List<Long> values = entry.getValue();
            if (canBeCalibrated(result, values)) {
                sum += result;
            }
        }
        assertEquals(0, sum);
    }

}
