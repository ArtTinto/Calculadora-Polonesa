# Calculadora Polonesa

Avaliador de expressões matemáticas em Java. Recebe uma expressão na forma infixa (ex.: `2 + 5 * 8 / (4 + 3)`), converte para a notação pós-fixa (polonesa inversa) e calcula o resultado, usando uma pilha implementada pelo próprio projeto.

## O que faz

1. Lê a expressão e verifica se ela é sintaticamente válida (caracteres, parênteses e posição dos operadores).
2. Converte de infixa para pós-fixa, respeitando precedência e associatividade.
3. Avalia a pós-fixa e exibe o resultado.

## Regras da entrada

- Números inteiros de **1 dígito** (0 a 9).
- Operadores: `+`, `-`, `*`, `/` e `^` (exponenciação).
- Parênteses `( )`.
- Espaços são ignorados.

O resultado pode ter mais de um dígito e casas decimais (ex.: `7/2` dá `3.5`).

## Como usar

Execute:

No menu, digite `1` para avaliar uma expressão ou `0` para sair.

```
Digite a expressão: 2 + 5 * 8 / (4 + 3)
Forma pós-fixa: 2 5 8 * 4 3 + / +
Resultado: 7.714285714285714
```

Em caso de expressão inválida ou divisão por zero, o programa mostra uma mensagem de erro.

## Estrutura

- `Main`: entrada e saída no console.
- `Controlador`: coordena as etapas.
- `Leitor`: limpeza e validação da expressão.
- `ConversorPosfixo`: infixa para pós-fixa.
- `Calculador`: cálculo da pós-fixa.
- `Operadores`: precedência e associatividade.
- `Pilha<T>`: pilha genérica própria sobre vetor.
- `ExpressaoIncorretaException`: erros da expressão.
