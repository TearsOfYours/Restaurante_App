package ies.sequeros.com.dam.pmdm.administrador.infraestructura.memoria

import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria
import ies.sequeros.com.dam.pmdm.administrador.modelo.Dependiente
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio

class MemCategoriaRepository: ICategoriaRepositorio {

    private val items=hashMapOf<String, Categoria>();
    init{
        val c1= Categoria("0239485-5ada-403d-9f66-c6c5591240d8","Hamburguesas", "", true)
        items.put(c1.id!!,c1)
        val c2= Categoria("0faea46f-b745-4fac-8048-2de194360332","Complementos", "", true)
        items.put(c2.id!!,c2)
        val c3= Categoria("f5be7eab-dbeb-4c54-870a-94c680ae6266","Bebidas", "", true)
        items.put(c3.id!!,c3)
    }
    override suspend  fun add(item: Categoria) {
        if( !items.containsKey(item.id)){
            items.put(item.id!!,item)
        }else{
            throw IllegalArgumentException("ALTA:La categoria con:"+item.id+" ya existe")
        }
    }

    override suspend  fun remove(item: Categoria): Boolean {
        return this.remove(item.id!!)
    }

    override suspend fun remove(id: String): Boolean {
        if( items.containsKey(id)){
            items.remove((id))
            return true
        }else{
            throw IllegalArgumentException("BORRADO:" +
                    " La categoria con id:"+id+" NO  existe")
        }
    }

    override suspend fun update(item: Categoria): Boolean {
        if( items.containsKey(item.id)){
            items[item.id!!]=item
            return true
        }else{
            throw IllegalArgumentException("ACTUALIZACION: " +
                    "La categoria con id:"+item.id+" NO existe")
        }
    }

    override suspend fun getAll(): List<Categoria> {
        return this.items.values.toList();
    }

    override suspend fun findByName(name: String): Categoria? {
        return this.items.values.firstOrNull { it.name.equals(name) };
    }

    override suspend fun getById(id: String): Categoria? {
        return this.items[id];
    }


}