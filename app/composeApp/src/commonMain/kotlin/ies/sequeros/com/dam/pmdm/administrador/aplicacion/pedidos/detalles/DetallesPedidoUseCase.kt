package ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.detalles

import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio

class DetallesPedidoUseCase(
    private val repositorio: IPedidoRepositorio
) {

    suspend fun invoke(id: String): DetallesPedidoCommand {
        val pedido = repositorio.getById(id)
            ?: throw IllegalArgumentException("El pedido con ese id no existe")

        return DetallesPedidoCommand(
            fecha = pedido.fecha,
            precio = pedido.precio,
            name = pedido.name
        )
    }
}
