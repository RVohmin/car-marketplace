package ru.vohmin.marketplace.biz.validation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.models.MkplAdFilter
import ru.vohmin.marketplace.common.models.MkplCommand
import ru.vohmin.marketplace.common.models.MkplState
import ru.vohmin.marketplace.common.models.MkplWorkMode
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@OptIn(ExperimentalCoroutinesApi::class)
class BizValidationSearchTest {

    private val command = MkplCommand.SEARCH
    private val processor by lazy { ru.vohmin.marketplace.biz.MkplAdProcessor() }

    @Test
    fun correctEmpty() = runTest {
        val ctx = MkplContext(
            command = command,
            state = MkplState.NONE,
            workMode = MkplWorkMode.TEST,
            adFilterRequest = MkplAdFilter()
        )
        processor.exec(ctx)
        assertEquals(0, ctx.errors.size)
        assertNotEquals(MkplState.FAILING, ctx.state)
    }
}

