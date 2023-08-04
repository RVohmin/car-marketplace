package ru.vohmin.marketplace.blackbox.test.action.v1

import io.kotest.assertions.asClue
import io.kotest.assertions.withClue
import io.kotest.matchers.should
import ru.vohmin.api.v1.models.AdOffersRequest
import ru.vohmin.api.v1.models.AdOffersResponse
import ru.vohmin.api.v1.models.AdReadObject
import ru.vohmin.api.v1.models.AdResponseObject
import ru.vohmin.marketplace.blackbox.fixture.client.Client

suspend fun Client.offersAd(id: String?): List<AdResponseObject> = offersAd(id) {
    it should haveSuccessResult
    it.ads ?: listOf()
}

suspend fun <T> Client.offersAd(id: String?, block: (AdOffersResponse) -> T): T =
    withClue("searchOffersV1: $id") {
        val response = sendAndReceive(
            "ad/offers",
            AdOffersRequest(
                requestType = "offers",
                debug = debug,
                ad = AdReadObject(id = id),
            )
        ) as AdOffersResponse

        response.asClue(block)
    }
