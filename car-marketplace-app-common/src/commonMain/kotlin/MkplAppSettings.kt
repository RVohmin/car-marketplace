package ru.vohmin.marketplace.app.common

import ru.vohmin.marketplace.biz.MkplAdProcessor
import ru.vohmin.marketplace.logging.common.MpLoggerProvider

data class MkplAppSettings(
    var appUrls: List<String> = emptyList(),
    var processor: MkplAdProcessor = MkplAdProcessor(),
    var logger: MpLoggerProvider
)
