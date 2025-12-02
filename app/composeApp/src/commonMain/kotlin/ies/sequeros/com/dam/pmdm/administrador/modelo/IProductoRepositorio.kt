package ies.sequeros.com.dam.pmdm.administrador.modelo

interface IProductoRepositorio {
    suspend fun add(item:Producto):Unit
    suspend fun remove(item:Producto): Boolean
    suspend fun remove(id:String): Boolean
    suspend fun update(item:Producto): Boolean
    suspend fun getAll():List<Producto>
    suspend fun findByName(name:String): Producto?
    suspend fun getById(id:String):Producto?
    suspend fun getByCategoria(idCategoria: String): List<Producto>
}