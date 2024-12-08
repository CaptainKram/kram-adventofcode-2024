import kram.advent.day6.Guard;
import kram.advent.records.Position;
import kram.advent.utils.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static kram.advent.day6.GuardGallivant.findGuard;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuardGallivantTest {

    private Set<Position> obstructionPotential;

    private static final String guardPathTest = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
            """;

    private static final String guardPathTest2 = """
            .#...
            ....#
            .....
            .^.#.
            #....
            ..#..
            """;

    private static final String guardPathTest3 = """
            ##..
            ...#
            ....
            ^.#.
            """;

    @BeforeEach
    void setUp() {
        this.obstructionPotential= new HashSet<>();
    }

    @Test
    public void day6Part2Test1() {
        char[][] matrix = StringUtil.stringToCharArr(guardPathTest);

        Guard guard = findGuard(matrix);
        assert guard != null;
        this.obstructionPotential.add(guard.getPosition());

        while (!guard.isOutside()) {
            Position obstruction = guard.haveIBeenHereBefore(matrix);
            if (obstruction != null) {
                this.obstructionPotential.add(obstruction);
            }
            guard.move(matrix);
        }

        assertEquals(6, this.obstructionPotential.size() - 1);
    }

    @Test
    public void day6Part2Test2() {
        char[][] matrix = StringUtil.stringToCharArr(guardPathTest2);

        Guard guard = findGuard(matrix);
        assert guard != null;
        this.obstructionPotential.add(guard.getPosition());

        while (!guard.isOutside()) {
            Position obstruction = guard.haveIBeenHereBefore(matrix);
            if (obstruction != null) {
                this.obstructionPotential.add(obstruction);
            }
            guard.move(matrix);
        }

        assertEquals(3, this.obstructionPotential.size() - 1);
    }

    @Test
    public void day6Part2Test3() {
        char[][] matrix = StringUtil.stringToCharArr(guardPathTest3);

        Guard guard = findGuard(matrix);
        assert guard != null;
        this.obstructionPotential.add(guard.getPosition());

        while (!guard.isOutside()) {
            Position obstruction = guard.haveIBeenHereBefore(matrix);
            if (obstruction != null) {
                this.obstructionPotential.add(obstruction);
            }
            guard.move(matrix);
        }

        assertEquals(0, this.obstructionPotential.size() - 1);
    }

}
