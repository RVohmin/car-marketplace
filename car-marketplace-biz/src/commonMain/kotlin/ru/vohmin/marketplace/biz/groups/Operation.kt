package ru.vohmin.marketplace.biz.groups

import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.models.MkplCommand
import ru.vohmin.marketplace.common.models.MkplState
import ru.vohmin.marketplace.cor.ICorChainDsl
import ru.vohmin.marketplace.cor.chain

fun ICorChainDsl<MkplContext>.operation(title: String, command: MkplCommand, block: ICorChainDsl<MkplContext>.() -> Unit) = chain {
    block()
    this.title = title
    on { this.command == command && state == MkplState.RUNNING }
}
