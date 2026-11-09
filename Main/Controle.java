public class Controle {

    private char[] expressaoInfixa;
    private String[] expressaoPosfixa;
    private double resultado;

    private final Leitura leitura;
    private final Conversor conversor;
    private final Avaliador avaliador;

    public Controle() {
        leitura = new Leitura();
        conversor = new Conversor();
        avaliador = new Avaliador();
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

    public String getResultadoFormatado() {
        return avaliador.formatarResultado(resultado);
}
