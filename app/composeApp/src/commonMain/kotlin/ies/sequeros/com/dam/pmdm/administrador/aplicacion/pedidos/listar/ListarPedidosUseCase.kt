package ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar


import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.Pedido
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos


class ListarPedidosUseCase(
    private val pedidosRepo: IPedidoRepositorio
) {
    suspend fun invoke(): List<Pedido> {
        return pedidosRepo.getAll()
    }
}
