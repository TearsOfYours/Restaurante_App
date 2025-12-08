package ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.detalles

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar.LineaPedidoDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio

class DetallesPedidoUseCase(
    private val repositorio: IPedidoRepositorio,
    private val productoRepo: IProductoRepositorio
) {

    suspend fun invoke(id: String): Pair<DetallesPedidoCommand, List<LineaPedidoDTO>> {
        val pedido = repositorio.getById(id)
            ?: throw IllegalArgumentException("El pedido con ese id no existe")

        val lineasDTO = pedido.lineas.map { linea ->
            val producto = productoRepo.getById(linea.idProducto)
            LineaPedidoDTO(
                nombreProducto = producto?.name ?: "Desconocido",
                cantidad = linea.cantidad,
                precioUnitario = linea.precioUnitario,
                subtotal = linea.subtotal
            )
        }

        val resumen = DetallesPedidoCommand(
            fecha = pedido.fecha,
            precio = pedido.precioTotal,
            name = pedido.name
        )

        return resumen to lineasDTO
    }
}