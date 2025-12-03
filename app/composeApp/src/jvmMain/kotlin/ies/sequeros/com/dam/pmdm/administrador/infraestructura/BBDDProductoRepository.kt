package ies.sequeros.com.dam.pmdm.administrador.infraestructura

import ies.sequeros.com.dam.pmdm.administrador.infraestructura.productos.BBDDRepositorioProductosJava
import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.Producto

class BBDDProductoRepository(
    private val bbddRepositorioProductosJava: BBDDRepositorioProductosJava
) : IProductoRepositorio {

    override suspend fun add(item: Producto) {
        bbddRepositorioProductosJava.add(item)
    }

    override suspend fun remove(item: Producto): Boolean {
        bbddRepositorioProductosJava.remove(item)
        return true
    }

    override suspend fun remove(id: String): Boolean {
        bbddRepositorioProductosJava.remove(id)
        return true
    }

    override suspend fun update(item: Producto): Boolean {
        bbddRepositorioProductosJava.update(item)
        return true
    }

    override suspend fun getAll(): List<Producto> {
        return bbddRepositorioProductosJava.getAll()
    }

    override suspend fun findByName(name: String): Producto? {
        return bbddRepositorioProductosJava.findByName(name)
    }

    override suspend fun getById(id: String): Producto? {
        return bbddRepositorioProductosJava.getById(id)
    }

    override suspend fun getByCategoria(idCategoria: String): List<Producto> {
        return bbddRepositorioProductosJava.getByCategoria(idCategoria)
    }
}