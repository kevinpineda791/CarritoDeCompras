package com.tul.pruebatul.carritodecompras.usecase.jpa.db.carrito

import com.tul.pruebatul.carritodecompras.delivery.exception.DatabaseRepositoryException
import com.tul.pruebatul.carritodecompras.domain.Producto
import com.tul.pruebatul.carritodecompras.usecase.jpa.ProductosJpaRepository
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito.GetProductoBySkuRepository
import org.springframework.stereotype.Service

@Service
class GetProductoBySkuDBRepository(private val productosJpaRepository: ProductosJpaRepository)
    : GetProductoBySkuRepository {
    override fun execute(sku: Long): Producto? {
        try {
            return productosJpaRepository.findBySku(sku)
        }catch (ex: Exception){
            throw DatabaseRepositoryException("GetProductoBySkuDBRepository SKU: ${sku}", ex)
        }
    }
}