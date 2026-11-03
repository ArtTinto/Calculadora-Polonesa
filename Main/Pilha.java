public class Pilha 
    private String[] elementos;
    private int quantidadeDeElementos;

    public Pilha(int capacidade) {
        elementos = new String[capacidade];
        quantidadeDeElementos = 0;
    }

    public void push(String valor) {
        lancarErroSeEstiverCheia();
        elementos[quantidadeDeElementos] = valor;
        quantidadeDeElementos = quantidadeDeElementos + 1;
    }

    public String pop() {
        lancarErroSeEstiverVazia();
        quantidadeDeElementos = quantidadeDeElementos - 1;
        String valorRemovido = elementos[quantidadeDeElementos];
        elementos[quantidadeDeElementos] = null;
        return valorRemovido;
    }

    public String peek() {
        lancarErroSeEstiverVazia();
        return elementos[quantidadeDeElementos - 1];
    }

    public boolean isEmpty() {
        return quantidadeDeElementos == 0;
    }

    public boolean isFull() {
        return quantidadeDeElementos == elementos.length;
    }

    private void lancarErroSeEstiverCheia() {
        if (isFull()) {
            throw new IllegalStateException("Pilha cheia: não é possível empilhar.");
        }
    }

    private void lancarErroSeEstiverVazia() {
        if (isEmpty()) {
            throw new IllegalStateException("Pilha vazia: não há elemento para acessar.");
        }
    }
}
