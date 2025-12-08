package ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar

import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria
import ies.sequeros.com.dam.pmdm.administrador.modelo.Producto

fun Producto.toDTO(path: String) = ProductoDTO(
    name = this.name,
    precio = this.precio,
    idCategoria = this.idCategoria,
    id = this.id,
    imagePath = path + imagePath,
    descripcion = descripcion,
    enabled = enabled
)
fun ProductoDTO.toProducto() = Producto(
    name = name,
    precio = precio,
    idCategoria = idCategoria,
    id = id,
    imagePath = imagePath,
    descripcion = descripcion,
    enabled = enabled
)

