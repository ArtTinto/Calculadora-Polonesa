public class Calculador {
    private final Operadores regrasDosOperadores;

    public Calculador() {
        regrasDosOperadores = new Operadores();
    }

    public double executarOperacao(double valorEsquerdo, double valorDireito, char simboloDoOperador)
            throws ExpressaoIncorretaException {
        if (simboloDoOperador == '+') {
            return valorEsquerdo + valorDireito;
        }
        if (simboloDoOperador == '-') {
            return valorEsquerdo - valorDireito;
        }
        if (simboloDoOperador == '*') {
            return valorEsquerdo * valorDireito;
        }
        if (simboloDoOperador == '/') {
            return dividir(valorEsquerdo, valorDireito);
        }
        if (simboloDoOperador == '^') {
            return Math.pow(valorEsquerdo, valorDireito);
        }
        throw new ExpressaoIncorretaException("Operador desconhecido: " + simboloDoOperador);
    }

    public double avaliar(char[] expressaoPosfixa) throws ExpressaoIncorretaException {
        Pilha<Double> pilhaDeValores = new Pilha<>(expressaoPosfixa.length);

        for (int i = 0; i < expressaoPosfixa.length; i++) {
            char simbolo = expressaoPosfixa[i];
            if (regrasDosOperadores.isOperando(simbolo)) {
                pilhaDeValores.push((double) (simbolo - '0'));
            } else {
                double valorDireito = pilhaDeValores.pop();
                double valorEsquerdo = pilhaDeValores.pop();
                double resultadoParcial = executarOperacao(valorEsquerdo, valorDireito, simbolo);
                pilhaDeValores.push(resultadoParcial);
            }
        }
        return pilhaDeValores.pop();
    }

    private double dividir(double dividendo, double divisor) throws ExpressaoIncorretaException {
        if (divisor == 0) {
            throw new ExpressaoIncorretaException("Divisão por zero.");
        }
        return dividendo / divisor;
    }

    // Se o resultado for um número inteiro, mostra sem casas decimais (ex.: 9 e não 9.0).
    public String formatarResultado(double resultado) {
        if (resultado == Math.floor(resultado) && Math.abs(resultado) < 1e15) {
            long resultadoInteiro = (long) resultado;
            return String.valueOf(resultadoInteiro);
        }
        return String.valueOf(resultado);
    }
}
