package com.example.msorders.api

import com.example.msorders.bl.OrderItemBl
import com.example.msorders.dto.OrderItemDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.*

@RestController
@RequestMapping("api/v1/orderItem")
class OrderItemApi(private val orderItemBl: OrderItemBl) {

    // API para registrar un item de orden
    @PostMapping("/register")
    fun registerOrderItem(
        @RequestParam cantidad: Int,
        @RequestParam fecha: Date,
        @RequestParam precioUnitario: BigDecimal,
        @RequestParam precioTotal: BigDecimal,
        @RequestParam productId: Long,
        @RequestParam userId: Long
    ): ResponseEntity<Any> {
        val orderItemDto = orderItemBl.registerOrderItem(
            cantidad,
            fecha,
            precioUnitario,
            precioTotal,
            productId,
            userId
        )
        return ResponseEntity.ok(orderItemDto)
    }
}


