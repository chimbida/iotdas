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


import br.senac.tcs.iotdas.usuario.domain.Canal;
import br.senac.tcs.iotdas.usuario.repository.CanalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author Éderson H. Chimbida
 * @version 2.0
 * @since 10/03/2017
 */

@RequestMapping("/canal")
@RestController
public class CanalResources {

    private Pattern uuid = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");

    @Autowired
    private CanalRepository canalRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criaCanal(@Valid @RequestBody Canal canal) {
        if (canal.getId() == null) {
            canal.setGuid(UUID.randomUUID().toString());
        }
        canalRepository.save(canal);
    }

    @GetMapping(value = "/{canalId}")
    public Canal findCanal(@PathVariable("canalId") String canalId) {
        if (uuid.matcher(canalId).matches()) {
            return canalRepository.findByGuid(canalId);
        } else {
            return canalRepository.findOne(Integer.valueOf(canalId));
        }
    }

    @GetMapping
    public List<Canal> findAll() {
        return canalRepository.findAll();
    }

    @GetMapping(value = "/pessoa/{pessoaId}")
    public List<Canal> findAllByPessoa(@PathVariable("pessoaId") String pessoaId) {
        if (uuid.matcher(pessoaId).matches()) {
            return canalRepository.findAllByPessoaGuid(pessoaId);
        } else {
            return canalRepository.findAllByPessoaId(Integer.valueOf(pessoaId));
        }
    }

    @DeleteMapping(value = "/{canalId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletecanal(@PathVariable("canalId") String canalId) {
        if (uuid.matcher(canalId).matches()) {
            canalRepository.deleteByGuid(canalId);
        } else {
            canalRepository.delete(Integer.valueOf(canalId));
        }
    }

    @PostMapping("/guid")
    @ResponseStatus(HttpStatus.CREATED)
    public void novoGuidCanal(@Valid @RequestBody Canal canal) {
        canal.setGuid(UUID.randomUUID().toString());
        canalRepository.save(canal);
    }

}
