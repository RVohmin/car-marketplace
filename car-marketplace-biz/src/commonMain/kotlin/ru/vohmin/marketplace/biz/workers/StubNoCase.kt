package ru.vohmin.marketplace.biz.workers

import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.helpers.fail
import ru.vohmin.marketplace.common.models.MkplError
import ru.vohmin.marketplace.common.models.MkplState
import ru.vohmin.marketplace.cor.ICorChainDsl
import ru.vohmin.marketplace.cor.worker

fun ICorChainDsl<MkplContext>.stubNoCase(title: String) = worker {
    this.title = title
    on { state == MkplState.RUNNING }
    handle {
        fail(
            MkplError(
                code = "validation",
                field = "stub",
                group = "validation",
                message = "Wrong stub case is requested: ${stubCase.name}"
            )
        )
    }
}
