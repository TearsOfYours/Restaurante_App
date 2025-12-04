package ies.sequeros.com.dam.pmdm.administrador.modelo

import kotlinx.serialization.Serializable
@Serializable
data class Categoria (
    var id:String,
    val name:String,
    val imagePath: String,
    val enabled:Boolean = true
)