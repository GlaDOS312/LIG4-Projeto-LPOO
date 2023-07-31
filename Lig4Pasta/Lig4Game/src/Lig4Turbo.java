package Lig4Pasta.Lig4Game.src;

public class Lig4Turbo extends Lig4 {

    public Lig4Turbo() {
        super(); // Chama o construtor da classe pai (Lig4) para inicializar o tabuleiro e jogadores
    }

    @Override
    protected void turboMode(int linha, int col) {
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

        // Lógica específica do modo Turbo: mudar as peças vizinhas verticalmente
        for (int i = linha + 1; i < linhas; i++) {
            if (tabuleiro[i][col] != jogador) {
                break;
            }
            tabuleiro[i][col] = jogador;
        }

        for (int i = linha - 1; i >= 0; i--) {
            if (tabuleiro[i][col] != jogador) {
                break;
            }
            tabuleiro[i][col] = jogador;
        }
    }

    // Métodos específicos do modo Turbo (se houver)
}
