package ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.detalles.DetallesPedidoCommand
import ies.sequeros.com.dam.pmdm.administrador.modelo.Pedido

fun Pedido.toDTO() = PedidoDTO(
    name = name,
    id = id,
    fecha = fecha,
    estado = estado
)
fun PedidoDTO.toPedido() = Pedido(
    name = name,
    id = id,
    fecha = fecha,
    lineas = emptyList(),
    estado = estado
)
fun Pedido.toDetallesCommand() = DetallesPedidoCommand(
    fecha = fecha,
    precio = precioTotal,
    name = name
)