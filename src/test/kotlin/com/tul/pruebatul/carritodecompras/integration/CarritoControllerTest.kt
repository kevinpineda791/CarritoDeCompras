package com.tul.pruebatul.carritodecompras.integration

import com.tul.pruebatul.carritodecompras.delivery.CarritoController
import com.tul.pruebatul.carritodecompras.domain.CarritoDB
import com.tul.pruebatul.carritodecompras.domain.Estado
import com.tul.pruebatul.carritodecompras.domain.Producto
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.carrito.SaveCarritoDeComprasRepository
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.producto.SaveProductoRepository
import com.tul.pruebatul.carritodecompras.usecase.usecasehelper.repository.GetCarritoDeCompraRepository
import org.h2.util.JdbcUtils
import org.h2.util.JdbcUtils.serializer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.util.*

@WebMvcTest(CarritoController::class)
class CarritoControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var saveCarritoDeComprasRepository: SaveCarritoDeComprasRepository

    @Autowired
    lateinit var getCarritoDeCompraRepository: GetCarritoDeCompraRepository

    private lateinit var carritoController: CarritoController

    @Test
    fun getAllProductosTest(){
        val carritoResp = "{\n    \"carritoId\":\"cce28b61-d545-470e-92c6-67017eec7869\",\n    \"sku\":1231231231231233333,\n    \"nombre\":\"reloj\",\n    \"precio\":1500.0,\n    \"cantidad\":1\n}"

        val productoDB = createProductos()
        val carrito = createCarritoDB(productoDB.get(0).sku, UUID.fromString(productoDB.get(0).id))
        saveCarritoDeComprasRepository.execute(carrito)

        mockMvc.get("/carrito/${carrito.carritoId}") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { is2xxSuccessful() }
        }

        val result = carritoController.getCarritoDeCompras(carrito.carritoId.toString())
        Assertions.assertNotNull(result)
        Assertions.assertEquals(result.body, carritoResp)

    }

    @Test
    fun saveNewElementoCarritoTest(){
        val carritoResp = "{\n    \"carritoId\":\"cce28b61-d545-470e-92c6-67017eec7869\",\n    \"sku\":1231231231231233333,\n    \"nombre\":\"reloj\",\n    \"precio\":1500.0,\n    \"cantidad\":1\n}"

        val productoDB = createProductos()
        val carrito = createCarritoDB(productoDB.get(0).sku, UUID.fromString(productoDB.get(0).id))
        saveCarritoDeComprasRepository.execute(carrito)

        mockMvc.post("/carrito/guardar") {
            contentType = MediaType.APPLICATION_JSON
            content = serializer.writeValueAsString(carrito)
        }.andExpect {
            status { is2xxSuccessful() }
        }

        val result = carritoController.saveNewElementoCarrito(carrito)
        Assertions.assertNotNull(result)
        Assertions.assertEquals(result.body, carritoResp)

    }

    @Test
    fun uploadProducto(){
        val carritoResp = "{\n    \"carritoId\":\"cce28b61-d545-470e-92c6-67017eec7869\",\n    \"sku\":1231231231231233333,\n    \"nombre\":\"reloj\",\n    \"precio\":1500.0,\n    \"cantidad\":1\n}"

        val productoDB = createProductos()
        val carrito = createCarritoDB(productoDB.get(0).sku, UUID.fromString(productoDB.get(0).id))
        saveCarritoDeComprasRepository.execute(carrito)

        mockMvc.post("/carrito/actualizar/${carrito.carritoId}") {
            contentType = MediaType.APPLICATION_JSON
            content = serializer.writeValueAsString(carrito)
        }.andExpect {
            status { is2xxSuccessful() }
        }

        val result = carritoController.uploadProducto(carrito.carritoId.toString(), carrito)
        Assertions.assertNotNull(result)
        Assertions.assertEquals(result.body, carritoResp)

    }

    @Test
    fun checkOutCarrito(){
        val carritoResp = listOf(Double.MIN_VALUE,Estado.COMPLETADO.toString())

        val productoDB = createProductos()
        val carrito = createCarritoDB(productoDB.get(0).sku, UUID.fromString(productoDB.get(0).id))
        saveCarritoDeComprasRepository.execute(carrito)

        mockMvc.post("/carrito/actualizar/${carrito.carritoId}") {
            contentType = MediaType.APPLICATION_JSON
            content = serializer.writeValueAsString(carrito)
        }.andExpect {
            status { is2xxSuccessful() }
        }

        val result = carritoController.checkOutCarrito(carrito.carritoId.toString())
        Assertions.assertNotNull(result)
        Assertions.assertEquals(result.body, carritoResp)

    }

    @Test
    fun deleteElementoCarrito(){
        val carritoResp = listOf(Double.MIN_VALUE,Estado.COMPLETADO.toString())

        val productoDB = createProductos()
        val carrito = createCarritoDB(productoDB.get(0).sku, UUID.fromString(productoDB.get(0).id))
        saveCarritoDeComprasRepository.execute(carrito)

        mockMvc.post("/carrito/actualizar/${carrito.carritoId}") {
            contentType = MediaType.APPLICATION_JSON
            content = serializer.writeValueAsString(carrito)
        }.andExpect {
            status { is2xxSuccessful() }
        }

        carritoController.deleteElementoCarrito(carrito.carritoId.toString())
        val result = getCarritoDeCompraRepository.execute(carrito.carritoId)
        Assertions.assertNull(result)
    }

    private fun createCarritoDB(sku: Long, carritoId: UUID):CarritoDB{
        val carritoDB = CarritoDB(UUID.randomUUID().toString(),carritoId,sku,"", Double.MIN_VALUE, Int.MIN_VALUE,Estado.PENDIENTE)
        return carritoDB
    }

    private fun createProductos():List<Producto>{

        val producto1 = Producto(UUID.randomUUID().toString(),"",Long.MIN_VALUE, Double.MIN_VALUE,"",false)
        val producto2 = Producto(UUID.randomUUID().toString(),"",Long.MIN_VALUE, Double.MIN_VALUE,"",false)

        return listOf(producto1,producto2)
    }
}