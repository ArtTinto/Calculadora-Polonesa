public class Conversor {

    private final Operador regrasDosOperadores;
    private Pilha pilhaDeOperadores;
    private String[] saida;
    private int tamanhoDaSaida;

    public Conversor() {
        regrasDosOperadores = new Operador();
    }

    public String[] converter(char[] expressaoInfixa) {
        pilhaDeOperadores = new Pilha(expressaoInfixa.length);
        saida = new String[expressaoInfixa.length];
        tamanhoDaSaida = 0;

        for (int i = 0; i < expressaoInfixa.length; i++) {
            char simbolo = expressaoInfixa[i];
            if (regrasDosOperadores.ehOperando(simbolo)) {
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
    private char consultarSimboloDoTopo() {
        return pilhaDeOperadores.peek().charAt(0);
    }

    private void processarOperando(char operando) {
        adicionarNaSaida(String.valueOf(operando));
    }

    private void processarAberturaDeParenteses() {
        pilhaDeOperadores.push("(");
    }

    private void processarFechamentoDeParenteses() {
        while (consultarSimboloDoTopo() != '(') {
            adicionarNaSaida(pilhaDeOperadores.pop());
        }
        pilhaDeOperadores.pop();
    }
