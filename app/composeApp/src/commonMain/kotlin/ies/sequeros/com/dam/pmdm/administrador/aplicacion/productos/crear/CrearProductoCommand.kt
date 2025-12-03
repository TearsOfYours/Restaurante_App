package ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.crear



data class CrearProductoCommand(
    val name: String,
    val precio: Double,
    val idCategoria: String,
    val imagePath: String
)
