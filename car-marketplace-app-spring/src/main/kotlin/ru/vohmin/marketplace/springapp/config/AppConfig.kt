package ru.vohmin.marketplace.springapp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.vohmin.marketplace.app.common.MkplAppSettings
import ru.vohmin.marketplace.biz.MkplAdProcessor
import ru.vohmin.marketplace.logging.common.MpLoggerProvider
import ru.vohmin.marketplace.logging.jvm.mpLoggerLogback

@Configuration
class AppConfig {
    @Bean
    fun loggerProvider(): MpLoggerProvider = MpLoggerProvider { mpLoggerLogback(it) }

    @Bean
    fun processor() = MkplAdProcessor()

    @Bean
    fun appSettings() = MkplAppSettings(
        processor = processor(),
        logger = loggerProvider(),
    )
}
