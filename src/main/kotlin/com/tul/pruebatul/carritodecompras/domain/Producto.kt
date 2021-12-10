package com.tul.pruebatul.carritodecompras.domain

import java.util.*
import javax.persistence.*

@Entity
class Producto (

    @Id
    @Column(name = "id", nullable = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: String = UUID.randomUUID().toString(),

    val nombre: String="",

    val sku: Long= Long.MIN_VALUE,

    var precio: Double=0.0,

    val descripcion: String? = "",

    val descuento: Boolean = false
) {
    companion object{
        fun build(id: String,
                  nombre: String,
                  sku: Long,
                  precio: Double,
                  descripcion: String?,
                  descuento: Boolean) = Producto(
            id = id,
            nombre = nombre,
            sku = sku,
            precio = precio,
            descripcion = descripcion,
            descuento = descuento
        )
    }



}