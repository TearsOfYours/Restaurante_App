package ies.sequeros.com.dam.pmdm.cliente.aplicacion.pedidos

import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.Pedido
import ies.sequeros.com.dam.pmdm.administrador.modelo.Pedido

class CreaPedidoUseCase(
    private val pedidosRepo: IPedidoRepositorio
) {
    suspend fun invoke(pedido: Pedido) {
        pedidosRepo.create(pedido)
    }
}