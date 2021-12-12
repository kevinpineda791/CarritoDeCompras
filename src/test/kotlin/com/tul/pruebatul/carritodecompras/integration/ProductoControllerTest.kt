package com.tul.pruebatul.carritodecompras.integration

import com.tul.pruebatul.carritodecompras.delivery.ProductosController
import com.tul.pruebatul.carritodecompras.domain.Producto
import com.tul.pruebatul.carritodecompras.usecase.jpa.repository.producto.SaveProductoRepository
import jdk.nashorn.internal.ir.RuntimeNode
import org.h2.util.JdbcUtils.serializer


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

//@RunWith(SpringRunner::class)
@WebMvcTest(ProductosController::class)
class ProductoControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var saveProductoRepository: SaveProductoRepository

    private lateinit var productosController: ProductosController

    @Test
    fun getAllProductosTest(){
        val producto = "[\n    {\n        \"nombre\":\"zapato\",\n        \"sku\":1231231231231233333,\n        \"precio\":100.0,\n        \"descripcion\":\"zapato asd\",\n        \"descuento\":false\n    },\n    {\n        \"nombre\":\"reloj\",\n        \"sku\":12312312312312,\n        \"precio\":100.0,\n        \"descripcion\":\"reloj asd\",\n        \"descuento\":false\n    }\n]"

        saveProductoRepository.execute(createProductos().get(0))

        mockMvc.get("/producto/") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { is2xxSuccessful() }
        }

        val result = productosController.getAllProductos()
        assertNotNull(result)
        assertEquals(result.body,producto)

    }

    @Test
    fun getProductoByProductoId(){
        val producto = "[\n    {\n        \"nombre\":\"zapato\",\n        \"sku\":1231231231231233333,\n        \"precio\":100.0,\n        \"descripcion\":\"zapato asd\",\n        \"descuento\":false\n    },\n    {\n        \"nombre\":\"reloj\",\n        \"sku\":12312312312312,\n        \"precio\":100.0,\n        \"descripcion\":\"reloj asd\",\n        \"descuento\":false\n    }\n]"
        val createdProducto = createProductos()
        val id = createdProducto.get(0).id

        saveProductoRepository.execute(createdProducto.get(0))

        mockMvc.get("/producto/$id") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { is2xxSuccessful() }
        }

        val result = productosController.getProductoByProductoId(id)
        assertNotNull(result)
        assertEquals(result.body, producto)

    }

    @Test
    fun saveProducto(){
        val producto = "[\n    {\n        \"nombre\":\"zapato\",\n        \"sku\":1231231231231233333,\n        \"precio\":100.0,\n        \"descripcion\":\"zapato asd\",\n        \"descuento\":false\n    },\n    {\n        \"nombre\":\"reloj\",\n        \"sku\":12312312312312,\n        \"precio\":100.0,\n        \"descripcion\":\"reloj asd\",\n        \"descuento\":false\n    }\n]"
        val createdProducto = createProductos()
        val id = createdProducto.get(0).id

        saveProductoRepository.execute(createdProducto.get(0))

        mockMvc.post("/producto/guardar") {
            contentType = MediaType.APPLICATION_JSON
            content = serializer.writeValueAsString(createdProducto)
        }.andExpect {
            status { is2xxSuccessful() }
        }

        val result = productosController.saveProducto(createdProducto.get(0))
        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)

    }

    @Test
    fun uploadProducto(){
        val producto = "[\n    {\n        \"nombre\":\"zapato\",\n        \"sku\":1231231231231233333,\n        \"precio\":100.0,\n        \"descripcion\":\"zapato asd\",\n        \"descuento\":false\n    },\n    {\n        \"nombre\":\"reloj\",\n        \"sku\":12312312312312,\n        \"precio\":100.0,\n        \"descripcion\":\"reloj asd\",\n        \"descuento\":false\n    }\n]"
        val createdProducto = createProductos()
        val id = createdProducto.get(0).id

        saveProductoRepository.execute(createdProducto.get(0))

        mockMvc.post("/producto/actualizar/$id") {
            contentType = MediaType.APPLICATION_JSON
            content = serializer.writeValueAsString(createdProducto)
        }.andExpect {
            status { is2xxSuccessful() }
        }

        val result = productosController.uploadProducto(id,createdProducto.get(0))
        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)

    }

    @Test
    fun removeProducto(){
        val producto = "[\n    {\n        \"nombre\":\"zapato\",\n        \"sku\":1231231231231233333,\n        \"precio\":100.0,\n        \"descripcion\":\"zapato asd\",\n        \"descuento\":false\n    },\n    {\n        \"nombre\":\"reloj\",\n        \"sku\":12312312312312,\n        \"precio\":100.0,\n        \"descripcion\":\"reloj asd\",\n        \"descuento\":false\n    }\n]"
        val createdProducto = createProductos()
        val id = createdProducto.get(0).id

        saveProductoRepository.execute(createdProducto.get(0))

        mockMvc.delete("/producto/borrar-producto/$id") {
            contentType = MediaType.APPLICATION_JSON
            content = serializer.writeValueAsString(createdProducto)
        }.andExpect {
            status { is2xxSuccessful() }
        }

        productosController.removeProducto(createdProducto.get(0))

    }


    private fun createProductos():List<Producto>{

        val producto1 = Producto(UUID.randomUUID().toString(),"",Long.MIN_VALUE, Double.MIN_VALUE,"",false)
        val producto2 = Producto(UUID.randomUUID().toString(),"",Long.MIN_VALUE, Double.MIN_VALUE,"",false)

        return listOf(producto1,producto2)
    }
}