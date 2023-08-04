package ru.vohmin.marketplace.biz.validation

import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.helpers.errorValidation
import ru.vohmin.marketplace.common.helpers.fail
import ru.vohmin.marketplace.cor.ICorChainDsl
import ru.vohmin.marketplace.cor.worker

fun ICorChainDsl<MkplContext>.validateTitleHasContent(title: String) = worker {
    this.title = title
    val regExp = Regex("\\p{L}")
    on { adValidating.title.isNotEmpty() && !adValidating.title.contains(regExp) }
    handle {
        fail(
            errorValidation(
                field = "title",
                violationCode = "noContent",
                description = "field must contain leters"
            )
        )
    }
}
