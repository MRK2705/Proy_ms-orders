package com.example.msorders.dao.Repository

import com.example.msorders.dao.OrderItem
import org.springframework.data.repository.PagingAndSortingRepository

interface OrderItemRepository : PagingAndSortingRepository<OrderItem, Long> {

    // Agrega aquí los métodos de consulta personalizados que necesites, por ejemplo:
    fun findByUserId(userId: Long): List<OrderItem>

    // ...
}

