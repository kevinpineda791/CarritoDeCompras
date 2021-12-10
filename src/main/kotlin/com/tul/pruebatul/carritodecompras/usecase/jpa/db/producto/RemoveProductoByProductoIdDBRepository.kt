package com.tul.pruebatul.carritodecompras.usecase.jpa.db.producto

import com.tul.pruebatul.carritodecompras.delivery.exception.DatabaseRepositoryException
import com.tul.pruebatul.carritodecompras.usecase.jpa.ProductosJpaRepository
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.producto.RemoveProductoByProductoIdRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RemoveProductoByProductoIdDBRepository(private val productosJpaRepository: ProductosJpaRepository)
    : RemoveProductoByProductoIdRepository {
    override fun execute(productoId: UUID) {
        try {
            return productosJpaRepository.removeById(productoId)
        }catch (ex: Exception){
            throw DatabaseRepositoryException("RemoveProductoByProductoIdDBRepository ProductoId: ${productoId.toString()}", ex)
        }
    }
}