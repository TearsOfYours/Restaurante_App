package ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.crear



data class CrearDependienteCommand(val name: String,
                                   val email: String,
                                   val password: String,
                                   val imagePath: String,
                                   val enabled: Boolean,
                                   val admin: Boolean)