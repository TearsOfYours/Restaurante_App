package ies.sequeros.com.dam.pmdm.cliente.aplicacion.pedidos.activar


data class CambiarEstadoLineaPedidoCommand(
    val id: String,
    val estado: String
)
