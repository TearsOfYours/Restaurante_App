package ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.cambiaclave



data class CambiarClaveDependienteCommand(
    val id: String,
    val oldPassword: String,
    val newPassword: String
)