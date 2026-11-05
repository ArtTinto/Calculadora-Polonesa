// M1 – Arquivo base do projeto

public class Operador {

    // ===== INÍCIO M7 – Operador: precedência e associatividade =====
    public boolean ehOperando(char simbolo) {
        return simbolo >= '0' && simbolo <= '9';
    }

    public boolean ehOperador(char simbolo) {
        return simbolo == '+' || simbolo == '-' || simbolo == '*'
                || simbolo == '/' || simbolo == '^';
    }

    public int obterPrecedencia(char operador) {
        if (operador == '^') {
            return 3;
        }
        if (operador == '*' || operador == '/') {
            return 2;
        }
        if (operador == '+' || operador == '-') {
            return 1;
        }
        return 0;
    }

    // A exponenciação é avaliada da direita para a esquerda; os demais, da esquerda para a direita.
    public boolean ehAssociativoADireita(char operador) {
        return operador == '^';
    }

    // Compara o operador lido com o do topo da pilha e diz se o do topo deve sair antes.
    public boolean deveDesempilhar(char operadorLido, char operadorNoTopo) {
        if (operadorNoTopo == '(') {
            return false;
        }
        int precedenciaLido = obterPrecedencia(operadorLido);
        int precedenciaTopo = obterPrecedencia(operadorNoTopo);
        if (precedenciaTopo > precedenciaLido) {
            return true;
        }
        if (precedenciaTopo == precedenciaLido && !ehAssociativoADireita(operadorLido)) {
            return true;
        }
        return false;
    }
    // ===== FIM M7 =====
}
