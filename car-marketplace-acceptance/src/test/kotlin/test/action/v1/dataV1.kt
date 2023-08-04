package ru.vohmin.marketplace.blackbox.test.action.v1

import ru.vohmin.api.v1.models.*

val debug = AdDebug(mode = AdRequestDebugMode.STUB, stub = AdRequestDebugStubs.SUCCESS)

val someCreateAd = AdCreateObject(
    title = "Требуется болт",
    description = "Требуется болт 100x5 с шестигранной шляпкой",
    adType = DealSide.DEMAND,
    visibility = AdVisibility.PUBLIC
)
