package ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.cambiarcontrasena

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.DependienteDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.Dependiente
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio

//CODIFICAR - LAST PART

class CambiarContrasenaUseCase(private val repositorio: IDependienteRepositorio) {
    suspend fun invoke(command: CambiarContrasenaCommand): DependienteDTO {
        val dependiente: Dependiente?=repositorio.getById(command.id)
        if (dependiente == null) {
            throw Exception("El usuario no se ha encontrado")
        } else {
            if (dependiente.password == (command.oldPassword)){
                dependiente.password == (command.newPassword)
                repositorio.update(dependiente)
            }
        }
        return dependiente.toDTO()
    }
}