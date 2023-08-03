package ru.vohmin.marketplace.biz.workers

import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.models.MkplAdId
import ru.vohmin.marketplace.common.models.MkplDealSide
import ru.vohmin.marketplace.common.models.MkplState
import ru.vohmin.marketplace.common.stubs.MkplStubs
import ru.vohmin.marketplace.cor.ICorChainDsl
import ru.vohmin.marketplace.cor.worker
import ru.vohmin.marketplace.stubs.MkplAdStub

fun ICorChainDsl<MkplContext>.stubOffersSuccess(title: String) = worker {
    this.title = title
    on { stubCase == MkplStubs.SUCCESS && state == MkplState.RUNNING }
    handle {
        state = MkplState.FINISHING
        adResponse = MkplAdStub.prepareResult {
            adRequest.id.takeIf { it != MkplAdId.NONE }?.also { this.id = it }
        }
        adsResponse.addAll(MkplAdStub.prepareOffersList(adResponse.title, MkplDealSide.SUPPLY))
    }
}
