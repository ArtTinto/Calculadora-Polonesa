// M1 – Arquivo base do projeto

public class Leitura {

    private final Operador regrasDosOperadores;

    public Leitura() {
        regrasDosOperadores = new Operador();
    }

    // ===== INÍCIO M4 – Leitura: limpar e filtrar a entrada =====
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
    // ===== FIM M4 =====

    // ===== INÍCIO M5 – Leitura: validar parênteses =====
    public void validarParenteses(char[] expressao) throws ExpressaoInvalidaException {
        Pilha pilhaDeParenteses = new Pilha(expressao.length);
        for (int i = 0; i < expressao.length; i++) {
            char caractere = expressao[i];
            if (caractere == '(') {
                pilhaDeParenteses.push(String.valueOf(caractere));
            }
            if (caractere == ')') {
                if (pilhaDeParenteses.isEmpty()) {
                    throw new ExpressaoInvalidaException("Parêntese de fechamento sem abertura "
                            + "correspondente na posição " + (i + 1) + ".");
                }
                pilhaDeParenteses.pop();
            }
        }
        if (!pilhaDeParenteses.isEmpty()) {
            throw new ExpressaoInvalidaException("Existe parêntese de abertura sem fechamento.");
        }
    }
    // ===== FIM M5 =====

    // ===== INÍCIO M6 – Leitura: validar a sequência da expressão =====
    // Regra: em cada ponto a expressão espera um operando (ou '(') ou espera
    // um operador (ou ')'). Qualquer símbolo fora do esperado é erro.
    public void validarSequencia(char[] expressao) throws ExpressaoInvalidaException {
        boolean esperaOperando = true;

        for (int i = 0; i < expressao.length; i++) {
            char caractere = expressao[i];
            int posicao = i + 1;

            if (regrasDosOperadores.ehOperando(caractere)) {
                if (!esperaOperando) {
                    throw new ExpressaoInvalidaException("Operando fora de posição: '" + caractere
                            + "' na posição " + posicao + ".");
                }
                esperaOperando = false;
            }

            if (caractere == '(') {
                if (!esperaOperando) {
                    throw new ExpressaoInvalidaException("Parêntese de abertura fora de posição "
                            + "na posição " + posicao + ".");
                }
            }

            if (caractere == ')') {
                if (esperaOperando) {
                    throw new ExpressaoInvalidaException("Parêntese de fechamento fora de posição "
                            + "na posição " + posicao + ".");
                }
            }

            if (regrasDosOperadores.ehOperador(caractere)) {
                if (esperaOperando) {
                    throw new ExpressaoInvalidaException("Operador fora de posição: '" + caractere
                            + "' na posição " + posicao + ".");
                }
                esperaOperando = true;
            }
        }

        if (esperaOperando) {
            throw new ExpressaoInvalidaException("A expressão termina de forma incompleta.");
        }
    }

    // Executa todas as validações e devolve a expressão pronta para ser convertida.
    public char[] lerExpressao(String textoDigitado) throws ExpressaoInvalidaException {
        char[] expressao = limparEntrada(textoDigitado);
        validarParenteses(expressao);
        validarSequencia(expressao);
        return expressao;
    }
    // ===== FIM M6 =====
}
