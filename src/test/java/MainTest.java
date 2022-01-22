import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class MainTest {

    @Test
    void testGet2DIntsArray() {
        List<int[]> list = Arrays.asList(new int[][]{
                {2, 3, 1, 5},
                {-1, 0, 72, 0},
                {5, 6, 5, 5},
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5},
                {9, 8, 7, 6, 5, 4, 3},
                {-7, -8, -9, -10}});
        int[][] result = Main.get2DIntsArray(list);

        int[][] expected = new int[][]{
                {5, 3, 2, 1}, //desc
                {-1, 0, 0, 72}, //asc
                {6, 5, 5, 5},//desc
                {1, 2, 3, 4, 5}, //asc
                {5, 4, 3, 2, 1},//desc
                {3, 4, 5, 6, 7, 8, 9}, //asc
                {-7, -8, -9, -10}};//desc

        Assertions.assertArrayEquals(expected, result);
    }
}