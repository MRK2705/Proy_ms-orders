package com.example.msorders.dto

import java.math.BigDecimal
import java.util.Date

data class OrderItemInfoDto(
    var productName: String?,
    var image: String?,
    var cantidad: Int?,
    var fecha: Date?,
    var precioTotal: BigDecimal?
) {
    constructor() : this(null, null, null, null, null)

    override fun toString(): String {
        return "OrderItemInfoDto(productName=$productName, image=$image, cantidad=$cantidad, fecha=$fecha, precioTotal=$precioTotal)"
    }
}
