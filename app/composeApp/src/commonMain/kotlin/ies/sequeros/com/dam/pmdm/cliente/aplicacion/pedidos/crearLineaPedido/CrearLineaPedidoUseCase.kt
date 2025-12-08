package ies.sequeros.com.dam.pmdm.cliente.aplicacion.pedidos.crearLineaPedido

import ies.sequeros.com.dam.pmdm.administrador.modelo.ILineaPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.LineaPedido

class CrearLineaPedidoUseCase(
    private val lineaRepo: ILineaPedidoRepositorio
) {
    suspend fun invoke(linea: LineaPedido) {
        lineaRepo.create(linea)
    }
}