package br.senac.tcs.iotdas.canal;

import br.senac.tcs.iotdas.canal.controller.DadosResources;
import br.senac.tcs.iotdas.canal.service.UsuarioServiceClient;
import br.senac.tcs.iotdas.canal.service.impl.DadosServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanReporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.client.RestTemplate;
import zipkin.Span;


/**
 * Created by chimbida on 15/05/2017.
 */

@Configuration
@EnableMongoRepositories(basePackages="br.senac.tcs.iotdas.canal")
@EnableMongoAuditing
@SpringBootApplication
@EnableDiscoveryClient
@EnableSpringDataWebSupport
@ComponentScan(useDefaultFilters = false)
public class CanalServiceApplication {

    public static final String USUARIO_SERVICE_URL = "http://USUARIO-SERVICE";

    private static Log logger = LogFactory.getLog(CanalServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CanalServiceApplication.class, args);
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public UsuarioServiceClient usuarioService() {
        return new UsuarioServiceClient(USUARIO_SERVICE_URL);
    }

    @Bean
    public DadosServiceImpl dadosServiceImpl() {
        return new DadosServiceImpl(usuarioService());
    }

    @Bean
    public DadosResources dadosResources() {return new DadosResources();}

    @Bean
    @ConditionalOnProperty(value = "sample.zipkin.enabled", havingValue = "false")
    public ZipkinSpanReporter spanCollector() {
        return new ZipkinSpanReporter() {
            @Override
            public void report(Span span) {
                logger.info(span);
            }
        };
    }

}
