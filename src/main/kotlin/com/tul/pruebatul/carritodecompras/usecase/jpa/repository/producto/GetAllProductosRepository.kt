package com.tul.pruebatul.carritodecompras.usecase.jpa.repository.producto

import com.tul.pruebatul.carritodecompras.domain.Producto

interface GetAllProductosRepository {

    fun execute(): List<Producto>?
}