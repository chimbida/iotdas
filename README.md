# Projeto Trabalho TCS - IOT DAS - Banco de Dados como Serviços para Internet das Coisas

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Repositório para aplicação distribuida IOT DAS ( Projeto TCS Senac Blumenau ADS 2015 )

## Compilar Projeto

```shell
$ cd pasta_projeto
$ mvn -e clean package
```

## Compilar Módulo em Especifico

###### Ex.: spring-iot-usuario-server
```shell
$ cd pasta_projeto
$ mvn install -pl spring-iotdas-usuario-server -am
```

## Rodar Projeto

Para executar primeiramente temos que ter o SERVIÇO de CONFIGURAÇÃO rodando, é dele que os outros serviços buscas as configurações.

###### spring-iot-config-server
```shell
$ cd pasta_projeto
java -jar spring-iotdas-config-server\target\spring-iotdas-config-server-1.5.1.jar
```

Depois de subir o SERVIÇO de CONFIGURAÇÃO é preciso executar o SERVIÇO DE REGISTRO, ( Serviço de Descoberta, ou Eureka Server), é nele que os demais serviços se registrarão.

###### spring-iot-discovery-server
```shell
$ cd pasta_projeto
java -jar spring-iotdas-discovery-server\target\spring-iotdas-discovery-server-1.5.1.jar
```

###### spring-iot-tracing-server
```shell
$ cd pasta_projeto
java -jar spring-iotdas-tracing-server\target\spring-iotdas-tracing-server-1.5.1.jar
```

###### spring-iot-admin-server
```shell
$ cd pasta_projeto
 java -jar spring-iotdas-admin-server\target\spring-iotdas-admin-server-1.5.1.jar
```
