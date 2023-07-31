package tabuleiro;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lig4 {
    private char[][] tabuleiro;
    private int jogadorAtual;
    private final char[] jogadores = {'X', 'O'};
    private final int linhas = 6;
    private final int colunas = 7;

    public Lig4() {
        tabuleiro = new char[linhas][colunas];
        for (char[] linha : tabuleiro) {
            Arrays.fill(linha, ' ');
        }
        jogadorAtual = 0;
    }

    public void jogar() {
        Scanner scanner = new Scanner(System.in);
        int modo;
        System.out.println("Bem-vindo ao Lig4!");
        do {
            System.out.println("Escolha o modo de jogo:");
            System.out.println("1 - Jogador vs. Jogador");
            System.out.println("2 - Jogador vs. Computador");
            modo = scanner.nextInt();
        } while (modo < 1 || modo > 2);

        if (modo == 1) {
            System.out.println("Modo selecionado: Jogador vs. Jogador");
            jogarModoJogador(scanner);
        } else {
            System.out.println("Modo selecionado: Jogador vs. Computador");
            jogarModoComputador();
        }

        scanner.close();
    }

    private void jogarModoJogador(Scanner scanner) {
        boolean fimDeJogo = false;
        int linha, col;

        while (!fimDeJogo) {
            printTabuleiro();

            do {
                System.out.printf("Jogador %c, escolha uma coluna (1 a 7): ", jogadores[jogadorAtual]);
                col = scanner.nextInt() - 1;
            } while (!movimentoValido(col));

            linha = soltarPeca(col);
            if (checarVitoria(linha, col)) {
                printTabuleiro();
                System.out.printf("Jogador %c venceu! Parabéns!\n", jogadores[jogadorAtual]);
                fimDeJogo = true;
            } else if (checarEmpate()) {
                printTabuleiro();
                System.out.println("Empate! O tabuleiro está cheio.");
                fimDeJogo = true;
            }

            jogadorAtual = (jogadorAtual + 1) % 2;
        }
    }

    private void jogarModoComputador() {
        Random random = new Random();
        boolean fimDeJogo = false;
        int linha, col;

        while (!fimDeJogo) {
            if (jogadorAtual == 0) {
                printTabuleiro();

                int colJogador;
                do {
                    Scanner scanner = new Scanner(System.in);
                    System.out.printf("Jogador %c, escolha uma coluna (1 a 7): ", jogadores[jogadorAtual]);
                    colJogador = scanner.nextInt() - 1;
                } while (!movimentoValido(colJogador));

                linha = soltarPeca(colJogador);
                if (checarVitoria(linha, colJogador)) {
                    printTabuleiro();
                    System.out.printf("Jogador %c venceu! Parabéns!\n", jogadores[jogadorAtual]);
                    fimDeJogo = true;
                } else if (checarEmpate()) {
                    printTabuleiro();
                    System.out.println("Empate! O tabuleiro está cheio.");
                    fimDeJogo = true;
                }
            } else {
                // Jogada do Computador
                col = random.nextInt(colunas);
                while (!movimentoValido(col)) {
                    col = random.nextInt(colunas);
                }
                linha = soltarPeca(col);
                if (checarVitoria(linha, col)) {
                    printTabuleiro();
                    System.out.println("O computador venceu!");
                    fimDeJogo = true;
                } else if (checarEmpate()) {
                    printTabuleiro();
                    System.out.println("Empate! O tabuleiro está cheio.");
                    fimDeJogo = true;
                }
            }

            jogadorAtual = (jogadorAtual + 1) % 2;
        }
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

    private boolean movimentoValido(int col) {
        if (col < 0 || col >= colunas) {
            return false;
        }
        return tabuleiro[0][col] == ' ';
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
        char jogador = jogadores[jogadorAtual];

        // Verificação na horizontal
        int contar = 0;
        for (int i = Math.max(0, col - 3); i <= Math.min(colunas - 1, col + 3); i++) {
            if (tabuleiro[linha][i] == jogador) {
                contar++;
                if (contar == 4) return true;
            } else {
                contar = 0;
            }
        }

        // Verificação na vertical
        contar = 0;
        for (int i = Math.max(0, linha - 3); i <= Math.min(linhas - 1, linha + 3); i++) {
            if (tabuleiro[i][col] == jogador) {
                contar++;
                if (contar == 4) return true;
            } else {
                contar = 0;
            }
        }

        // Verificação na diagonal principal (de cima para baixo e da esquerda para a direita)
        contar = 0;
        int offset = Math.min(col, linha);
        int startCol = col - offset;
        int startRow = linha - offset;
        for (int i = startRow, j = startCol; i < linhas && j < colunas; i++, j++) {
            if (tabuleiro[i][j] == jogador) {
                contar++;
                if (contar == 4) return true;
            } else {
                contar = 0;
            }
        }

        // Verificação na diagonal secundária (de cima para baixo e da direita para a esquerda)
        contar = 0;
        offset = Math.min(colunas - col - 1, linha);
        startCol = col + offset;
        startRow = linha - offset;
        for (int i = startRow, j = startCol; i < linhas && j >= 0; i++, j--) {
            if (tabuleiro[i][j] == jogador) {
                contar++;
                if (contar == 4) return true;
            } else {
                contar = 0;
            }
        }

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
        Lig4 jogo = new Lig4();
        jogo.jogar();
    }
}
