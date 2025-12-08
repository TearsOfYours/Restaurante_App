package ies.sequeros.com.dam.pmdm.cliente.modelo


interface IClienteRepositorio {
    suspend fun save(cliente: Cliente)
    suspend fun getById(id: String): Cliente?
    suspend fun listar(): List<Cliente>
    suspend fun remove(id: String)
}

