package com.tul.pruebatul.carritodecompras.usecase.jpa

import com.tul.pruebatul.carritodecompras.domain.CarritoDB
import com.tul.pruebatul.carritodecompras.domain.Producto
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface CarritoJpaRepository: JpaSpecificationExecutor<CarritoDB>,
    PagingAndSortingRepository<CarritoDB, UUID> {


    fun findById(carritoDBId: String): CarritoDB?

    fun findByCarritoId(carritoId: UUID): List<CarritoDB>

    fun save(carritoDB: CarritoDB)

    fun removeByCarritoId(carritoId: UUID)

    fun removeById(carritoDBId: UUID)

    fun findBySku(sku: Long): CarritoDB?
}