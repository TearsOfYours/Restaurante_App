package ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.cambiarcontrasena

data class CambiarContrasenaCommand(
    val id: String,
    val oldPassword: String,
    val newPassword: String
)