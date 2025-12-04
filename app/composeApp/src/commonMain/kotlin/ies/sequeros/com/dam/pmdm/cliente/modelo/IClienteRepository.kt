package ies.sequeros.com.dam.pmdm.cliente.modelo

interface ClienteRepository {
    fun guardar(cliente: Cliente)
    fun buscarPorId(id: String): Cliente?
    fun listar(): List<Cliente>
    fun eliminar(id: String)
}