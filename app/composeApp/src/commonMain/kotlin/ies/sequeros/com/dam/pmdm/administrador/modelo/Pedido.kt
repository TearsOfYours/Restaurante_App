package ies.sequeros.com.dam.pmdm.administrador.modelo

import kotlinx.serialization.Serializable

@Serializable
data class LineaPedido(
    val idProducto: String,
    val cantidad: Int,
    val precioUnitario: Double
) {
    val subtotal: Double get() = cantidad * precioUnitario
}

@Serializable
data class Pedido(
    val id: String,
    val name: String,
    val fecha: String,
    val lineas: List<LineaPedido>,
    val estado: String = "PENDIENTE",
    val idCliente: String
) {
    val precioTotal: Double get() = lineas.sumOf { it.subtotal }
}