public class Jogador {
    private String nome;
    private int idade;
    private int pontuacao;
    private int numeroTentativas;

    public void adicionaPontos(int pontos){
        pontuacao += pontos;
        System.out.println("O jogador " + nome + " ganhou mais " + pontos + " pontos.");
    }

    public void perdePontos(int pontos) {
        pontuacao -= pontos;
        System.out.println("O jogador " + nome + " perdeu " + pontos + " pontos.");

        if (pontuacao < 0) {
            pontuacao = 0;
        }
    }

    public void adicionaTentativa(){
        numeroTentativas++;
    }
}
