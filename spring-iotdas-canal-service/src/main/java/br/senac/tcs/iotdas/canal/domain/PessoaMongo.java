package br.senac.tcs.iotdas.canal.domain;

/**
 * Created by chimbida on 21/05/2017.
 */
public class PessoaMongo {

    private String id;

    private String usuario;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "PessoaMongo{" +
            "id='" + id + '\'' +
            ", usuario='" + usuario + '\'' +
            '}';
    }
}
