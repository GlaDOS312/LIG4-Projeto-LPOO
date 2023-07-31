package Lig4Pasta.Lig4Game.src;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lig4 {
    private char[][] tabuleiro;
    private int jogadorAtual;
    private final char[] jogadores = {'X', 'O'};
    private final int linhas = 6;
    private final int colunas = 7;
    private Lig4Turbo turbo; // Instância da classe Lig4Turbo

    public Lig4() {
        tabuleiro = new char[linhas][colunas];
        for (char[] linha : tabuleiro) {
            Arrays.fill(linha, ' ');
        }
        jogadorAtual = 0;
        turbo = new Lig4Turbo(); // Inicializa a instância da classe Lig4Turbo
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
                    // Modo contra a máquina - escolhe uma coluna aleatória
                    Random random = new Random();
                    col = random.nextInt(colunas);
                }
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

                // Chama o método turboMode através da instância da classe Lig4Turbo
                turbo.turboMode(linha, col);

                break;
            }
        }
        return linha;
    }

    protected boolean checarVitoria(int linha, int col) {   

        // Lógica para verificar a vitória (igual ao código original)
        // ...

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

    // Classe Lig4Turbo
    protected class Lig4Turbo {
        // Construtor da classe Lig4Turbo
        public Lig4Turbo() {
            // Você pode adicionar um construtor para a classe Lig4Turbo se necessário
        }

        // Método turboMode
        protected void turboMode(int linha, int col) {
            char jogador = jogadores[jogadorAtual];

            // Modo Turbo - Altera as peças vizinhas na horizontal
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
                    jogo.jogar();
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
