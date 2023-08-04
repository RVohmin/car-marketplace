package ru.vohmin.marketplace.biz.workers

import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.models.MkplState
import ru.vohmin.marketplace.cor.ICorChainDsl
import ru.vohmin.marketplace.cor.worker

fun ICorChainDsl<MkplContext>.initStatus(title: String) = worker() {
    this.title = title
    on { state == MkplState.NONE }
    handle { state = MkplState.RUNNING }
}
