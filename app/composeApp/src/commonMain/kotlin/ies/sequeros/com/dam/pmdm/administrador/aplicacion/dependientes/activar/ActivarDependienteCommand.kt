package ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.activar


data class ActivarDependienteCommand(
    val id: String,
    val enabled: Boolean,

)