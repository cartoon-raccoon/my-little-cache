package raccoonapps;

import org.apache.commons.lang3.time.StopWatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    private static int counter = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader inputReader =
                new BufferedReader(new InputStreamReader(System.in));
        boolean stop = false;
        System.out.println("Enter the stack length.");
        int[] stack = new int[Integer.parseInt(inputReader.readLine())];

        while (!stop) {
            System.out.println("Select an action:");
            System.out.println("[1] Push");
            System.out.println("[2] Generate");
            System.out.println("[3] Pop");
            System.out.println("[4] Print");
            System.out.println("[5] Peek");
            System.out.println("[6] Empty");
            System.out.println("[7] Exit");
            switch (Integer.parseInt(inputReader.readLine())) {
                case (1):
                    if (counter >= stack.length) {
                        System.out.println("Error: Stack overflow, cannot push.");
                        break;
                    } else {
                        System.out.println("Enter number to push:");
                        int push = Integer.parseInt(inputReader.readLine());
                        push(push, stack);
                        stop = false;
                        break;
                    }
                case (2):
                    if (counter > 0) {
                        System.out.println("Cannot fill a stack already containing data.");
                        break;
                    } else {
                        generate(stack);
                        break;
                    }
                case (3):
                    pop(stack);
                    stop = false;
                    break;
                case (4):
                    print(stack);
                    stop = false;
                    break;
                case (5):
                    peek(stack);
                    stop = false;
                    break;
                case (6):
                    if (counter == 0) {
                        System.out.println("Stack is already empty.");
                        break;
                    } else {
                        emptyStack(stack);
                        System.out.println("Stack emptied.");
                        break;
                    }
                case (7):
                    System.out.println("Emptying stack...");
                    System.out.println("Stopping...");
                    emptyStack(stack);
                    stop = true;
                    break;
                default:
                    System.out.println("Invalid input, please enter 1-7.");
                    break;
            }
        }
    }

    private static void push(int input, int[] arr) {
        System.out.println(input + " pushed to stack.");
        arr[counter] = input;
        counter++;
        if (counter == arr.length) {
            System.out.println("Stack is full.");
        } else {
            System.out.println("Elements in stack: " + counter);
        }
    }

    private static void generate(int[] arr) {
        Random numGen = new Random();
        StopWatch genTimer = new StopWatch();
        genTimer.start();
        for (int i = 0; i < arr.length; i++) {
            arr[counter] = numGen.nextInt(500);
            counter++;
        }
        genTimer.stop();
        long time = genTimer.getTime(TimeUnit.MICROSECONDS);
        System.out.println("Stack filled in " + time + " microseconds.");
    }

    private static void pop(int[] arr) {
        if (counter <= 0) {
            System.out.println("Error: Stack underflow, number not popped.");
        } else {
            System.out.println("Popping top index: " + arr[counter - 1]);
            counter--;
            if (counter == 0) {
                System.out.println("Stack is now empty.");
            } else {
                System.out.println("Top index is now: " + arr[counter - 1]);
                System.out.println("Elements in stack: " + counter);
            }
        }
    }

    private static void print(int[] arr) {
        if (counter == 0) {
            System.out.println("Stack is empty, nothing to print.");
        } else {
            System.out.println("Printing stack:");
            System.out.println("\n-----\nTop");
            for (int i = counter-1; i > -1; i--) {
                System.out.println(arr[i]);
            }
            System.out.println("-----\n");
            System.out.println("Total element count: " + counter);
        }
    }

    private static void peek(int[] arr) {
        if (counter == 0) {
            System.out.println("Stack is empty, nothing to peek.");
        } else {
            System.out.println("Element at top is: " + arr[counter - 1]);
        }
    }

    private static void emptyStack(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
            counter = 0;
        }
    }
}
