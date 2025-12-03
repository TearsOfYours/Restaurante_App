package ies.sequeros.com.dam.pmdm.administrador.modelo

import kotlinx.serialization.Serializable

@Serializable
data class Pedido(
    val name: String,
    val id: String,
    val fecha: String,
    val precio: Double,
    val estado: String
    // val estado:String = "PENDIENTE"
    // val lineas: List<LineaPedido> = emptyList()
)

data class LineaPedido(
    val sm: String
    //idProducto, cantidad, precioUnitario
)