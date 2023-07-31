package lig4.tabuleiro;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import lig4.tabuleiro.Lig4TurboMode;

public class Lig4TurboMaluco {
    protected char[][] tabuleiro;
    protected int jogadorAtual;
    protected final char[] jogadores = {'X', 'O'};
    protected final int linhas = 6;
    protected final int colunas = 7;
    protected int nivelMaluquice;

    public Lig4TurboMaluco() {
        tabuleiro = new char[linhas][colunas];
        for (char[] linha : tabuleiro) {
            Arrays.fill(linha, ' ');
        }
        jogadorAtual = 0;
        nivelMaluquice = 5;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean fimDeJogo = false;
        int linha, col;

        System.out.println("Bem-vindo ao Lig 4 Turbo Maluco!");

        while (!fimDeJogo) {
            printTabuleiro();

            do {
                System.out.printf("Jogador %c, escolha uma coluna (1 a 7): ", jogadores[jogadorAtual]);
                col = scanner.nextInt() - 1;
            } while (!movimentoValido(col));

            linha = soltarPeca(col);
            malucoMode(linha, col);
            turboMalucoMode(linha, col);
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

        scanner.close();
    }

    protected void printTabuleiro() {
        System.out.println(" 1 2 3 4 5 6 7");
        for (int i = 0; i < linhas; i++) {
            System.out.print("|");
            for (int j = 0; j < colunas; j++) {
                System.out.print(tabuleiro[i][j] + "|");
            }
            System.out.println();
        }
    }

    protected boolean movimentoValido(int col) {
        return col >= 0 && col < colunas && tabuleiro[0][col] == ' ';
    }

    protected int soltarPeca(int col) {
        int linha;
        for (linha = linhas - 1; linha >= 0; linha--) {
            if (tabuleiro[linha][col] == ' ') {
                tabuleiro[linha][col] = jogadores[jogadorAtual];
                break;
            }
        }
        return linha;
    }

    protected boolean checarVitoria(int linha, int col) {
        char jogador = jogadores[jogadorAtual];
        int contar = 0;
        for (int i = Math.max(0, col - 3); i <= Math.min(colunas - 1, col + 3); i++) {
            if (tabuleiro[linha][i] == jogador) {
                contar++;
                if (contar == 4) return true;
            } else {
                contar = 0;
            }
        }
        return false;
    }

    protected boolean checarEmpate() {
        for (int i = 0; i < colunas; i++) {
            if (tabuleiro[0][i] == ' ') {
                return false;
            }
        }
        return true;
    }

    protected void malucoMode(int linha, int col) {
        char jogador = jogadores[jogadorAtual];
        for (int i = col + 1; i <= Math.min(col + 3, colunas - 1); i++) {
            if (tabuleiro[linha][i] != jogador) {
                break;
            }
            tabuleiro[linha][i] = jogador;
        }

        for (int i = col - 1; i >= Math.max(col - 3, 0); i--) {
            if (tabuleiro[linha][i] != jogador) {
                break;
            }
            tabuleiro[linha][i] = jogador;
        }
    }

    protected void turboMalucoMode(int linha, int col) {
        char jogador = jogadores[jogadorAtual];
        Random random = new Random();

        for (int i = linha - 1; i <= linha + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < linhas && j >= 0 && j < colunas && tabuleiro[i][j] != jogador) {
                    int chanceMudanca = random.nextInt(10);
                    if (chanceMudanca < nivelMaluquice) {
                        tabuleiro[i][j] = jogador;
                    }
                }
            }
        }
    }

    public void setNivelMaluquice(int nivelMaluquice) {
        this.nivelMaluquice = nivelMaluquice;
    }

}