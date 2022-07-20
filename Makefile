
MS_POPULAR_HOME=ms-popular-home

install:
	docker-compose build
	docker-compose up -d
	docker-compose exec $(MS_POPULAR_HOME) sh -c "chmod a+x mvnw"
	docker-compose exec $(MS_POPULAR_HOME) sh -c "mkdir ../.m2"
	docker-compose exec $(MS_POPULAR_HOME) sh -c "./mvnw clean install -Dmaven.repo.local=/home/spring/desafio-digix-casa-popular/.m2"
	docker-compose stop

m2-install:
	docker-compose exec $(MS_POPULAR_HOME) sh -c "./mvnw clean install -Dmaven.repo.local=/home/spring/desafio-digix-casa-popular/.m2"

start:
	docker-compose exec $(MS_POPULAR_HOME) sh -c "./mvnw spring-boot:run"

bash:
	docker-compose exec $(MS_POPULAR_HOME) bash