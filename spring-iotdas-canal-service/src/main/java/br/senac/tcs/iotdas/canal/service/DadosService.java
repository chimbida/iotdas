package br.senac.tcs.iotdas.canal.service;

import br.senac.tcs.iotdas.canal.domain.Canal;
import br.senac.tcs.iotdas.canal.domain.CanalMongo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by chimbida on 16/05/2017.
 */

public interface DadosService {

    Boolean ValidaJsonPost(CanalMongo canalMongo, String guid);
    Boolean ValidaGuidPessoa(String guid);
    Page<CanalMongo> findAllByUsuarioId(String guid, Pageable pageable);
    Page<CanalMongo> finAllByCanalId(String guid, String cguid, Pageable pageable);

}
