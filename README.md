# Star Wars SN

## Descrição

- Projeto simples de uma "Rede Social" de star wars
- Permite negociação de items
- Permite reportar localização
- Permite reportar traidores

## Tecnologias
- O projeto foi feito usando `spring boot`
- Banco de dados `h2 em memória`
  - O banco sobe automaticamente junto com a aplicação, não sendo necessário nenhuma configuração adicional
- Também foi utilizado `liquibase` para controle das versões.
- `Maven` para o gerenciamento de dependências

## Documentação

- Existe uma pequena documentação feita com o uso do `swagger` para o uso das apis.
- Para acessar digite `http://localhost:8080/swagger-ui.html` no navegador (com o projeto rodando)


## Rodando o projeto

- Clone este repositório
- Rode normalmente o main pela IDE escolhida 
- Para rodar em linha de comando, execute um `mvn clean install`
  - Em seguida acesse a pasta `target` que foi gerada
  - E rode o comando abaixo:

```shell
java -jar Star-Wars-Rebel-s-Network-0.0.1-SNAPSHOT.jar
```

## Testando

- Projeto conta com uma `collection` do `postman` para teste das APIs
- Basta importar no `Postman` o arquivo `Star Wars.postman_collection.json` que está na raiz deste projeto.
