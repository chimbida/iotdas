package br.senac.tcs.iotdas.usuario.repository;


import br.senac.tcs.iotdas.usuario.domain.Canal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Éderson H. Chimbida
 * @version 1.0
 * @since 10/03/2017
 */

public interface CanalRepository extends JpaRepository<Canal, Integer> {

    List<Canal> findAllByPessoaId(int PessoaId);
    List<Canal> findAllByPessoaGuid(String guid);
    Canal findByGuid(String guid);
    void deleteByGuid(String guid);

}
