package com.tul.pruebatul.carritodecompras.usecase.jpa.repository.producto

import java.util.*

interface RemoveProductoByProductoIdRepository {

    fun execute(productoId: UUID)
}