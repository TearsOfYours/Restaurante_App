package ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio


class BorrarCategoriaUseCase(private val repositorio: ICategoriaRepositorio, private val almacenDatos: AlmacenDatos) {

    suspend  fun invoke(id: String) {
        val tempo=repositorio.getById(id)
        val elementos=repositorio.getAll();
        //this.validateUser(user)
        if (tempo==null) {
            throw IllegalArgumentException("La categoría no existe.")
        }
        //se borra del repositorio
        val tempoDto=tempo.toDTO(almacenDatos.getAppDataDir()+"/categorias/")


        repositorio.remove(id)
        //se borra la imagen una vez borrado del repositorio
        //almacenDatos.remove(tempoDto.imagePath)
    }
}