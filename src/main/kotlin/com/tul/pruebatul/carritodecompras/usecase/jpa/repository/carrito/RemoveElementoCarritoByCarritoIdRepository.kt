package com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito

import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
@Transactional
interface RemoveElementoCarritoByCarritoIdRepository {

    fun execute(carritoDBId: UUID)
}