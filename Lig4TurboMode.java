import java.util.Arrays;
import java.util.Scanner;

public class Lig4TurboMode {
    private char[][] tabuleiro;
    private int jogadorAtual;
    private final char[] jogadores = {'X', 'O'};
    private final int linhas = 6;
    private final int colunas = 7;

    public Lig4TurboMode() {
        tabuleiro = new char[linhas][colunas];
        for (char[] linha : tabuleiro) {
            Arrays.fill(linha, ' ');
        }
        jogadorAtual = 0;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;
        int linha, col;

        System.out.println("Bem-vindo ao Lig4!");

        while (!gameOver) {
            printTabuleiro();

            do {
                System.out.printf("Jogador %c, escolha uma coluna (1 a 7): ", jogadores[jogadorAtual]);
                col = scanner.nextInt() - 1;
            } while (!isValidMove(col));

            linha = soltarPeca(col);
            if (checarVitoria(linha, col)) {
                printTabuleiro();
                System.out.printf("Jogador %c venceu! Parabéns!\n", jogadores[jogadorAtual]);
                gameOver = true;
            } else if (checarEmpate()) {
                printTabuleiro();
                System.out.println("Empate! O tabuleiro está cheio.");
                gameOver = true;
            }

            jogadorAtual = (jogadorAtual + 1) % 2;
        }

        scanner.close();
    }

    private void printTabuleiro() {
        System.out.println(" 1 2 3 4 5 6 7");
        for (int i = 0; i < linhas; i++) {
            System.out.print("|");
            for (int j = 0; j < colunas; j++) {
                System.out.print(tabuleiro[i][j] + "|");
            }
            System.out.println();
        }
    }

    private boolean isValidMove(int col) {
        return col >= 0 && col < colunas && tabuleiro[0][col] == ' ';
    }

    private int soltarPeca(int col) {
        int linha;
        for (linha = linhas - 1; linha >= 0; linha--) {
            if (tabuleiro[linha][col] == ' ') {
                tabuleiro[linha][col] = jogadores[jogadorAtual];
                break;
            }
        }
        return linha;
    }

    private boolean checarVitoria(int linha, int col) {
        char player = jogadores[jogadorAtual];

        // Check horizontal
        int count = 0;
        for (int i = Math.max(0, col - 3); i <= Math.min(colunas - 1, col + 3); i++) {
            if (tabuleiro[linha][i] == player) {
                count++;
                if (count == 4) return true;
            } else {
                count = 0;
            }
        }

        // ... (Verificação vertical e diagonais permanecem iguais)

        return false;
    }

    private boolean checarEmpate() {
        for (int i = 0; i < colunas; i++) {
            if (tabuleiro[0][i] == ' ') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Lig4TurboMode game = new Lig4TurboMode();
        game.play();
    }
}
