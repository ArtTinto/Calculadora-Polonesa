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
            return calcularPotencia(valorEsquerdo, valorDireito);
        }
        throw new ExpressaoIncorretaException("Operador desconhecido: " + simboloDoOperador);
    }

    private double calcularPotencia(double base, double expoente) throws ExpressaoIncorretaException {
        if (expoente == 0) {
            return 1;
        }
        if (base == 0 && expoente < 0) {
            throw new ExpressaoIncorretaException("Divisão por zero (zero elevado a expoente negativo).");
        }

        double resultado;
        if (expoente == Math.floor(expoente)) {
            resultado = calcularPotenciaInteira(base, (long) Math.abs(expoente));
            if (expoente < 0) {
                resultado = 1 / resultado;
            }
        } else {
            resultado = Math.pow(base, expoente);
        }

        return resultado;
    }

    private double calcularPotenciaInteira(double base, long expoente) {
        double resultado = 1;
        double baseAtual = base;
        long expoenteRestante = expoente;
        while (expoenteRestante > 0) {
            if (expoenteRestante % 2 == 1) {
                resultado = resultado * baseAtual;
            }
            baseAtual = baseAtual * baseAtual;
            expoenteRestante = expoenteRestante / 2;
        }
        return resultado;
    }

    public double avaliar(String[] expressaoPosfixa) throws ExpressaoIncorretaException {
        Pilha pilhaDeValores = new Pilha(expressaoPosfixa.length);

        for (int i = 0; i < expressaoPosfixa.length; i++) {
            String simbolo = expressaoPosfixa[i];
            char primeiroCaractere = simbolo.charAt(0);
            if (regrasDosOperadores.isOperando(primeiroCaractere)) {
                pilhaDeValores.push(simbolo);
            } else {
                double valorDireito = Double.parseDouble(pilhaDeValores.pop());
                double valorEsquerdo = Double.parseDouble(pilhaDeValores.pop());
                double resultadoParcial = executarOperacao(valorEsquerdo, valorDireito, primeiroCaractere);
                pilhaDeValores.push(String.valueOf(resultadoParcial));
            }
        }
        return Double.parseDouble(pilhaDeValores.pop());
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
