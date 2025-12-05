package ies.sequeros.com.dam.pmdm.administrador.infraestructura.memoria

import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.LineaPedido
import ies.sequeros.com.dam.pmdm.administrador.modelo.Pedido

class MemPedidoRepository : IPedidoRepositorio {

    private val items = hashMapOf<String, Pedido>()

    init {
        val p1 = Pedido(
            id = "1",
            name = "Juan",
            fecha = "2024-01-01",
            lineas = listOf(LineaPedido(idProducto = "prod1", cantidad = 1, precioUnitario = 12.50))
        )
        val p2 = Pedido(
            id = "2",
            name = "Pepe",
            fecha = "2024-01-02",
            lineas = listOf(LineaPedido(idProducto = "prod2", cantidad = 2, precioUnitario = 4.10))
        )
        items[p1.id] = p1
        items[p2.id] = p2
    }

    override suspend fun getAll(): List<Pedido> = items.values.toList()

    override suspend fun getById(id: String): Pedido? = items[id]
    override suspend fun getByCliente(clienteName: String): List<Pedido> =
        items.values.filter { it.name == clienteName }

    override suspend fun create(pedido: Pedido) {
        items[pedido.id] = pedido
    }

    override suspend fun update(pedido: Pedido) {
        items[pedido.id] = pedido
    }
    suspend fun findByName(name: String): Pedido? =
        items.values.firstOrNull { it.name == name }
}
