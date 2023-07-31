package Lig4Pasta.Lig4Game.src;

public interface Lig4Interface {
    void jogar();
    void printTabuleiro();
    boolean movimentoValido(int col);
    int soltarPeca(int col);
    boolean checarVitoria(int linha, int col);
    boolean checarEmpate();
    void turboMode(int linha, int col);
}
