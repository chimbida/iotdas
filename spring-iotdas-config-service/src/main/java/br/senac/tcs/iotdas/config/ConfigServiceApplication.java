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

/**
 * IOT DAS - Spring Cloud Config Server
 *
 * O Spring Cloud Config fornece suporte ao servidor e ao cliente para configuração externalizada
 * em um sistema distribuído. Com o Config Server você tem um local central para gerenciar
 * propriedades externas para aplicativos em todos os ambientes.
 * Os conceitos no cliente e no servidor mapeiam de forma idêntica as abstrações
 * Spring Environment e PropertySource, por isso se encaixam muito bem com as aplicações Spring,
 * mas podem ser utilizadas com qualquer aplicação em execução em qualquer idioma.
 * À medida que um aplicativo se move através do pipeline de desenvolvimento de dev para testar e em produção,
 * você pode gerenciar a configuração entre esses ambientes e ter certeza de que os aplicativos
 * têm tudo o que precisam para serem executados quando migram. A implementação padrão do back-end
 * de armazenamento do servidor usa o git para que ele suporte facilmente versões rotuladas dos ambientes
 * de configuração, além de ser acessível a uma ampla gama de ferramentas para gerenciar o conteúdo.
 * É fácil adicionar implementações alternativas e conectá-los com a configuração da Primavera.
 *
 * http://cloud.spring.io/spring-cloud-config/spring-cloud-config.html
 *
 */

package br.senac.tcs.iotdas.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Éderson H. Chimbida
 * @version 1.0
 * @since 07/03/2017
 */

@EnableConfigServer
@SpringBootApplication
public class ConfigServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServiceApplication.class, args);
    }

}
