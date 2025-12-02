package ies.sequeros.com.dam.pmdm.administrador.modelo

import kotlinx.serialization.Serializable

@Serializable
data class Producto(
    val name: String,
    val idCategoria: String,
    var id: String,
    val precio: Double,
    val imagePath:String
    // val activo:Boolean = true
)
