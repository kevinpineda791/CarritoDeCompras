package com.tul.pruebatul.carritodecompras.delivery

import com.tul.pruebatul.carritodecompras.domain.Producto
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.producto.GetAllProductosRepository
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.producto.GetProductoByProductoIdRepository
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.producto.RemoveProductoByProductoIdRepository
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.producto.SaveProductoRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/producto")
class ProductosController (private val getAllProductosRepository: GetAllProductosRepository,
                           private val getProductoByProductoIdRepository: GetProductoByProductoIdRepository,
                           private val saveProductoRepository: SaveProductoRepository,
                           private val removeProductoByProductoIdRepository: RemoveProductoByProductoIdRepository
){

    @GetMapping("/")
    fun getAllProductos():ResponseEntity<List<Producto>>{
        return getAllProductosRepository.execute()
            ?. let { ResponseEntity.ok(it) }
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "No existen productos asociados")
    }

    @GetMapping("/{productoId}")
    fun getProductoByProductoId(@PathVariable productoId: String):ResponseEntity<Producto?>{
        return getProductoByProductoIdRepository.execute(productoId)
            ?. let { ResponseEntity.ok(it) }
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "El Producto $productoId no existe")
    }

    @PostMapping("/guardar")
    fun saveProducto(@RequestBody producto: Producto):ResponseEntity<Producto>{
        return saveProductoRepository.execute(producto)
            . let { ResponseEntity.ok(producto) }

    }

    @PatchMapping("/actualizar/{productoId}")
    fun uploadProducto(@PathVariable productoId: String, @RequestBody producto: Producto):ResponseEntity<String>{
        producto.id = productoId
        return saveProductoRepository.execute(producto)
            . let { ResponseEntity.ok(productoId) }
    }

    @DeleteMapping("/borrar-producto/{productoid}")
    fun removeProducto(@RequestBody producto: Producto){
        removeProductoByProductoIdRepository.execute(UUID.fromString(producto.id))
    }
}
