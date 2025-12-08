package ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.actualizar

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.CategoriaDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos


class ActualizarCategoriaUseCase(private val repositorio: ICategoriaRepositorio,
                                 private val almacenDatos: AlmacenDatos) {

    suspend fun invoke(command: ActualizarCategoriaCommand, ): CategoriaDTO {

        val item: Categoria?=repositorio.getById(command.id)

        val nombreArchivo = command.imagePath.substringAfterLast('/')
        var nuevaImagePath:String?=null
        if (item==null) {
            throw IllegalArgumentException("El usuario no esta registrado.")
        }
        //se pasa a dto para tener el path
        var itemDTO: CategoriaDTO =item.toDTO(almacenDatos.getAppDataDir()+"/categorias/")

        //si las rutas son diferentes se borra y se copia
        if(itemDTO.imagePath!=command.imagePath) {
           almacenDatos.remove(itemDTO.imagePath)
            nuevaImagePath=almacenDatos.copy(command.imagePath,command.id,"/categorias/")
        }else{
           nuevaImagePath=item.imagePath
        }

        var newCategory= item.copy(
            name=command.name
            //si se ha sustituido
            //imagePath = nuevaImagePath
        )
        repositorio.update(newCategory)
        //se devuelve con el path correcto
        return newCategory.toDTO(almacenDatos.getAppDataDir()+"/categorias/")
    }
}