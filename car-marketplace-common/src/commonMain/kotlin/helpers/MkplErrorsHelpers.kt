package ru.vohmin.marketplace.common.helpers

import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.models.MkplError
import ru.vohmin.marketplace.common.models.MkplState

fun Throwable.asMkplError(
    code: String = "unknown",
    group: String = "exceptions",
    message: String = this.message ?: "",
) = MkplError(
    code = code,
    group = group,
    field = "",
    message = message,
    exception = this,
)

fun MkplContext.addError(vararg error: MkplError) = errors.addAll(error)

fun MkplContext.fail(error: MkplError) {
    addError(error)
    state = MkplState.FAILING
}

fun errorValidation(
    field: String,
    /**
     * Код, характеризующий ошибку. Не должен включать имя поля или указание на валидацию.
     * Например: empty, badSymbols, tooLong, etc
     */
    violationCode: String,
    description: String,
    level: MkplError.Level = MkplError.Level.ERROR,
) = MkplError(
    code = "validation-$field-$violationCode",
    field = field,
    group = "validation",
    message = "Validation error for field $field: $description",
    level = level,
)
