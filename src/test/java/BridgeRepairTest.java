import kram.advent.utils.StringUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static kram.advent.day7.BridgeRepair.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BridgeRepairTest {

    static String bridgeTestInput = """
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

    @Test
    public void day7Part1Test() {
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
        assertEquals(3749, sum);
    }

}
