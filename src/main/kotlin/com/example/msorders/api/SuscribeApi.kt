package com.example.msorders.api

import com.amazonaws.services.sns.AmazonSNSClient
import com.amazonaws.services.sns.model.PublishRequest
import com.example.msorders.bl.OrderItemBl
import com.example.msorders.bl.SuscribeBl
import com.example.msorders.dao.OrderItem
import com.example.msorders.dto.ResponseDto
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
    fun suscribe(@RequestBody suscribe: OrderItem): ResponseDto<Any> {
        val orderItemDto = orderItemBl.registerOrderItem(
            suscribe.cantidad,
            suscribe.precioUnitario,
            suscribe.precioTotal,
            suscribe.productId,
            suscribe.userId
        )
        logger.info("Suscribiendo usuario con id: ${suscribe.userId}")
        val email = suscribeBl.getEmail(suscribe.userId)
        val request = PublishRequest(topic_arn, "Orden confirmada",email)
        amazonSNSClient.publish(request)
        logger.info("Usuario con id: ${suscribe.userId} suscrito")
        return ResponseDto(200, "Usuario suscrito", true)
    }
//    @PostMapping("/subscribe")
//    fun suscribe(@RequestBody suscribe: OrderItem): ResponseDto<Any> {
//        logger.info("Suscribiendo usuario con id: ${suscribe.userId}")
//        val email = suscribeBl.getEmail(suscribe.userId)
//        val order = orderItemBl.registerOrderItem(2, Date(), )  )
//        val request = PublishRequest(topic_arn, "Orden confirmada",email)
//        amazonSNSClient.publish(request)
//        logger.info("Usuario con id: ${suscribe.userId} suscrito")
//        return ResponseDto(200, "Usuario suscrito", true)
//    }

}