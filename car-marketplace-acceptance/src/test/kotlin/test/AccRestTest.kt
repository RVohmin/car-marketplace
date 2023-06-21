package ru.vohmin.marketplace.blackbox.test

import fixture.client.RestClient
import io.kotest.core.annotation.Ignored
import ru.vohmin.marketplace.blackbox.docker.WiremockDockerCompose
import ru.vohmin.marketplace.blackbox.fixture.BaseFunSpec
import ru.vohmin.marketplace.blackbox.fixture.docker.DockerCompose

@Ignored
open class AccRestTestBase(dockerCompose: DockerCompose) : BaseFunSpec(dockerCompose, {
    val client = RestClient(dockerCompose)

    testApiV1(client)
})
class AccRestWiremockTest : AccRestTestBase(WiremockDockerCompose)
// TODO class AccRestSpringTest : AccRestTestBase(SpringDockerCompose)
// TODO class AccRestKtorTest : AccRestTestBase(KtorDockerCompose)
