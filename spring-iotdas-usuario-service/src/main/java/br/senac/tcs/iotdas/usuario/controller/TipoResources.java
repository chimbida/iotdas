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

import br.senac.tcs.iotdas.usuario.domain.Tipo;
import br.senac.tcs.iotdas.usuario.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Éderson H. Chimbida
 * @version 1.0
 * @since 10/03/2017
 */

@EnableResourceServer
@RequestMapping("/tipo")
@RestController
public class TipoResources {

    @Autowired
    private TipoRepository tipoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criaTipo(@Valid @RequestBody Tipo tipo) {
        tipoRepository.save(tipo);
    }

    @GetMapping(value = "/{tipoId}")
    public Tipo findTipo(@PathVariable("tipoId") int tipoId) {
        return tipoRepository.findOne(tipoId);
    }

    @GetMapping
    public List<Tipo> findAllTipo() {
        return tipoRepository.findAll();
    }

    @DeleteMapping(value = "/{tipoId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTipo(@PathVariable("tipoId") int tipoId) {
        tipoRepository.delete(tipoId);
    }

}
