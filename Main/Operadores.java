public class Operadores {
    public boolean isOperando(char simbolo) {
        return simbolo >= '0' && simbolo <= '9';
    }

    public boolean isOperador(char simbolo) {
        return simbolo == '+' || simbolo == '-' || simbolo == '*'
                || simbolo == '/' || simbolo == '^';
    }

    public int getPrecedencia(char operador) {
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
    public boolean isAssociativoADireita(char operador) {
        return operador == '^';
    }

    // Compara o operador lido com o do topo da pilha e diz se o do topo deve sair antes.
    public boolean deveDesempilhar(char operadorLido, char operadorNoTopo) {
        if (operadorNoTopo == '(') {
            return false;
        }
        int precedenciaLido = getPrecedencia(operadorLido);
        int precedenciaTopo = getPrecedencia(operadorNoTopo);
        if (precedenciaTopo > precedenciaLido) {
            return true;
        }
        if (precedenciaTopo == precedenciaLido && !isAssociativoADireita(operadorLido)) {
            return true;
        }
        return false;
    }
}
