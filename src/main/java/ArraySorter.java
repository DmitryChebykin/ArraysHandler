import java.util.concurrent.ConcurrentLinkedQueue;

public class ArraySorter {
    public void quickSort(int[] array, boolean isAscending) {

        ConcurrentLinkedQueue<int[]> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

        int low = 0;
        int high = array.length - 1;

        int[] indexes = {low, high};

        concurrentLinkedQueue.add(indexes);

        while (!concurrentLinkedQueue.isEmpty()) {

            Runnable runnable = () -> sortStep(array, concurrentLinkedQueue, isAscending);

            runnable.run();
        }
    }

    private int getPivot(int low, int high) {
        return (high - low) / 2 + low;
    }

    private void sortStep(int[] array, ConcurrentLinkedQueue<int[]> stack, boolean isAscending) {
        int[] indices = stack.poll();

        int leftIndex = indices[0];
        int rightIndex = indices[1];

        int pivot = getPivot(leftIndex, rightIndex);

        int pivotValue = array[pivot];

        while (leftIndex <= rightIndex) {
            while (isAscending
                    ? array[leftIndex] < pivotValue
                    : array[leftIndex] > pivotValue) {
                leftIndex++;
            }
            while (isAscending
                    ? array[rightIndex] > pivotValue
                    : array[rightIndex] < pivotValue) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                if (leftIndex != rightIndex) {
                    swap(leftIndex, rightIndex, array);
                }
                leftIndex++;
                rightIndex--;
            }
        }

        if (leftIndex - 1 > indices[0]) {
            stack.add(new int[]{indices[0], leftIndex - 1});
        }

        if (leftIndex  < indices[1]) {
            stack.add(new int[]{leftIndex, indices[1]});
        }
    }

    private void swap(int index1, int index2, int[] array) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
