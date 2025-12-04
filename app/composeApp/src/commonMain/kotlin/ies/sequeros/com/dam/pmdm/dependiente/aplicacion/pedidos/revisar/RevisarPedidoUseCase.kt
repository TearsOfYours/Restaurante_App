package ies.sequeros.com.dam.pmdm.dependiente.aplicacion.pedidos.revisar

import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio

class RevisarPedidoUseCase(private val repo: IPedidoRepositorio) {
    suspend fun ejecutar(cmd: RevisarPedidoCommand) {
        val actualizado = cmd.pedido.copy(estado="REVISADO")
        repo.update(actualizado)
    }
}