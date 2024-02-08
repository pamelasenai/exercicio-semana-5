import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private Jogador melhorJogador;
    private static int numeroJogadas;
    private Jogador jogador;
    private Scanner entrada = new Scanner(System.in);

    public Jogo() {
        numeroJogadas = 0;
    }

    public void jogar(){
        imprimirBoasVindas();
        System.out.println("Antes de dar início ao jogo vamos fazer seu cadastro!");
        jogador = criarJogador();

        while (true) {
            System.out.print("\nDigite sua jogada (1 - Pedra, 2 - Papel, 3 - Tesoura, 0 - SAIR): ");
            int escolhaJogador = solicitarJogada();

            if (escolhaJogador == 0) {
                break;
            }

            numeroJogadas++;
            jogador.adicionaTentativa();
            int escolhaComputador = new Random().nextInt(3) + 1;
            System.out.println("\nVocê jogou: ");
            imprimirJogada(escolhaJogador);
            System.out.println("\nO computador jogou: ");
            imprimirJogada(escolhaComputador);
            verificarVencedor(escolhaJogador, escolhaComputador, jogador);
            melhorJogador = Jogador.getMelhoresJogadores().get(0);
        }

        jogador.imprimirTopDezJogadores();
        jogador.imprimirPosicaoJogador(jogador.getNome());

        System.out.print("Deseja iniciar uma partida com um novo jogador (1 - sim, 2 - não/ sair)? ");
        int opcao = nextInt(entrada);
        if (opcao == 2){
            entrada.close();
            System.out.println("Encerrando........");
        } else {
            jogar();
        }
    }

    private void imprimirBoasVindas() {
        System.out.println("Bem-vindo ao jogo clássico");
        System.out.println("※ ~ × ~ × ~ × ~ ×  ※  × ~ × ~ × ~ × ~ ※");
        System.out.println("          PEDRA, PAPEL, TESOURA          ");
        System.out.println("※ ~ × ~ × ~ × ~ ×  ※  × ~ × ~ × ~ × ~ ※\n");
        System.out.println("REGRAS DO JOGO");
        System.out.println("==================================================================================");
        System.out.println("Aqui, você deve escolher entre pedra, papel ou tesoura.");
        System.out.println("Sua escolha será comparada a escolha do computador e veremos quem será o vencedor.");
        System.out.println("Pedra quebra a Tesoura");
        System.out.println("Tesoura corta o Papel");
        System.out.println("Papel cobre a Pedra");
        System.out.println("Faça sua escolha e que vença o melhor!\n");
    }

    private Jogador criarJogador() {
        System.out.print("Digite seu nome: ");
        String nome = entrada.nextLine();
        System.out.print("Digite sua idade: ");
        int idade = nextInt(entrada);
        jogador = new Jogador(nome, idade);
        return jogador;
    }

    public Jogador getMelhorJogador() {
        return melhorJogador;
    }

    public static int getNumeroJogadas() {
        return numeroJogadas;
    }

    private void verificarVencedor(int escolhaJogador, int escolhaComputador, Jogador jogador){
         if (escolhaJogador == escolhaComputador) {
             System.out.println("~=~=~=~=~=~=~=~ EMPATOU ~=~=~=~=~=~=~=~");
         } else if (
                 escolhaJogador == 1 && escolhaComputador == 3 ||
                 escolhaJogador == 2 && escolhaComputador == 1 ||
                 escolhaJogador == 3 && escolhaComputador == 2
         ) {
            System.out.println("※~※~※~※~※~ VOCÊ GANHOU ~※~※~※~※~※");
            jogador.adicionaPontos(1);
         } else {
             System.out.println("~×~×~×~×~×~×~ VOCÊ PERDEU ~×~×~×~×~×~×~");
         }
    }

    private void imprimirJogada(int jogada){
        switch (jogada) {
            case 0:
                return;
            case 1:
                System.out.println(
                        "    _______        \n" +
                        "---'   ____)       \n" +
                        "      (_____)      \n" +
                        "      (_____)      \n" +
                        "      (____)       \n" +
                        "---.__(___)"
                );
                break;
            case 2:
                System.out.println(
                        "    _______        \n" +
                        "---'   ____)____   \n" +
                        "          ______)  \n" +
                        "          _______) \n" +
                        "         _______)  \n" +
                        "---.__________)"
                );
                break;
            case 3:
                System.out.println(
                        "    _______        \n" +
                        "---'   ____)____   \n" +
                        "          ______)  \n" +
                        "       __________) \n" +
                        "      (____)       \n" +
                        "---.__(___) "
                );
                break;
            default:
                System.out.println("Jogada invalida!");
                break;
        }
    }

    private int solicitarJogada() {
        while (true){
            int escolhaJogador = nextInt(entrada);
            if (escolhaJogador >= 0 && escolhaJogador < 4){
                return escolhaJogador;
            }
            System.out.println("Opção invalida! Tente novamente.");
            System.out.print("Digite sua jogada (1 - Pedra, 2 - Papel, 3 - Tesoura, 0 - SAIR): ");
        }
    }

    private int nextInt(Scanner scn) {
        int i = scn.nextInt();
        scn.nextLine();
        return i;
    }
}
