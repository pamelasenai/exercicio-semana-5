public class Jogador {
    private String nome;
    private int idade;
    private int pontuacao;
    private int numeroTentativas;

    public Jogador(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
        this.pontuacao = 0;
        this.numeroTentativas = 0;
    }

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getNumeroTentativas() {
        return numeroTentativas;
    }

    public void setNumeroTentativas(int numeroTentativas) {
        this.numeroTentativas = numeroTentativas;
    }
}
