package com.example.msorders.api

import com.amazonaws.services.sns.AmazonSNSClient
import com.amazonaws.services.sns.model.PublishRequest
import com.example.msorders.bl.OrderItemBl
import com.example.msorders.bl.SuscribeBl
import com.example.msorders.dao.OrderItem
import com.example.msorders.dto.ResponseDto
import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import io.github.resilience4j.retry.annotation.Retry
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.*

@RestController
@RequestMapping("api/v1/orders")
class SuscribeApi (private val amazonSNSClient: AmazonSNSClient, private val suscribeBl: SuscribeBl,
                   private val orderItemBl: OrderItemBl){

    private val logger: Logger = LoggerFactory.getLogger(SuscribeApi::class.java)

    private val topic_arn = "arn:aws:sns:us-east-1:939998388209:ms-sns"

    //post de suscripcion a servicio de notificaciones mediante funcion de orderitembl
    @PostMapping("/subscribe")
    @CircuitBreaker(name = "productCB", fallbackMethod = "suscribeFallback")
    @Bulkhead(name = "productBH")
    @RateLimiter(name = "productRL")
    @Retry(name = "productRT", fallbackMethod = "suscribeFallback")
    fun suscribe(@RequestBody suscribe: OrderItem): ResponseDto<Any> {
        val orderItemDto = orderItemBl.registerOrderItem(
            suscribe.productId,
            suscribe.cantidad,
            Date(),
            suscribe.precioUnitario,
            suscribe.userId
        )
        logger.info("Suscribiendo usuario con id: ${suscribe.userId}")
        val email = suscribeBl.getEmail(suscribe.userId)
        val request = PublishRequest(topic_arn, "Orden confirmada",email)
        amazonSNSClient.publish(request)
        logger.info("Usuario con id: ${suscribe.userId} suscrito")
        return ResponseDto(200, "Usuario suscrito", true)
    }
    fun suscribeFallback(
        productId: Long,
        cantidad: Int,
        fecha:Date,
        precioUnitario: BigDecimal,
        userId: String,
        throwable: Throwable
    ): ResponseDto<Any> {
        return ResponseDto(500, "Error", false)
    }

}