package com.tul.pruebatul.carritodecompras.usecase.jpa.db.producto

import com.tul.pruebatul.carritodecompras.delivery.exception.DatabaseRepositoryException
import com.tul.pruebatul.carritodecompras.domain.Producto
import com.tul.pruebatul.carritodecompras.usecase.jpa.ProductosJpaRepository
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.producto.GetProductoByProductoIdRepository
import org.springframework.stereotype.Service

@Service
class GetProductoByProductoIdDBRepository(private val productosJpaRepository: ProductosJpaRepository)
    : GetProductoByProductoIdRepository {

    override fun execute(productoId: String): Producto? {
        try {
            return productosJpaRepository.findById(productoId).orElse(null)
        }catch (ex: Exception){
            throw DatabaseRepositoryException("GetProductoByProductoIdDBRepository ProductoId: ${productoId.toString()}", ex)
        }
    }
}