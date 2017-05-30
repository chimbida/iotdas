package br.senac.tcs.iotdas.usuario.repository;


import br.senac.tcs.iotdas.usuario.domain.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Éderson H. Chimbida
 * @version 1.0
 * @since 10/03/2017
 */

public interface TipoRepository extends JpaRepository<Tipo,Integer> {

}
