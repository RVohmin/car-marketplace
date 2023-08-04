package ru.vohmin.marketplace.biz.validation

import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.models.MkplState
import ru.vohmin.marketplace.cor.ICorChainDsl
import ru.vohmin.marketplace.cor.worker

fun ICorChainDsl<MkplContext>.finishAdValidation(title: String) = worker {
    this.title = title
    on { state == MkplState.RUNNING }
    handle {
        adValidated = adValidating
    }
}

fun ICorChainDsl<MkplContext>.finishAdFilterValidation(title: String) = worker {
    this.title = title
    on { state == MkplState.RUNNING }
    handle {
        adFilterValidated = adFilterValidating
    }
}
