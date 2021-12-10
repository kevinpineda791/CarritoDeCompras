package com.tul.pruebatul.carritodecompras.usecase.jpa.repository.producto

import com.tul.pruebatul.carritodecompras.domain.Producto
import java.util.*

interface GetProductoByProductoIdRepository {

    fun execute(productoId: String): Producto?
}