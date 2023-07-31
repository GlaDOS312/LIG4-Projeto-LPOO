import java.util.Arrays;
import java.util.Scanner;

public class Lig4 {
    private char[][] board;
    private int currentPlayer;
    private final char[] players = {'X', 'O'};
    private final int rows = 6;
    private final int columns = 7;

    public Lig4() {
        board = new char[rows][columns];
        for (char[] row : board) {
            Arrays.fill(row, ' ');
        }
        currentPlayer = 0;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;
        int row, col;

        System.out.println("Bem-vindo ao Lig4!");

        while (!gameOver) {
            printBoard();

            do {
                System.out.printf("Jogador %c, escolha uma coluna (1 a 7): ", players[currentPlayer]);
                col = scanner.nextInt() - 1;
            } while (!isValidMove(col));

            row = dropPiece(col);
            if (checkWin(row, col)) {
                printBoard();
                System.out.printf("Jogador %c venceu! Parabéns!\n", players[currentPlayer]);
                gameOver = true;
            } else if (checkDraw()) {
                printBoard();
                System.out.println("Empate! O tabuleiro está cheio.");
                gameOver = true;
            }

            currentPlayer = (currentPlayer + 1) % 2;
        }

        scanner.close();
    }

    private void printBoard() {
        System.out.println(" 1 2 3 4 5 6 7");
        for (int i = 0; i < rows; i++) {
            System.out.print("|");
            for (int j = 0; j < columns; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
    }

    private boolean isValidMove(int col) {
        return col >= 0 && col < columns && board[0][col] == ' ';
    }

    private int dropPiece(int col) {
        int row;
        for (row = rows - 1; row >= 0; row--) {
            if (board[row][col] == ' ') {
                board[row][col] = players[currentPlayer];
                break;
            }
        }
        return row;
    }

    private boolean checkWin(int row, int col) {
        char player = players[currentPlayer];

        // Check horizontal
        int count = 0;
        for (int i = Math.max(0, col - 3); i <= Math.min(columns - 1, col + 3); i++) {
            if (board[row][i] == player) {
                count++;
                if (count == 4) return true;
            } else {
                count = 0;
            }
        }

        // Check vertical
        count = 0;
        for (int i = Math.max(0, row - 3); i <= Math.min(rows - 1, row + 3); i++) {
            if (board[i][col] == player) {
                count++;
                if (count == 4) return true;
            } else {
                count = 0;
            }
        }

        // Check diagonal (top-left to bottom-right)
        count = 0;
        int startRow = row - Math.min(row, col);
        int startCol = col - Math.min(row, col);
        for (int i = 0; i < Math.min(rows - startRow, columns - startCol); i++) {
            if (board[startRow + i][startCol + i] == player) {
                count++;
                if (count == 4) return true;
            } else {
                count = 0;
            }
        }

        // Check diagonal (top-right to bottom-left)
        count = 0;
        startRow = row - Math.min(row, columns - col - 1);
        startCol = col + Math.min(row, columns - col - 1);
        for (int i = 0; i < Math.min(rows - startRow, startCol + 1); i++) {
            if (board[startRow + i][startCol - i] == player) {
                count++;
                if (count == 4) return true;
            } else {
                count = 0;
            }
        }

        return false;
    }

    private boolean checkDraw() {
        for (int i = 0; i < columns; i++) {
            if (board[0][i] == ' ') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Lig4 game = new Lig4();
        game.play();
    }
}
