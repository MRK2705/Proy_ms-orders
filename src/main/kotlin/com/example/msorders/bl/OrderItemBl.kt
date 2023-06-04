package com.example.msorders.bl

import com.example.msorders.dao.OrderItem
import com.example.msorders.dao.Repository.OrderItemRepository
import com.example.msorders.dto.OrderItemDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class OrderItemBl @Autowired constructor(private val orderItemRepository: OrderItemRepository) {

    companion object {
        val objectMapper = jacksonObjectMapper()
        val LOGGER: Logger = LoggerFactory.getLogger(OrderItemBl::class.java.name)
    }

    // Función para registrar un item de orden
    fun registerOrderItem(cantidad: Int, precioUnitario: BigDecimal, precioTotal: BigDecimal, productId: Long, userId: String): OrderItemDto {
        LOGGER.info("Registrando item de orden")
        val orderItem: OrderItem = OrderItem()
        orderItem.cantidad = cantidad
        orderItem.precioUnitario = precioUnitario
        orderItem.precioTotal = precioTotal
        orderItem.productId = productId
        orderItem.userId = userId
        orderItemRepository.save(orderItem)
        LOGGER.info("Item de orden guardado en base de datos")
        val orderItemDto = OrderItemDto(orderItem.cantidad, orderItem.fecha, orderItem.precioUnitario, orderItem.precioTotal, orderItem.productId, orderItem.userId, orderItem.orderId)

        return orderItemDto
    }

    // Función para obtener un item de orden por su ID
    fun getOrderItemById(orderItemId: Long): OrderItemDto? {
        LOGGER.info("Obteniendo item de orden por ID")
        val orderItem = orderItemRepository.findById(orderItemId).orElse(null)
        return if (orderItem != null) {
            OrderItemDto(orderItem.cantidad, orderItem.fecha, orderItem.precioUnitario, orderItem.precioTotal, orderItem.productId, orderItem.userId, orderItem.orderId)
        } else {
            null
        }
    }


}

