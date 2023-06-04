package com.example.msorders

import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableDiscoveryClient
class MsOrdersApplication

@Bean
fun runner(cf: ConnectionFactory): ApplicationRunner {
	return ApplicationRunner {
		var open = false
		while (!open) {
			try {
				cf.createConnection().close()
				open = true
			} catch (e: Exception) {
			}
		}
	}
}

fun main(args: Array<String>) {
	runApplication<MsOrdersApplication>(*args)
}
