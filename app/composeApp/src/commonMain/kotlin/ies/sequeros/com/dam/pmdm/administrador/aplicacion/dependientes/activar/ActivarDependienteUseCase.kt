package ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.activar


import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.DependienteDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.toDTO
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos

import ies.sequeros.com.dam.pmdm.administrador.modelo.Dependiente
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio


class ActivarDependienteUseCase(private val repositorio: IDependienteRepositorio,private val almacenDatos: AlmacenDatos){

    suspend fun invoke(command: ActivarDependienteCommand ): DependienteDTO {
        val item: Dependiente?=repositorio.getById(command.id)
        if (item==null) {
            throw IllegalArgumentException("El usuario no esta registrado.")
        }
        var newUser= item.copy(
            enabled = command.enabled,
        )
        repositorio.update(newUser)
        //se devuelve con el path correcto
        return newUser.toDTO(almacenDatos.getAppDataDir()+"/dependientes/")
    }
}