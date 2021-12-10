package com.tul.pruebatul.carritodecompras.usecase.usecasehelper

import com.tul.pruebatul.carritodecompras.domain.CarritoDeCompra
import com.tul.pruebatul.carritodecompras.domain.Estado
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito.GetCarritoByCarritoIdRepository
import com.tul.pruebatul.carritodecompras.usecase.usecasehelper.repository.GetCarritoDeCompraRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class GetCarritoDeCompraUCRepository(private val getCarritoByCarritoIdDBRepository: GetCarritoByCarritoIdRepository)
    : GetCarritoDeCompraRepository {
    override fun execute(carritoId: UUID): CarritoDeCompra?  {

        val carritoDeCompra = getCarritoByCarritoIdDBRepository.execute(carritoId)
            ?.let { it.filter { it.estado != Estado.COMPLETADO  } }
            ?:throw Exception("No existe carrito de compras para el id: $carritoId")

        val valortotal = carritoDeCompra
            .let { it -> it.map { it.precio } }
            .let { it -> it.sumOf { it } }

        val estado = carritoDeCompra
            .map { it.estado }
            .first()

        return CarritoDeCompra(carritoId, carritoDeCompra, estado, valortotal)

    }
}