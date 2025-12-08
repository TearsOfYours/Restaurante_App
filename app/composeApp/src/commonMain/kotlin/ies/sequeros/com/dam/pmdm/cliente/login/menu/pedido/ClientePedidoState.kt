package ies.sequeros.com.dam.pmdm.cliente.login.menu.pedido

import ies.sequeros.com.dam.pmdm.cliente.login.menu.pedido.lineapedido.LineaPedidoState

data class ClientePedidoState(
    val nombrePedido: String = "",
    val lineas: List<LineaPedidoState> = emptyList(), //Productos del pedido

    val submitted: Boolean = false,
    //Errors
    val nombrePedidoError: String? = null,
    val lineasError: String? = null
)