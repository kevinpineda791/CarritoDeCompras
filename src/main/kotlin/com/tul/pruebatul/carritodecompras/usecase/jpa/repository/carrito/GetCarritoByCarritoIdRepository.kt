package com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito

import com.tul.pruebatul.carritodecompras.domain.CarritoDB
import java.util.*

interface GetCarritoByCarritoIdRepository {

    fun execute(carritoId: UUID):List<CarritoDB>?
}