import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {

        demo();

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Введите n >= 1 - число массивов случайной длины, которые нужно сгенерировать и отсортировать");
        System.out.println("Для удобочитаемости вывода рекомендуется диапазон n от 5 до 20");

        int arraysQuantity = keyboard.nextInt();

        ArraysGenerator arraysGenerator = new ArraysGenerator();

        arraysGenerator.setRangeValues(-100, 100);

        arraysGenerator.setMaxArraySize(21);

        int[][] arrayOfSortedArrays = getArrayOfSortedArrays(arraysQuantity, 10, arraysGenerator);

        System.out.println("Массивы сгенерированы и отсортированы");

        System.out.println(Arrays.deepToString(arrayOfSortedArrays));
    }

    private static void demo() {
        ArraysGenerator arraysGenerator = new ArraysGenerator(15, 8);
        arraysGenerator.setRangeValues(-5, 27);

        List<int[]> primitiveIntArraysCollection = arraysGenerator.getRandomArrays(10);

        ArraySorter arraySorter = new ArraySorter();

        final boolean[] isAscending = {true};

        String ascending = "ascending";
        String descending = "descending";

        System.out.println("Демонстрационный пример.");
        System.out.println("Создание 10 массивов с минимальной длиной 8, максимальной 15");
        System.out.println("Диапазон случайных значений от -5 до 27");
        System.out.println();

        primitiveIntArraysCollection.forEach(e -> {
            isAscending[0] = !isAscending[0];

            System.out.println("Arrays before /" + ANSI_GREEN + " after (" + (isAscending[0]
                    ? ascending
                    : descending) + " sorting)" + ANSI_RESET);
            System.out.println(Arrays.toString(e));

            arraySorter.quickSort(e, isAscending[0]);

            System.out.println(ANSI_GREEN + Arrays.toString(e) + ANSI_RESET);
        });
    }

    public static int[][] getArrayOfSortedArrays(int arraysQuantity, int minSize, ArraysGenerator arraysGenerator) {

        if (arraysQuantity < 1) {
            throw new IllegalArgumentException("The number of arrays must be at least 1");
        }

        List<int[]> randomArraysWithDifferentLength = arraysGenerator.getRandomArraysWithDifferentLength(arraysQuantity, minSize, minSize + arraysQuantity * 2);

        return get2DIntsArray(randomArraysWithDifferentLength);
    }

    public static int[][] get2DIntsArray(List<int[]> randomArraysWithDifferentLength) {
        int[][] array = new int[randomArraysWithDifferentLength.size()][];

        ArraySorter arraySorter = new ArraySorter();

        boolean isAscending = false;

        for (int i = 0; i < array.length; i++) {
            array[i] = randomArraysWithDifferentLength.get(i);
            arraySorter.quickSort(randomArraysWithDifferentLength.get(i), isAscending);
            isAscending = !isAscending;
        }

        return array;
    }
}
