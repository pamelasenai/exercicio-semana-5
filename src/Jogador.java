import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Jogador {
    private static List<Jogador> melhoresJogadores = new ArrayList<>();

    private String nome;
    private int idade;
    private int pontuacao;
    private int numeroTentativas;

    public Jogador(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
        this.pontuacao = 0;
        this.numeroTentativas = 0;

        setMelhorJogador();
    }

    public void adicionaPontos(int pontos){
        pontuacao += pontos;
        ordenarMelhoresJogadores();
        System.out.println("O jogador " + nome + " ganhou " + pontos + " pontos.");
    }

    public void perdePontos(int pontos) {
        pontuacao -= pontos;
        if (pontuacao < 0) {
            pontuacao = 0;
        }
        ordenarMelhoresJogadores();
        System.out.println("O jogador " + nome + " perdeu " + pontos + " pontos.");
    }

    public void adicionaTentativa(){
        numeroTentativas++;
    }

    private static void ordenarMelhoresJogadores() {
        melhoresJogadores.sort(new Comparator<Jogador>() {
            public int compare(Jogador jogador1, Jogador jogador2) {
                return jogador2.getPontuacao() - jogador1.getPontuacao();
            }
        });
    }

    public static void imprimirTopDezJogadores() {
        ordenarMelhoresJogadores();
        int limite = Math.min(melhoresJogadores.size(), 10);

        System.out.println("\n▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
        System.out.println("          TOP 10 JOGADORES          ");
        System.out.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
        for (int i = 0; i < limite; i++) {
            String nomeJogador = melhoresJogadores.get(i).getNome();
            int posicao = i + 1;
            System.out.println(nomeJogador + " " + posicao + "º lugar ");
        }
        System.out.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
    }

    public void imprimirPosicaoJogador(String nome){
        ordenarMelhoresJogadores();
        System.out.println(Utils.BLUE + "\n~※~※~※~※~※~※~※~※~※~※~※~※~※~※~※~" + Utils.RESET);
        for (Jogador jogador : melhoresJogadores) {
            if(jogador.getNome().equals(nome)){
                int posicao = melhoresJogadores.indexOf(jogador) + 1;
                System.out.println(jogador.getNome() + " - " + "posição " + posicao);
            }
        }
        System.out.println(Utils.BLUE + "~※~※~※~※~※~※~※~※~※~※~※~※~※~※~※~" + Utils.RESET);
    }

    public static Jogador getJogadorPorNome(String nome) {
        for (Jogador jogador : melhoresJogadores) {
            if (jogador.getNome().equals(nome)) {
                return jogador;
            }
        }
        return null;
    }

    public static boolean ehCadastrado(String nome) {
        for (Jogador jogador : melhoresJogadores) {
            if (jogador.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
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
        ordenarMelhoresJogadores();
    }

    public int getNumeroTentativas() {
        return numeroTentativas;
    }

    public void setNumeroTentativas(int numeroTentativas) {
        this.numeroTentativas = numeroTentativas;
    }

    public static List<Jogador> getMelhoresJogadores() {
        ordenarMelhoresJogadores();
        return melhoresJogadores;
    }

    private void setMelhorJogador() {
        melhoresJogadores.add(this);
        ordenarMelhoresJogadores();
    }
}
