package ru.vohmin.marketplace.biz.validation

import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.models.MkplState
import ru.vohmin.marketplace.cor.ICorChainDsl
import ru.vohmin.marketplace.cor.chain

fun ICorChainDsl<MkplContext>.validation(block: ICorChainDsl<MkplContext>.() -> Unit) = chain {
    block()
    title = "Валидация"

    on { state == MkplState.RUNNING }
}
