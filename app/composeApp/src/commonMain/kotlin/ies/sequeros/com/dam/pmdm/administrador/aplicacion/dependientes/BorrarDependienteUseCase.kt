package ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.toDTO
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio


class BorrarDependienteUseCase(private val repositorio: IDependienteRepositorio,private val almacenDatos: AlmacenDatos) {

    suspend  fun invoke(id: String) {
        val tempo=repositorio.getById(id)
        val elementos=repositorio.getAll();
        //this.validateUser(user)
        if (tempo==null) {
            throw IllegalArgumentException("El id no está registrado.")
        }
        //se borra del repositorio
        val tempoDto=tempo.toDTO(almacenDatos.getAppDataDir()+"/dependientes/")

        repositorio.remove(id)
        //se borra la imagen una vez borrado del repositorio
        almacenDatos.remove(tempoDto.imagePath)
    }
}