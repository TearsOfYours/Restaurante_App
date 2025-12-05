package ies.sequeros.com.dam.pmdm.administrador.modelo

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar.PedidoDTO

interface IPedidoRepositorio {
    suspend fun getAll(): List<Pedido>
    suspend fun getById(id: String): Pedido?
    suspend fun getByCliente(clienteName: String): List<Pedido>
    suspend fun create(pedido: Pedido)
    suspend fun update(pedido: Pedido)
}