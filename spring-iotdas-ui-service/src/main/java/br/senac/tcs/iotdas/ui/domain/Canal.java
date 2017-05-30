package br.senac.tcs.iotdas.ui.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Éderson H. Chimbida
 * @version 1.0
 * @since 10/03/2017
 */


public class Canal {

    private Integer id;

    @NotEmpty
    private String nome;

    private String descricao;

    private Pessoa pessoa;

    private String guid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "Canal{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", descricao='" + descricao + '\'' +
            ", pessoa=" + pessoa +
            ", guid='" + guid + '\'' +
            '}';
    }
}
