import java.util.Scanner;
 
public class Main {
  public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controle controle = new Controle();
 
        executar(scanner, controle);
        scanner.close();
    }
 

    public static void executar(Scanner scanner, Controle controle) {
        boolean continuar = true;
        while (continuar) {
            exibirMenu();
            String opcao = scanner.nextLine().trim();
            if (opcao.equals("1")) {
                avaliarNovaExpressao(scanner, controle);
            } else if (opcao.equals("0")) {
                continuar = false;
                System.out.println("Encerrando o programa.");
            } else {
                System.out.println("Opção inválida. Digite 1 ou 0.");
            }
        }
    }
 
    private static void exibirMenu() {
        System.out.println();
        System.out.println("=== Avaliador de Expressões Matemáticas ===");
        System.out.println("1 - Avaliar uma expressão");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private static String lerExpressao(Scanner scanner) {
        System.out.println("Use números inteiros de 1 dígito, + - * / ^ e parênteses.");
        System.out.print("Digite a expressão: ");
        return scanner.nextLine();
    }
 
    private static void avaliarNovaExpressao(Scanner scanner, Controle controle) {
        String textoDigitado = lerExpressao(scanner);
        try {
            controle.processarExpressao(textoDigitado);
            exibirResultado(controle);
        } catch (ExpressaoInvalidaException erro) {
            exibirMensagemDeErro(erro);
        }
    }

    private static void exibirResultado(Controle controle) {
        System.out.println("Forma pós-fixa: " + controle.getExpressaoPosfixa());
        System.out.println("Resultado: " + controle.getResultadoFormatado());
    }

    private static void exibirMensagemDeErro(ExpressaoInvalidaException erro) {
        System.out.println("Erro: " + erro.getMessage());
    }
}
