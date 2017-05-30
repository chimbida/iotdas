package br.senac.tcs.iotdas.canal.service.impl;

import br.senac.tcs.iotdas.canal.domain.*;
import br.senac.tcs.iotdas.canal.service.UsuarioServiceClient;
import br.senac.tcs.iotdas.canal.repository.CanalMongoRepository;
import br.senac.tcs.iotdas.canal.service.DadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by chimbida on 16/05/2017.
 */

@Service
public class DadosServiceImpl implements DadosService {

    protected Logger logando = Logger.getLogger(DadosServiceImpl.class.getName());

    @Autowired
    protected UsuarioServiceClient usuarioServiceClient;

    @Autowired
    private CanalMongoRepository canalMongoRepository;

    public DadosServiceImpl(UsuarioServiceClient usuarioServiceClient) {
        this.usuarioServiceClient = usuarioServiceClient;
    }

    public Boolean ValidaGuidPessoa(String guid) {

        // TALVEZ COLOCAR UM OPCAO PARA DESATIVAR OS CANAIS E QUANDO DESATIVAR USUARIO, DESATIVA TODOS OS CANAIS
        if (usuarioServiceClient.pessoaById(guid) == null) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean ValidaJsonPost(CanalMongo canalMongo, String guid) {

        // VALIDA A GUID QUE PASSOU NO JSON, PARA SABER DE O CANAL EXISTE
        if (usuarioServiceClient.canalById(canalMongo.getGuid()) == null) {
            return false;
        }

        // PEGA CANAL PELA GUID QUE PASSOU NO JSON
        Canal canal = usuarioServiceClient.canalById(canalMongo.getGuid());

        // PEGA PESSOA PELA GUID DA URL
        Pessoa pessoa = usuarioServiceClient.pessoaById(guid);

        // CANAL QUE VAI SER SALVO
        CanalMongo salvaCanal = new CanalMongo();

        // USUARIO QUE VAI SER SALVO
        PessoaMongo salvaPessoa = new PessoaMongo();

        // LISTA DE CAMPOS VALIDOS
        List<CampoMongo> salvaCampos = new ArrayList<CampoMongo>();

        // COLOCA O UUID DO CANAL
        salvaCanal.setGuid(canalMongo.getGuid());
        // COLOCA NOME DO CANAL
        salvaCanal.setNome(canal.getNome());
        // COLOCA O ID DO CANAL PARA PESQUISA
        salvaCanal.setCanalId(canal.getId().toString());

        // COLOCA A PESSOA NO CANAL - MUITA INFORMAÇÃO DA PESSOA, TALVEZ REMOVER
        salvaPessoa.setUsuario(pessoa.getUsuario());
        salvaPessoa.setId(pessoa.getId().toString());
        salvaCanal.setUsuario(salvaPessoa);

        // LISTA TODOS OS CAMPOS QUE O CANAL POSSUI
        List<Campo> camposExistentes = usuarioServiceClient.campoListByCanal(canalMongo.getGuid());

        for ( CampoMongo c: canalMongo.getCampos()) {
             camposExistentes.forEach((campo) -> {
                 if (campo.getGuid().equals(c.getGuid())) { // SE O CAMPO QUE PASSOU EXISTE
                     CampoMongo campoAdd = new CampoMongo();
                     campoAdd.setNome(campo.getNome());
                     campoAdd.setId(campo.getId());
                     campoAdd.setGuid(c.getGuid());
                     campoAdd.setDados(c.getDados());
                     salvaCampos.add(campoAdd);
                 }
             });
         }

        if (salvaCampos.isEmpty()) { // SE NENHUM CAMPO FOR VALIDO OU NAO TIVER CAMPOS NEM SALVA
            return false;
        } else {
            salvaCanal.setCampos(salvaCampos);
            canalMongoRepository.save(salvaCanal);
            return true;
        }
    }

    public Page<CanalMongo> findAllByUsuarioId(String guid, Pageable pageable) {
        String id = usuarioServiceClient.pessoaById(guid).getId().toString();
        return canalMongoRepository.findAllByUsuarioId(id, pageable);
    }

    public Page<CanalMongo> finAllByCanalId(String guid, String cguid, Pageable pageable) {
        String pessoId = usuarioServiceClient.pessoaById(guid).getId().toString();
        String canalId = usuarioServiceClient.canalById(guid).getId().toString();

        return canalMongoRepository.findAllByUsuarioIdAndCanalId(pessoId, canalId , pageable);
    }

}
