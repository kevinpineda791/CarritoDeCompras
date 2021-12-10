package com.tul.pruebatul.carritodecompras.usecase.jpa.db.carrito

import com.tul.pruebatul.carritodecompras.delivery.exception.DatabaseRepositoryException
import com.tul.pruebatul.carritodecompras.domain.CarritoDB
import com.tul.pruebatul.carritodecompras.usecase.jpa.CarritoJpaRepository
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito.GetAllProductosInCarrito
import org.springframework.stereotype.Service
import java.util.*

@Service
class GetAllProductosInCarritoDBRepository(private val carritoJpaRepository: CarritoJpaRepository)
    :GetAllProductosInCarrito{

    override fun execute(carritoId: UUID): CarritoDB? {
        try {
            return carritoJpaRepository.findById(carritoId).orElse(null)
        }catch (ex: Exception){
            throw DatabaseRepositoryException("`GetAllProductosInCarrito(Verificar)DBRepository` CarritoId: ${carritoId}", ex)
        }
    }
}