package ies.sequeros.com.dam.pmdm.administrador.infraestructura

import ies.sequeros.com.dam.pmdm.administrador.infraestructura.categorias.BBDDRepositorioCategoriasJava
import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio

class BBDDCategoriaRepository(
    private val bbddRepositorioCategoriasJava: BBDDRepositorioCategoriasJava
) : ICategoriaRepositorio {
    override suspend fun add (item:Categoria){
        bbddRepositorioCategoriasJava.add(item)
    }

    override suspend fun remove (item:Categoria):Boolean {
        bbddRepositorioCategoriasJava.remove(item)
        return true
    }
    override suspend fun remove (id:String):Boolean {

        bbddRepositorioCategoriasJava.remove(id)
        return true

    }

    override suspend fun update (item:Categoria):Boolean {

        bbddRepositorioCategoriasJava.update(item)
        return true
    }

    override suspend fun getAll ():List<Categoria> {

        return bbddRepositorioCategoriasJava.all
    }

    override suspend fun findByName (name:String):Categoria ? {

        return bbddRepositorioCategoriasJava.findByName(name)
    }
    override suspend fun getById (id:String):Categoria ? {
        return bbddRepositorioCategoriasJava.getById(id)
    }
}
