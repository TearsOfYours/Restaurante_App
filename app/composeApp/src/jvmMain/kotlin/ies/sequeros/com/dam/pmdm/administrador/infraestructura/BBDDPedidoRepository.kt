package ies.sequeros.com.dam.pmdm.administrador.infraestructura

import ies.sequeros.com.dam.pmdm.administrador.infraestructura.pedidos.BBDDRepositorioPedidosJava
import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.Pedido

class BBDDPedidoRepository(
    private val bbddRepositorioPedidosJava: BBDDRepositorioPedidosJava
) : IPedidoRepositorio {

    override suspend fun getAll(): List<Pedido> {
        return bbddRepositorioPedidosJava.getAll()
    }

    override suspend fun findByName(name: String): Pedido? {
        return bbddRepositorioPedidosJava.findByName(name)
    }

    override suspend fun getById(id: String): Pedido? {
        return bbddRepositorioPedidosJava.getById(id)
    }

    override suspend fun create(pedido: Pedido) {
        bbddRepositorioPedidosJava.add(pedido)
    }

    override suspend fun update(pedido: Pedido) {
        bbddRepositorioPedidosJava.update(pedido)
    }
}
