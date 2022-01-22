import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class ArraySorterTest {
    ArraySorter arraySorter = new ArraySorter();

    @DisplayName("Проверка сортировки массива по возрастанию и по убыванию")
    @Test
    void testQuickSortOfRegularArray() {
        int[] array = {1, 2, 3, 9, -1, -4, 0};

        arraySorter.quickSort(array, true);

        int[] arrayAscSorted = {-4, -1, -0, 1, 2, 3, 9};
        assertThat(array).containsSequence(arrayAscSorted);

        int[] arrayDescSorted = {9, 3, 2, 1, 0, -1, -4};
        arraySorter.quickSort(array, false);
        assertThat(array).containsSequence(arrayDescSorted);
    }

    @DisplayName("Проверка asc-сортировки уже отсортированного asc-массива")
    @RepeatedTest(value = 1000, name = RepeatedTest.LONG_DISPLAY_NAME)
    void testAscQuickSortOfAscSortedArray() {
        IntStream stream = IntStream.generate(() -> (int) (Math.random() * 10000)).limit(1000);

        int[] intsAscSorted = stream.sorted().toArray();

        arraySorter.quickSort(intsAscSorted, true);
        assertThat(intsAscSorted).isSortedAccordingTo(Comparator.naturalOrder());
    }

    @DisplayName("Проверка asc-сортировки уже отсортированного desc-массива")
    @RepeatedTest(value = 1000, name = RepeatedTest.LONG_DISPLAY_NAME)
    void testAscQuickSortOfDescSortedArray() {
        IntStream stream = IntStream.generate(() -> {
            double v = Math.random() * 10000;
            return (int) v;
        }).limit(1000).sorted();

        int[] intsDescSorted = stream.boxed().sorted(Comparator.reverseOrder()).mapToInt(i -> i).toArray();

        arraySorter.quickSort(intsDescSorted, true);
        assertThat(intsDescSorted).isSortedAccordingTo(Comparator.naturalOrder());
    }

    @DisplayName("Проверка desc-сортировки уже отсортированного asc-массива")
    @RepeatedTest(value = 1000, name = RepeatedTest.LONG_DISPLAY_NAME)
    void testDescQuickSortOfAscSortedArray() {
        IntStream stream = IntStream.generate(() -> (int) (Math.random() * 10000)).limit(1000);

        int[] intsAscSorted = stream.sorted().toArray();

        arraySorter.quickSort(intsAscSorted, false);
        assertThat(intsAscSorted).isSortedAccordingTo(Comparator.reverseOrder());
    }

    @DisplayName("Проверка desc-сортировки уже отсортированного desc-массива")
    @RepeatedTest(value = 1000, name = RepeatedTest.LONG_DISPLAY_NAME)
    void testDescQuickSortOfDescSortedArray() {
        IntStream stream = IntStream.generate(() -> {
            double v = Math.random() * 10000;
            return (int) v;
        }).limit(1000).sorted();

        int[] intsDescSorted = stream.boxed().sorted(Comparator.reverseOrder()).mapToInt(i -> i).toArray();

        arraySorter.quickSort(intsDescSorted, false);
        assertThat(intsDescSorted).isSortedAccordingTo(Comparator.reverseOrder());
    }

    @DisplayName("Проверка desc-сортировки массивов, случайно сгенерированных")
    @RepeatedTest(value = 1000, name = RepeatedTest.LONG_DISPLAY_NAME)
    void testDescQuickSortOfRandomArrays() {
        final int MIN_SIZE = 3;
        final int MAX_SIZE = 10;
        IntStream stream = IntStream.generate(() -> {
            double v = Math.random() * 10;
            return (int) v;
        }).limit(ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE));

        int[] intsDescSorted = stream.toArray();

        arraySorter.quickSort(intsDescSorted, false);
        assertThat(intsDescSorted).isSortedAccordingTo(Comparator.reverseOrder());
    }

    @DisplayName("Проверка asc-сортировки массивов, случайно сгенерированных")
    @RepeatedTest(value = 1000, name = RepeatedTest.LONG_DISPLAY_NAME)
    void testAscQuickSortOfRandomArrays() {
        final int MIN_SIZE = 3;
        final int MAX_SIZE = 10;
        IntStream stream = IntStream.generate(() -> {
            double v = Math.random() * 10;
            return (int) v;
        }).limit(ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE));

        int[] intsDescSorted = stream.toArray();

        arraySorter.quickSort(intsDescSorted, true);
        assertThat(intsDescSorted).isSortedAccordingTo(Comparator.naturalOrder());
    }
}

