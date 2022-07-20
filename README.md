# Desafio Digix Casa Popular
Seleção de famílias aptas a ganharem uma casa popular.

Conta com 2 microserviços um para manter os cadastros das famílias (ms-popular-home - Java 17)e outro para listagem ordenada por pontuação das famílias (ms-catalog - Java 17).

Para centralizar as requisições também foi criado um API Gateway em nodejs.

Já para agilizar as requisições de listagem foi utilizado o redis como banco de cache.

E para observabilidade e auditoria foi implantando uma integração com o newrelic.
### Requisitos:
 - Linux native or Linux wsl2 (stable)
 - Bash ou Git Bash
 - Docker
 - Make

## Getting started

```bash
make install
```
```bash
docker-compose up -d
```
```bash
make start
```

Configure seu ide ou editor de preferência para apontar o maven repositório para a pasta .m2 na raiz do projeto

### Instalar novas dependências no MS Popular Home
```bash
make home-m2-install
```

### Instalar novas dependências no MS Catalog
```bash
make catalog-m2-install
```

### Project Links:

[ms_popular_home](http://localhost:8100) <br />
[ms_catalog](http://localhost:8105) <br />
[gateway_casa_popular](http://localhost:8000) <br />
[pgadmin](http://localhost:8102)

### Project Services:
| Service         | Port | Status        |
|-----------------|------|---------------|
| MS Popular Home | 8000 | Desenvolvido  |
| Postgres        | 8101 | Desenvolvido |
| Pgadmin         | 8102 | Desenvolvido |
| Redis           | 8103 | Desenvolvido |
| MongoDB         | 8104 | Não desenvolvido |
| MS Catalog      | 8105 | Não desenvolvido |
| Api Gateway     | 8000 | Não desenvolvido |

### Diagramas:
