package ru.vohmin.marketplace.blackbox.test.action.v1

import io.kotest.assertions.asClue
import io.kotest.assertions.withClue
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import ru.vohmin.api.v1.models.AdDeleteObject
import ru.vohmin.api.v1.models.AdDeleteRequest
import ru.vohmin.api.v1.models.AdDeleteResponse
import ru.vohmin.marketplace.blackbox.fixture.client.Client
import ru.vohmin.marketplace.blackbox.test.action.beValidId
import ru.vohmin.marketplace.blackbox.test.action.beValidLock

suspend fun Client.deleteAd(id: String?, lock: String?) {
    withClue("deleteAdV1: $id, lock: $lock") {
        id should beValidId
        lock should beValidLock

        val response = sendAndReceive(
            "ad/delete",
            AdDeleteRequest(
                requestType = "delete",
                debug = debug,
                ad = AdDeleteObject(id = id, lock = lock)
            )
        ) as AdDeleteResponse

        response.asClue {
            response should haveSuccessResult
            response.ad shouldNotBe null
            response.ad?.id shouldBe id
        }
    }
}