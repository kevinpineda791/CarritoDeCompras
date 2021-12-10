package com.tul.pruebatul.carritodecompras.usecase.usecasehelper.repository

import com.tul.pruebatul.carritodecompras.domain.CarritoDeCompra
import com.tul.pruebatul.carritodecompras.domain.Estado
import java.util.*

interface GetCarritoDeCompraRepository {

    fun execute(carritoId: UUID): CarritoDeCompra?
}