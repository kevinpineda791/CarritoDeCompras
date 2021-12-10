package com.tul.pruebatul.carritodecompras.usecase.jpa.db.producto

import com.tul.pruebatul.carritodecompras.delivery.exception.DatabaseRepositoryException
import com.tul.pruebatul.carritodecompras.domain.Producto
import com.tul.pruebatul.carritodecompras.usecase.jpa.ProductosJpaRepository
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.producto.GetAllProductosRepository
import org.springframework.stereotype.Service

@Service
class GetAllProductosDBRepository(private val productosJpaRepository: ProductosJpaRepository)
    : GetAllProductosRepository {
    override fun execute(): List<Producto>? {
        try {
            return productosJpaRepository.findAll()
        }catch (ex: Exception){
            throw DatabaseRepositoryException("GetAllProductosDBRepository GeneralDatabaseException", ex)
        }
    }

}