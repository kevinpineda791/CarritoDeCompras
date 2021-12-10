package com.tul.pruebatul.carritodecompras.domain

import java.util.*

data class CarritoDeCompra (

    val carritoId: UUID = UUID.randomUUID(),
    val productos: List<CarritoDB>,
    //val cantidad: Int = 0,
    val estado: Estado = Estado.PENDIENTE,
    val valorTotal: Double = Double.MIN_VALUE
){

}

enum class Estado{
    PENDIENTE,
    COMPLETADO
}