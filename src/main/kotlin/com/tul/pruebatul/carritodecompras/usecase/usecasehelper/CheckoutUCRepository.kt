package com.tul.pruebatul.carritodecompras.usecase.usecasehelper

import com.tul.pruebatul.carritodecompras.domain.Estado
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito.GetCarritoByCarritoIdRepository
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito.SaveCarritoDeComprasRepository
import com.tul.pruebatul.carritodecompras.usecase.usecasehelper.repository.CheckoutRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CheckoutUCRepository(
    private val saveCarritoDeComprasRepository: SaveCarritoDeComprasRepository,
    private val getCarritoByCarritoIdRepository: GetCarritoByCarritoIdRepository
): CheckoutRepository {
    override fun execute(carritoId: UUID):Double {

        val carritoDeCompra = getCarritoByCarritoIdRepository.execute(carritoId)
            ?.map { it.changeEstado(Estado.COMPLETADO) }
            ?:throw Exception("No existe carrito de compras para el id: $carritoId")

        val valorTotal = carritoDeCompra
            .takeIf { it!=null }
            ?.let { it -> it.map { it.precio*it.cantidad } }
            ?.let { it -> it.sumOf { it } }
            ?:0.0

        carritoDeCompra
            .forEach { saveCarritoDeComprasRepository.execute(it) }

        return valorTotal
    }
}