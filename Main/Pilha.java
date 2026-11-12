public class Pilha<T> {
    private T[] elementos;
    private int quantidadeDeElementos;

    @SuppressWarnings("unchecked")
    public Pilha(int capacidade) {
        elementos = (T[]) new Object[capacidade];
        quantidadeDeElementos = 0;
    }

    public void push(T valor) {
        lancarErroSeEstiverCheia();
        elementos[quantidadeDeElementos] = valor;
        quantidadeDeElementos = quantidadeDeElementos + 1;
    }

    public T pop() {
        lancarErroSeEstiverVazia();
        quantidadeDeElementos = quantidadeDeElementos - 1;
        T valorRemovido = elementos[quantidadeDeElementos];
        elementos[quantidadeDeElementos] = null;
        return valorRemovido;
    }

    public T peek() {
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
