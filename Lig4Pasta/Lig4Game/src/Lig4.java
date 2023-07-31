package Lig4Pasta.Lig4Game.src;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class Lig4 {
    protected char[][] tabuleiro;
    protected int jogadorAtual;
    protected final char[] jogadores = {'X', 'O'};
    protected final int linhas = 6;
    protected final int colunas = 7;

    public Lig4() {
        tabuleiro = new char[linhas][colunas];
        for (char[] linha : tabuleiro) {
            Arrays.fill(linha, ' ');
        }
        jogadorAtual = 0;
    }

    public void jogar() {
        Scanner scanner = new Scanner(System.in);
        boolean fimDeJogo = false;
        int linha, col;

        System.out.println("Bem-vindo ao Lig4!");

        while (!fimDeJogo) {
            printTabuleiro();

            do {
                if (jogadorAtual == 0) {
                    System.out.printf("Jogador %c, escolha uma coluna (1 a 7): ", jogadores[jogadorAtual]);
                    col = scanner.nextInt() - 1;
                } else {
                    Random random = new Random();
                    col = random.nextInt(colunas);
                }
            } while (!movimentoValido(col));

            linha = soltarPeca(col);
            if (checarVitoria(linha, col)) {
                if (checarVitoria(linha, col)) {
                    printTabuleiro();
                    System.out.printf("Jogador %c venceu! Parabéns!\n", jogadores[jogadorAtual]);
                    fimDeJogo = true;
                    break;
                }                
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
        char jogador = tabuleiro[linha][col];
    
        // Verificar vitória na horizontal
        int contar = 0;
        for (int i = Math.max(0, col - 3); i <= Math.min(colunas - 1, col + 3); i++) {
            if (tabuleiro[linha][i] == jogador) {
                contar++;
                if (contar == 4) return true;
            } else {
                contar = 0;
            }
        }
    
        // Verificar vitória na vertical
        contar = 0;
        for (int i = Math.max(0, linha - 3); i <= Math.min(linhas - 1, linha + 3); i++) {
            if (tabuleiro[i][col] == jogador) {
                contar++;
                if (contar == 4) return true;
            } else {
                contar = 0;
            }
        }
    
        // Verificar vitória na diagonal inferior direita
        contar = 0;
        int startLinha = linha - Math.min(linha, col);
        int startCol = col - Math.min(linha, col);
        for (int i = 0; i < Math.min(linhas - startLinha, colunas - startCol); i++) {
            if (tabuleiro[startLinha + i][startCol + i] == jogador) {
                contar++;
                if (contar == 4) return true;
            } else {
                contar = 0;
            }
        }
    
        // Verificar vitória na diagonal superior direita
        contar = 0;
        startLinha = linha - Math.min(linha, colunas - col - 1);
        startCol = col + Math.min(linha, colunas - col - 1);
        for (int i = 0; i < Math.min(linhas - startLinha, startCol + 1); i++) {
            if (tabuleiro[startLinha + i][startCol - i] == jogador) {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao Lig4!");

        Lig4 jogo = new Lig4();

        boolean continuar = true;

        while (continuar) {
            System.out.println("Escolha o modo de jogo:");
            System.out.println("1 - Jogar contra outro jogador");
            System.out.println("2 - Jogar contra a máquina");
            System.out.println("3 - Jogar no modo Turbo");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Modo: Jogador vs. Jogador");
                    jogo.jogar();
                    break;
                case 2:
                    System.out.println("Modo: Jogador vs. Máquina");
                    jogo.jogar();
                    break;
                case 3:
                    System.out.println("Modo: Lig4Turbo");
                    Lig4Turbo jogoTurbo = new Lig4Turbo();
                    jogoTurbo.jogar();
                    break;
                case 0:
                    System.out.println("Saindo do jogo. Até a próxima!");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }
}
