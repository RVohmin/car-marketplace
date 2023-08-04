package ru.vohmin.marketplace.blackbox.test.action.v1

import io.kotest.assertions.asClue
import io.kotest.assertions.withClue
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import ru.vohmin.api.v1.models.AdResponseObject
import ru.vohmin.api.v1.models.AdUpdateObject
import ru.vohmin.api.v1.models.AdUpdateRequest
import ru.vohmin.api.v1.models.AdUpdateResponse
import ru.vohmin.marketplace.blackbox.fixture.client.Client
import ru.vohmin.marketplace.blackbox.test.action.beValidId
import ru.vohmin.marketplace.blackbox.test.action.beValidLock

suspend fun Client.updateAd(id: String?, lock: String?, ad: AdUpdateObject): AdResponseObject =
    updateAd(id, lock, ad) {
        it should haveSuccessResult
        it.ad shouldNotBe null
        it.ad?.apply {
            if (ad.title != null)
                title shouldBe ad.title
            if (ad.description != null)
                description shouldBe ad.description
            if (ad.adType != null)
                adType shouldBe ad.adType
            if (ad.visibility != null)
                visibility shouldBe ad.visibility
        }
        it.ad!!
    }

suspend fun <T> Client.updateAd(id: String?, lock: String?, ad: AdUpdateObject, block: (AdUpdateResponse) -> T): T =
    withClue("updatedV1: $id, lock: $lock, set: $ad") {
        id should beValidId
        lock should beValidLock

        val response = sendAndReceive(
            "ad/update", AdUpdateRequest(
                requestType = "update",
                debug = debug,
                ad = ad.copy(id = id, lock = lock)
            )
        ) as AdUpdateResponse

        response.asClue(block)
    }
