package ru.vohmin.marketplace.biz.groups

import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.models.MkplState
import ru.vohmin.marketplace.common.models.MkplWorkMode
import ru.vohmin.marketplace.cor.ICorChainDsl
import ru.vohmin.marketplace.cor.chain

fun ICorChainDsl<MkplContext>.stubs(title: String, block: ICorChainDsl<MkplContext>.() -> Unit) = chain {
    block()
    this.title = title
    on { workMode == MkplWorkMode.STUB && state == MkplState.RUNNING }
}
