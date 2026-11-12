public class ConversorPosfixo {
    private final Operadores regrasDosOperadores;
    private Pilha<Character> pilhaDeOperadores;
    private char[] saida;
    private int tamanhoDaSaida;

    public ConversorPosfixo() {
        regrasDosOperadores = new Operadores();
    }

    public char[] converter(char[] expressaoInfixa) {
        pilhaDeOperadores = new Pilha<>(expressaoInfixa.length);
        saida = new char[expressaoInfixa.length];
        tamanhoDaSaida = 0;

        for (int i = 0; i < expressaoInfixa.length; i++) {
            char simbolo = expressaoInfixa[i];
            if (regrasDosOperadores.isOperando(simbolo)) {
                processarOperando(simbolo);
            } else if (simbolo == '(') {
                processarAberturaDeParenteses();
            } else if (simbolo == ')') {
                processarFechamentoDeParenteses();
            } else {
                processarOperador(simbolo);
            }
        }

        esvaziarPilhaNaSaida();
        return copiarSaida();
    }

    private void adicionarNaSaida(char simbolo) {
        saida[tamanhoDaSaida] = simbolo;
        tamanhoDaSaida = tamanhoDaSaida + 1;
    }

    private void processarOperando(char operando) {
        adicionarNaSaida(operando);
    }

    private void processarAberturaDeParenteses() {
        pilhaDeOperadores.push('(');
    }

    private void processarFechamentoDeParenteses() {
        while (pilhaDeOperadores.peek() != '(') {
            adicionarNaSaida(pilhaDeOperadores.pop());
        }
        pilhaDeOperadores.pop();
    }

    private void processarOperador(char operadorLido) {
        while (!pilhaDeOperadores.isEmpty()
                && regrasDosOperadores.deveDesempilhar(operadorLido, pilhaDeOperadores.peek())) {
            adicionarNaSaida(pilhaDeOperadores.pop());
        }
        pilhaDeOperadores.push(operadorLido);
    }

    private void esvaziarPilhaNaSaida() {
        while (!pilhaDeOperadores.isEmpty()) {
            adicionarNaSaida(pilhaDeOperadores.pop());
        }
    }

    private char[] copiarSaida() {
        char[] expressaoPosfixa = new char[tamanhoDaSaida];
        for (int i = 0; i < tamanhoDaSaida; i++) {
            expressaoPosfixa[i] = saida[i];
        }
        return expressaoPosfixa;
    }
}
