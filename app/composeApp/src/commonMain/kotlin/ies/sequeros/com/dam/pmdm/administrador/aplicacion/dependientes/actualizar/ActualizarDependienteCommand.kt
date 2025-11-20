package ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.actualizar


data class ActualizarDependienteCommand(
    val id: String,
    val name: String,
    val email: String,
    val imagePath: String,
    val enabled: Boolean,
    val admin: Boolean
)