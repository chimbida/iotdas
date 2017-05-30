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


import br.senac.tcs.iotdas.usuario.domain.Campo;
import br.senac.tcs.iotdas.usuario.domain.Canal;
import br.senac.tcs.iotdas.usuario.repository.CampoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author Éderson H. Chimbida
 * @version 1.0
 * @since 10/03/2017
 */

@RequestMapping("/campo")
@RestController
public class CampoResources {

    private Pattern uuid = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");

    @Autowired
    private CampoRepository campoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criaCampo(@Valid @RequestBody Campo campo) {
        if (campo.getId() == null) {
            campo.setGuid(UUID.randomUUID().toString());
        }
        campoRepository.save(campo);
    }

    @GetMapping(value = "/{campoId}")
    public Campo findCampo(@PathVariable("campoId") String campoId) {
        if (uuid.matcher(campoId).matches()) {
            return campoRepository.findByGuid(campoId);
        } else {
            return campoRepository.findOne(Integer.valueOf(campoId));
        }
    }

    @GetMapping(value = "/canal/{canalId}")
    public List<Campo> findAllByCanal(@PathVariable("canalId") String canalId) {
        if (uuid.matcher(canalId).matches()) {
            return campoRepository.findAllByCanalGuid(canalId);
        } else {
            return campoRepository.findAllByCanalId(Integer.valueOf(canalId));
        }
    }

    @GetMapping
    public List<Campo> findAll() {
        return campoRepository.findAll();
    }

    @DeleteMapping(value = "/{campoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void deletecampo(@PathVariable("campoId") String campoId) {
        if (uuid.matcher(campoId).matches()) {
            campoRepository.deleteByGuid(campoId);
        } else {
            campoRepository.delete(Integer.valueOf(campoId));
        }
    }

    @PostMapping("/guid")
    @ResponseStatus(HttpStatus.CREATED)
    public void novoGuidCampo(@Valid @RequestBody Campo campo) {
        campo.setGuid(UUID.randomUUID().toString());
        campoRepository.save(campo);
    }

}
