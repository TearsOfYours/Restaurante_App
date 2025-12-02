package ies.sequeros.com.dam.pmdm.administrador.modelo

interface ICategoriaRepositorio {
suspend fun add(item:Categoria):Unit
suspend fun remove(item:Categoria): Boolean
suspend fun remove(id:String): Boolean
suspend fun update(item:Categoria): Boolean
suspend fun getAll(): List<Categoria>
suspend fun findByName(name:String): Categoria?
suspend fun getById(id:String): Categoria?
}