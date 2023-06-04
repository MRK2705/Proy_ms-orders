package com.example.msorders.api

/*
import com.amazonaws.services.sns.AmazonSNSClient
import com.amazonaws.services.sns.model.PublishRequest
import com.amazonaws.services.sns.model.SubscribeRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/sns")
class SNSApi {

    private val logger: Logger = LoggerFactory.getLogger(SNSApi::class.java)
    private val topic_arn = "arn:aws:sns:us-east-1:939998388209:ms-sns"

    @Autowired
    private lateinit var amazonSNSClient: AmazonSNSClient

    @GetMapping("/suscribe/{email}")
    fun suscribe(@PathVariable("email") email: String): String {
        logger.info("Suscrito al servicio de notificaciones")
        val subscribeRequest = SubscribeRequest(topic_arn, "email", email)
        amazonSNSClient.subscribe(subscribeRequest)
        return "Verifique su email"
    }

    @GetMapping("/send/{message}")
    fun send(@PathVariable("message") message: String): String {
        logger.info("Enviando mensaje")
        val request = PublishRequest(topic_arn, "Orden confirmada", message)
        amazonSNSClient.publish(request)
        return "Mensaje enviado"
    }

}

 */