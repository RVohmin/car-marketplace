package ru.vohmin.marketplace.api.v2.requests

import kotlinx.serialization.KSerializer
import ru.vohmin.marketplace.api.v2.models.AdInitResponse
import ru.vohmin.marketplace.api.v2.models.IResponse
import kotlin.reflect.KClass

object InitResponseStrategy: IResponseStrategy {
    override val discriminator: String = "init"
    override val clazz: KClass<out IResponse> = AdInitResponse::class
    override val serializer: KSerializer<out IResponse> = AdInitResponse.serializer()
    override fun <T : IResponse> fillDiscriminator(req: T): T {
        require(req is AdInitResponse)
        @Suppress("UNCHECKED_CAST")
        return req.copy(responseType = discriminator) as T
    }
}
