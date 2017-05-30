package br.senac.tcs.iotdas.canal.controller;

import br.senac.tcs.iotdas.canal.domain.CampoMongo;
import br.senac.tcs.iotdas.canal.domain.CanalMongo;
import br.senac.tcs.iotdas.canal.service.DadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Created by chimbida on 16/05/2017.
 */

@RequestMapping("/")
@RestController
public class DadosResources {

    private Pattern uuid = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");

    protected Logger logger = Logger.getLogger(DadosResources.class.getName());

    @Autowired
    protected DadosService dadosService;

    @PostMapping("/{guid}") //UUID DE PESSOA
    @ResponseBody
    public ResponseEntity<String> salvaCanalMongo(@PathVariable("guid") String guid, @Valid @RequestBody CanalMongo canalMongo, BindingResult result) {

        logger.info("dadoEnviado() chamado: PESSOA GUID: " + guid + "\n"
            + " DADOS: " + canalMongo.toString() + " \n"
            + "\n RESULT: " + result.toString() + " \n");

        if (result.hasErrors()) {
            logger.warning(result.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).build();
        }

        // VALIDA DE EH UM UUID MESMO
        // VALIDA SE AQUELE UUID EXISTE NO BANCO
        if(!uuid.matcher(guid).matches() || !dadosService.ValidaGuidPessoa(guid)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON).build();
        }

        if(dadosService.ValidaJsonPost(canalMongo, guid)) { // VALIDA E FILTRA DADOS QUE VAO PARA O MONGO
            return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).contentType(MediaType.APPLICATION_JSON).build(); //customizar erro: .body("texto");
        }
    }

    @GetMapping("/{guid}")
    public Page<CanalMongo> listaCanalMongo(@PathVariable("guid") String guid, Pageable pageable) {

        logger.info("findAll() chamado - GUID: " + guid + "\n");

        // VALIDA DE EH UM UUID MESMO
        // VALIDA SE AQUELE UUID EXISTE NO BANCO
        if(!uuid.matcher(guid).matches() || !dadosService.ValidaGuidPessoa(guid)) {
            return null;
        }

        return dadosService.findAllByUsuarioId(guid, pageable);
    }

    @GetMapping("/{guid}/canal/{cguid}")
    public Page<CanalMongo> listaCanalMongo(@PathVariable("guid") String guid, @PathVariable("cguid") String cguid, Pageable pageable) {

        logger.info("findAll() chamado - GUID: " + guid + "\n");

        // VALIDA DE EH UM UUID MESMO
        // VALIDA SE AQUELE UUID EXISTE NO BANCO
        if(!uuid.matcher(guid).matches() || !dadosService.ValidaGuidPessoa(guid)) {
            return null;
        }

        return dadosService.finAllByCanalId(guid, cguid, pageable);
    }



}
