package ies.sequeros.com.dam.pmdm.administrador.modelo
import kotlinx.serialization.Serializable
@Serializable
data class Dependiente(
    var id:String,
    val name:String,
    val email:String,
    val password:String,
    val imagePath:String,
    val enabled: Boolean,
    val isAdmin:Boolean
)

