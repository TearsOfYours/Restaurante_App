package ies.sequeros.com.dam.pmdm.administrador.infraestructura

import ies.sequeros.com.dam.pmdm.administrador.modelo.ILineaPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.LineaPedido
import ies.sequeros.com.dam.pmdm.cliente.infraestructura.pedidos.BBDDRepositorioLineaPedidosJava

class BBDDLineaPedidoRepository(
    private val bbddRepositorioLineaPedidosJava: BBDDRepositorioLineaPedidosJava
) : ILineaPedidoRepositorio {

    override suspend fun create(linea: LineaPedido) {
        bbddRepositorioLineaPedidosJava.add(linea)
    }

    override suspend fun update(linea: LineaPedido): Boolean {
        bbddRepositorioLineaPedidosJava.update(linea)
        return true
    }

    override suspend fun getAll(): List<LineaPedido> {
        return bbddRepositorioLineaPedidosJava.getAll()
    }

    override suspend fun getById(id: String): LineaPedido? {
        return bbddRepositorioLineaPedidosJava.getById(id)
    }

    override suspend fun getByPedido(idPedido: String): List<LineaPedido> {
        return bbddRepositorioLineaPedidosJava.getByPedido(idPedido)
    }
}

