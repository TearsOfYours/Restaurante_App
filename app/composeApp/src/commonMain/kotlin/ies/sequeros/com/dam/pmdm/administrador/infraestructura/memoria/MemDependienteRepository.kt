package ies.sequeros.com.dam.pmdm.administrador.infraestructura.memoria

import ies.sequeros.com.dam.pmdm.administrador.modelo.Dependiente
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio


class MemDependienteRepository: IDependienteRepositorio {

    private val items=hashMapOf<String, Dependiente>();
    init{
       val u1= Dependiente("02830d95-5ada-403d-9f66-c6c5591240d8","Paco","inventado@gmail.com","1234","ara",true,true)
        items.put(u1.id!!,u1)
        val u2= Dependiente("0faea46f-b745-4fac-8048-2de194360332","Pedro","pedro@gmail.com","1234","midori",true,false)
        items.put(u2.id!!,u2)
        val u3= Dependiente("f5be7eab-dbeb-4c54-870a-94c680ae6266","Maria","maria@gmail.com","1234","dr",true,false)
        items.put(u3.id!!,u3)
    }
    override suspend  fun add(item: Dependiente) {
        if( !items.containsKey(item.id)){
            items.put(item.id!!,item)
        }else{
            throw IllegalArgumentException("ALTA:El usuario con id:"+item.id+" ya existe")
        }
    }

    override suspend  fun remove(item: Dependiente): Boolean {
       return this.remove(item.id!!)
    }

    override suspend fun remove(id: String): Boolean {
        if( items.containsKey(id)){
            items.remove((id))
            return true
        }else{
            throw IllegalArgumentException("BORRADO:" +
                    " El usuario con id:"+id+" NO  existe")
        }
    }

    override suspend fun update(item: Dependiente): Boolean {
        if( items.containsKey(item.id)){
            items[item.id!!]=item
            return true
        }else{
            throw IllegalArgumentException("ACTUALIZACION: " +
                    "El usuario con id:"+item.id+" NO existe")
        }
    }

    override suspend fun getAll(): List<Dependiente> {
      return this.items.values.toList();
    }

    override suspend fun findByName(name: String): Dependiente? {
        return this.items.values.firstOrNull { it.name.equals(name) };
    }

    override suspend fun getById(id: String): Dependiente? {
        return this.items[id];
    }


}