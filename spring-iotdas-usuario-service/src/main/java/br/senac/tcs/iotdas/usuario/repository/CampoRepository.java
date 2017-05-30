package br.senac.tcs.iotdas.usuario.repository;


import br.senac.tcs.iotdas.usuario.domain.Campo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Éderson H. Chimbida
 * @version 1.0
 * @since 10/03/2017
 */

public interface CampoRepository extends JpaRepository<Campo, Integer> {

    List<Campo> findAllByCanalId(int PessoaId);
    List<Campo> findAllByCanalGuid(String guid);
    Campo findByGuid(String guid);
    void deleteByGuid(String guid);

}
