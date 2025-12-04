package ies.sequeros.com.dam.pmdm.cliente.aplicacion.listar
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.CategoriaDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ProductoDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria
import ies.sequeros.com.dam.pmdm.administrador.modelo.Producto
import ies.sequeros.com.dam.pmdm.cliente.modelo.CategoriaTPVDTO
import ies.sequeros.com.dam.pmdm.cliente.modelo.ProductoTPVDTO

fun Categoria.toTPV(path: String = "") = CategoriaTPVDTO(
    name = this.name,
    id = this.id,
    descripcion = "",
    imagePath = path + this.imagePath,
    productos = emptyList()
)

fun Producto.toTPV(path: String = "") = ProductoTPVDTO(
    name = this.name,
    precio = this.precio,
    imagePath = path + this.imagePath,
    idCategoria = this.idCategoria
)

