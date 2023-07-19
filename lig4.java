package lig4;
public class Lig4 {
    private char[][] tabuleiro;
    private int jogadorAtual;
    private boolean fimDeJogo;

    private static final int NUM_LINHAS = 6;
    private static final int NUM_COLUNAS = 7;
    private static final char VAZIO = '-';
    private static final char JOGADOR1 = 'X';
    private static final char JOGADOR2 = 'O';

    public Lig4() {
        tabuleiro = new char[NUM_LINHAS][NUM_COLUNAS];
        jogadorAtual = 1;
        fimDeJogo = false;
        iniciar();
    }

    private void iniciar() {
        for (int linha = 0; linha < NUM_LINHAS; linha++) {
            for (int coluna = 0; coluna < NUM_COLUNAS; coluna++) {
                tabuleiro[linha][coluna] = VAZIO;
            }
        }
    }

    public void realizarMovimento(int coluna) {
        if (fimDeJogo) {
            System.out.println("O jogo acabou. Reinicie para jogar novamente");
            return;
        }

        if (coluna < 0 || coluna >= NUM_COLUNAS) {
            System.out.println("Coluna Inválida. Tente Novamente");
            return;
        }

        int linha = getLinhaVazia(coluna);
        if (linha == -1) {
            System.out.println("A coluna está cheia. Tente novamente.");
            return;
        }

        char simbolo = (jogadorAtual == 1) ? JOGADOR1 : JOGADOR2;
        tabuleiro[linha][coluna] = simbolo;

        if (verificarVitoria(linha, coluna)) {
            fimDeJogo = true;
            System.out.println("Jogador " + jogadorAtual + " venceu!");
            return;
        }

        if (tabuleiroCheio()) {
            fimDeJogo = true;
            System.out.println("Empate! O jogo terminou sem vencedores.");
            return;
        }

        jogadorAtual = (jogadorAtual == 1) ? 2 : 1;
    }

    private int getLinhaVazia(int coluna) {
        for (int linha = NUM_LINHAS - 1; linha >= 0; linha--) {
            if (tabuleiro[linha][coluna] == VAZIO) {
                return linha;
            }
        }
        return -1;
    }

    private boolean verificarVitoria(int linha, int coluna) {
        char simbolo = tabuleiro[linha][coluna];

        if (linha >= 3 && tabuleiro[linha - 1][coluna] == simbolo && tabuleiro[linha - 2][coluna] == simbolo && tabuleiro[linha - 3][coluna] == simbolo) {
            return true;
        }

        int contar = 0;
        for (int c = Math.max(0, coluna - 3); c <= Math.min(coluna + 3, NUM_COLUNAS - 1); c++) {
            if (tabuleiro[linha][c] == simbolo) {
                contar++;
                if (contar >= 4) {
                    return true;
                }
            } else {
                contar = 0;
            }
        }

        contar = 0;
        int startRow = linha;
        int startCol = coluna;
        while (startRow > 0 && startCol > 0) {
            startRow--;
            startCol--;
        }
        while (startRow <= NUM_LINHAS - 4 && startCol <= NUM_COLUNAS - 4) {
            if (tabuleiro[startRow][startCol] == simbolo && tabuleiro[startRow + 1][startCol + 1] == simbolo
                    && tabuleiro[startRow + 2][startCol + 2] == simbolo && tabuleiro[startRow + 3][startCol + 3] == simbolo) {
                return true;
            }
            startRow++;
            startCol++;
        }

        contar = 0;
        startRow = linha;
        startCol = coluna;
        while (startRow > 0 && startCol < NUM_COLUNAS - 1) {
            startRow--;
            startCol++;
        }
        while (startRow <= NUM_LINHAS - 4 && startCol >= 3) {
            if (tabuleiro[startRow][startCol] == simbolo && tabuleiro[startRow + 1][startCol - 1] == simbolo
                    && tabuleiro[startRow + 2][startCol - 2] == simbolo && tabuleiro[startRow + 3][startCol - 3] == simbolo) {
                return true;
            }
            startRow++;
            startCol--;
        }

        return false;
    }

    private boolean tabuleiroCheio() {
        for (int linha = 0; linha < NUM_LINHAS; linha++) {
            for (int coluna = 0; coluna < NUM_COLUNAS; coluna++) {
                if (tabuleiro[linha][coluna] == VAZIO) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Lig4 jogo = new Lig4();
        jogo.realizarMovimento(3);
        jogo.realizarMovimento(2);
        jogo.realizarMovimento(3);
        jogo.realizarMovimento(2);
        jogo.realizarMovimento(3);
        jogo.realizarMovimento(2);
        jogo.realizarMovimento(3);
    }
}