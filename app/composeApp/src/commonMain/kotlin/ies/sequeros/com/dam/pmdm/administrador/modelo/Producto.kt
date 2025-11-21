package ies.sequeros.com.dam.pmdm.administrador.modelo

import kotlinx.serialization.Serializable

@Serializable
data class Producto(
    var id: String,
    val name: String,
    val idCategoria: String,
    val precio: String
)
