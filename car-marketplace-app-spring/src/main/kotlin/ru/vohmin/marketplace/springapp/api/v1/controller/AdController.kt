package ru.vohmin.marketplace.springapp.api.v1.controller

import org.springframework.web.bind.annotation.*
import ru.vohmin.api.v1.models.*
import ru.vohmin.marketplace.app.common.MkplAppSettings
import ru.vohmin.marketplace.mappers.v1.*


@RestController
@RequestMapping("v1/ad")
class AdController(
    private val appSettings: MkplAppSettings
) {
    private val logger by lazy { appSettings.logger.logger(AdController::class) }
    @PostMapping("create")
    suspend fun createAd(@RequestBody request: AdCreateRequest): AdCreateResponse =
        processV1(appSettings, request, logger, "ad-create")

    @PostMapping("read")
    suspend fun  readAd(@RequestBody request: AdReadRequest): AdReadResponse =
        processV1(appSettings, request, logger, "ad-read")

    @RequestMapping("update", method = [RequestMethod.POST])
    suspend fun  updateAd(@RequestBody request: AdUpdateRequest): AdUpdateResponse =
        processV1(appSettings, request, logger, "ad-update")

    @PostMapping("delete")
    suspend fun  deleteAd(@RequestBody request: AdDeleteRequest): AdDeleteResponse =
        processV1(appSettings, request, logger, "ad-delete")

    @PostMapping("search")
    suspend fun  searchAd(@RequestBody request: AdSearchRequest): AdSearchResponse =
        processV1(appSettings, request, logger, "ad-search")
}
