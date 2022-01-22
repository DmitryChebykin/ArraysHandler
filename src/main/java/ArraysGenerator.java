import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArraysGenerator {
    private static final int MINIMUM_UNSORTED_ARRAY_SIZE = 2;
    private final int minArraySize;
    private int maxArraySize;
    private int minRandomValue;
    private int maxRandomValue;

    public ArraysGenerator(int maxArraySize, int minArraySize) {
        if (maxArraySize < minArraySize) {
            throw new IllegalArgumentException("The maximum size of an array must be greater than or equal to the minimum size");
        }
        setMaxArraySize(maxArraySize);
        this.minArraySize = minArraySize;

        setDefaultRange();
    }

    public ArraysGenerator(int maxArraySize) {
        setMaxArraySize(maxArraySize);
        minArraySize = MINIMUM_UNSORTED_ARRAY_SIZE;
    }

    public ArraysGenerator() {
        maxArraySize = Integer.MAX_VALUE;
        minArraySize = MINIMUM_UNSORTED_ARRAY_SIZE;

        setDefaultRange();
    }

    private void setDefaultRange() {
        this.maxRandomValue = Integer.MAX_VALUE;
        this.minRandomValue = Integer.MIN_VALUE;
    }

    public void setRangeValues(int minRandomValue, int maxRandomValue) {
        if (minRandomValue == maxRandomValue) {
            throw new IllegalArgumentException("Range limits must be different");
        }

        this.maxRandomValue = Math.max(minRandomValue, maxRandomValue);
        this.minRandomValue = Math.min(minRandomValue, maxRandomValue);
    }

    public void setMaxArraySize(int maxArraySize) {
        if (maxArraySize <= 2) {
            throw new IllegalArgumentException("The minimum array size must be greater than 1");
        }
        this.maxArraySize = maxArraySize;
    }

    public List<int[]> getRandomArrays(Integer arraysQuantity) {

        if (arraysQuantity < 1) {
            throw new IllegalArgumentException("The number of arrays must be at least 1");
        }

        return IntStream.range(0, arraysQuantity).mapToObj(e -> {
            int size = ThreadLocalRandom.current().nextInt(minArraySize, maxArraySize);
            return getIntArrayWithRandomValues(size);
        }).collect(Collectors.toList());
    }

    private int[] getIntArrayWithRandomValues(int size) {
        int[] intArray = new int[size];
        for (int i = 0; i < size; i++) {
            intArray[i] = ThreadLocalRandom.current().nextInt(minRandomValue, maxRandomValue);
        }
        return intArray;
    }

    private int[] getArraysSizesSet(Integer arraysQuantity, int minSize, int maxSize) {
        return IntStream.generate(() -> ThreadLocalRandom.current().nextInt(minSize, maxSize))
                .distinct().limit(arraysQuantity).toArray();
    }

    public List<int[]> getRandomArraysWithDifferentLength(Integer arraysQuantity, int minSize, int maxSize) {
        int[] arraysSizesSet = getArraysSizesSet(arraysQuantity, minSize, maxSize);
        List<int[]> arraysCollection = new ArrayList<>();

        for (int j : arraysSizesSet) {
            arraysCollection.add(getIntArrayWithRandomValues(j));
        }

        return arraysCollection;
    }


}
