# ReadWrite
## Leitura e escrita de arquivos
Faça as seguintes operações sobre o arquivo:
1 - Crie um arquivo para leitura chamado READ.txt em uma pasta anterior a raiz da JVM.
2 - Crie um arquivo de escrita chamado WRITE.txt em uma pasta chamada "pasta" dentro da raiz da JVM.
3 - Coloque diversos rendimentos no seu arquivo de leitura.

4 - Leia o arquivo de leitura em uma única vez e armazene todos os rendimentos em uma classe Rendimento (long, String, BigDecimal, LocalDate)
5 - Imprima o relatório de rendimentos da seguinte forma.
Ex:
RELATÓRIO DE RENDIMENTOS
O valor de rendimentos registrados no arquivo é: X
A pessoa que recebeu o maior valor foi: X

O valor total de rendimentos agrupado por pessoa é:
PESSOA1 VALOR_TOTAL_1
PESSOA2 VALOR_TOTAL_2
...
...
O valor total de rendimentos agrupado por dia é:
DIA1	VALOR_TOTAL_1
DIA2    VALOR_TOTAL_1
...
...
OBS1: ordene o valor total de rendimentos por pessoa pelo nome da pessoa (ordem crescente);
OBS2: ordene o total de rendimentos por dia pelo valor total de rendimentos (ordem crescente);
6 - Trate o caso onde o PARSER para valores financeiros lê um valor incoerente e gera uma exceção.
	Ex.: valor lido = I45.27 -> valor corrigido = 90.80
7 - Trate o caso onde o PARSER para datas lê um valor incoerente e gera uma exceção.
	Ex.: valor lido = 12-11/98 -> valor corrigido = 14/03/2023
	
CLASSES E MÉTODOS INTERESSANTES A SEREM USADOS
List, ArrayList,Rendimento, método split da String,Map para agrupamento, Comparator como critério de ordenação,
try-catch para se recuperar do erro
