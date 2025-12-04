package ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.activar


data class ActivarProductoCommand(
    val id: String,
    val enabled: Boolean
)