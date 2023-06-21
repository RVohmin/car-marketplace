package ru.vohmin.marketplace.springapp.api.v1.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.vohmin.api.v1.models.AdOffersRequest
import ru.vohmin.api.v1.models.AdOffersResponse
import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.mappers.v1.fromTransport
import ru.vohmin.marketplace.mappers.v1.toTransportOffers
import ru.vohmin.marketplace.springapp.service.MkplAdBlockingProcessor

@RestController
@RequestMapping("v1/ad")
class OfferController(private val processor: MkplAdBlockingProcessor) {

    @PostMapping("offers")
    fun searchOffers(@RequestBody request: AdOffersRequest): AdOffersResponse {
        val context = MkplContext()
        context.fromTransport(request)
        processor.exec(context)
        return context.toTransportOffers()
    }
}
