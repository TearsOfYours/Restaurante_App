package ies.sequeros.com.dam.pmdm.administrador.infraestructura

import ies.sequeros.com.dam.pmdm.administrador.infraestructura.dependientes.BBDDRepositorioDependientesJava
import ies.sequeros.com.dam.pmdm.administrador.modelo.Dependiente
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio


class BBDDDependienteRepository(
    private val bbddRepositorioDepedientesJava: BBDDRepositorioDependientesJava
) : IDependienteRepositorio {
    override suspend fun add(item: Dependiente) {
        bbddRepositorioDepedientesJava.add(item)
    }

    override suspend fun remove(item: Dependiente): Boolean {
        bbddRepositorioDepedientesJava.remove(item)
        return true
    }
    override suspend fun remove(id: String): Boolean {

        bbddRepositorioDepedientesJava.remove(id)
        return true

    }

    override suspend fun update(item: Dependiente): Boolean {

        bbddRepositorioDepedientesJava.update(item)
        return true
    }

    override suspend fun getAll(): List<Dependiente> {

        return bbddRepositorioDepedientesJava.all
    }

    override suspend fun findByName(name: String): Dependiente? {

        return bbddRepositorioDepedientesJava.findByName( name)
    }
    override suspend fun getById(id: String): Dependiente? {
        return bbddRepositorioDepedientesJava.getById(id)
    }
}