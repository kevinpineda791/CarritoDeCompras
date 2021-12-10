package com.tul.pruebatul.carritodecompras.usecase.jpa

import com.tul.pruebatul.carritodecompras.domain.Producto
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional
interface ProductosJpaRepository : JpaSpecificationExecutor<Producto>,
    PagingAndSortingRepository<Producto,UUID> {

        override fun findAll(): List<Producto>

        fun findById(productoId: String): Optional<Producto>

        fun save(producto: Producto)

        fun removeById(productoId: UUID)

        fun findBySku(sku: Long): Producto?
}