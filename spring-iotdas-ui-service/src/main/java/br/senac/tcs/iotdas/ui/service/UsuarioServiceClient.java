package br.senac.tcs.iotdas.ui.service;

import br.senac.tcs.iotdas.ui.domain.Campo;
import br.senac.tcs.iotdas.ui.domain.Canal;
import br.senac.tcs.iotdas.ui.domain.Pessoa;
import br.senac.tcs.iotdas.ui.domain.Tipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by chimbida on 03/04/2017.
 */

@Service
public class UsuarioServiceClient {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    protected Logger logger = Logger.getLogger(UsuarioServiceClient.class.getName());

    @PostConstruct
    public void demoOnly() {
        logger.warning("A fábrica do pedido RestTemplate é "
            + restTemplate.getRequestFactory().getClass());
    }

    public UsuarioServiceClient(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Pessoa
     * Busca pessoa pelo ID ou GUID
     */
    public Pessoa pessoaById(String pessoaId) {
        logger.info("pessoaById() chamou: " + pessoaId);
        return restTemplate.getForObject(serviceUrl + "/pessoa/{pessoaId}",
            Pessoa.class, pessoaId);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Pessoa
     * Cria nova pessoa passando o objeto
     */
    public Pessoa pessoaNova(Pessoa pessoa) {
        logger.info("pessoaNova() chamou: Nome" + pessoa.getNome() + " Sobrenome " + pessoa.getSobrenome() + " Tipo" + pessoa.getTipoPessoa());
        return restTemplate.postForObject(serviceUrl + "/pessoa", pessoa, Pessoa.class);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Pessoa
     * Lista todas as pessoas
     */
    public List<Pessoa> pessoaList() {
        logger.info("pessoaList() foi chamado!");
        Pessoa[] pessoas = null;
        try {
            pessoas = restTemplate.getForObject(serviceUrl
                + "/pessoa", Pessoa[].class);
        } catch (HttpClientErrorException e) {
            // 404 Nothing found
        }
        if (pessoas == null || pessoas.length == 0)
            return null;
        else
            return Arrays.asList(pessoas);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Pessoa
     * Deleta a uma pessoa pelo ID ou GUID
     */
    public void pessoaDelete(String pessoaId) {
        logger.info("pessoaDelete() chamou: " + pessoaId);
        restTemplate.delete(serviceUrl + "/pessoa/{pessoaId}", pessoaId);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Tipo
     * Busca um tipo de pessoa por ID
     */
    public Tipo tipoById(Integer tipoId) {
        logger.info("tipoById() chamou: " + tipoId);
        return restTemplate.getForObject(serviceUrl + "/tipo/{tipoId}",
            Tipo.class, tipoId);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Tipo
     * Cria um novo tipo de pessoa passando o objeto
     */
    public Tipo tipoNovo(Tipo tipo) {
        logger.info("tipoNovo() chamou: Nome" + tipo.getNome() + " Descrição " + tipo.getDescricao());
        return restTemplate.postForObject(serviceUrl + "/tipo", tipo, Tipo.class);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Tipo
     * Lista todos os tipos de pessoa
     */
    public List<Tipo> tipoList() {
        logger.info("tipoList() foi chamado!");
        Tipo[] tipos = null;
        try {
            tipos = restTemplate.getForObject(serviceUrl
                + "/tipo", Tipo[].class);
        } catch (HttpClientErrorException e) {
            // 404 Nothing found
        }
        if (tipos == null || tipos.length == 0)
            return null;
        else
            return Arrays.asList(tipos);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Tipo
     * Delete um tipo de pessoa pelo ID
     */
    public void tipoDelete(Integer tipoId) {
        logger.info("tipoaDelete() chamou: " + tipoId);
        restTemplate.delete(serviceUrl + "/tipo/{tipoId}", tipoId);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Campo
     * Busca um campo pelo ID ou GUID
     */
    public Campo campoById(String campoId) {
        logger.info("campoById() chamou: " + campoId);
        return restTemplate.getForObject(serviceUrl + "/campo/{campoId}",
            Campo.class, campoId);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Campo
     * Cria um campo novo passando o objeto
     */
    public Campo campoNovo(Campo campo) {
        logger.info("campoNovo() chamou: Nome" + campo.getNome());
        return restTemplate.postForObject(serviceUrl + "/campo", campo, Campo.class);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Campo
     * Lista todos os campos
     */
    public List<Campo> campoList() {
        logger.info("campoList() foi chamado!");
        Campo[] campos = null;
        try {
            campos = restTemplate.getForObject(serviceUrl
                + "/campo", Campo[].class);
        } catch (HttpClientErrorException e) {
            // 404 Nothing found
        }
        if (campos == null || campos.length == 0)
            return null;
        else
            return Arrays.asList(campos);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Campo
     * Delete um campo pelo ID ou GUID
     */
    public void campoDelete(String campoId) {
        logger.info("campoaDelete() chamou: " + campoId);
        restTemplate.delete(serviceUrl + "/campo/{campoId}", campoId);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Campo
     * Lista todos os campos pelo ID ou GUID do canal que ele esta
     */
    public List<Campo> campoListByCanal(String canalId) {
        logger.info("campoListByCanal() chamou: " + canalId);
        Campo[] campos = null;
        try {
            campos = restTemplate.getForObject(serviceUrl
                + "/campo/canal/{canalId}", Campo[].class, canalId);
        } catch (HttpClientErrorException e) {
            // 404 Nothing found
        }
        if (campos == null || campos.length == 0)
            return null;
        else
            return Arrays.asList(campos);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Canal
     * Lista um canal pelo ID ou GUID
     */
    public Canal canalById(String canalId) {
        logger.info("canalById() chamou: " + canalId);
        return restTemplate.getForObject(serviceUrl + "/canal/{canalId}",
            Canal.class, canalId);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Canal
     * Cria um novo canal passando o objeto
     */
    public Canal canalNovo(Canal canal) {
        logger.info("canalNovo() chamou: Nome: " + canal.getNome());
        return restTemplate.postForObject(serviceUrl + "/canal", canal, Canal.class);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Canal
     * Lista todos os canais
     */
    public List<Canal> canalList() {
        logger.info("canalList() foi chamado!");
        Canal[] canals = null;
        try {
            canals = restTemplate.getForObject(serviceUrl
                + "/canal", Canal[].class);
        } catch (HttpClientErrorException e) {
            // 404 Nothing found
        }
        if (canals == null || canals.length == 0)
            return null;
        else
            return Arrays.asList(canals);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Canal
     * Delete um canal passando o ID ou GUID
     */
    public void canalDelete(String canalId) {
        logger.info("canalaDelete() chamou: " + canalId);
        restTemplate.delete(serviceUrl + "/canal/{canalId}", canalId);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Canal
     * Lista canaais pelo ID ou GUID da pessoa
     */
    public List<Canal> canalListByPessoa(String pessoaId) {
        logger.info("canalListByPessoa() chamou: " + pessoaId);
        Canal[] canals = null;
        try {
            canals = restTemplate.getForObject(serviceUrl
                + "/canal/pessoa/{pessoaId}", Canal[].class, pessoaId);
        } catch (HttpClientErrorException e) {
            // 404 Nothing found
        }
        if (canals == null || canals.length == 0)
            return null;
        else
            return Arrays.asList(canals);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Campo
     * Cria um novo guid pro campo passando o objeto
     */
    public void campoNovoGuid(Campo campo) {
        logger.info("campoNovoGuid() chamou: Nome" + campo.getNome());
        restTemplate.postForObject(serviceUrl + "/campo/guid", campo, Campo.class);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Canal
     * Cria um novo guid pro canal passando o objeto
     */
    public void canalNovoGuid(Canal canal) {
        logger.info("canalNovoGuid() chamou: Nome" + canal.getNome());
        restTemplate.postForObject(serviceUrl + "/canal/guid", canal, Canal.class);
    }

    /**
     * Chamada REST ao Microserviço de Usuário - Objeto Pessoa
     * Cria um novo guid pra pessoa passando o objeto
     */
    public void pessoaNovoGuid(Pessoa pessoa) {
        logger.info("pessoaNovoGuid() chamou: Nome" + pessoa.getNome());
        restTemplate.postForObject(serviceUrl + "/pessoa/guid", pessoa, Pessoa.class);
    }

}
