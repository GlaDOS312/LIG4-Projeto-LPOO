package Lig4Pasta.Lig4Game.src;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lig4 implements Lig4Interface {
    public char[][] tabuleiro;
    public int jogadorAtual;
    public final char[] jogadores = {'X', 'O'};
    public final int linhas = 6;
    public final int colunas = 7;

    public Lig4() {
        tabuleiro = new char[linhas][colunas];
        for (char[] linha : tabuleiro) {
            Arrays.fill(linha, ' ');
        }
        jogadorAtual = 0;
    }

    public void jogar() {
        try (Scanner scanner = new Scanner(System.in)) {
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
                    printTabuleiro();
                    System.out.printf("Jogador %c venceu! Parabéns!\n", jogadores[jogadorAtual]);
                    fimDeJogo = true;
                    System.out.print("Deseja voltar ao menu principal? (S/N): ");
                    scanner.nextLine();
                    String resposta = scanner.next().toLowerCase();
                    if (resposta.equals("s")) {
                        return;
                    } else if (resposta.equals("n")) {
                        System.out.println("Saindo do jogo. Até a próxima!");
                        System.exit(0);
}


                    if (resposta.equals("s")) {
                        return;
                    } else if (resposta.equals("n")) {
                        System.out.println("Saindo do jogo. Até a próxima!");
                        System.exit(0);
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

    }

    public void printTabuleiro() {
        System.out.println(" 1 2 3 4 5 6 7");
        for (int i = 0; i < linhas; i++) {
            System.out.print("|");
            for (int j = 0; j < colunas; j++) {
                System.out.print(tabuleiro[i][j] + "|");
            }
            System.out.println();
        }
    }

    public boolean movimentoValido(int col) {
        return col >= 0 && col < colunas && tabuleiro[0][col] == ' ';
    }

    public int soltarPeca(int col) {
        int linha;
        for (linha = linhas - 1; linha >= 0; linha--) {
            if (tabuleiro[linha][col] == ' ') {
                tabuleiro[linha][col] = jogadores[jogadorAtual];
                turboMode(linha, col);
                break;
            }
        }
        return linha;
    }

    public boolean checarVitoria(int linha, int col) {
        char jogador = jogadores[jogadorAtual];
    
        int contarHorizontal = 0;
        for (int i = Math.max(0, col - 3); i <= Math.min(col + 3, colunas - 1); i++) {
            if (tabuleiro[linha][i] == jogador) {
                contarHorizontal++;
                if (contarHorizontal == 4) {
                    return true;
                }
            } else {
                contarHorizontal = 0;
            }
        }

        int contarVertical = 0;
        for (int i = Math.max(0, linha - 3); i <= Math.min(linha + 3, linhas - 1); i++) {
            if (tabuleiro[i][col] == jogador) {
                contarVertical++;
                if (contarVertical == 4) {
                    return true;
                }
            } else {
                contarVertical = 0;
            }
        }
    
        int contarDiagonal1 = 0;
        int startColDiagonal1 = col - Math.min(col, linha);
        int startRowDiagonal1 = linha - Math.min(col, linha);
        for (int i = 0; i < Math.min(linhas - startRowDiagonal1, colunas - startColDiagonal1); i++) {
            if (tabuleiro[startRowDiagonal1 + i][startColDiagonal1 + i] == jogador) {
                contarDiagonal1++;
                if (contarDiagonal1 == 4) {
                    return true;
                }
            } else {
                contarDiagonal1 = 0;
            }
        }
    
        int contarDiagonal2 = 0;
        int startColDiagonal2 = col - Math.min(col, linhas - linha - 1);
        int startRowDiagonal2 = linha + Math.min(col, linhas - linha - 1);
        for (int i = 0; i < Math.min(startRowDiagonal2 + 1, colunas - startColDiagonal2); i++) {
            if (tabuleiro[startRowDiagonal2 - i][startColDiagonal2 + i] == jogador) {
                contarDiagonal2++;
                if (contarDiagonal2 == 4) {
                    return true;
                }
            } else {
                contarDiagonal2 = 0;
            }
        }
    
        return false;
    }

    public boolean checarEmpate() {
        for (int i = 0; i < colunas; i++) {
            if (tabuleiro[0][i] == ' ') {
                return false;
            }
        }
        return true;
    }

    public void turboMode(int linha, int col) {
        char jogador = jogadores[jogadorAtual];
        for (int i = col + 1; i < colunas; i++) {
            if (tabuleiro[linha][i] != jogador) {
                break;
            }
            tabuleiro[linha][i] = jogador;
        }

        for (int i = col - 1; i >= 0; i--) {
            if (tabuleiro[linha][i] != jogador) {
                break;
            }
            tabuleiro[linha][i] = jogador;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao Lig4!");

        Lig4 jogo = new Lig4();

        boolean continuar = true;

        while (continuar) {
            System.out.println("Escolha o modo de jogo:");
            System.out.println("1 - Jogar X jogador");
            System.out.println("2 - Jogar X máquina");
            System.out.println("3 - Jogar no modo Turbo");
            System.out.println("4 - Jogar no modo Turbo Maluco");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Modo: Jogador X Jogador");
                    jogo.jogar();
                    break;
                case 2:
                    System.out.println("Modo: Jogador X Máquina");
                    jogo.jogar();
                    break;
                case 3:
                    System.out.println("Modo: Turbo");
                    jogo.jogar();
                    break;
                case 4:
                    System.out.println("Modo: Turbo Maluco");
                    System.out.print("Digite o nível de Maluquice (0 a 5): ");
                    int nivelMaluquice = scanner.nextInt();
                    Lig4TurboMaluco jogoTurboMaluco = new Lig4TurboMaluco(nivelMaluquice);
                    jogoTurboMaluco.jogar();
                    break;
                case 0:
                    System.out.println("Saindo do jogo. Até a próxima!");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
            } else {
                System.out.println("Entrada inválida. Insira um valor inteiro válido.");
                scanner.nextLine();
                continue;
            }
        }

        scanner.close();
    }
}