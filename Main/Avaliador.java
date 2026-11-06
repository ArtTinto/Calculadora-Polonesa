public class Avaliador {
    private final Operador regrasDosOperadores;

    public Avaliador() {
        regrasDosOperadores = new Operador();
    }

    public double executarOperacao(double valorEsquerdo, double valorDireito, char operador)
            throws ExpressaoInvalidaException {
        if (operador == '+') {
            return valorEsquerdo + valorDireito;
        }
        if (operador == '-') {
            return valorEsquerdo - valorDireito;
        }
        if (operador == '*') {
            return valorEsquerdo * valorDireito;
        }
        if (operador == '/') {
            return dividir(valorEsquerdo, valorDireito);
        }
        if (operador == '^') {
            return calcularPotencia(valorEsquerdo, valorDireito);
        }
        throw new ExpressaoInvalidaException("Operador Desconhecido" + operador);
    }

    private double calcularPotencia(double base, double expoente) {
        // Verifica o expoente;
        if (expoente == 0) {
            return 1;
        }
        if (base == 0 && expoente < 0) {
            throw new ArithmeticException("Divisão por zero");
        }

        double resultado;
        if (expoente == Math.floor(expoente)) {
            resultado = calcularPotenciaInteira(base, (long) Math.abs(expoente));

            if (expoente < 0) // Caso expoente negativo
                resultado = 1 / expoente;
            else
                resultado = Math.pow(base, expoente);

            if (Double.isNaN(resultado) || Double.isInfinite(resultado)) {
                throw new ExpressaoInvalidaException("Valor não pode ser calculado");
            }
        }
        return resultado;
    }

    private double calcularPotenciaInteira(double base, long expoente) {
        double resultado = 1;
        double baseAtual = base;
        long expoenteRestante = expoente;

        while (expoenteRestante > 0) {
            if (expoenteRestante % 2 == 1) {
                resultado *= baseAtual;
            }

            baseAtual *= baseAtual;
            expoenteRestante /= 2;
        }
        
        return resultado;
    }

     public double avaliar(String[] expressaoPosfixa) throws ExpressaoInvalidaException {
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
}
