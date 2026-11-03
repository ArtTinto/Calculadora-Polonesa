public class Pilha {
    private String[] elementos;
    private int quantidadeDeElementos;
 
    public Pilha(int capacidade) {
        elementos = new String[capacidade];
        quantidadeDeElementos = 0;
    }
 
    public void empilhar(String valor) {
        lancarErroSeEstiverCheia();
        elementos[quantidadeDeElementos] = valor;
        quantidadeDeElementos = quantidadeDeElementos + 1;
    }
 
    public String desempilhar() {
        lancarErroSeEstiverVazia();
        quantidadeDeElementos = quantidadeDeElementos - 1;
        String valorRemovido = elementos[quantidadeDeElementos];
        elementos[quantidadeDeElementos] = null;
        return valorRemovido;
    }
}
