package ru.vohmin.marketplace.biz.validation

import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.helpers.errorValidation
import ru.vohmin.marketplace.common.helpers.fail
import ru.vohmin.marketplace.cor.ICorChainDsl
import ru.vohmin.marketplace.cor.worker

// TODO-validation-4: смотрим пример COR DSL валидации
fun ICorChainDsl<MkplContext>.validateTitleNotEmpty(title: String) = worker {
    this.title = title
    on { adValidating.title.isEmpty() }
    handle {
        fail(
            errorValidation(
                field = "title",
                violationCode = "empty",
                description = "field must not be empty"
            )
        )
    }
}
