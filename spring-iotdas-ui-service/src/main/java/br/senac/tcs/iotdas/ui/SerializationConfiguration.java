package br.senac.tcs.iotdas.ui;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chimbida on 24/05/2017.
 */
@Configuration
public class SerializationConfiguration {

    @Bean
    public String overwriteSerializationId(ApplicationContext appContext) {
        BeanFactory beanFactory = appContext.getAutowireCapableBeanFactory();
        ((DefaultListableBeanFactory) beanFactory).setSerializationId("springOauth2GatewayAndSpringSession");
        return "overwritten";
    }
}
