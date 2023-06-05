package com.example.msorders.dao.Repository

import com.example.msorders.dao.OrderItem
import org.springframework.data.repository.query.Param
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface OrderItemRepository : PagingAndSortingRepository<OrderItem, Long> {

    fun findByOrderId(orderId: Long): List<OrderItem>

    @Query("SELECT email FROM users WHERE sub_id = ?", nativeQuery = true)
    fun findEmailBySubId(subId: String): String
    @Query("SELECT p.productName, p.image, oi.cantidad, oi.fecha, oi.precioTotal FROM OrderItem oi JOIN Product p ON oi.productId = p.id WHERE oi.userId = :userId")
    fun getOrderInfo(@Param("userId") userId: String): List<Any>
}

