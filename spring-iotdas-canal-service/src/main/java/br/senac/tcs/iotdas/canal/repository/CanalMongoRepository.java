package br.senac.tcs.iotdas.canal.repository;

import br.senac.tcs.iotdas.canal.domain.CanalMongo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

/**
 * Created by chimbida on 15/05/2017.
 */


public interface CanalMongoRepository extends MongoRepository<CanalMongo, String> {

    Page<CanalMongo> findAllByUsuarioId(String id, Pageable pageable);
    Page<CanalMongo> findAllByUsuarioIdAndCanalId(String pessoaId, String canalId, Pageable pageable);

}
