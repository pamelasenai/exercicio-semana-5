import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Jogador {
    private static List<Jogador> melhoresJogadores = new ArrayList<>();

    private String nome;
    private int idade;
    private int pontuacao;
    private int numeroTentativas;

    public Jogador(String nome, int idade) {
        this.nome = verificarCadastro(nome);
        this.idade = idade;
        this.pontuacao = 0;
        this.numeroTentativas = 0;

        setMelhorJogador();
    }

    public void adicionaPontos(int pontos){
        pontuacao += pontos;
        ordenarMelhoresJogadores();
        System.out.println("O jogador " + nome + " ganhou mais " + pontos + " pontos.");
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
        return melhoresJogadores;
    }

    private void setMelhorJogador() {
        melhoresJogadores.add(this);
        ordenarMelhoresJogadores();
    }

    private void ordenarMelhoresJogadores() {
        melhoresJogadores.sort(new Comparator<Jogador>() {
            public int compare(Jogador jogador1, Jogador jogador2) {
                return jogador2.getPontuacao() - jogador1.getPontuacao();
            }
        });
    }

    public void imprimirTopDezJogadores() {
        ordenarMelhoresJogadores();
        int limite = Math.min(melhoresJogadores.size(), 10);

        System.out.println("------------------------------------");
        System.out.println("          TOP 10 JOGADORES          ");
        System.out.println("------------------------------------");
        for (int i = 0; i < limite; i++) {
            String nomeJogador = melhoresJogadores.get(i).getNome();
            int posicao = i + 1;
            System.out.println(nomeJogador + " " + posicao + "º lugar ");
        }
        System.out.println("------------------------------------");
    }

    public void imprimirPosicaoJogador(String nome){
        ordenarMelhoresJogadores();
        System.out.println("************************************");
        for (Jogador jogador : melhoresJogadores) {
            if(jogador.getNome().equals(nome)){
                int posicao = melhoresJogadores.indexOf(jogador) + 1;
                System.out.println(jogador.getNome() + " - " + "posição " + posicao);
            }
        }
        System.out.println("************************************");
    }

    private String verificarCadastro(String nome) {
        Scanner entrada = new Scanner(System.in);
        String nomeVerificado = "";
        String nomeParaVerificar = nome;
        do {
            if (jogadorExistente(nomeParaVerificar)){
                System.out.print("Este jogador já é cadastrado, favor informar outro nome: ");
                nomeParaVerificar = entrada.nextLine();
            } else {
                nomeVerificado = nomeParaVerificar;
            }
        } while (nomeVerificado.isEmpty());
        return nomeVerificado;
    }

    private boolean jogadorExistente(String nome) {
        for (Jogador jogador : melhoresJogadores) {
            if (jogador.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }
}
