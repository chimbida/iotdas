package br.senac.tcs.iotdas.canal.domain;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by chimbida on 15/05/2017.
 */


public class CampoMongo {

    private Integer id;

    private String nome;

    private String guid;

    private String dados;

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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    public CampoMongo() {
    }

    public CampoMongo(Integer id, String nome, String guid, String dados) {
        this.id = id;
        this.nome = nome;
        this.guid = guid;
        this.dados = dados;
    }

    @Override
    public String toString() {
        return "CampoMongo{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", guid='" + guid + '\'' +
            ", dados='" + dados + '\'' +
            '}';
    }
}
