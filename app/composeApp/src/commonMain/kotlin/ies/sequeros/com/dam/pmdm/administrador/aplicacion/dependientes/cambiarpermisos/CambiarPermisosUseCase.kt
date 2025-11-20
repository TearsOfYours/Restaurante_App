package ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.cambiarpermisos
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.DependienteDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.toDTO
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos

import ies.sequeros.com.dam.pmdm.administrador.modelo.Dependiente
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio


class CambiarPermisosUseCase(private val repositorio: IDependienteRepositorio, private val almacenDatos: AlmacenDatos) {

    suspend fun invoke(command: CambiarPermisosCommand ): DependienteDTO {

        val item: Dependiente?=repositorio.getById(command.id)
        if (item==null) {
            throw IllegalArgumentException("El usuario no esta registrado.")
        }
        var newUser= item.copy(
            isAdmin = command.isAdmin,
        )
        repositorio.update(newUser)
        //se devuelve con el path correcto
        return newUser.toDTO(almacenDatos.getAppDataDir()+"/dependientes/")
    }
}