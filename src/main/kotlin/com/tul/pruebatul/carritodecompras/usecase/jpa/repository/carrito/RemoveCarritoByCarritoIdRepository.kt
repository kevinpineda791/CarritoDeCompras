package com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito

import java.util.*

interface RemoveCarritoByCarritoIdRepository {

    fun execute(carritoId: UUID)
}