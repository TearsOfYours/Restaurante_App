package ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.actualizar
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.DependienteDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.toDTO
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos

import ies.sequeros.com.dam.pmdm.administrador.modelo.Dependiente
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio


class ActualizarDependienteUseCase(private val repositorio: IDependienteRepositorio,
                                   private val almacenDatos: AlmacenDatos) {

    suspend fun invoke(command: ActualizarDependienteCommand, ): DependienteDTO {

        val item: Dependiente?=repositorio.getById(command.id)

        //val nombreArchivo = command.imagePath.substringAfterLast('/')
        var nuevaImagePath:String?=null
        if (item==null) {
            throw IllegalArgumentException("El usuario no esta registrado.")
        }
        //se pasa a dto para tener el path
        var itemDTO: DependienteDTO=item.toDTO(almacenDatos.getAppDataDir()+"/dependientes/")

        //si las rutas son diferentes se borra y se copia
        if(itemDTO.imagePath!=command.imagePath) {
            almacenDatos.remove(itemDTO.imagePath)
            nuevaImagePath=almacenDatos.copy(command.imagePath,command.id,"/dependientes/")
        }else{
            nuevaImagePath=item.imagePath
        }

        var newUser= item.copy(
            name=command.name,
            email = command.email,
            //si se ha sustituido
            imagePath = nuevaImagePath,
            enabled = command.enabled,
            isAdmin = command.admin
        )
        repositorio.update(newUser)
        //se devuelve con el path correcto
        return newUser.toDTO(almacenDatos.getAppDataDir()+"/dependientes/")
    }
}