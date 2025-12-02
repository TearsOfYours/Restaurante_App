package ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar

data class PedidoDTO(
    val name: String,
    val id: String,
    val fecha: String,
    val estado: String = "PENDIENTE"
)