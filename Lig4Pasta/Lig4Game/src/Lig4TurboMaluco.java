package Lig4Pasta.Lig4Game.src;

import java.util.Random;

public class Lig4TurboMaluco extends Lig4 {

    private static final int NIVEL_MALUQUICE_MAXIMO = 5;

    private int nivelMaluquice;

    public Lig4TurboMaluco(int nivelMaluquice) {
        setNivelMaluquice(nivelMaluquice);
    }

    public void setNivelMaluquice(int nivelMaluquice) {
        if (nivelMaluquice < 0 || nivelMaluquice > NIVEL_MALUQUICE_MAXIMO) {
            throw new ValorAtributoInvalido("Nível de maluquice inválido. O nível deve estar entre 0 e " + NIVEL_MALUQUICE_MAXIMO);
        }
        this.nivelMaluquice = nivelMaluquice;
    }

    public boolean movimentoValido(int col) {
        if (col < 0 || col >= colunas) {
            throw new MovimentoInvalido("Movimento inválido. Escolha uma coluna entre 1 e 7.");
        }
        return tabuleiro[0][col] == ' ';
    }
    
    @Override
    public void turboMode(int linha, int col) {
        super.turboMode(linha, col);

        char jogador = jogadores[jogadorAtual];
        Random random = new Random();
        if (random.nextInt(5) < nivelMaluquice) {
            int cima = linha - 1;
            int baixo = linha + 1;

            if (cima >= 0 && tabuleiro[cima][col] != jogador) {
                tabuleiro[cima][col] = jogador;
            }

            if (baixo < linhas && tabuleiro[baixo][col] != jogador) {
                tabuleiro[baixo][col] = jogador;
            }
        }

        if (random.nextInt(100) < nivelMaluquice) {
            int esqCima = linha - 1;
            int dirCima = linha - 1;
            int esqBaixo = linha + 1;
            int dirBaixo = linha + 1;

            if (esqCima >= 0 && col - 1 >= 0 && tabuleiro[esqCima][col - 1] != jogador) {
                tabuleiro[esqCima][col - 1] = jogador;
            }

            if (dirCima >= 0 && col + 1 < colunas && tabuleiro[dirCima][col + 1] != jogador) {
                tabuleiro[dirCima][col + 1] = jogador;
            }

            if (esqBaixo < linhas && col - 1 >= 0 && tabuleiro[esqBaixo][col - 1] != jogador) {
                tabuleiro[esqBaixo][col - 1] = jogador;
            }

            if (dirBaixo < linhas && col + 1 < colunas && tabuleiro[dirBaixo][col + 1] != jogador) {
                tabuleiro[dirBaixo][col + 1] = jogador;
            }
        }
    }

    @Override
    public void jogar() {
        super.jogar();
    }
}
