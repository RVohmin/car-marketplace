package ru.vohmin.marketplace.blackbox.docker

object WiremockDockerCompose : AbstractDockerCompose(
    "app-wiremock_1", 8080, "wiremock/docker-compose-wiremock.yml"
)