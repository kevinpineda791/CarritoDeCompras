package com.tul.pruebatul.carritodecompras.usecase.jpa.db.carrito

import com.tul.pruebatul.carritodecompras.delivery.exception.DatabaseRepositoryException
import com.tul.pruebatul.carritodecompras.domain.CarritoDB
import com.tul.pruebatul.carritodecompras.usecase.jpa.CarritoJpaRepository
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito.SaveCarritoDeComprasRepository
import org.springframework.stereotype.Service

@Service
class SaveCarritoDeComprasDBRepository(private val carritoJpaRepository: CarritoJpaRepository)
    :SaveCarritoDeComprasRepository{
    override fun execute(carritoDB: CarritoDB) {
        try {
            return carritoJpaRepository.save(carritoDB)
        }catch (ex: Exception){
            throw DatabaseRepositoryException("SaveCarritoDeComprasDBRepository CarritoId: ${carritoDB.id}", ex)
        }
    }
}