package com.example.msorders.api

import com.example.msorders.bl.OrderItemBl
import com.example.msorders.dto.OrderItemDto
import com.example.msorders.dto.OrderItemInfoDto
import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import io.github.resilience4j.retry.annotation.Retry
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

@RestController
@RequestMapping("api/v1/orderItem")
class OrderItemApi(private val orderItemBl: OrderItemBl) {

    // API para registrar un item de orden
    // API para registrar un item de orden
    @PostMapping("/register")
    @CircuitBreaker(name = "productCB", fallbackMethod = "registerOrderItemFallback")
    @Bulkhead(name = "productBH")
    @RateLimiter(name = "productRL")
    @Retry(name = "productRT", fallbackMethod = "registerOrderItemFallback")
    fun registerOrderItem(
        @RequestParam productId: Long,
        @RequestParam cantidad: Int,
        @RequestParam fecha:Date,
        @RequestParam precioUnitario: BigDecimal,
        @RequestParam userId: String
    ): ResponseEntity<Any> {
        val orderItemDto = orderItemBl.registerOrderItem(
            productId,
            cantidad,
            fecha,
            precioUnitario,
            userId
        )
        return ResponseEntity.ok(orderItemDto)
    }

    fun registerOrderItemFallback(
        productId: Long,
        cantidad: Int,
        fecha:Date,
        precioUnitario: BigDecimal,
        userId: String,
        throwable: Throwable
    ): ResponseEntity<Any> {
        return ResponseEntity.ok("Error")
    }

    // Endpoint para obtener la información de los order items de un usuario
    @GetMapping("/getOrderInfoByUserId")
    @CircuitBreaker(name = "productCB", fallbackMethod = "getOrderInfoByUserIdFallback")
    @Bulkhead(name = "productBH")
    @RateLimiter(name = "productRL")
    @Retry(name = "productRT", fallbackMethod = "getOrderInfoByUserIdFallback")
    fun getOrderInfoByUserId(@RequestParam("userId") userId: String): ResponseEntity<List<OrderItemInfoDto>> {
        val orderItemsInfo = orderItemBl.getOrderInfoByUserId(userId)
        return ResponseEntity.ok(orderItemsInfo)
    }

    fun getOrderInfoByUserIdFallback(userId: String, throwable: Throwable): ResponseEntity<Any> {
        return ResponseEntity.ok("Error")
    }

}







