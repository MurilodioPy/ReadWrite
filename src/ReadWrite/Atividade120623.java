package ReadWrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Murilo
 */
public class Atividade120623 {

    public static void main(String[] args) throws IOException {
        //diretorio do projeto inicialmente
        InputStream is = new FileInputStream("READ.txt"); //lê byte

        BigDecimal soma = new BigDecimal("0.00");
        BigDecimal maior = new BigDecimal("0.00");
        List<String> arquivoTxt = new ArrayList<>();

        InputStreamReader isr = new InputStreamReader(is); //lê char

        try (BufferedReader br = new BufferedReader(isr)) {//lê string

            List<Rendimento> rendimentos = new ArrayList();

            String s = br.readLine();
            while (s != null) {
                Rendimento linha = new Rendimento(); //cria um objeto do tipo Rendimento

                String arquivo[];
                arquivo = s.split(" ");//cria um vetor de 4 posições
                linha.setId(Long.parseLong(arquivo[0]));//insere o id 
                linha.setNome(arquivo[1]);//insero o nome

                try {//uso do try para possíveis problemas de formato
                    linha.setRendimentos(new BigDecimal(arquivo[2]));//insere o rendimento
                } catch (NumberFormatException ex) {
                    linha.setRendimentos(new BigDecimal("90.80"));
                }

                try {//uso do try para possíveis problemas de formato
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(arquivo[3], dtf);//insere a data do evento
                    linha.setData(date);
                } catch (DateTimeParseException ex) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse("14/03/2023", dtf);
                    linha.setData(date);
                }

                s = br.readLine();

                rendimentos.add(linha);
            }
            arquivoTxt.add("................................................................................................................");
            arquivoTxt.add("...........................................RELATÓRIO DE RENDIMENTOS.............................................");
            arquivoTxt.add("................................................................................................................");
            arquivoTxt.add("");

            Map<String, BigDecimal> totalPorPessoa = new HashMap<>();
            Map<LocalDate, List<Rendimento>> rendimentosPorData = new HashMap<>();
            Map<LocalDate, BigDecimal> totalPorData = new HashMap<>();
            String pessoaTotal;
            BigDecimal totalPessoa = null;
            BigDecimal total = null;

            for (Rendimento obj : rendimentos) {//percorre a lista de rendimentos
                soma = soma.add(obj.getRendimentos()); //soma dos rendimentos da lista
                //popular map de total por pessoa
                pessoaTotal = obj.getNome();
                totalPessoa = totalPorPessoa.getOrDefault(pessoaTotal, BigDecimal.ZERO);
                totalPorPessoa.put(pessoaTotal, totalPessoa.add(obj.getRendimentos()));

                //popular map de rendimantos por data
                LocalDate data = obj.getData();
                List<Rendimento> listaRendimentos = rendimentosPorData.getOrDefault(data, new ArrayList<>());//entrega a lista referente a chave data ou uma nova lista
                listaRendimentos.add(obj);
                rendimentosPorData.put(data, listaRendimentos);

                //popular map de total por data
                BigDecimal valor = obj.getRendimentos();
                total = totalPorData.getOrDefault(data, BigDecimal.ZERO);
                total = total.add(valor);
                totalPorData.put(data, total);
            }

            arquivoTxt.add("................................................................................................................");
            arquivoTxt.add("...............................................TOTAL POR PESSOA.................................................");
            arquivoTxt.add("................................................................................................................");
            arquivoTxt.add("");

            for (Map.Entry<String, BigDecimal> entry : totalPorPessoa.entrySet()) {//percorrendo map total por pessoa
                pessoaTotal = entry.getKey();
                totalPessoa = entry.getValue();

                arquivoTxt.add("Pessoa: " + pessoaTotal);
                arquivoTxt.add("Total: R$ " + totalPessoa);
                arquivoTxt.add(" ");
            }

            String pessoaMaiorValor = null;
            for (String i : totalPorPessoa.keySet()) {//procurando pessoa com maior rendimento 
                int resultado = totalPorPessoa.get(i).compareTo(maior);
                if (resultado > 0) {
                    maior = totalPorPessoa.get(i);
                    pessoaMaiorValor = i;
                }
            }
            arquivoTxt.add("................................................................................................................");
            arquivoTxt.add("................................................TOTAL POR DATA..................................................");
            arquivoTxt.add("................................................................................................................");
            arquivoTxt.add("");

            for (Map.Entry<LocalDate, BigDecimal> entry : totalPorData.entrySet()) {//percorrendo map total por data
                LocalDate data = entry.getKey();
                total = entry.getValue();

                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String formatDateTime = data.format(format);

                arquivoTxt.add("Data: " + formatDateTime);
                arquivoTxt.add("Total: R$ " + total);
                arquivoTxt.add(" ");
            }
            
            //formatando data para printar no java
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formatDateTime = now.format(format);

            arquivoTxt.add("A pessoa que recebeu o maior valor foi.........: " + pessoaMaiorValor + " : R$ " + maior);
            arquivoTxt.add("O valor de rendimentos registrados no arquivo é: R$ " + soma);

            arquivoTxt.add("");
            arquivoTxt.add("............................................." + formatDateTime + "....................................................");
            arquivoTxt.add("");
            arquivoTxt.add("");

            //formatando data para printar no nome da pasta e no nome do arquivo gerado
            LocalDateTime nomeNow = LocalDateTime.now();
            DateTimeFormatter nomeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
            DateTimeFormatter pastaNome = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String nomeFormatTime = nomeNow.format(nomeFormat);
            String nomeFormatPasta = nomeNow.format(pastaNome);

            String dirPrincipal = "src/files/";//caminho do diretorio principal
            boolean criado = new File(dirPrincipal + nomeFormatPasta).mkdir();//criando pasta

            String outFile = "src/files/" + nomeFormatPasta + "/Relatorio-" + nomeFormatTime + ".txt";//concatena o endereço do arquivo

            if (criado) {//verifica se a pasta foi criada ou sobreposta 
                System.out.println("Pasta " + nomeFormatPasta + "criada com sucesso: " + criado);
            } else {
                System.out.println("Pasta " + nomeFormatPasta + " ja foi criada!!");
            }

            //ecrever no arquivo // append: true -- para escrever abaixo e não apagar todo o arquivo
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile, true))) {
                for (String linha : arquivoTxt) {//percorre a lista escreendo as linhas
                    bw.write(linha);
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Arquivo de saída gerado com sucesso -----> " + formatDateTime);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
