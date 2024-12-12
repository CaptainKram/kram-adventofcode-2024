import kram.advent.day12.GardenGroups;
import kram.advent.utils.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GardenGroupsTest {

    // 140
    private static final String gardenGroupsTestInput1 = """
            AAAA
            BBCD
            BBCC
            EEEC
            """;

    // 772
    private static final String gardenGroupsTestInput2 = """
            OOOOO
            OXOXO
            OOOOO
            OXOXO
            OOOOO
            """;

    // 1930
    private static final String gardenGroupsTestInput3 = """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
            """;

    @BeforeEach
    void setUp() {
        GardenGroups.beenHere.clear();
    }

    @Test
    public void gardenGroupsPart1Test1() {
        int result = doWork(gardenGroupsTestInput1);
        assertEquals(140, result);
    }

    @Test
    public void gardenGroupsPart1Test2() {
        int result = doWork(gardenGroupsTestInput2);
        assertEquals(772, result);
    }

    @Test
    public void gardenGroupsPart1Test3() {
        int result = doWork(gardenGroupsTestInput3);
        assertEquals(1930, result);
    }

    private static int doWork(String input) {
        char[][] matrix = StringUtil.stringToCharArr(input);

        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result += GardenGroups.calculatePlant(matrix, i, j, new HashMap<>());
            }
        }
        return result;
    }

}
