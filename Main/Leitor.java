public class Leitor {
    private final Operadores regrasDosOperadores;

    public Leitor() {
        regrasDosOperadores = new Operadores();
    }

    public char[] limparEntrada(String textoDigitado) throws ExpressaoIncorretaException {
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

    public void validarCaracteres(char[] expressao) throws ExpressaoIncorretaException {
        if (expressao.length == 0) {
            throw new ExpressaoIncorretaException("A expressão está vazia.");
        }
        
        for (int i = 0; i < expressao.length; i++) {
            char caractere = expressao[i];
            boolean caractereValido = regrasDosOperadores.isOperando(caractere)
                    || regrasDosOperadores.isOperador(caractere)
                    || caractere == '('
                    || caractere == ')';
            
            if (caractere == '.' || caractere == ',') {
                throw new ExpressaoIncorretaException("Só são aceitos números inteiros de 0 a 9, sem vírgula ou ponto.");
            }
            
            if (!caractereValido) {
                throw new ExpressaoIncorretaException("Caractere inválido.");
            }
        }
    }

    public void validarParenteses(char[] expressao) throws ExpressaoIncorretaException {
        Pilha<Character> pilhaDeParenteses = new Pilha<>(expressao.length);
        
        for (int i = 0; i < expressao.length; i++) {
            char caractere = expressao[i];
            
            if (caractere == '(') {
                pilhaDeParenteses.push(caractere);
            }
            if (caractere == ')') {
                if (pilhaDeParenteses.isEmpty()) {
                    throw new ExpressaoIncorretaException("Parêntese de fechamento sem abertura correspondente.");
                }
                pilhaDeParenteses.pop();
            }
        }
        
        if (!pilhaDeParenteses.isEmpty()) {
            throw new ExpressaoIncorretaException("Existe parêntese de abertura sem fechamento.");
        }
    }

    public void validarSequencia(char[] expressao) throws ExpressaoIncorretaException {
        boolean esperaOperando = true;

        for (int i = 0; i < expressao.length; i++) {
            char caractere = expressao[i];

            if (regrasDosOperadores.isOperando(caractere)) {
                if (!esperaOperando && regrasDosOperadores.isOperando(expressao[i - 1])) {
                    throw new ExpressaoIncorretaException("Só são aceitos números de 1 dígito (0 a 9).");
                }
                if (!esperaOperando) {
                    throw new ExpressaoIncorretaException("Operando fora de posição.");
                }
                esperaOperando = false;
            }

            if (caractere == '(') {
                if (!esperaOperando) {
                    throw new ExpressaoIncorretaException("Parêntese de abertura fora de posição.");
                }
            }

            if (caractere == ')') {
                if (esperaOperando) {
                    throw new ExpressaoIncorretaException("Parêntese de fechamento fora de posição.");
                }
            }

            if (regrasDosOperadores.isOperador(caractere)) {
                if (esperaOperando) {
                    throw new ExpressaoIncorretaException("Operador fora de posição.");
                }
                esperaOperando = true;
            }
        }

        if (esperaOperando) {
            throw new ExpressaoIncorretaException("A expressão termina de forma incompleta.");
        }
    }

    public char[] lerExpressao(String textoDigitado) throws ExpressaoIncorretaException {
        char[] expressao = limparEntrada(textoDigitado);
        validarParenteses(expressao);
        validarSequencia(expressao);
        return expressao;
    }
}
