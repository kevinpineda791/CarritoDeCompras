package com.tul.pruebatul.carritodecompras.usecase.usecasehelper.repository

import java.util.*

interface CheckoutRepository {

    fun execute(carritoId: UUID):Double
}