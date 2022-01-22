import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArraysGeneratorTest {
    @Test
    @DisplayName("Выброс исключения если в конструктор переданы неверные параметры")
    void whenWrongArraySizeInConstructor_theExceptionThrown() {

        assertThrows(IllegalArgumentException.class, () -> {
            ArraysGenerator arraysGenerator = new ArraysGenerator(0, 0);
        });
    }

    @RepeatedTest(value = 1000, name = "Размеры массивов должны быть в пределах задаваемого диапазона")
    void whenTheArrayIsCreated_itsSizeIsAsExpected() {
        int maxArraySize = 100;
        int minArraySize = 6;

        ArraysGenerator arraysGenerator = new ArraysGenerator(maxArraySize, minArraySize);

        int arraysQuantity = 1;
        List<int[]> randomArrays = arraysGenerator.getRandomArrays(arraysQuantity);

        int length = randomArrays.get(0).length;
        Assertions.assertAll("Check min and max array size", () -> {
            assertThat(length).isGreaterThanOrEqualTo(minArraySize);
            assertThat(length).isLessThanOrEqualTo(maxArraySize);
        });
    }

    @DisplayName("Значения элементов в массиве должны отличаться, должно быть хотя бы два неодинаковых элемента")
    @Test
    void whenTheArrayIsCreated_itsValuesAreDifferent() {
        ArraysGenerator arraysGenerator = new ArraysGenerator(100, 50);
        List<int[]> randomArrays = arraysGenerator.getRandomArrays(1);
        int[] ints = randomArrays.get(0);

        LongStream distinct = Arrays.stream(ints).asLongStream().distinct();

        long count = distinct.count();

        final int MINIMUM_DIFFERENT_ARRAY_ELEMENT = 2;
        assertThat(count).isGreaterThanOrEqualTo(MINIMUM_DIFFERENT_ARRAY_ELEMENT);
    }

    @RepeatedTest(value = 10, name = "Генерируемые массивы должны иметь разную длину")
    void whenGetRandomArraysWithDifferentLength_arraysHasDifferentSize() {

        ArraysGenerator arraysGenerator = new ArraysGenerator();

        arraysGenerator.setRangeValues(-100, 100);

        arraysGenerator.setMaxArraySize(100);

        final int ARRAYS_QUANTITY = ThreadLocalRandom.current().nextInt(50, 100);
        final int MIN_SIZE = 50;
        final int MAX_SIZE = 500;

        List<int[]> randomArraysWithDifferentLength = arraysGenerator.getRandomArraysWithDifferentLength(ARRAYS_QUANTITY, MIN_SIZE, MAX_SIZE);

        List<Integer> uniqueLength = randomArraysWithDifferentLength.stream().map(e -> e.length).distinct().collect(Collectors.toList());

        assertThat(uniqueLength.size()).isEqualTo(ARRAYS_QUANTITY);
    }
}
