package ies.sequeros.com.dam.pmdm.dependiente.aplicacion.pedidos.cerrar

import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio

class CerrarPedidoUseCase(private val repo: IPedidoRepositorio) {
    suspend fun ejecutar(cmd: CerrarPedidoCommand) {
        val actualizado = cmd.pedido.copy(estado="CERRADO")
        repo.update(actualizado)
    }
}