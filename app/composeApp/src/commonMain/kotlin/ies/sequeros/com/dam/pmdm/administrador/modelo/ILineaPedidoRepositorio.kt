package ies.sequeros.com.dam.pmdm.administrador.modelo

interface ILineaPedidoRepositorio {
    suspend fun getAll(): List<LineaPedido>
    suspend fun getById(id: String): LineaPedido?
    suspend fun getByPedido(idPedido: String): List<LineaPedido>
    suspend fun create(linea: LineaPedido)
    suspend fun update(linea: LineaPedido): Boolean
}