package ru.vohmin.marketplace.springapp.api.v1.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.vohmin.api.v1.models.AdOffersRequest
import ru.vohmin.api.v1.models.AdOffersResponse
import ru.vohmin.marketplace.app.common.MkplAppSettings

@RestController
@RequestMapping("v1/ad")
class OfferController(
    private val appSettings: MkplAppSettings
) {
    private val logger by lazy { appSettings.logger.logger(AdController::class) }

    @PostMapping("offers")
    suspend fun searchOffers(@RequestBody request: AdOffersRequest): AdOffersResponse =
        processV1(appSettings, request, logger, "ad-offers")
}
