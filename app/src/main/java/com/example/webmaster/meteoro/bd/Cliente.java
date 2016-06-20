package com.example.webmaster.meteoro.bd;

/**
 * Created by webmaster on 20/06/16.
 */
public class Cliente {
    private long codigo = -1;
    private String nome;
    private String cidade;
    private String email;
    private String telefone;

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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return nome + "\n" +
                "email: " + email + "/" + "telefone: " + telefone;
    }
}
