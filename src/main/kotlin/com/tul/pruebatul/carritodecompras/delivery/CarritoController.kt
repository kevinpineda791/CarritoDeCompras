package com.tul.pruebatul.carritodecompras.delivery

import com.tul.pruebatul.carritodecompras.domain.CarritoDB
import com.tul.pruebatul.carritodecompras.domain.CarritoDeCompra
import com.tul.pruebatul.carritodecompras.domain.Estado
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito.GetProductoBySkuRepository
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito.RemoveElementoCarritoByCarritoIdRepository
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito.SaveCarritoDeComprasRepository
import com.tul.pruebatul.carritodecompras.usecase.usecasehelper.repository.CheckoutRepository
import com.tul.pruebatul.carritodecompras.usecase.usecasehelper.GetCarritoDeCompraUCRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/carrito")
class CarritoController(
    private val getCarritoDeCompraUCRepository: GetCarritoDeCompraUCRepository,
    private val removeElementoCarritoByCarritoIdRepository: RemoveElementoCarritoByCarritoIdRepository,
    private val saveCarritoDeComprasRepository: SaveCarritoDeComprasRepository,
    private val checkoutRepository: CheckoutRepository,
    private val getProductoBySkuRepository: GetProductoBySkuRepository
) {

    @GetMapping("/{carritoId}")
    fun getCarritoDeCompras(@PathVariable carritoId: String):ResponseEntity<CarritoDeCompra>{
        return getCarritoDeCompraUCRepository.execute(UUID.fromString(carritoId))
            ?. let { ResponseEntity.ok(it) }
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "El Carrito $carritoId no existe")
    }

    @PostMapping("/guardar")
    fun saveNewElementoCarrito(@RequestBody carritoDB: CarritoDB): ResponseEntity<CarritoDB>{
        getProductoBySkuRepository.execute(carritoDB.sku)
            ?.let { return saveCarritoDeComprasRepository.execute(carritoDB)
                .let { ResponseEntity.ok(carritoDB) } }
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "El Producto segun SKU ${carritoDB.sku} no existe")

    }

    @PatchMapping("/actualizar/{carritoId}")
    fun uploadProducto(@PathVariable carritoId: String, @RequestBody carritoDB: CarritoDB):ResponseEntity<String>{
        carritoDB.carritoId = UUID.fromString(carritoId)
        return saveCarritoDeComprasRepository.execute(carritoDB)
            . let { ResponseEntity.ok(carritoId) }
    }

    @PostMapping("/checkout/{carritoId}")
    fun checkOutCarrito(@PathVariable carritoId: String):ResponseEntity<List<String>>{
        return checkoutRepository.execute(UUID.fromString(carritoId))
            .takeIf { it!=0.0 }
            ?.let { ResponseEntity.ok(listOf(it.toString(), Estado.COMPLETADO.toString())) }
            ?:throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se ha podido completar la compra por el id ")
    }

    @DeleteMapping("/{carritoId}")
    fun deleteElementoCarrito(@PathVariable carritoDBId: String){
        removeElementoCarritoByCarritoIdRepository.execute(UUID.fromString(carritoDBId))
    }
}