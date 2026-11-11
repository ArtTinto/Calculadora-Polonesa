public class ConversorPosfixo {
    private final Operadores regrasDosOperadores;
    private Pilha pilhaDeOperadores;
    private String[] saida;
    private int tamanhoDaSaida;

    public ConversorPosfixo() {
        regrasDosOperadores = new Operadores();
    }

    public String[] converter(char[] expressaoInfixa) {
        pilhaDeOperadores = new Pilha(expressaoInfixa.length);
        saida = new String[expressaoInfixa.length];
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

    private void adicionarNaSaida(String simbolo) {
        saida[tamanhoDaSaida] = simbolo;
        tamanhoDaSaida = tamanhoDaSaida + 1;
    }

    // Lê o topo da pilha como caractere, para poder comparar com as regras dos operadores.
    private char getSimboloDoTopo() {
        return pilhaDeOperadores.peek().charAt(0);
    }

    private void processarOperando(char operando) {
        adicionarNaSaida(String.valueOf(operando));
    }

    private void processarAberturaDeParenteses() {
        pilhaDeOperadores.push("(");
    }

    private void processarFechamentoDeParenteses() {
        while (getSimboloDoTopo() != '(') {
            adicionarNaSaida(pilhaDeOperadores.pop());
        }
        pilhaDeOperadores.pop();
    }

    private void processarOperador(char operadorLido) {
        while (!pilhaDeOperadores.isEmpty()
                && regrasDosOperadores.deveDesempilhar(operadorLido, getSimboloDoTopo())) {
            adicionarNaSaida(pilhaDeOperadores.pop());
        }
        pilhaDeOperadores.push(String.valueOf(operadorLido));
    }

    private void esvaziarPilhaNaSaida() {
        while (!pilhaDeOperadores.isEmpty()) {
            adicionarNaSaida(pilhaDeOperadores.pop());
        }
    }

    private String[] copiarSaida() {
        String[] expressaoPosfixa = new String[tamanhoDaSaida];
        for (int i = 0; i < tamanhoDaSaida; i++) {
            expressaoPosfixa[i] = saida[i];
        }
        return expressaoPosfixa;
    }
}
