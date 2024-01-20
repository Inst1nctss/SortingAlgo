import java.util.*;

public class RadixSort {
    private static int instructionCounter = 0;

    public static void main(String[] args) {
        int[] array = generateRandomArray(10, 1000);

        System.out.println("Before:");
        printArray(array);

        instructionCounter = 0; // Reset the counter before sorting
        radixSort(array);

        System.out.println("\nAfter:");
        printArray(array);
        System.out.println("\nNumber of instructions executed: " + instructionCounter);
    }

    private static void radixSort(int[] array) {
        int maxDigits = getMaxDigits(array);

        List<Queue<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new LinkedList<>());
        }

        for (int digitPlace = 1; digitPlace <= maxDigits; digitPlace++) {
            placeIntoBuckets(array, buckets, digitPlace);
            collectFromBuckets(array, buckets);
            clearBuckets(buckets);
        }
    }

    private static void placeIntoBuckets(int[] array, List<Queue<Integer>> buckets, int digitPlace) {
        for (int num : array) {
            int digit = getDigit(num, digitPlace);
            buckets.get(digit).offer(num);
            incrementCounter();
        }
    }

    private static void collectFromBuckets(int[] array, List<Queue<Integer>> buckets) {
        int index = 0;
        for (Queue<Integer> bucket : buckets) {
            while (!bucket.isEmpty()) {
                array[index++] = bucket.poll();
                incrementCounter();
            }
        }
    }

    private static void clearBuckets(List<Queue<Integer>> buckets) {
        for (Queue<Integer> bucket : buckets) {
            bucket.clear();
            incrementCounter();
        }
    }

    private static int getDigit(int number, int digitPlace) {
        int digit = (number / digitPlace) % 10;
        incrementCounter();
        return digit;
    }

    private static int getMaxDigits(int[] array) {
        int maxDigits = 1;
        for (int num : array) {
            int numDigits = (int) Math.log10(num) + 1;
            maxDigits = Math.max(maxDigits, numDigits);
            incrementCounter();
        }
        return maxDigits;
    }

    private static void printArray(int[] array) {
        for (int num : array) {
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
