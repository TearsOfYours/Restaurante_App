package ies.sequeros.com.dam.pmdm.administrador.infraestructura

import ies.sequeros.com.dam.pmdm.administrador.infraestructura.productos.BBDDRepositorioProductosJava
import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.Producto

class BBDDProductoRepository(
    private val bbddRepositorioProductosJava: BBDDRepositorioProductosJava
) : ICategoriaRepositorio {
    suspend fun add (item: Producto){
        bbddRepositorioProductosJava.add(item)
    }

    suspend fun remove (item:Producto):Boolean {
        bbddRepositorioProductosJava.remove(item)
        return true
    }

    override suspend fun add(item: Categoria) {
        TODO("Not yet implemented")
    }

    override suspend fun remove(item: Categoria): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun remove (id:String):Boolean {

        bbddRepositorioProductosJava.remove(id)
        return true

    }

    override suspend fun update(item: Categoria): Boolean {
        TODO("Not yet implemented")
    }

    suspend fun update (item:Producto):Boolean {

        bbddRepositorioProductosJava.update(item)
        return true
    }

    override suspend fun getAll (): List<Categoria> {

        return bbddRepositorioProductosJava.all as List<Categoria>
    }

    override suspend fun findByName (name:String): Categoria? {

        return bbddRepositorioProductosJava.findByName(name) as Categoria?
    }
    override suspend fun getById (id:String): Categoria? {
        return bbddRepositorioProductosJava.getById(id) as Categoria?
    }
}