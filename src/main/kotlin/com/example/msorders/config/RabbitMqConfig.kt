package com.example.msorders.config

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMqConfig {

    @Bean
    fun orderExchange(): DirectExchange {
        return DirectExchange("orderExchange")
    }

    @Bean
    fun orderQueue(): Queue {
        return QueueBuilder.durable("orderQueue").build()
    }

    @Bean
    fun orderBinding(reservationQueue: Queue, reservationExchange: DirectExchange): Binding {
        return BindingBuilder
            .bind(reservationQueue)
            .to(reservationExchange)
            .with("orderRoutingKey")
    }
    @Bean
    fun jsonMessageConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun amqpTemplate(connectionFactory: ConnectionFactory): AmqpTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = jsonMessageConverter()
        return rabbitTemplate
    }

}