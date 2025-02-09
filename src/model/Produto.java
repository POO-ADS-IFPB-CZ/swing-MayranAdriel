package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Produto implements Serializable {

    private final long serialVersionUID;
    private int id;
    private String descricao;
    private BigDecimal preco;
    private LocalDate validade;

    public Produto( int id, String descricao, BigDecimal preco, LocalDate validade) {
        this.serialVersionUID = 1L;
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.validade = validade;
    }

    public long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    @Override
    public String toString() {
        return
                " id=" + id +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", validade=" + validade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return getId() == produto.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
