package com.example.msorders.dao

import com.example.msorders.dto.OrderItemDto
import java.math.BigDecimal
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "order_items")
class OrderItem(
    var cantidad: Int,
    var fecha: Date,
    var precioUnitario: BigDecimal,
    var precioTotal: BigDecimal,
    var productId: Long=0,
    var userId: Long=0,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var orderId: Long = 0,
) {
    constructor() : this(0, Date(), BigDecimal.ZERO, BigDecimal.ZERO, 0,0,0)
}




