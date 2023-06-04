package com.example.msorders.api

import com.example.msorders.bl.OrderItemBl
import com.example.msorders.dto.OrderItemDto
import com.example.msorders.dto.OrderItemInfoDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.util.*

@RestController
@RequestMapping("api/v1/orderItem")
class OrderItemApi(private val orderItemBl: OrderItemBl) {

    // API para registrar un item de orden
    @PostMapping("/register")
    fun registerOrderItem(
        @RequestParam cantidad: Int,
        @RequestParam precioUnitario: BigDecimal,
        @RequestParam precioTotal: BigDecimal,
        @RequestParam productId: Long,
        @RequestParam userId: String
    ): ResponseEntity<Any> {
        val orderItemDto = orderItemBl.registerOrderItem(cantidad, Date(), precioUnitario, precioTotal, productId, userId)
        return ResponseEntity.ok(orderItemDto)
    }

    // Endpoint para obtener la información de los order items de un usuario
    @GetMapping("/getOrderInfoByUserId")
    fun getOrderInfoByUserId(@RequestParam("userId") userId: Long): ResponseEntity<OrderItemDto> {
        val orderItemInfoDtoList = orderItemBl.getOrderItemById(userId)
        return ResponseEntity.ok(orderItemInfoDtoList)
    }
}




