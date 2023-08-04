package ru.vohmin.marketplace.biz.validation

import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.helpers.errorValidation
import ru.vohmin.marketplace.common.helpers.fail
import ru.vohmin.marketplace.cor.ICorChainDsl
import ru.vohmin.marketplace.cor.worker

fun ICorChainDsl<MkplContext>.validateDescriptionNotEmpty(title: String) = worker {
    this.title = title
    on { adValidating.description.isEmpty() }
    handle {
        fail(
            errorValidation(
                field = "description",
                violationCode = "empty",
                description = "field must not be empty"
            )
        )
    }
}
