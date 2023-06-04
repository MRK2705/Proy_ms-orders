
package com.example.msorders.dao

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "products")
class Product (
    var productName: String,
    var description: String,
    var price: BigDecimal,
    var image: String,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
) {
    constructor() : this("","", BigDecimal.ZERO, "",0)
}