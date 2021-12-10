package com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito

import com.tul.pruebatul.carritodecompras.domain.Producto
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
interface GetProductoBySkuRepository {

    fun execute(sku: Long): Producto?
}