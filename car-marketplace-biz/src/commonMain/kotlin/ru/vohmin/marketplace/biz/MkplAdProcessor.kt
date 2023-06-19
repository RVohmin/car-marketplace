package ru.vohmin.marketplace.biz

import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.stubs.MkplAdStub

class MkplAdProcessor {
    suspend fun exec(ctx: MkplContext) {
        ctx.adResponse = MkplAdStub.get()
    }
}
