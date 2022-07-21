FROM openjdk:17-buster

ENV TZ=UTC
ENV DOCKERIZE_VERSION v0.6.1

RUN apt-get install wget -y

RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

COPY entrypoint.sh /entrypoint.sh

RUN chmod a+x /entrypoint.sh

RUN useradd -ms /bin/bash spring
RUN usermod -u 1000 spring

WORKDIR /home/spring/desafio-digix-casa-popular/ms-benefited-catalog

USER spring
