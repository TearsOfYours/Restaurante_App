package ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.cambiaclave
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.DependienteDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.Dependiente
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos

//CODIFICAR - LAST PART

class CambiarClaveDependienteUseCase(
    private val repositorio: IDependienteRepositorio,
    almacenDatos: AlmacenDatos
) {
    suspend fun invoke(command: CambiarClaveDependienteCommand): DependienteDTO {
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