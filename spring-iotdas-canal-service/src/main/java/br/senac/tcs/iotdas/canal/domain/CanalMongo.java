package br.senac.tcs.iotdas.canal.domain;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by chimbida on 18/05/2017.
 */

@Document(collection = "dados")
public class CanalMongo {

    @Id
    private String id;

    // GUID DO CANAL
    private String guid;

    // ID DO CANAL - PARA PROCURAR CASO TROQUE A GUID DELE
    private String canalId;

    // NOME DO CANAL
    private String nome;

    private PessoaMongo usuario;

    private List<CampoMongo> campos;

    @CreatedDate
    private DateTime criado;

    @LastModifiedDate
    private DateTime modificado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getCanalId() {
        return canalId;
    }

    public void setCanalId(String canalId) {
        this.canalId = canalId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PessoaMongo getUsuario() {
        return usuario;
    }

    public void setUsuario(PessoaMongo usuario) {
        this.usuario = usuario;
    }

    public List<CampoMongo> getCampos() {
        return campos;
    }

    public void setCampos(List<CampoMongo> campos) {
        this.campos = campos;
    }

    public DateTime getCriado() {
        return criado;
    }

    public void setCriado(DateTime criado) {
        this.criado = criado;
    }

    public DateTime getModificado() {
        return modificado;
    }

    public void setModificado(DateTime modificado) {
        this.modificado = modificado;
    }

    public CanalMongo() {
    }

    public CanalMongo(String id, String guid, String canalId, String nome, PessoaMongo usuario, List<CampoMongo> campos, DateTime criado, DateTime modificado) {
        this.id = id;
        this.guid = guid;
        this.canalId = canalId;
        this.nome = nome;
        this.usuario = usuario;
        this.campos = campos;
        this.criado = criado;
        this.modificado = modificado;
    }

    @Override
    public String toString() {
        return "CanalMongo{" +
            "id='" + id + '\'' +
            ", guid='" + guid + '\'' +
            ", canalId='" + canalId + '\'' +
            ", nome='" + nome + '\'' +
            ", usuario=" + usuario +
            ", campos=" + campos +
            ", criado=" + criado +
            ", modificado=" + modificado +
            '}';
    }
}
