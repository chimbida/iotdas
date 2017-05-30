package br.senac.tcs.iotdas.ui;

import br.senac.tcs.iotdas.ui.controller.*;
import br.senac.tcs.iotdas.ui.service.UsuarioServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanReporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import zipkin.Span;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

/**
 * @author Éderson H. Chimbida
 * @version 1.0
 * @since 10/03/2017
 */

//@EnableFeignClients
//@EnableZuulProxy

@SpringBootApplication
@EnableWebMvc
@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false)
@EnableResourceServer
public class UiServiceApplication extends WebMvcConfigurerAdapter {

    public static final String USUARIO_SERVICE_URL = "http://USUARIO-SERVICE";

    private static Log logger = LogFactory.getLog(UiServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(UiServiceApplication.class, args);
    }

    @RequestMapping("/user")
    public Map<String, String> user(Principal user) {
        return Collections.singletonMap("name", user.getName());
    }

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers("/index.html", "/").permitAll()
                .anyRequest().hasRole("ROLE_USER");
            // @formatter:on
        }
    }

    @LoadBalanced
    @Bean
    OAuth2RestTemplate restTemplate(OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details) {
        return new OAuth2RestTemplate(details, oauth2ClientContext);
    }


    @Bean
    public UsuarioServiceClient usuarioService() {
        return new UsuarioServiceClient(USUARIO_SERVICE_URL);
    }

    @Bean
    public PessoaController pessoaController() {
        return new PessoaController(usuarioService());
    }

    @Bean
    public TipoController TipoController() {
        return new TipoController(usuarioService());
    }

    @Bean
    public CampoController CampoController() {
        return new CampoController(usuarioService());
    }

    @Bean
    public CanalController CanalController() {
        return new CanalController(usuarioService());
    }

    @Bean
    public HomeController homeController() {
        return new HomeController();
    }

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
