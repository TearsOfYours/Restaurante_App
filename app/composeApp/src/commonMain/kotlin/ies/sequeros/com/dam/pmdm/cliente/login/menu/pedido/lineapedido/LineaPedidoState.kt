package ies.sequeros.com.dam.pmdm.cliente.login.menu.pedido.lineapedido

data class LineaPedidoState(
    val idProducto: String = "",
    val nombreProducto: String = "",
    val precioUnitario: Double = 0.0,
    val cantidad: Int = 1,

    //Errors
    val cantidadError: String? = null
)