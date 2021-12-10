package com.tul.pruebatul.carritodecompras.usecase.jpa.db.producto

import com.tul.pruebatul.carritodecompras.delivery.exception.DatabaseRepositoryException
import com.tul.pruebatul.carritodecompras.domain.Producto
import com.tul.pruebatul.carritodecompras.usecase.jpa.ProductosJpaRepository
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.producto.SaveProductoRepository
import org.springframework.stereotype.Service

@Service
class SaveProductoDBRepository(private val productosJpaRepository: ProductosJpaRepository)
    : SaveProductoRepository {
    override fun execute(producto: Producto) {
        try {
            producto
                .takeIf { it.descuento }
                ?.let { it.precio = it.precio/2 }
                ?:let { it }

            return productosJpaRepository.save(producto)
        }catch (ex: Exception){
            throw DatabaseRepositoryException("SaveProductoDBRepository ProductoId ${producto.id}", ex)
        }
    }
}