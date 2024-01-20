import java.util.Random;

public class QuickSort{
    private static int instructionCounter = 0;

    public static void main(String []args){
        Random rand = new Random();
        int[] numbers = new int[10];

        for (int i = 0; i < numbers.length; i++){
            numbers[i] = rand.nextInt(100);
        }

        System.out.println("Before");
        printArray(numbers);

        instructionCounter = 0;
        quicksort(numbers, 0, numbers.length - 1);

        System.out.println("\nAfter");
        printArray(numbers);
        System.out.println("\nNumber of instructions executed: " + instructionCounter);
    }

    private static void quicksort(int[] array, int lowIndex, int highIndex) {
        incrementCounter(); 

        if (lowIndex >= highIndex){
            return;
        }

        int pivot = array[highIndex];

        int leftpointer = lowIndex;
        int rightpointer = highIndex;

        while (leftpointer < rightpointer) {
            incrementCounter();
            
            while(array[leftpointer] <= pivot && leftpointer < rightpointer) {
                leftpointer++;
                incrementCounter();
            }
            while(array[rightpointer] >= pivot && leftpointer < rightpointer) {
                rightpointer--;
                incrementCounter();
            }

            swap(array, leftpointer, rightpointer);
        }
        swap(array, leftpointer, highIndex);

        quicksort(array, lowIndex, leftpointer -1);
        quicksort(array, leftpointer +1 ,highIndex);
    }

    private static void swap(int[] array, int index1, int index2) {
        incrementCounter();
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private static void printArray(int[] array) {
        incrementCounter();
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static void incrementCounter(){
        instructionCounter++;
    }
}