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
        jogador.setNumeroTentativas(0);
        imprimirBoasVindasPedraPapelTesoura();

        while (true) {
            int escolhaJogador = solicitarJogadaPedraPapelTesoura();
            if (escolhaJogador == 0) {
                System.out.println("\nSaindo...");
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

            imprimirMelhoresJogadores();

            if (encerrarJogo()) {
               return;
            }
        }

    }

    public void jogar(int num){
        int limiteTentativas = num / 3;
        jogador.setNumeroTentativas(0);
        imprimirBoasVindasNumeros(num, limiteTentativas);
        int escolhaComputador = new Random().nextInt(0, num + 1);

        do {
            if(limiteTentativas < jogador.getNumeroTentativas()) {
                imprimirMensagemDerrota();
                jogador.perdePontos(1);
                break;
            }

            System.out.println("\nTentativas: " + jogador.getNumeroTentativas() + "/" + limiteTentativas);
            System.out.print("Digite seu chute (número entre 0 e " + num +" ou [s] para SAIR): ");
            int escolhaJogador = solicitarJogadaNumeros(num);

            if (escolhaJogador == -1) {
                System.out.println("\nSaindo...");
                return;
            }

            numeroJogadas++;
            jogador.adicionaTentativa();

            verificarJogada(escolhaJogador, escolhaComputador, jogador);
            if (escolhaJogador == escolhaComputador) break;
        } while (limiteTentativas >= jogador.getNumeroTentativas());

        imprimirMelhoresJogadores();
        if(!encerrarJogo()) {
            int limite = solicitarLimiteIntervalo();
            jogar(limite);
        }
    }

    private void imprimirBoasVindasPedraPapelTesoura() {
        System.out.println("\nBem-vindo ao jogo clássico!");
        System.out.println(Utils.BLUE +
            "※ ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ ×  ※  × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ ※\n" +
            "                              PEDRA, PAPEL, TESOURA                              \n" +
            "※ ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ ×  ※  × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ ※\n" +
            Utils.RESET
        );
        System.out.println("REGRAS DO JOGO");
        System.out.println(
            Utils.BLUE +
            "==================================================================================" +
            Utils.RESET
        );
        System.out.println("Aqui, você deve escolher entre pedra, papel ou tesoura.");
        System.out.println("Sua escolha será comparada a escolha do computador e veremos quem será o vencedor.");
        System.out.println("Pedra quebra a Tesoura");
        System.out.println("Tesoura corta o Papel");
        System.out.println("Papel cobre a Pedra");
        System.out.println("Faça sua escolha e que vença o melhor!");
        System.out.println(
            Utils.BLUE +
            "==================================================================================" +
            Utils.RESET
        );
    }

    private void imprimirBoasVindasNumeros(int maximo, int limiteTentativas) {
        System.out.println("\nBem-vindo ao jogo");
        System.out.println(Utils.BLUE +
            "※ ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ ×  ※  × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ ※\n" +
            "                                 ACERTE O NÚMERO                                 \n" +
            "※ ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ ×  ※  × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ × ~ ※\n" +
            Utils.RESET
        );
        System.out.println("REGRAS DO JOGO");
        System.out.println(
            Utils.BLUE +
            "==================================================================================" +
            Utils.RESET
        );
        System.out.println("O computador irá escolher um número aleatório entre 0 e " + maximo + ".");
        System.out.println("Você terá " + limiteTentativas + " chances para adivinhar o número escolhido.");
        System.out.println("Caso acerte você ganhará um ponto.");
        System.out.println("Caso erre você perderá um ponto.");
        System.out.println(
            Utils.BLUE +
            "==================================================================================" +
            Utils.RESET
        );
    }

    public void criarJogador() {
        System.out.println(
            "\n▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n" +
            "           LOGIN / CADASTRO         \n" +
            "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀"
        );
        System.out.print("Digite seu nome: ");
        String nome = entrada.nextLine();
        System.out.print("Digite sua idade: ");
        int idade = Utils.nextInt(entrada);

        if (Jogador.ehCadastrado(nome)){
            System.out.println("Este jogador já é cadastrado.");
            System.out.print("Deseja logar com o cadastro já existente? ( 1- sim, 0 - não): ");
            int opcao = Utils.nextInt(entrada);
            if (opcao == 1) {
                jogador = Jogador.getJogadorPorNome(nome);
            } else {
                criarJogador();
            }
        } else {
            jogador = new Jogador(nome, idade);
        }
    }

    private boolean retomarJogo() {


        System.out.print("Deseja iniciar uma nova partida? (1 - sim, 0 - não)? ");
        int opcao = Utils.nextInt(entrada);
        if (opcao == 1){
            return true;
        }
        System.out.println("Retornando ao menu anterior.");
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

    private void imprimirMelhoresJogadores() {
        melhorJogador = Jogador.getMelhoresJogadores().get(0);
        Jogador.imprimirTopDezJogadores();
        jogador.imprimirPosicaoJogador(jogador.getNome());
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
        System.out.println(Utils.GREEN + "※~※~※~※~※~ VOCÊ GANHOU ~※~※~※~※~※" + Utils.RESET);
    }

    private void imprimirMensagemDerrota() {
        System.out.println(Utils.RED + "~×~×~×~×~×~×~ VOCÊ PERDEU ~×~×~×~×~×~×~" + Utils.RESET);
    }

    private void imprimirMensagemEmpate() {
        System.out.println(Utils.YELLOW + "~=~=~=~=~=~=~=~ EMPATOU ~=~=~=~=~=~=~=~" + Utils.RESET);
    }

    private void imprimirPedra() {
        System.out.println(Utils.PURPLE +
                "PEDRA \n" +
                "    _______        \n" +
                "---'   ____)       \n" +
                "      (_____)      \n" +
                "      (_____)      \n" +
                "      (____)       \n" +
                "---.__(___)" + Utils.RESET
        );
    }

    private void imprimirPapel() {
        System.out.println(Utils.PURPLE +
                "PAPEL \n" +
                "    _______        \n" +
                "---'   ____)____   \n" +
                "          ______)  \n" +
                "          _______) \n" +
                "         _______)  \n" +
                "---.__________)" + Utils.RESET
        );
    }

    private void imprimirTesoura() {
        System.out.println(Utils.PURPLE +
                "TESOURA \n" +
                "    _______        \n" +
                "---'   ____)____   \n" +
                "          ______)  \n" +
                "       __________) \n" +
                "      (____)       \n" +
                "---.__(___) " + Utils.RESET
        );
    }

    private void imprimirMenu() {
        System.out.print(Utils.BLUE +
                "\n▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n" +
                "                MENU                \n" +
                "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n" + Utils.RESET +
                "[1] Ver ranking completo\n" +
                "[2] Ver top 10\n" +
                "[3] Jogar novamente\n" +
                "[4] Encerrar o Jogo\n" + Utils.BLUE +
                "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n" + Utils.RESET +
                "Digite qual das opções acima você deseja seguir: "
        );
    }

    private static void imprimirRankingJogadores() {
        System.out.println("\n▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
        System.out.println("            RANKING GERAL           ");
        System.out.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
        for (Jogador jogador : Jogador.getMelhoresJogadores()) {
            String nomeJogador = jogador.getNome();
            int posicao = Jogador.getMelhoresJogadores().indexOf(jogador);
            System.out.println(nomeJogador + " " + posicao + "º lugar ");
        }
        System.out.println("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
    }

    private int solicitarJogadaPedraPapelTesoura() {
        System.out.print("\nDigite sua jogada (1 - Pedra, 2 - Papel, 3 - Tesoura, 0 - SAIR): ");
        while (true){
            int escolhaJogador = Utils.nextInt(entrada);
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

    public int solicitarLimiteIntervalo() {
        int limite;
        System.out.println("\nVamos criar um limite para gerar um número aleatório que você irá adivinhar.");
        while (true) {
            System.out.print("Informe um número para o limite:  ");
            limite = Utils.nextInt(entrada);
            if (limite == 0 || limite < 0){
                System.out.println("Opção inválida, tente novamente.");
            } else {
                break;
            }
        }
        return limite;
    }

    private boolean encerrarJogo() {
        imprimirMenu();
        int escolha = Utils.nextInt(entrada);

        switch (escolha) {
            case 1:
                imprimirRankingJogadores();
                encerrarJogo();
                break;
            case 2:
                Jogador.imprimirTopDezJogadores();
                encerrarJogo();
                break;
            case 3:
                System.out.println("Iniciando nova partida...");
                return false;
            case 4:
                System.out.println("Encerrando o Jogo...");
                return true;
            default:
                System.out.println("Opção inválida!");
                encerrarJogo();
                break;
        }
        return false;
    }
}
