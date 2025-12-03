package ies.sequeros.com.dam.pmdm.cliente.aplicacion.pedidos

import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio

class CreaPedidoUseCase(private val repo: IPedidoRepositorio) {
    suspend fun ejecutar(cmd: CrearPedidoCommand) {
        repo.create(cmd.pedido)
    }
}