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

    public Jogador getMelhorJogador() {
        return melhorJogador;
    }

    public static int getNumeroJogadas() {
        return numeroJogadas;
    }

    public void jogar(){
        criarJogador();
        jogador.setNumeroTentativas(0);
        imprimirBoasVindasPedraPapelTesoura();

        while (true) {
            int escolhaJogador = solicitarJogadaPedraPapelTesoura();
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
            verificarVencedorPedraPapelTesoura(escolhaJogador, escolhaComputador, jogador);
        }

        if (retomarJogo()){
            jogar();
        }
    }

    public void jogar(int num){
        int limiteTentativas = num / 3;
        criarJogador();
        jogador.setNumeroTentativas(0);
        imprimirBoasVindasNumeros(num, limiteTentativas);
        int escolhaComputador = new Random().nextInt(0, num + 1);

        do {
            if(limiteTentativas < jogador.getNumeroTentativas()) {
                imprimirMensagemDerrota();
                jogador.perdePontos(1);
                break;
            }

            System.out.println("Tentativas: " + jogador.getNumeroTentativas() + "/" + limiteTentativas);
            System.out.print("Digite seu chute (número entre 0 e " + num +" ou [s] para SAIR): ");
            int escolhaJogador = solicitarJogadaNumeros(num);

            if (escolhaJogador == -1) break;

            numeroJogadas++;
            jogador.adicionaTentativa();

            verificarJogada(escolhaJogador, escolhaComputador, jogador);
            if (escolhaJogador == escolhaComputador) break;
        } while (limiteTentativas >= jogador.getNumeroTentativas());

        if(retomarJogo()) {
            int limite = solicitarLimiteIntervalo();
            jogar(limite);
        }
    }

    private void imprimirBoasVindasPedraPapelTesoura() {
        System.out.println("\nBem-vindo ao jogo clássico!");
        System.out.println("※ ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ ×  ※  × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ ※");
        System.out.println("                              PEDRA, PAPEL, TESOURA                              ");
        System.out.println("※ ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ ×  ※  × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ ※\n");
        System.out.println("REGRAS DO JOGO");
        System.out.println("==================================================================================");
        System.out.println("Aqui, você deve escolher entre pedra, papel ou tesoura.");
        System.out.println("Sua escolha será comparada a escolha do computador e veremos quem será o vencedor.");
        System.out.println("Pedra quebra a Tesoura");
        System.out.println("Tesoura corta o Papel");
        System.out.println("Papel cobre a Pedra");
        System.out.println("Faça sua escolha e que vença o melhor!\n");
    }

    private void imprimirBoasVindasNumeros(int maximo, int limiteTentativas) {
        System.out.println("\nBem-vindo ao jogo");
        System.out.println("※ ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ ×  ※  × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ ※");
        System.out.println("                                 ACERTE O NÚMERO                                 ");
        System.out.println("※ ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ ×  ※  × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ ※\n");
        System.out.println("REGRAS DO JOGO");
        System.out.println("==================================================================================");
        System.out.println("O computador irá escolher um número aleatório entre 0 e " + maximo + ".");
        System.out.println("Você terá " + limiteTentativas + " chances para adivinhar o número escolhido.");
        System.out.println("Caso acerte você ganhará um ponto.");
        System.out.println("Caso erre você perderá um ponto.\n");
    }

    private void criarJogador() {
        System.out.println("+--------------------+");
        System.out.println("|  Login / cadastro  |");
        System.out.println("+--------------------+");
        System.out.print("Digite seu nome: ");
        String nome = entrada.nextLine();
        System.out.print("Digite sua idade: ");
        int idade = nextInt(entrada);

        if (Jogador.ehCadastrado(nome)){
            System.out.println("Este jogador já é cadastrado.");
            System.out.print("Deseja logar com o cadastro já existente? ( 1- sim, 0 - não): ");
            int opcao = nextInt(entrada);
            if (opcao == 1) {
                jogador = jogador.getJogadorPorNome(nome);
            } else {
                criarJogador();
            }
        } else {
            jogador = new Jogador(nome, idade);
        }
    }

    private boolean retomarJogo() {
        melhorJogador = Jogador.getMelhoresJogadores().get(0);
        jogador.imprimirTopDezJogadores();
        jogador.imprimirPosicaoJogador(jogador.getNome());

        System.out.print("Deseja iniciar uma partida com um novo jogador (1 - sim, 0 - não)? ");
        int opcao = nextInt(entrada);
        if (opcao == 1){
            return true;
        }
        entrada.close();
        System.out.println("Encerrando........");
        return false;
    }

    private void verificarVencedorPedraPapelTesoura(
            int escolhaJogador,
            int escolhaComputador, Jogador jogador
    ){
         if (escolhaJogador == escolhaComputador) {
             imprimirMensagemEmpate();
         } else if (
                 escolhaJogador == 1 && escolhaComputador == 3 ||
                 escolhaJogador == 2 && escolhaComputador == 1 ||
                 escolhaJogador == 3 && escolhaComputador == 2
         ) {
            imprimirMensagemVitoria();
            jogador.adicionaPontos(10);
         } else {
             imprimirMensagemDerrota();
         }
    }

    private void verificarJogada(int escolhaJogador, int escolhaComputador, Jogador jogador) {
        if (escolhaJogador == escolhaComputador) {
            imprimirMensagemVitoria();
            jogador.adicionaPontos(1);
        } else if (escolhaComputador < escolhaJogador) {
            System.out.println("O número é MENOR que o número digitado (" + escolhaJogador + ").");
        } else {
            System.out.println("O número é MAIOR que o número digitado (" + escolhaJogador + ").");
        }
    }

    private void imprimirJogada(int jogada){
        switch (jogada) {
            case 0:
                System.out.println("Saindo...");
                return;
            case 1:
                imprimirPedra();
                break;
            case 2:
                imprimirPapel();
                break;
            case 3:
                imprimirTesoura();
                break;
            default:
                System.out.println("Jogada invalida!");
                break;
        }
    }

    private void imprimirMensagemVitoria() {
        System.out.println("※~※~※~※~※~ VOCÊ GANHOU ~※~※~※~※~※");
    }

    private void imprimirMensagemDerrota() {
        System.out.println("~×~×~×~×~×~×~ VOCÊ PERDEU ~×~×~×~×~×~×~");
    }

    private void imprimirMensagemEmpate() {
        System.out.println("~=~=~=~=~=~=~=~ EMPATOU ~=~=~=~=~=~=~=~");
    }

    private void imprimirPedra() {
        System.out.println(
                "    _______        \n" +
                "---'   ____)       \n" +
                "      (_____)      \n" +
                "      (_____)      \n" +
                "      (____)       \n" +
                "---.__(___)"
        );
    }

    private void imprimirPapel() {
        System.out.println(
                "    _______        \n" +
                "---'   ____)____   \n" +
                "          ______)  \n" +
                "          _______) \n" +
                "         _______)  \n" +
                "---.__________)"
        );
    }

    private void imprimirTesoura() {
        System.out.println(
                "    _______        \n" +
                "---'   ____)____   \n" +
                "          ______)  \n" +
                "       __________) \n" +
                "      (____)       \n" +
                "---.__(___) "
        );
    }

    private int solicitarJogadaPedraPapelTesoura() {
        System.out.print("Digite sua jogada (1 - Pedra, 2 - Papel, 3 - Tesoura, 0 - SAIR): ");
        while (true){
            int escolhaJogador = nextInt(entrada);
            if (escolhaJogador >= 0 && escolhaJogador < 4){
                return escolhaJogador;
            }
            System.out.println("Opção invalida! Tente novamente.");
            System.out.print("Digite sua jogada (1 - Pedra, 2 - Papel, 3 - Tesoura, 0 - SAIR): ");
        }
    }

    private int solicitarJogadaNumeros(int num) {
        while (true) {
            try {
                String escolhaJogador = entrada.next();
                if(escolhaJogador.equals("s")) {
                    return -1;
                }
                int jogada = Integer.parseInt(escolhaJogador);
                if (jogada > num || jogada < 0){
                    System.out.println("Opção invalida! Tente novamente.");
                    System.out.print("\nDigite seu chute (número entre 0 e " + num +" ou [s] para SAIR): ");
                    continue;
                }
                return jogada;
            } catch (NumberFormatException e) {
                System.out.println("Opção invalida! Tente novamente.");
                System.out.print("\nDigite seu chute (número entre 0 e " + num +" ou [s] para SAIR): ");
            }
        }
    }

    private int nextInt(Scanner scanner) {
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }

    public int solicitarLimiteIntervalo() {
        int limite;
        System.out.println("Vamos criar um limite para gerar um número aleatório que você irá adivinhar.");
        while (true) {
            System.out.print("Informe um número para o limite:  ");
            limite = nextInt(entrada);
            if (limite == 0 || limite < 0){
                System.out.println("Opção inválida, tente novamente.");
            } else {
                break;
            }
        }
        return limite;
    }
}
