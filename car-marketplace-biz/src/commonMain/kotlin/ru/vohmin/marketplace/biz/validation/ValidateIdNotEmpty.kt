package ru.vohmin.marketplace.biz.validation

import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.helpers.errorValidation
import ru.vohmin.marketplace.common.helpers.fail
import ru.vohmin.marketplace.cor.ICorChainDsl
import ru.vohmin.marketplace.cor.worker

fun ICorChainDsl<MkplContext>.validateIdNotEmpty(title: String) = worker {
    this.title = title
    on { adValidating.id.asString().isEmpty() }
    handle {
        fail(
            errorValidation(
                field = "id",
                violationCode = "empty",
                description = "field must not be empty"
            )
        )
    }
}
