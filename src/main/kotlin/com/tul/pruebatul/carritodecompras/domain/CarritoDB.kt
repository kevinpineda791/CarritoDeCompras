package com.tul.pruebatul.carritodecompras.domain

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class CarritoDB(

    @Id
    @Column(name = "id", nullable = false)
    val id: String = UUID.randomUUID().toString(),

    var carritoId: UUID,

    val sku: Long = Long.MIN_VALUE,

    val nombre: String = "",

    val precio: Double = Double.MIN_VALUE,

    val cantidad: Int = Int.MIN_VALUE,

    var estado: Estado = Estado.PENDIENTE

) {

    fun changeEstado(estado: Estado) = copy(estado = estado)
}