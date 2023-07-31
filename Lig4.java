import java.util.Arrays;
import java.util.Scanner;

public class Lig4 {
    private char[][] Tabuleiro;
    private int jogadorAtual;
    private final char[] Jogadores = {'X', 'O'};
    private final int Linhas = 6;
    private final int Colunas = 7;

    public Lig4() {
        Tabuleiro = new char[Linhas][Colunas];
        for (char[] Linha : Tabuleiro) {
            Arrays.fill(Linha, ' ');
        }
        jogadorAtual = 0;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean fimDeJogo = false;
        int Linha, col;

        System.out.println("Bem-vindo ao Lig4!");

        while (!fimDeJogo) {
            printTabuleiro();

            do {
                System.out.printf("Jogador %c, escolha uma coluna (1 a 7): ", Jogadores[jogadorAtual]);
                col = scanner.nextInt() - 1;
            } while (!movimentoValido(col));

            Linha = soltarPeca(col);
            if (checarVitoria(Linha, col)) {
                printTabuleiro();
                System.out.printf("Jogador %c venceu! Parabéns!\n", Jogadores[jogadorAtual]);
                fimDeJogo = true;
            } else if (checarEmpate()) {
                printTabuleiro();
                System.out.println("Empate! O tabuleiro está cheio.");
                fimDeJogo = true;
            }

            jogadorAtual = (jogadorAtual + 1) % 2;
        }

        scanner.close();
    }

    private void printTabuleiro() {
        System.out.println(" 1 2 3 4 5 6 7");
        for (int i = 0; i < Linhas; i++) {
            System.out.print("|");
            for (int j = 0; j < Colunas; j++) {
                System.out.print(Tabuleiro[i][j] + "|");
            }
            System.out.println();
        }
    }

    private boolean movimentoValido(int col) {
        return col >= 0 && col < Colunas && Tabuleiro[0][col] == ' ';
    }

    private int soltarPeca(int col) {
        int Linha;
        for (Linha = Linhas - 1; Linha >= 0; Linha--) {
            if (Tabuleiro[Linha][col] == ' ') {
                Tabuleiro[Linha][col] = Jogadores[jogadorAtual];
                break;
            }
        }
        return Linha;
    }

    private boolean checarVitoria(int Linha, int col) {
        char player = Jogadores[jogadorAtual];

        int contar = 0;
        for (int i = Math.max(0, col - 3); i <= Math.min(Colunas - 1, col + 3); i++) {
            if (Tabuleiro[Linha][i] == player) {
                contar++;
                if (contar == 4) return true;
            } else {
                contar = 0;
            }
        }

        contar = 0;
        for (int i = Math.max(0, Linha - 3); i <= Math.min(Linhas - 1, Linha + 3); i++) {
            if (Tabuleiro[i][col] == player) {
                contar++;
                if (contar == 4) return true;
            } else {
                contar = 0;
            }
        }

        contar = 0;
        int startLinha = Linha - Math.min(Linha, col);
        int startCol = col - Math.min(Linha, col);
        for (int i = 0; i < Math.min(Linhas - startLinha, Colunas - startCol); i++) {
            if (Tabuleiro[Linha + i][startCol + i] == player) {
                contar++;
                if (contar == 4) return true;
            } else {
                contar = 0;
            }
        }

        contar = 0;
        startLinha = Linha - Math.min(Linha, Colunas - col - 1);
        startCol = col + Math.min(Linha, Colunas - col - 1);
        for (int i = 0; i < Math.min(Linhas - startLinha, startCol + 1); i++) {
            if (Tabuleiro[startLinha + i][startCol - i] == player) {
                contar++;
                if (contar == 4) return true;
            } else {
                contar = 0;
            }
        }

        return false;
    }

    private boolean checarEmpate() {
        for (int i = 0; i < Colunas; i++) {
            if (Tabuleiro[0][i] == ' ') {
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