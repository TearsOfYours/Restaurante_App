package ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.cambiaclave
import ies.sequeros.com.dam.pmdm.administrador.modelo.Dependiente
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio


class CambiarClaveDependienteUseCase(private val repositorio: IDependienteRepositorio) {

    suspend fun invoke(cambiarClaveDependienteCommand: CambiarClaveDependienteCommand) {

        val item: Dependiente?=repositorio.findByName(cambiarClaveDependienteCommand.name)
        if (item==null) {
            throw IllegalArgumentException("El usuario no esta registrado.")
        }
        var newItem= item.copy(
            password=cambiarClaveDependienteCommand.password
        )
        repositorio.update(newItem)

    }
}