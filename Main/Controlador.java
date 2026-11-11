public class Controlador {
    private char[] expressaoInfixa;
    private String[] expressaoPosfixa;
    private double resultado;

    private final Leitor leitor;
    private final ConversorPosfixo conversor;
    private final Calculador calculador;

    public Controlador() {
        leitor = new Leitor();
        conversor = new ConversorPosfixo();
        calculador = new Calculador();
    }

    public String getExpressaoInfixa() {
        String texto = "";
        for (int i = 0; i < expressaoInfixa.length; i++) {
            texto = texto + expressaoInfixa[i];
            if (i < expressaoInfixa.length - 1) {
                texto = texto + " ";
            }
        }
        return texto;
    }

    public String getExpressaoPosfixa() {
        String texto = "";
        for (int i = 0; i < expressaoPosfixa.length; i++) {
            texto = texto + expressaoPosfixa[i];
            if (i < expressaoPosfixa.length - 1) {
                texto = texto + " ";
            }
        }
        return texto;
    }

    private void executarEtapas(String textoDigitado) throws ExpressaoIncorretaException {
        expressaoInfixa = leitor.lerExpressao(textoDigitado);
        expressaoPosfixa = conversor.converter(expressaoInfixa);
        resultado = calculador.avaliar(expressaoPosfixa);
    }

    public void processarExpressao(String textoDigitado) throws ExpressaoIncorretaException {
        try {
            executarEtapas(textoDigitado);
        } catch (IllegalStateException erroDaPilha) {
            throw new ExpressaoIncorretaException("Expressão inconsistente: " + erroDaPilha.getMessage());
        }
    }
}
