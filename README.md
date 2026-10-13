# PuzzleAI-Collection

Coleção de puzzles resolvidos em Java, com foco em busca inteligente e representação de estados para problemas de encaixe e organização de bolas em tubos.

## Visão geral

Este projeto implementa uma solução para o clássico problema de ordenação de bolas em tubos, usando uma abordagem de busca heurística (`BestFirst`) e modelos de estado (`BallSortLayout`).

A aplicação lê uma configuração inicial do puzzle pela entrada padrão, resolve o problema e imprime o número mínimo de movimentos necessários (quando existe solução).

## Estrutura do projeto

- `src/Main.java` — ponto de entrada da aplicação.
- `src/BallSortLayout.java` — representação do estado do puzzle e regras de movimento.
- `src/BestFirst.java` — algoritmo de busca para encontrar uma solução.
- `src/Board.java` — abstração de tabuleiro/base do problema.
- `src/Ilayout.java` — interface que define o contrato da representação do estado.
- `test/` — testes automatizados com JUnit.
- `samples/` — arquivos de exemplo de entrada/saída.

## Como funciona

O programa espera a seguinte entrada:

- `height` = número de linhas da configuração
- `tubes` = número de tubos
- em seguida, `height` linhas contendo os estados dos tubos
- cada tubo é representado por letras que indicam as bolas
- `E` representa espaço vazio

Exemplo:

```text
3 3
R B E
B B E
R R E
```

A partir dessa configuração, o programa tenta resolver o puzzle, verificando movimentos válidos entre tubos e aplicando busca para encontrar uma solução ótima ou uma solução válida dependendo da estratégia implementada.

## Requisitos

- Java 8 ou superior
- JUnit 5 para rodar os testes (opcional)

## Compilar o projeto

Na raiz do repositório:

```bash
javac src/*.java -d out
```

## Executar a aplicação

```bash
java -cp out Main
```

Você pode fornecer a entrada via stdin, por exemplo:

```bash
printf '3 3\nR B E\nB B E\nR R E\n' | java -cp out Main
```

## Exemplos

Os arquivos em `samples/` mostram casos de teste prontos para uso:

- `sample1.in`
- `sample1.out`
- `sample2.in`
- `sample2.out`

## Testes

Os testes ficam em `test/` e cobrem cenários do puzzle e validação de comportamento esperados.

Para executar os testes com JUnit, normalmente o projeto deve ser compilado junto com o jar do JUnit e executado usando a console runner.

Exemplo genérico:

```bash
javac -cp junit-platform-console-standalone.jar -d out $(find src test -name "*.java")
java -jar junit-platform-console-standalone.jar --class-path out --scan-class-path
```

## Observações

- O código é escrito em Java puro, sem uso de framework.
- A arquitetura é simples e focada em estudo de algoritmos de busca.
- O projeto serve como base para explorar puzzles de lógica, IA e resolução automática de estados.

## Licença

Este projeto não possui arquivo de licença explícito no repositório.

## Autor

Repositório de `kakurokan/PuzzleAI-Collection`.
