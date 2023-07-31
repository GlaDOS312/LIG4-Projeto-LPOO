import java.util.Arrays;
import java.util.Scanner;

public class Lig4Turbo {
    protected char[][] board;
    protected int currentPlayer;
    protected final char[] players = {'X', 'O'};
    protected final int rows = 6;
    protected final int columns = 7;

    public Lig4Turbo() {
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

        System.out.println("Bem-vindo ao Lig 4!");

        while (!gameOver) {
            printBoard();

            do {
                System.out.printf("Jogador %c, escolha uma coluna (1 a 7): ", players[currentPlayer]);
                col = scanner.nextInt() - 1;
            } while (!isValidMove(col));

            row = dropPiece(col);
            turboMode(row, col); // Activate Turbo Mode
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

    protected void printBoard() {
        System.out.println(" 1 2 3 4 5 6 7");
        for (int i = 0; i < rows; i++) {
            System.out.print("|");
            for (int j = 0; j < columns; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
    }

    protected boolean isValidMove(int col) {
        return col >= 0 && col < columns && board[0][col] == ' ';
    }

    protected int dropPiece(int col) {
        int row;
        for (row = rows - 1; row >= 0; row--) {
            if (board[row][col] == ' ') {
                board[row][col] = players[currentPlayer];
                break;
            }
        }
        return row;
    }

    protected boolean checkWin(int row, int col) {
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

        // ... (o restante do código do checkWin permanece o mesmo)
    }

    protected boolean checkDraw() {
        // ... (código do checkDraw permanece o mesmo)
    }
}

class Lig4TurboGame extends Lig4Game {
    @Override
    protected void turboMode(int row, int col) {
        char player = players[currentPlayer];

        // Check left
        for (int i = col - 1; i >= Math.max(0, col - 3); i--) {
            if (board[row][i] != player) {
                break;
            }
            board[row][i] = player;
        }

        // Check right
        for (int i = col + 1; i <= Math.min(columns - 1, col + 3); i++) {
            if (board[row][i] != player) {
                break;
            }
            board[row][i] = player;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Lig4Turbo game = new Lig4Turbo();
        game.play();
    }
}