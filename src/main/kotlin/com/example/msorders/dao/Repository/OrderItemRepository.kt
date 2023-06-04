package com.example.msorders.dao.Repository

import com.example.msorders.dao.OrderItem
import com.example.msorders.dao.Product
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface OrderItemRepository : PagingAndSortingRepository<OrderItem, Long> {

    // Agrega aquí los métodos de consulta personalizados que necesites, por ejemplo:
    fun findByUserId(userId: String): List<OrderItem>

    @Query("SELECT p.productName, p.image, oi.cantidad, oi.fecha, oi.precioTotal FROM OrderItem oi JOIN Product p ON oi.productId = p.id WHERE oi.userId = :userId")
    fun getOrderInfo(@Param("userId") userId: String): List<Any>
}


