FROM openjdk:latest

ENV BUILD_ONLY_PACKAGES='wget' \
  DOCKERIZE_VERSION=v0.6.1

RUN apt-get update && apt-get upgrade -y \
  && apt-get install --no-install-recommends -y \
    $BUILD_ONLY_PACKAGES \
  && wget "https://github.com/jwilder/dockerize/releases/download/${DOCKERIZE_VERSION}/dockerize-linux-amd64-${DOCKERIZE_VERSION}.tar.gz" \
  && tar -C /usr/local/bin -xzvf "dockerize-linux-amd64-${DOCKERIZE_VERSION}.tar.gz" \
  && rm "dockerize-linux-amd64-${DOCKERIZE_VERSION}.tar.gz" && dockerize --version \
  && apt-get remove -y $BUILD_ONLY_PACKAGES \
  && apt-get purge -y --auto-remove -o APT::AutoRemove::RecommendsImportant=false \
  && apt-get clean -y && rm -rf /var/lib/apt/lists/*

WORKDIR /code

COPY ./docker/cloud/entrypoint.sh /docker-entrypoint.sh
COPY ./docker/cloud/build.sh /build.sh

RUN chmod +x '/docker-entrypoint.sh' \
  && chmod +x '/build.sh' \
  && groupadd -r ende && useradd -d /code -r -g ende ende \
  && chown ende:ende -R /code \
  && mkdir -p /data \
  && chown ende:ende /data

USER ende

RUN ["tini", "--", "/build.sh"]

ENTRYPOINT ["tini", "--", "/docker-entrypoint.sh"]