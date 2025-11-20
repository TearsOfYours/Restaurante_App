package ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.cambiarpermisos


data class CambiarPermisosCommand(
    val id: String,
    val isAdmin: Boolean,

)