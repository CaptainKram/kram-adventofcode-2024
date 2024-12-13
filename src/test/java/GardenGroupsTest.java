import kram.advent.day12.GardenGroups;
import kram.advent.utils.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GardenGroupsTest {

    private static final String gardenGroupsTestInput1 = """
            AAAA
            BBCD
            BBCC
            EEEC
            """;

    private static final String gardenGroupsTestInput2 = """
            OOOOO
            OXOXO
            OOOOO
            OXOXO
            OOOOO
            """;

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

    private static final String gardenGroupsTestInput4 = """
            EEEEE
            EXXXX
            EEEEE
            EXXXX
            EEEEE
            """;

    private static final String gardenGroupsTestInput5 = """
            AAAAAA
            AAABBA
            AAABBA
            ABBAAA
            ABBAAA
            AAAAAA
            """;

    @BeforeEach
    void setUp() {
        GardenGroups.beenHere.clear();
    }

    @Test
    public void gardenGroupsTest1() {
        int result = doWork(gardenGroupsTestInput1);
        assertEquals(80, result);
    }

    @Test
    public void gardenGroupsTest2() {
        int result = doWork(gardenGroupsTestInput2);
        assertEquals(436, result);
    }

    @Test
    public void gardenGroupsTest3() {
        int result = doWork(gardenGroupsTestInput3);
        assertEquals(1206, result);
    }

    @Test
    public void gardenGroupsTest4() {
        int result = doWork(gardenGroupsTestInput4);
        assertEquals(236, result);
    }

    @Test
    public void gardenGroupsTest5() {
        int result = doWork(gardenGroupsTestInput5);
        assertEquals(368, result);
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
