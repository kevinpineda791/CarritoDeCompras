package com.tul.pruebatul.carritodecompras.usecase.jpa.db.carrito

import com.tul.pruebatul.carritodecompras.delivery.exception.DatabaseRepositoryException
import com.tul.pruebatul.carritodecompras.usecase.jpa.CarritoJpaRepository
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito.RemoveCarritoByCarritoIdRepository
import java.util.*

class RemoveCarritoByCarritoIdDBRepository(private val carritoJpaRepository: CarritoJpaRepository)
    : RemoveCarritoByCarritoIdRepository {
    override fun execute(carritoId: UUID) {
        try {
            return carritoJpaRepository.removeByCarritoId(carritoId)
        }catch (ex: Exception){
            throw DatabaseRepositoryException("RemoveCarritoDeComprasByCarritoIdDBRepository CarritoId: ${carritoId.toString()}", ex)
        }
    }
}