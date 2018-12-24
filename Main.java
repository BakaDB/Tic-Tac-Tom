import java.util.*;
import javax.swing.*;

/**
 * Using the mi minimax algorithm. Tom plays Tic-Tac-Toe against the player.
 */
public class Main {
    
    /*
    The board is a 3x3 matrix, however its internal representation is
    an equal length array.
    */
    private static int[] board = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private static final int playerID = 100;
    private static final int aiID = 200;
    private static boolean humanTurn = false;
    private static int round = 1;
    
    public static class Move {    
        protected int index, score;        
        public Move (int index, int score) {
            this.index = index;
            this.score = score;
        }
        public Move() {
            this(-1, -1);
        }
    }
    
    private static void printInstructions() {
        System.out.println("You are playing against Tom an articial intelligence.");
        System.out.println("Who starts first will be choosen afterwards.");
        System.out.println("To play you must introduce the following coding to choose a cell:");
        System.out.println("\n----------------\n| 00 | 01 | 02 |\n----------------\n| 10 | 11 | 12 |" +
                           "\n----------------\n| 20 | 21 | 22 |\n----------------\n");
        System.out.println("The game begins!");
    }

    private static void printBoard() {
        int c = 1;
        for (int i = 0; i < board.length; i++) {            
            if (board[i] == 100) System.out.print("X ");
            else if (board[i] == 200) System.out.print("O ");
            else System.out.print("_ ");
            if (c == 3) {System.out.println(); c = 1;}
            else c++;
        }
        System.out.println();
    }
    
    /**
     * Search for the positions where a move can be performed.
     */
    public static List<Integer> emptyIndexes(int[] board) {
        List<Integer> l = new LinkedList<Integer>();
        for (int i = 0; i < board.length; i++) {
            if (board[i] != 100 && board[i] != 200) {
                l.add(i);
            }
        }
        return l;
    }
    
    /**
     * Checks if a player has won the game. 
     */
    public static boolean winning(int[] board, int player) {
        if (
            (board[0] == player && board[1] == player && board[2] == player) ||
            (board[3] == player && board[4] == player && board[5] == player) ||
            (board[6] == player && board[7] == player && board[8] == player) ||
            (board[0] == player && board[3] == player && board[6] == player) ||
            (board[1] == player && board[4] == player && board[7] == player) ||
            (board[2] == player && board[5] == player && board[8] == player) ||
            (board[0] == player && board[4] == player && board[8] == player) ||
            (board[2] == player && board[4] == player && board[6] == player)
        ) {
            return true;
        } else {
            return false;
        }
    }
    
    private static void humanMove() {
        while (true) {
            String first = JOptionPane.showInputDialog("Choose a cell to mark");
            if (first.equals("00") && board[0] != aiID && board[0] != playerID) {
                board[0] = playerID;
                break;
            } else if (first.equals("01") && board[1] != aiID && board[1] != playerID) {
                board[1] = playerID;
                break;
            } else if (first.equals("02") && board[2] != aiID && board[2] != playerID) {
                board[2] = playerID;
                break;
            } else if (first.equals("10") && board[3] != aiID && board[3] != playerID) {
                board[3] = playerID;
                break;
            } else if (first.equals("11") && board[4] != aiID && board[4] != playerID) {
                board[4] = playerID;
                break;
            } else if (first.equals("12") && board[5] != aiID && board[5] != playerID) {
                board[5] = playerID;
                break;
            } else if (first.equals("20") && board[6] != aiID && board[6] != playerID) {
                board[6] = playerID;
                break;
            } else if (first.equals("21") && board[7] != aiID && board[7] != playerID) {
                board[7] = playerID;
                break;
            } else if (first.equals("22") && board[8] != aiID && board[8] != playerID) {
                board[8] = playerID;
                break;
            }
        }
    }
    
    public static void aiMove() {
        int index = (int) miniMax(board, aiID).index;
        board[index] = aiID;
    }
    
    /**
     * Tom applies the minimax algorithm to search for its best possible move.
     */
    public static Move miniMax(int[] newBoard, int player) {
        List<Integer> availableSpots = emptyIndexes(newBoard);
        
        if (winning(newBoard, playerID)) {
            return new Move(-1, -10);
        } else if (winning(newBoard, aiID)) {
            return new Move(-1, 10);
        } else if (availableSpots.size() == 0) {
            return new Move(-1, 0);
        }
        
        List<Move> moves = new LinkedList<Move>();
        for (Integer n : availableSpots) {
            Move move = new Move();
            move.index = n;
            newBoard[n] = player;
            
            if (player == aiID) {
                Move aux = miniMax(newBoard, playerID);
                move.score = aux.score;
            } else {
                Move aux = miniMax(newBoard, aiID);
                move.score = aux.score;
            }
            
            newBoard[n] = move.index;
            moves.add(move);
        }
        
        int bestMove = 0;
        if (player == aiID) {
            int bestScore = -1000;
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).score > bestScore) {
                    bestScore = moves.get(i).score;
                    bestMove = i;
                }
            }
        } else {
            int bestScore = 1000;
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).score < bestScore) {
                    bestScore = moves.get(i).score;
                    bestMove = i;
                }
            }
        }
        return moves.get(bestMove);
    }
    
    public static void main(String[] args) {
        System.out.print('\u000C');
        printInstructions();
        printBoard();
        //Select the first player
        while (true) {
            String first = JOptionPane.showInputDialog("Who goes first? the player(Enter P) or Tom(T)");
            if (first == null) {
                System.out.println("See you next time!");
                System.exit(0);
            }
            first = first.toUpperCase();
            if (first.equals("P")) {
                System.out.println("\nPlayer goes first");
                humanTurn = true;
                break;
            } else if (first.equals("T")) {
                System.out.println("\nTom goes first");
                humanTurn = false;
                break;
            } else {
                System.out.println("Invalid answer");
            }
        }
        
        while (true) {
            if (humanTurn) {
                humanMove();
            } else {
                if (round == 1) {
                    //Tom's first move is random
                    int pos = (int) Math.floor(Math.random()*9);                    
                    board[pos] = aiID;
                } else {
                    aiMove();
                }
            }
            humanTurn = !humanTurn;
            printBoard();
            
            //Check if a player won
            if (winning(board, playerID)) {
                System.out.println("You win");
                break;
            } else if(winning(board, aiID)) {
                System.out.println("You lose");
                break;
            }
            /*
            At round 9 the board is complete the game finishes with a draw
            or next round occurs
            */
            if (round > 8) {
                System.out.println("It is a draw");
                break;
            } else {
                round++;
            }
        }
    }
}
