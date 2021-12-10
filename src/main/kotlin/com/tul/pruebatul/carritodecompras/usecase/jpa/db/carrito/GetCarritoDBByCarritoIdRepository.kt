package com.tul.pruebatul.carritodecompras.usecase.jpa.db.carrito

import com.tul.pruebatul.carritodecompras.delivery.exception.DatabaseRepositoryException
import com.tul.pruebatul.carritodecompras.domain.CarritoDB
import com.tul.pruebatul.carritodecompras.usecase.jpa.CarritoJpaRepository
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito.GetCarritoByCarritoIdRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class GetCarritoDBByCarritoIdRepository (private val carritoJpaRepository: CarritoJpaRepository)
    : GetCarritoByCarritoIdRepository {
    override fun execute(carritoId: UUID): List<CarritoDB>? {
        try {
            return carritoJpaRepository.findByCarritoId(carritoId)
        }catch (ex: Exception){
            throw DatabaseRepositoryException("GetCarritoDBByCarritoIdDBRepository CarritoId: ${carritoId}", ex)
        }
    }
}