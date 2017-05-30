/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.senac.tcs.iotdas.usuario.controller;

import br.senac.tcs.iotdas.usuario.domain.Pessoa;
import br.senac.tcs.iotdas.usuario.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;


/**
 * @author Éderson H. Chimbida
 * @version 1.0
 * @since 10/03/2017
 */

@RequestMapping("/pessoa")
@RestController
public class PessoaResources {

    private Pattern uuid = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criaPessoa(@Valid @RequestBody Pessoa pessoa) {
        if (pessoa.getId() == null) {
            pessoa.setGuid(UUID.randomUUID().toString());
        }
        pessoaRepository.save(pessoa);
    }

    @GetMapping(value = "/{pessoaId}")
    public Pessoa findPessoa(@PathVariable("pessoaId") String pessoaId) {
        if (uuid.matcher(pessoaId).matches()) {
            return pessoaRepository.findByGuid(pessoaId);
        } else {
            return pessoaRepository.findOne(Integer.valueOf(pessoaId));
        }

    }

    @GetMapping
    public List<Pessoa> findAllPessoa() {
        return pessoaRepository.findAll();
    }

    @DeleteMapping(value = "/{pessoaId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePessoa(@PathVariable("pessoaId") String pessoaId)  {
        if (uuid.matcher(pessoaId).matches()) {
            pessoaRepository.deleteByGuid(pessoaId);
        } else {
            pessoaRepository.delete(Integer.valueOf(pessoaId));
        }
    }

    @PostMapping("/guid")
    @ResponseStatus(HttpStatus.CREATED)
    public void novoGuidPessoa(@Valid @RequestBody Pessoa pessoa) {
        pessoa.setGuid(UUID.randomUUID().toString());
        pessoaRepository.save(pessoa);
    }

}
