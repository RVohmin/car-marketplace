package ru.vohmin.marketplace.app.common

import kotlinx.datetime.Clock
import ru.vohmin.marketplace.biz.MkplAdProcessor
import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.helpers.asMkplError
import ru.vohmin.marketplace.common.helpers.fail
import ru.vohmin.marketplace.common.models.MkplCommand
import ru.vohmin.marketplace.logging.common.IMpLogWrapper

suspend fun <T> MkplAdProcessor.process(
    logger: IMpLogWrapper,
    logId: String,
    fromTransport: suspend MkplContext.() -> Unit,
    sendResponse: suspend MkplContext.() -> T,
    toLog: MkplContext.(logId: String) -> Any): T {
    var command = MkplCommand.NONE
    return try {
        val ctx = MkplContext(
            timeStart = Clock.System.now(),
        )

        logger.doWithLogging(id = logId) {
            ctx.fromTransport()
            command = ctx.command

            logger.info(
                msg = "$command request is got",
                data = ctx.toLog("${logId}-got")
            )
            exec(ctx)
            logger.info(
                msg = "$command request is handled",
                data = ctx.toLog("${logId}-handled")
            )
            ctx.sendResponse()
        }
    } catch (e: Throwable) {
        logger.doWithLogging(id = "${logId}-failure") {
            logger.error(msg = "$command handling failed")

            val ctx = MkplContext(
                timeStart = Clock.System.now(),
                command = command
            )

            ctx.fail(e.asMkplError())
            exec(ctx)
            sendResponse(ctx)
        }
    }
}
