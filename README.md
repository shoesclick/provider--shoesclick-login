# PROVIDER SITE SHOES LOGIN

Projeto conceito para utilização do Keycloak - Provedor de Autenticação

## Iniciando o Projeto

O projeto contém alguns exemplos de serviço REST. O projeto já está configurado em modo standalone

### Pré requisitos

```
Java 17 : ([https://www.oracle.com/br/java/technologies/javase/jdk17-archive-downloads.html]);

Spring Boot na versão 3.1.9:  (https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-dependencies/3.1.9);

Maven 3.9.5: (https://dlcdn.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.tar.gz)

Eclipse ou Intellij

Docker: Para Rodar o arquivo docker-compose (Contido no projeto principal) que contém a imagem do mysql para facilitar a integração

```

## Rodando os Testes

Utilize o postman Para rodar os testes.

Collection estão na raiz do repositório.


```
Shoes.click.postman_collection.json
```

## Rodando local

Rode o Comando do Docker file:

```
 docker build -t provider--shoesckick-login .
```

Apos a compilação rode o docker compose contido no projeto:

```
 docker-compose up
```

Rode o keycloak com a seguinte url:

```
http://localhost:7080/auth/admin
```


## Observação

Este projeto é um provedor de autenticação keycloak. Também estará contido os temas.

## Rodando no kubernetes (Stack)

No arquivo k8s/configMap.yaml, definir o nome do host:

```
KC_HOSTNAME: "svc-login.ns-login.svc.cluster.local"
```

Crie um Realm chamado "shoesclick-site"

![alt text](https://github.com/shoesclick/provider--shoesclick-login/blob/master/img/CreateRealm.jpg?raw=true)

Crie um client id chamado "site--shoesclick"

![alt text](https://github.com/shoesclick/provider--shoesclick-login/blob/master/img/CreateClient.jpg?raw=true)

Habilite as opções "Client authentication" e "Authorization"

![alt text](https://github.com/shoesclick/provider--shoesclick-login/blob/master/img/ClientAuthentication.jpg?raw=true)

Crie uma Role chamada "SITE_SHOES"

![alt text](https://github.com/shoesclick/provider--shoesclick-login/blob/master/img/CreateRole.jpg?raw=true)

Agora Associe a Role ao cliente criado.

![alt text](https://github.com/shoesclick/provider--shoesclick-login/blob/master/img/AssociateClientRealm.jpg?raw=true)


## Autores

* **Clayton Morais de Oliveira**
