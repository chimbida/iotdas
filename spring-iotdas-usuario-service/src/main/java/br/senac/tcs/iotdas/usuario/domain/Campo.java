package br.senac.tcs.iotdas.usuario.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * @author Éderson H. Chimbida
 * @version 1.0
 * @since 10/03/2017
 */

@Entity
public class Campo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "guid", nullable = false)
    private String guid;

    @NotEmpty
    private String nome;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Canal canal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public String getGuid() { return guid; }
//
//    public void setGuid(String guid) { this.guid = guid; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Canal getCanal() {
        return canal;
    }

    public void setCanal(Canal canal) {
        this.canal = canal;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "Campo{" +
            "id=" + id +
            ", guid='" + guid + '\'' +
            ", nome='" + nome + '\'' +
            ", canal=" + canal +
            '}';
    }
}
