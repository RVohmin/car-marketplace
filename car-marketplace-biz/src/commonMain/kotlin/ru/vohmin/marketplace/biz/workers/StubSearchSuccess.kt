package ru.vohmin.marketplace.biz.workers

import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.models.MkplDealSide
import ru.vohmin.marketplace.common.models.MkplState
import ru.vohmin.marketplace.common.stubs.MkplStubs
import ru.vohmin.marketplace.cor.ICorChainDsl
import ru.vohmin.marketplace.cor.worker
import ru.vohmin.marketplace.stubs.MkplAdStub

fun ICorChainDsl<MkplContext>.stubSearchSuccess(title: String) = worker {
    this.title = title
    on { stubCase == MkplStubs.SUCCESS && state == MkplState.RUNNING }
    handle {
        state = MkplState.FINISHING
        adsResponse.addAll(MkplAdStub.prepareSearchList(adFilterRequest.searchString, MkplDealSide.DEMAND))
    }
}
