package com.example.msorders.bl

import com.example.msorders.dao.Repository.OrderItemRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SuscribeBl @Autowired  constructor(private val orderItemRepository: OrderItemRepository) {

    companion object {
        val objectMapper = jacksonObjectMapper()
        val LOGGER: Logger = LoggerFactory.getLogger(SuscribeBl::class.java.name)
    }

    //funcion para obtener el email del usuario por medio del id
    fun getEmail(id: String): String {
        LOGGER.info("Obteniendo email del usuario con id: $id")
        val email = orderItemRepository.findEmailBySubId(id.toString())
        LOGGER.info("Email obtenido: $email")
        if (email.isEmpty()) {
            throw NoSuchElementException("No se encontro el email del usuario con id: $id")
        }
        return email
    }

}