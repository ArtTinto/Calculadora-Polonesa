public class Main{
  public static void main(String[] args) {
    private final Scanner scanner;
    private final Controle controle;
 
    public Main() {
        scanner = new Scanner(System.in);
        controle = new Controle();
    }
 
    public static void main(String[] args) {
        Main tela = new Main();
        tela.executar();
    }
 
    public void executar() {
        boolean continuar = true;
        while (continuar) {
            exibirMenu();
            String opcao = scanner.nextLine().trim();
            if (opcao.equals("1")) {
                avaliarNovaExpressao();
            } else if (opcao.equals("0")) {
                continuar = false;
                System.out.println("Encerrando o programa.");
            } else {
                System.out.println("Opção inválida. Digite 1 ou 0.");
            }
        }
    }
 
    private void exibirMenu() {
        System.out.println();
        System.out.println("=== Avaliador de Expressões Matemáticas ===");
        System.out.println("1 - Avaliar uma expressão");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }
  }
}
