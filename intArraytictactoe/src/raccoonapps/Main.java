package raccoonapps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        //initializing game board and input object
        int[] board = {0,0,0,0,0,0,0,0,0};
        BufferedReader inputStream =
                new BufferedReader(new InputStreamReader(System.in));

        //while loop implemented for main game cycle
        //runs on conditionals: game board still has space and no winning rows exist
        while ((gameOverCheck(board)) && (checkWinState(board) == 0)) {
            //parses user input, converts to coordinates on game board
            //in array: 1 is player 1, 2 is player 2, 0 is blank cell

            System.out.print("Player 1's turn.");
            System.out.println(" Enter the index of the cell you wish to play:");
            printBoard(board);
            while (true) {
                int input1 = Integer.parseInt(inputStream.readLine())-1;
                if (input1 > 8 || input1 < 0) {
                    System.out.println("Invalid input, please enter 1-9.");
                } else if (!isEmptySpace(input1, board)) {
                    System.out.println("Space is already taken, please choose another.");
                } else {
                    board[input1] = 1;
                    break;
                }
            }

            //if statement checks whether game is over
            if ((gameOverCheck(board)) && (checkWinState(board) == 0)) {
                System.out.print("Player 2's turn.");
                System.out.println(" Enter the index of the cell you wish to play:");
                printBoard(board);
                while (true) {
                    int input2 = Integer.parseInt(inputStream.readLine())-1;
                    if (input2 > 8 || input2 < 0) {
                        System.out.println("Invalid input, please enter 0-8.");
                    } else if (!isEmptySpace(input2, board)) {
                        System.out.println("Space is already taken, please choose another.");
                    } else {
                        board[input2] = 2;
                        break;
                    }
                }
            } else {break;}
        }

        //takes winState and prints results
        printBoard(board);
        int win = checkWinState(board);
        switch(win) {
            case 1: System.out.println("Player 1 wins!");
                break;
            case 2: System.out.println("Player 2 wins!");
                break;
            default: System.out.println("It's a draw.");
        }
        System.out.println("Thank you for playing.");
    }

    private static boolean isEmptySpace(int index, int[] board) {
        boolean hasSpace = true;
        int check = board[index];
        if (check != 0) {
            hasSpace = false;
        }
        return hasSpace;
    }

    //evaluates to see if there are any empty spaces left
    //returns false if no more empty spaces
    private static boolean gameOverCheck(int[] board) {
        boolean hasClearSpace = false;

        //linear search algorithm to return index value
        for (int i = 0; i < 9; i++) {
            int spaceIndex = board[i];
            if (spaceIndex == 0) {
                hasClearSpace = true;
                break;
            }
        }
        return hasClearSpace;
    }

    //evaluates which side won or draw
    //returns integer from 0-2 for win state
    private static int checkWinState(int[] board) {
        int winState = 0;

        //for loop iterates across game board
        //switch statement equates rows and diagonals
        //8 possible cases
        for (int a = 0; a < 8; a++) {

            switch (a) {
                case 0:
                    if (board[0] == board[1] && board[1] == board[2]) {
                        winState = board[0];
                    } break;
                case 1:
                    if (board[3] == board[4] && board[4] == board[5]) {
                        winState = board[4];
                    } break;
                case 2:
                    if (board[6] == board[7] && board[7] == board[8]) {
                        winState = board[6];
                    } break;
                case 3:
                    if (board[0] == board[3] && board[3] == board[6]) {
                        winState = board[0];
                    } break;
                case 4:
                    if (board[1] == board[4] && board[4] == board[7]) {
                        winState = board[1];
                    } break;
                case 5:
                    if (board[2] == board[5] && board[5] == board[8]) {
                        winState = board[2];
                    } break;
                case 6:
                    if (board[0] == board[4] && board[4] == board[8]) {
                        winState = board[0];
                    } break;
                case 7:
                    if (board[2] == board[4] && board[4] == board[6]) {
                        winState = board[2];
                    } break;
            }
            if (winState != 0) {
                break;
            }
        }
        return winState;
    }

    private static void printBoard(int[] board) {
        System.out.println("/---|---|---\\");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("/---|---|---\\");
    }
}