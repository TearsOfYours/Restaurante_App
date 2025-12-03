package ies.sequeros.com.dam.pmdm.administrador.infraestructura.memoria

import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.Producto

class MemProductoRepository : IProductoRepositorio {
    private val items = hashMapOf<String, Producto>()

    override suspend fun add(item: Producto) {
        if (!items.containsKey(item.id)) items[item.id!!] = item
        else throw IllegalArgumentException("Producto ya existe")
    }

    override suspend fun remove(item: Producto): Boolean {
        TODO("Not yet implemented")
    }

    override  suspend fun remove(id: String): Boolean {
        return items.remove(id) != null
    }

    override suspend fun update(item: Producto): Boolean {
        if (!items.containsKey(item.id)) throw IllegalArgumentException("No existe")
        items[item.id!!] = item
        return true
    }

    override suspend fun getAll(): List<Producto> = items.values.toList()

    override suspend fun findByName(name: String): Producto? =
        items.values.firstOrNull { it.name == name }

    override suspend fun getById(id: String): Producto? =
        items[id]

    override suspend fun getByCategoria(idCategoria: String): List<Producto> {
        return items.values.filter { it.idCategoria == idCategoria }
    }

}