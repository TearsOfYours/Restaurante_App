package ies.sequeros.com.dam.pmdm.dependiente.aplicacion.pedidos.obtener

import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio

class ObtenerPedidosUseCase(private val repo: IPedidoRepositorio) {
    suspend fun ejecutar(cmd: ObtenerPedidoCommand) = repo.getAll()
}