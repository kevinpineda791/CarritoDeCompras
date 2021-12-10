package com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito

import com.tul.pruebatul.carritodecompras.domain.CarritoDB
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
@Transactional
interface GetAllProductosInCarrito {

    fun execute(carritoId: UUID): CarritoDB?
}