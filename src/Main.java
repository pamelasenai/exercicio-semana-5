import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Jogo jogo = new Jogo();

        imprimirBoasVindas();
        jogo.criarJogador();
        while (true) {
            imprimirOpcoesJogos();
            int jogoEscolhido = Utils.nextInt(entrada);

            switch (jogoEscolhido) {
                case 0:
                    System.out.println("Encerrando...");
                    return;
                case 1:
                    jogo.jogar();
                    break;
                case 2:
                    int num = jogo.solicitarLimiteIntervalo();
                    jogo.jogar(num);
                    break;
                default:
                    System.out.println("Opção invalida!");
            }
        }
    }

    private static void imprimirBoasVindas() {
        System.out.println(Utils.BLUE +
            "   ┳┓       ┓┏•   ┓     \n" +
            "   ┣┫┏┓┏┳┓━━┃┃┓┏┓┏┫┏┓   \n" +
            "   ┻┛┗ ┛┗┗  ┗┛┗┛┗┗┻┗┛   " + Utils.RESET
        );
    }

    private static void imprimirOpcoesJogos() {
        System.out.println(Utils.BLUE +
            "\n▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n" +
            "            JOGOS             \n" +
            "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n" + Utils.RESET +
            "[1] Pedra Papel e Tesoura \n" +
            "[2] Acerte o número \n" +
            "[0] SAIR \n" + Utils.BLUE +
            "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀" + Utils.RESET
        );
        System.out.print("Por favor, escolha uma das opções do menu: ");
    }
}