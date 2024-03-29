package ru.vohmin.marketplace.biz.validation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.models.*
import ru.vohmin.marketplace.stubs.MkplAdStub
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

private val stub = MkplAdStub.get()

@OptIn(ExperimentalCoroutinesApi::class)
fun validationTitleCorrect(command: MkplCommand, processor: ru.vohmin.marketplace.biz.MkplAdProcessor) = runTest {
    val ctx = MkplContext(
        command = command,
        state = MkplState.NONE,
        workMode = MkplWorkMode.TEST,
        adRequest = MkplAd(
            id = stub.id,
            title = "abc",
            description = "abc",
            adType = MkplDealSide.DEMAND,
            visibility = MkplVisibility.VISIBLE_PUBLIC,
        ),
    )
    processor.exec(ctx)
    assertEquals(0, ctx.errors.size)
    assertNotEquals(MkplState.FAILING, ctx.state)
    assertEquals("abc", ctx.adValidated.title)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationTitleTrim(command: MkplCommand, processor: ru.vohmin.marketplace.biz.MkplAdProcessor) = runTest {
    val ctx = MkplContext(
        command = command,
        state = MkplState.NONE,
        workMode = MkplWorkMode.TEST,
        adRequest = MkplAd(
            id = stub.id,
            title = " \n\t abc \t\n ",
            description = "abc",
            adType = MkplDealSide.DEMAND,
            visibility = MkplVisibility.VISIBLE_PUBLIC,
        ),
    )
    processor.exec(ctx)
    assertEquals(0, ctx.errors.size)
    assertNotEquals(MkplState.FAILING, ctx.state)
    assertEquals("abc", ctx.adValidated.title)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationTitleEmpty(command: MkplCommand, processor: ru.vohmin.marketplace.biz.MkplAdProcessor) = runTest {
    val ctx = MkplContext(
        command = command,
        state = MkplState.NONE,
        workMode = MkplWorkMode.TEST,
        adRequest = MkplAd(
            id = stub.id,
            title = "",
            description = "abc",
            adType = MkplDealSide.DEMAND,
            visibility = MkplVisibility.VISIBLE_PUBLIC,
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(MkplState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("title", error?.field)
    assertContains(error?.message ?: "", "title")
}

@OptIn(ExperimentalCoroutinesApi::class)
fun validationTitleSymbols(command: MkplCommand, processor: ru.vohmin.marketplace.biz.MkplAdProcessor) = runTest {
    val ctx = MkplContext(
        command = command,
        state = MkplState.NONE,
        workMode = MkplWorkMode.TEST,
        adRequest = MkplAd(
            id = MkplAdId("123"),
            title = "!@#$%^&*(),.{}",
            description = "abc",
            adType = MkplDealSide.DEMAND,
            visibility = MkplVisibility.VISIBLE_PUBLIC,
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(MkplState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("title", error?.field)
    assertContains(error?.message ?: "", "title")
}
