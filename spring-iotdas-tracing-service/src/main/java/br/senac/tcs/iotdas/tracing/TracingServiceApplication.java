/*
 * Copyright 2015-2017 the original author or authors.
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

/**
 * IOT DAS - Tracing Server
 *
 * Zipkin é um sistema de rastreamento distribuído. Ele ajuda a coletar dados de tempo necessários
 * para solucionar problemas de latência em arquiteturas de microserviço. Ele gerencia a coleção
 * ea pesquisa desses dados. O design do Zipkin é baseado no papel do Google Dapper.
 *
 * As aplicações são instrumentadas para relatar os dados de tempo para o Zipkin.
 * A interface do usuário do Zipkin também apresenta um diagrama de Dependências
 * mostrando quantas solicitações rastreadas passaram por cada aplicativo.
 * Se você estiver solucionando problemas de latência ou erros, você pode filtrar
 * ou classificar todos os rastreamentos com base no aplicativo, comprimento do rastreamento,
 * anotação ou timestamp. Depois de selecionar um rastreamento, você pode ver a porcentagem do tempo
 * total de rastreamento que cada intervalo demora, o que permite identificar o aplicativo problemático.
 *
 * Este projeto inclui uma biblioteca sem dependência e um servidor de
 * inicialização de mola. As opções de armazenamento incluem em memória, JDBC (mysql), Cassandra e Elasticsearch.
 *
 * https://github.com/openzipkin/zipkin
 * https://spring.io/blog/2016/02/15/distributed-tracing-with-spring-cloud-sleuth-and-spring-cloud-zipkin
 *
 */

package br.senac.tcs.iotdas.tracing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;

/**
 * Created by chimbida on 05/05/2017.
 */

@EnableDiscoveryClient
@SpringBootApplication
@EnableZipkinServer
public class TracingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TracingServiceApplication.class, args);
    }

}
