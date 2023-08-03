package ru.vohmin.marketplace.app.kafka

import ru.vohmin.marketplace.app.common.MkplAppSettings
import ru.vohmin.marketplace.logging.common.MpLoggerProvider
import ru.vohmin.marketplace.logging.jvm.mpLoggerLogback

private val loggerProvider = MpLoggerProvider { mpLoggerLogback(it) }

val corSettings = MkplAppSettings(
    logger = loggerProvider
)