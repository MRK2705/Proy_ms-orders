package com.example.msorders.dto

import java.math.BigDecimal
import java.util.Date

data class OrderItemDto(
    var cantidad: Int,
    var fecha: Date,
    var precioUnitario: BigDecimal,
    var precioTotal: BigDecimal,
    var productId: Long,
    var userId: Long,
    var orderId: Long
) {
    constructor() : this(0, Date(), BigDecimal.ZERO, BigDecimal.ZERO, 0, 0, 0)

    override fun toString(): String {
        return "OrderItemDto(cantidad=$cantidad, fecha=$fecha, precioUnitario=$precioUnitario, precioTotal=$precioTotal, productId=$productId, userId=$userId, orderId=$orderId)"
    }
}

