package ies.sequeros.com.dam.pmdm.administrador.modelo

interface  IDependienteRepositorio {
    suspend fun add(item:Dependiente):Unit
    suspend fun remove(item:Dependiente): Boolean
    suspend fun remove(id:String): Boolean
    suspend fun update(item:Dependiente): Boolean
    suspend fun getAll():List<Dependiente>
    suspend fun findByName(name:String): Dependiente?
    suspend fun getById(id:String):Dependiente?

}