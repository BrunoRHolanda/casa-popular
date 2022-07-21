
MS_POPULAR_HOME=ms-popular-home
MS_CATALOG=ms-benefited-catalog
install:
	docker-compose build
	docker-compose up -d
	docker-compose exec $(MS_POPULAR_HOME) sh -c "chmod a+x mvnw"
	docker-compose exec $(MS_CATALOG) sh -c "chmod a+x mvnw"
	docker-compose exec $(MS_POPULAR_HOME) sh -c "mkdir ../.m2"
	docker-compose exec $(MS_POPULAR_HOME) sh -c "./mvnw clean install -Dmaven.repo.local=/home/spring/desafio-digix-casa-popular/.m2"
	docker-compose exec $(MS_CATALOG) sh -c "./mvnw clean install -Dmaven.repo.local=/home/spring/desafio-digix-casa-popular/.m2"
	docker-compose stop

m2-install-home:
	docker-compose exec $(MS_POPULAR_HOME) sh -c "./mvnw clean install -Dmaven.repo.local=/home/spring/desafio-digix-casa-popular/.m2"

m2-install-catalog:
	docker-compose exec $(MS_CATALOG) sh -c "./mvnw clean install -Dmaven.repo.local=/home/spring/desafio-digix-casa-popular/.m2"

start-home:
	docker-compose exec $(MS_POPULAR_HOME) sh -c "./mvnw spring-boot:run"

start-catalog:
	docker-compose exec $(MS_CATALOG) sh -c "./mvnw spring-boot:run"

bash-home:
	docker-compose exec $(MS_POPULAR_HOME) bash

bash-catalog:
	docker-compose exec $(MS_CATALOG) bash