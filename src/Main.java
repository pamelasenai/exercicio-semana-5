import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Jogo jogo = new Jogo();

        System.out.println("Antes de iniciar o jogo...");

        int num = jogo.solicitarLimiteIntervalo();
        jogo.jogar(num);
    }
}