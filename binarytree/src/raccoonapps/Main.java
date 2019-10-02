package raccoonapps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader inputReader =
                new BufferedReader(new InputStreamReader(System.in));
        boolean stop = false;
        boolean validInput = false;
        int rootValue = 0;
        System.out.println("Enter the root value:");
        do {
            try {
                rootValue = Integer.parseInt(inputReader.readLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        } while (!validInput);
        Node root = new Node(rootValue);
        while (!stop) {
            validInput = false;
            System.out.println("Select an action:");
            System.out.println("[1] Add a value");
            System.out.println("[2] Check for a value");
            System.out.println("[3] Print the tree");
            System.out.println("[4] Exit program");
            int choice = 0;
            do {
                try {
                    choice = Integer.parseInt(inputReader.readLine());
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, please enter 1-4.");
                }
            } while(!validInput);
            validInput = false;
            switch (choice) {
                case 1:
                    System.out.println("Enter the value to add:");
                    int addValue = 0;
                    do {
                        try {
                            addValue = Integer.parseInt(inputReader.readLine());
                            validInput = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input.");
                        }
                    } while (!validInput);
                    addNode(root, addValue);
                    System.out.println("Value added.");
                    stop = false;
                    break;
                case 2:
                    System.out.println("Enter the value to check for:");
                    int checkValue = 0;
                    do {
                        try {
                            checkValue = Integer.parseInt(inputReader.readLine());
                            validInput = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input.");
                        }
                    } while (!validInput);
                    boolean check = containsValue(root, checkValue);
                    if (check) {
                        System.out.println("Value is present.");
                    } else {
                        System.out.println("Value not found.");
                    }
                    stop = false;
                    break;
                case 3:
                    traversalInOrder(root);
                    stop = false;
                    break;
                case 4:
                    System.out.println("Stopping...");
                    stop = true;
                    break;
                default:
                    System.out.println("Invalid input, please enter 1-4.");
                    stop = false;
                    break;
            }
        }
    }

    private static void addNode(Node node, int inputValue) {
        if (inputValue < node.value) {
            if (node.left == null) {
                node.left = new Node(inputValue);
            } else {addNode(node.left, inputValue);}
        }
        else {
            if (node.right == null) {
                node.right = new Node(inputValue);
            } else {addNode(node.right, inputValue);}
        }
    }

    private static void traversalInOrder(Node node) {
        if (node == null) {
            return;
        } else {
            traversalInOrder(node.left);
            System.out.println(node.value);
            traversalInOrder(node.right);
        }

    }

    private static boolean containsValue(Node node, int key) {
        if (key == node.value) {
            return true;
        } else if (key < node.value) {
            if (node.left == null) {
                return false;
            } else {
                return containsValue(node.left, key);
            }
        } else {
            if (node.right == null) {
                return false;
            } else {return containsValue(node.right, key);}
        }

    }
}
