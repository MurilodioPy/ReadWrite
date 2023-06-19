package ReadWrite;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 *
 * @author murilodio
 */
class Rendimento {

    private long id;
    private String nome;
    private BigDecimal rendimentos;
    private LocalDate data;
    
    public Rendimento(long id, String nome, BigDecimal valor, LocalDate data) {
        this.id = id;
        this.nome = nome;
        this.rendimentos = valor;
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate Data) {
        this.data = Data;
    }

    public Rendimento() {
        
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRendimentos(BigDecimal rendimentos) {
        this.rendimentos = rendimentos;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getRendimentos() {
        return rendimentos;
    }

    @Override
    public String toString() {
        return "Rendimentos{" 
                + "id=" + id 
                + ", nome=" + nome 
                + ", rendimentos=" + rendimentos 
                + ", Data=" + data + '}';
    }

}
