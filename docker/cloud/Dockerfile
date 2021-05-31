FROM maven:3.8.1-openjdk-16-slim AS build

COPY ./server/src /home/code/src
COPY ./server/pom.xml /home/code

RUN mvn -f /home/code/pom.xml package

FROM openjdk:16-slim-buster

ENV BUILD_ONLY_PACKAGES='wget' \
  DOCKERIZE_VERSION=v0.6.1 \
  TINI_VERSION=v0.19.0

RUN apt-get update && apt-get upgrade -y \
  && apt-get install --no-install-recommends -y \
    $BUILD_ONLY_PACKAGES \
  && wget "https://github.com/jwilder/dockerize/releases/download/${DOCKERIZE_VERSION}/dockerize-linux-amd64-${DOCKERIZE_VERSION}.tar.gz" \
  && tar -C /usr/local/bin -xzvf "dockerize-linux-amd64-${DOCKERIZE_VERSION}.tar.gz" \
  && rm "dockerize-linux-amd64-${DOCKERIZE_VERSION}.tar.gz" && dockerize --version \
  && wget -O /usr/local/bin/tini "https://github.com/krallin/tini/releases/download/${TINI_VERSION}/tini" \
  && chmod +x /usr/local/bin/tini && tini --version \
  && apt-get remove -y $BUILD_ONLY_PACKAGES \
  && apt-get purge -y --auto-remove -o APT::AutoRemove::RecommendsImportant=false \
  && apt-get clean -y && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY ./docker/cloud/entrypoint.sh /docker-entrypoint.sh

RUN chmod +x '/docker-entrypoint.sh' \
  && groupadd -r ende && useradd -d /app -r -g ende ende \
  && chown ende:ende -R /app \
  && mkdir -p /data \
  && chown ende:ende /data

COPY --from=build /home/code/target/server-1.0-SNAPSHOT.jar /app/server.jar

USER ende

ENTRYPOINT ["tini", "--", "/docker-entrypoint.sh"]