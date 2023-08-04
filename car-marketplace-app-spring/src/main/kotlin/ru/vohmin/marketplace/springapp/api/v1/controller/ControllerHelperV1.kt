package ru.vohmin.marketplace.springapp.api.v1.controller

import ru.vohmin.api.v1.models.IRequest
import ru.vohmin.api.v1.models.IResponse
import ru.vohmin.marketplace.api.logs.mapper.toLog
import ru.vohmin.marketplace.app.common.MkplAppSettings
import ru.vohmin.marketplace.app.common.process
import ru.vohmin.marketplace.logging.common.IMpLogWrapper
import ru.vohmin.marketplace.mappers.v1.fromTransport
import ru.vohmin.marketplace.mappers.v1.toTransportAd

suspend inline fun <reified Q : IRequest, reified R : IResponse> processV1(
    appSettings: MkplAppSettings,
    request: Q,
    logger: IMpLogWrapper,
    logId: String,
): R  = appSettings.processor.process(logger, logId,
        fromTransport = { fromTransport(request) },
        sendResponse = { toTransportAd() as R },
        toLog = { toLog("spring") }
    )
