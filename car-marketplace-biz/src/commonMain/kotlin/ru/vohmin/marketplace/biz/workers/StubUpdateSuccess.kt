package ru.vohmin.marketplace.biz.workers

import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.models.MkplAdId
import ru.vohmin.marketplace.common.models.MkplDealSide
import ru.vohmin.marketplace.common.models.MkplState
import ru.vohmin.marketplace.common.models.MkplVisibility
import ru.vohmin.marketplace.common.stubs.MkplStubs
import ru.vohmin.marketplace.cor.ICorChainDsl
import ru.vohmin.marketplace.cor.worker
import ru.vohmin.marketplace.stubs.MkplAdStub

fun ICorChainDsl<MkplContext>.stubUpdateSuccess(title: String) = worker {
    this.title = title
    on { stubCase == MkplStubs.SUCCESS && state == MkplState.RUNNING }
    handle {
        state = MkplState.FINISHING
        val stub = MkplAdStub.prepareResult {
            adRequest.id.takeIf { it != MkplAdId.NONE }?.also { this.id = it }
            adRequest.title.takeIf { it.isNotBlank() }?.also { this.title = it }
            adRequest.description.takeIf { it.isNotBlank() }?.also { this.description = it }
            adRequest.adType.takeIf { it != MkplDealSide.NONE }?.also { this.adType = it }
            adRequest.visibility.takeIf { it != MkplVisibility.NONE }?.also { this.visibility = it }
        }
        adResponse = stub
    }
}
