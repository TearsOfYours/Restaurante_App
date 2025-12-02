package ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.actualizar

import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria


data class ActualizarProductoCommand(
    val name: String,
    val precio: Double,
    val idCategoria: String,
    val id: String,
    val imagePath:String
)