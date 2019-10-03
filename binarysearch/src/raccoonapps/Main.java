package raccoonapps;

import org.apache.commons.lang3.time.StopWatch;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Random numGen = new Random();
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter length of array:");
        int length = userInput.nextInt();
        int[] arrayToSearch = new int[length];

        for (int i = 0; i < length; i++) {
            arrayToSearch[i] = numGen.nextInt(10000);
        }

        System.out.println("\nArray generated.");
        int key = arrayToSearch[numGen.nextInt(length)];
        System.out.println("Random key generated.");

        StopWatch binaryTimer = new StopWatch();
        StopWatch linearTimer = new StopWatch();
        StopWatch sortTimer = new StopWatch();

        System.out.println("Linear search:");
        System.out.println("1. Sorted array");
        System.out.println("2. Unsorted array");
        int choice = userInput.nextInt();
        boolean isPresent;
        long sortTime;
        System.out.println("Processing...");

        switch (choice) {
            case 1:
                sortTimer.start();
                Arrays.sort(arrayToSearch);
                sortTimer.stop();
                sortTime = sortTimer.getTime(TimeUnit.MICROSECONDS);
                System.out.println("\nArray sorted for both searches in " + sortTime + " microseconds.");

                linearTimer.start();
                isPresent = linearSearch(arrayToSearch, key);
                linearTimer.stop();

                break;

            case 2:
                linearTimer.start();
                isPresent = linearSearch(arrayToSearch, key);
                linearTimer.stop();

                sortTimer.start();
                Arrays.sort(arrayToSearch);
                sortTimer.stop();
                sortTime = sortTimer.getTime(TimeUnit.MICROSECONDS);
                System.out.println("\nArray left unsorted for linear search.");
                System.out.println("Array sorted for binary search in " + sortTime + " microseconds.");

                break;

            default:
                System.out.println("Invalid input, defaulting to unsorted.");
                linearTimer.start();
                isPresent = linearSearch(arrayToSearch, key);
                linearTimer.stop();

                sortTimer.start();
                Arrays.sort(arrayToSearch);
                sortTimer.stop();
                sortTime = sortTimer.getTime(TimeUnit.MICROSECONDS);
                System.out.println("Array sorted for binary search in " + sortTime + " microseconds.");

                break;
        }

        binaryTimer.start();
        int result = binarySearch(arrayToSearch, 0, length-1, key);
        binaryTimer.stop();

        long binaryTime = binaryTimer.getTime(TimeUnit.MICROSECONDS);
        long linearTime = linearTimer.getTime(TimeUnit.MICROSECONDS);

        if (result == -1) {
            System.out.println("\nBinary search: Key not found.");
            System.out.println("Process time: " + binaryTime + " microseconds.");
        } else {
            System.out.println("\nBinary search found key.");
            System.out.println("Process time: " + binaryTime + " microseconds.");
        }

        if (isPresent) {
            System.out.println("\nLinear search found key.");
            System.out.println("Process time: " + linearTime + " microseconds.");
        } else {
            System.out.println("\nLinear search: Key not found.");
            System.out.println("Process time: " + linearTime + " microseconds.");
        }
    }

    private static int binarySearch(int[] arr, int bound1, int bound2, int key) {
        if (bound1 <= bound2) {
            int mid = bound1 + (bound2 - bound1) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] > key) {
                return binarySearch(arr, bound1, mid - 1, key);
            } else {
                return binarySearch(arr, mid + 1, bound2, key);
            }
        } else {return -1;}
    }

    private static boolean linearSearch(int[] arr, int key) {
        boolean isKeyPresent = false;
        for (int item : arr) {
            if (key == item) {
                isKeyPresent = true;
                break;
            }
        }
        return isKeyPresent;
    }
}
