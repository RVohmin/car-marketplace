package ru.vohmin.marketplace.common.models

data class MkplAdFilter(
    var searchString: String = "",
    var ownerId: MkplUserId = MkplUserId.NONE,
    var dealSide: MkplDealSide = MkplDealSide.NONE,
)
