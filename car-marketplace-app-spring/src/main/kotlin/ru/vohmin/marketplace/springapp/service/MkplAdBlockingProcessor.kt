package ru.vohmin.marketplace.springapp.service

import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import ru.vohmin.marketplace.biz.MkplAdProcessor
import ru.vohmin.marketplace.common.MkplContext

@Service
class MkplAdBlockingProcessor {
    private val processor = MkplAdProcessor()

    fun exec(ctx: MkplContext) = runBlocking { processor.exec(ctx) }
}