package com.tul.pruebatul.carritodecompras.usecase.jpa.db.carrito

import com.tul.pruebatul.carritodecompras.delivery.exception.DatabaseRepositoryException
import com.tul.pruebatul.carritodecompras.usecase.jpa.CarritoJpaRepository
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito.RemoveElementoCarritoByCarritoIdRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RemoveElementoCarritoByCarritoIdDBRepository (private val carritoJpaRepository: CarritoJpaRepository)
    :RemoveElementoCarritoByCarritoIdRepository{
    override fun execute(carritoDBId: UUID) {
        try {
            return carritoJpaRepository.removeById(carritoDBId)
        }catch (ex: Exception){
            throw DatabaseRepositoryException("RemoveCarritoDeComprasByCarritoIdDBRepository CarritoId: ${carritoDBId.toString()}", ex)
        }
    }

}