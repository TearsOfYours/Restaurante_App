package ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar

data class ProductoDTO (
    val name: String,
    val precio: Double,
    val idCategoria: String,
    val id: String,
    val imagePath: String,
    val enabled: Boolean
)