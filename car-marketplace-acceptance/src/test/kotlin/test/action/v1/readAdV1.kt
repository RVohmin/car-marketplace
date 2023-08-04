package ru.vohmin.marketplace.blackbox.test.action.v1

import io.kotest.assertions.asClue
import io.kotest.assertions.withClue
import io.kotest.matchers.should
import io.kotest.matchers.shouldNotBe
import ru.vohmin.api.v1.models.AdReadObject
import ru.vohmin.api.v1.models.AdReadRequest
import ru.vohmin.api.v1.models.AdReadResponse
import ru.vohmin.api.v1.models.AdResponseObject
import ru.vohmin.marketplace.blackbox.fixture.client.Client
import ru.vohmin.marketplace.blackbox.test.action.beValidId

suspend fun Client.readAd(id: String?): AdResponseObject = readAd(id) {
    it should haveSuccessResult
    it.ad shouldNotBe null
    it.ad!!
}

suspend fun <T> Client.readAd(id: String?, block: (AdReadResponse) -> T): T =
    withClue("readAdV1: $id") {
        id should beValidId

        val response = sendAndReceive(
            "ad/read",
            AdReadRequest(
                requestType = "read",
                debug = debug,
                ad = AdReadObject(id = id)
            )
        ) as AdReadResponse

        response.asClue(block)
    }
