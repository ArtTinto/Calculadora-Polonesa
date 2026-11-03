public class Leitura {
    private final Operador regrasDosOperadores;

    public Leitura() {
        regrasDosOperadores = new Operador();
    }

    public char[] limparEntrada(String textoDigitado) throws ExpressaoInvalidaException {
        int quantidadeSemEspacos = 0;
        for (int i = 0; i < textoDigitado.length(); i++) {
            if (!Character.isWhitespace(textoDigitado.charAt(i))) {
                quantidadeSemEspacos = quantidadeSemEspacos + 1;
            }
        }

        char[] expressao = new char[quantidadeSemEspacos];
        int posicaoNaExpressao = 0;
        for (int i = 0; i < textoDigitado.length(); i++) {
            char caractere = textoDigitado.charAt(i);
            if (!Character.isWhitespace(caractere)) {
                expressao[posicaoNaExpressao] = caractere;
                posicaoNaExpressao = posicaoNaExpressao + 1;
            }
        }

        validarCaracteres(expressao);
        return expressao;
    }

    public void validarCaracteres(char[] expressao) throws ExpressaoInvalidaException {
        if (expressao.length == 0) {
            throw new ExpressaoInvalidaException("A expressão está vazia.");
        }
        for (int i = 0; i < expressao.length; i++) {
            char caractere = expressao[i];
            boolean caractereValido = regrasDosOperadores.ehOperando(caractere)
                    || regrasDosOperadores.ehOperador(caractere)
                    || caractere == '('
                    || caractere == ')';
            if (!caractereValido) {
                throw new ExpressaoInvalidaException("Caractere inválido '" + caractere
                        + "' na posição " + (i + 1) + " (contando sem os espaços).");
            }
        }
    }
}
