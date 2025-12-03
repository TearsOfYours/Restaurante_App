package ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos


class BorrarProductoUseCase(private val repositorio: IProductoRepositorio, private val almacenDatos: AlmacenDatos) {

    suspend  fun invoke(id: String) {
        val tempo=repositorio.getById(id)
        val elementos=repositorio.getAll();
        //this.validateUser(user)
        if (tempo==null) {
            throw IllegalArgumentException("El producto no existe.")
        }
        //se borra del repositorio
        val tempoDto=tempo.toDTO(almacenDatos.getAppDataDir()+"/productos/")


        repositorio.remove(id)
        //se borra la imagen una vez borrado del repositorio
        almacenDatos.remove(tempoDto.imagePath)
    }
}