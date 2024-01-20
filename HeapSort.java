import java.util.Random;

public class HeapSort {
    private static int instructionCounter = 0;
    public static void main(String[] args) {
        Random rand = new Random();
        int[] numbers = new int[10];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rand.nextInt(100);
        }

        System.out.println("Before:");
        printArray(numbers);

        instructionCounter = 0;
        heapSort(numbers);

        System.out.println("\nAfter:");
        printArray(numbers);
        System.out.println("\nNumber of instrucions executed: " + instructionCounter);
    }

    private static void heapSort(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            swap(array, 0, i);
            incrementCounter();
        
            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] array, int n, int i) {
        int largest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < n && array[leftChild] > array[largest]) {
            largest = leftChild;
        }

        if (rightChild < n && array[rightChild] > array[largest]) {
            largest = rightChild;
        }

        if (largest != i) {
            swap(array, i, largest);
            incrementCounter();

            heapify(array, n, largest);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        incrementCounter();
    }

    private static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
            incrementCounter();
        }

        System.out.println();
    }

    private static void incrementCounter() {
        instructionCounter++;
    }
}
