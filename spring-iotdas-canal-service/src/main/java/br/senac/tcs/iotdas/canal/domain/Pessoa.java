package br.senac.tcs.iotdas.canal.domain;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * @author Éderson H. Chimbida
 * @version 1.0
 * @since 10/03/2017
 */

public class Pessoa {

    private Integer id;

    private String nome;

    private String sobrenome;

    private String usuario;

    private String senha;

    private String email;

    private Boolean ativo;

    private Tipo tipoPessoa;

    private Date criado;

    private Date modificado;

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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Tipo getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Tipo tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Date getCriado() {
        return criado;
    }

    public void setCriado(Date criado) {
        this.criado = criado;
    }

    public Date getModificado() {
        return modificado;
    }

    public void setModificado(Date modificado) {
        this.modificado = modificado;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", sobrenome='" + sobrenome + '\'' +
            ", usuario='" + usuario + '\'' +
            ", senha='" + senha + '\'' +
            ", email='" + email + '\'' +
            ", ativo=" + ativo +
            ", tipoPessoa=" + tipoPessoa +
            ", criado=" + criado +
            ", modificado=" + modificado +
            ", guid='" + guid + '\'' +
            '}';
    }
}
