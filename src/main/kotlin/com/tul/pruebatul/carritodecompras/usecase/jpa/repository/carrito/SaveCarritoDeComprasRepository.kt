package com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito

import com.tul.pruebatul.carritodecompras.domain.CarritoDB
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
interface SaveCarritoDeComprasRepository {

    fun execute(carritoDB: CarritoDB)
}