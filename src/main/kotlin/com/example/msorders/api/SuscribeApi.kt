package com.example.msorders.api

//import com.amazonaws.services.sns.AmazonSNSClient
//import com.amazonaws.services.sns.model.PublishRequest
//import com.example.msorders.dto.ResponseDto
//import org.slf4j.Logger
//import org.slf4j.LoggerFactory
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.RequestBody
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController
//
//@RestController
//@RequestMapping("api/v1/orders")
//class SuscribeApi (private val amazonSNSClient: AmazonSNSClient){
//
//    private val logger: Logger = LoggerFactory.getLogger(SuscribeApi::class.java)
//
//    private val topic_arn = "arn:aws:sns:us-east-1:939998388209:ms-sns"
//
////    //post de suscripcion a servicio de notificaciones
////    @PostMapping("/subscribe")
////    fun suscribe(@RequestBody suscribe: SuscribeDao): ResponseDto<Any> {
////        val subscription = suscribeBl.createSuscription(suscribe)
////        //enviamos la notificacion al usuario
////        val email = suscribeBl.getUserEmail(subscription.id)
////        val request = PublishRequest(topic_arn, "Orden confirmada", email)
////        amazonSNSClient.publish(request)
////
////        return ResponseDto(subscription, "Ok", true)
////    }
//
//}