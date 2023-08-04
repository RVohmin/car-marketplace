package ru.vohmin.marketplace.biz.workers

import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.models.MkplError
import ru.vohmin.marketplace.common.models.MkplState
import ru.vohmin.marketplace.common.stubs.MkplStubs
import ru.vohmin.marketplace.cor.ICorChainDsl
import ru.vohmin.marketplace.cor.worker

fun ICorChainDsl<MkplContext>.stubDbError(title: String) = worker {
    this.title = title
    on { stubCase == MkplStubs.DB_ERROR && state == MkplState.RUNNING }
    handle {
        state = MkplState.FAILING
        this.errors.add(
            MkplError(
                group = "internal",
                code = "internal-db",
                message = "Internal error"
            )
        )
    }
}
