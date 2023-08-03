package ru.vohmin.marketplace.biz.validation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.vohmin.marketplace.common.models.MkplCommand
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BizValidationOffersTest {

    private val command = MkplCommand.OFFERS
    private val processor by lazy { ru.vohmin.marketplace.biz.MkplAdProcessor() }

    @Test fun correctId() = validationIdCorrect(command, processor)
    @Test fun trimId() = validationIdTrim(command, processor)
    @Test fun emptyId() = validationIdEmpty(command, processor)
    @Test fun badFormatId() = validationIdFormat(command, processor)

}

