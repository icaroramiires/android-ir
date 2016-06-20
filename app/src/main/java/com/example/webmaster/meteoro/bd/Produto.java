package com.example.webmaster.meteoro.bd;

/**
 * Created by webmaster on 20/06/16.
 */
public class Produto {
    private long codigo = -1;
    private String nome;
    private int qnt;
    private Double preco;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return nome + "\n" +
                "Qnt: " + qnt + "/" + "Preço: " + preco;
    }
}
