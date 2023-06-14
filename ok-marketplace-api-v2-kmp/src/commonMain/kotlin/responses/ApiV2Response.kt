package ru.vohmin.marketplace.api.v2.responses

import ru.vohmin.marketplace.api.v2.AdResponseSerializer
import ru.vohmin.marketplace.api.v2.apiV2Mapper
import ru.vohmin.marketplace.api.v2.models.IResponse

fun apiV2ResponseSerialize(Response: IResponse): String = apiV2Mapper.encodeToString(AdResponseSerializer, Response)

@Suppress("UNCHECKED_CAST")
fun <T : Any> apiV2ResponseDeserialize(json: String): T = apiV2Mapper.decodeFromString(AdResponseSerializer, json) as T
