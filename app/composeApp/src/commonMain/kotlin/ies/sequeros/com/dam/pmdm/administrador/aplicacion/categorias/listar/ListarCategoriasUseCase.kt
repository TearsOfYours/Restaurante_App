package ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar


import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio




class ListarCategoriasUseCase(private val repositorio: ICategoriaRepositorio, private val almacenDatos: AlmacenDatos) {

    suspend fun invoke( ): List<CategoriaDTO> {
        //this.validateUser(user)
        //si tiene imagen
        val items= repositorio.getAll().map {
            it.toDTO(if(it.imagePath.isEmpty()) "" else
             almacenDatos.getAppDataDir()+"/categorias/") }
        return items
    }
}