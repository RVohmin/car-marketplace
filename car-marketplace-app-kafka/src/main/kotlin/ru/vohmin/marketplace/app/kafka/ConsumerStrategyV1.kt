package ru.vohmin.marketplace.app.kafka

import ru.vohmin.api.v1.models.IRequest
import ru.vohmin.api.v1.models.IResponse
import ru.vohmin.marketplace.api.v1.apiV1RequestDeserialize
import ru.vohmin.marketplace.api.v1.apiV1ResponseSerialize
import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.mappers.v1.fromTransport
import ru.vohmin.marketplace.mappers.v1.toTransportAd

class ConsumerStrategyV1 : ConsumerStrategy {
    override fun topics(config: AppKafkaConfig): InputOutputTopics {
        return InputOutputTopics(config.kafkaTopicInV1, config.kafkaTopicOutV1)
    }

    override fun serialize(source: MkplContext): String {
        val response: IResponse = source.toTransportAd()
        return apiV1ResponseSerialize(response)
    }

    override fun deserialize(value: String, target: MkplContext) {
        val request: IRequest = apiV1RequestDeserialize(value)
        target.fromTransport(request)
    }
}