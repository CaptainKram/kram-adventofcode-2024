import kram.advent.day10.HoofIt;
import kram.advent.records.ReversedPosition;
import kram.advent.utils.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HoofItTest {

    private static final String hoofItTestInput1 = """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
            """;

    private static final String hoofItTestInput2 = """
            0123
            1234
            8765
            9876
            """;

    @BeforeEach
    public void setUp() {
        HoofIt.hills.clear();
    }

    @Test
    public void hoofItPart1Test1() {
        int result = doWorkPart1(hoofItTestInput1);
        assertEquals(36, result);
    }

    @Test
    public void hoofItPart1Test2() {
        int result = doWorkPart1(hoofItTestInput2);
        assertEquals(1, result);
    }

    @Test
    public void hoofItPart2Test1() {
        int result = doWorkPart2(hoofItTestInput1);
        assertEquals(81, result);
    }

    private int doWorkPart1(String input) {
        char[][] matrix = StringUtil.stringToCharArr(input);

        List<ReversedPosition> trailHeads = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                char current = matrix[i][j];
                if (current == '0') {
                    ReversedPosition position = new ReversedPosition(i, j);
                    trailHeads.add(position);
                } else if (current == '9') {
                    HoofIt.hills.add(new ReversedPosition(i, j));
                }
            }
        }

        int reachedHills = 0;
        for (ReversedPosition trailHead : trailHeads) {
            for (ReversedPosition hill : HoofIt.hills) {
                List<ReversedPosition> beenHere = new ArrayList<>();
                if (HoofIt.findHill(matrix, trailHead, hill, beenHere)) {
                    reachedHills++;
                }
            }
        }
        return reachedHills;
    }

    private int doWorkPart2(String input) {
        char[][] matrix = StringUtil.stringToCharArr(input);

        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                char current = matrix[i][j];
                if (current == '0') {
                    ReversedPosition position = new ReversedPosition(i, j);
                    Integer score = HoofIt.findHills(position, matrix);
                    sum += score;
                }
            }
        }
        return sum;
    }

}
