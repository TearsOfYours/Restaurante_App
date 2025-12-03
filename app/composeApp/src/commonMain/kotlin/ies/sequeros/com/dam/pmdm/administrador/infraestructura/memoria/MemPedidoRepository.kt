package ies.sequeros.com.dam.pmdm.administrador.infraestructura.memoria

import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.Pedido

class MemPedidoRepository : IPedidoRepositorio {

    private val items = hashMapOf<String, Pedido>()

    init {
        val p1 = Pedido(name = "Juan", id = "1", fecha = "2024-01-01", precio = 12.50, estado = "PENDIENTE")
        val p2 = Pedido(name = "Pepe", id = "2", fecha = "2024-01-02", precio = 8.20, estado = "PENDIENTE")
        items[p1.id] = p1
        items[p2.id] = p2
    }

    override suspend fun getAll(): List<Pedido> = items.values.toList()

    override suspend fun getById(id: String): Pedido? = items[id]
    override suspend fun create(pedido: Pedido) {
        items[pedido.id] = pedido
    }

    override suspend fun update(pedido: Pedido) {
        items[pedido.id] = pedido
    }
    override suspend fun findByName(name: String): Pedido? =
        items.values.firstOrNull { it.name == name }
}
