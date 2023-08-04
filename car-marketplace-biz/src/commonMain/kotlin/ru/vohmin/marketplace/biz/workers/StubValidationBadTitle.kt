package ru.vohmin.marketplace.biz.workers

import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.models.MkplError
import ru.vohmin.marketplace.common.models.MkplState
import ru.vohmin.marketplace.common.stubs.MkplStubs
import ru.vohmin.marketplace.cor.ICorChainDsl
import ru.vohmin.marketplace.cor.worker

fun ICorChainDsl<MkplContext>.stubValidationBadTitle(title: String) = worker {
    this.title = title
    on { stubCase == MkplStubs.BAD_TITLE && state == MkplState.RUNNING }
    handle {
        state = MkplState.FAILING
        this.errors.add(
            MkplError(
                group = "validation",
                code = "validation-title",
                field = "title",
                message = "Wrong title field"
            )
        )
    }
}
