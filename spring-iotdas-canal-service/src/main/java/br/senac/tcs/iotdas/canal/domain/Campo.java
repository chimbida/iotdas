package br.senac.tcs.iotdas.canal.domain;

/**
 * @author Éderson H. Chimbida
 * @version 1.0
 * @since 10/03/2017
 */

public class Campo {

    private Integer id;

    private String guid;

    private String nome;

    private Canal canal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

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
