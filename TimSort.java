import java.util.Arrays;
import java.util.Random;

public class TimSort {
    private static int instructionCounter = 0;

    private static final int MIN_MERGE = 32;

    public static void main(String[] args) {
        int[] array = generateRandomArray(10, 100);

        System.out.println("Before:");
        printArray(array);

        instructionCounter = 0;
        timSort(array);

        System.out.println("\nAfter:");
        printArray(array);
        System.out.println("\nNumber of instructions executed: "+ instructionCounter);
    }

    public static void timSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i += MIN_MERGE) {
            int left = i;
            int right = Math.min((i + MIN_MERGE - 1), (n - 1));
            insertionSort(arr, left, right);
        }

        for (int size = MIN_MERGE; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));

                if (mid < right) {
                    merge(arr, left, mid, right);
                }
            }
        }
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                incrementCounter();
            }
            arr[j + 1] = key;
            incrementCounter();
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int len1 = mid - left + 1;
        int len2 = right - mid;

        int[] leftArr = Arrays.copyOfRange(arr, left, left + len1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, mid + 1 + len2);

        int i = 0, j = 0, k = left;

        while (i < len1 && j < len2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
            incrementCounter();
        }

        while (i < len1) {
            arr[k++] = leftArr[i++];
        }

        while (j < len2) {
            arr[k++] = rightArr[j++];
            incrementCounter();
        }
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
            incrementCounter();
        }
        System.out.println();
    }

    private static int[] generateRandomArray(int size, int maxValue) {
        int[] array = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(maxValue + 1);
            incrementCounter();
        }
        return array;
    }

    private static void incrementCounter() {
        instructionCounter++;
    }
}
