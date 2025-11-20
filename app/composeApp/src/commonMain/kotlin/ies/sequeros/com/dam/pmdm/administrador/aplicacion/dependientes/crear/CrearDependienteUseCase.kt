package ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.crear

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.DependienteDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.toDTO
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.administrador.modelo.Dependiente
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio
import ies.sequeros.com.dam.pmdm.generateUUID


class CrearDependienteUseCase(private val repositorio: IDependienteRepositorio,private val almacenDatos: AlmacenDatos)  {

    suspend  fun invoke(createUserCommand: CrearDependienteCommand): DependienteDTO {
        //this.validateUser(user)
        if (repositorio.findByName(createUserCommand.name)!=null) {
            throw IllegalArgumentException("El nombre ya está registrado.")
        }
        val id=generateUUID()
        //se almacena el fichero
        val imageName=almacenDatos.copy(createUserCommand.imagePath,id,"/dependientes/")
        val item = Dependiente(
            id = id,
            name = createUserCommand.name,
            email = createUserCommand.email,
            imagePath = imageName,
            password = createUserCommand.password,
            enabled = createUserCommand.enabled,
            isAdmin = createUserCommand.admin
        )
        val element=repositorio.findByName(item.name)
        if(element!=null)
            throw IllegalArgumentException("El nombre ya está registrado.")
        repositorio.add(item)
        return item.toDTO( almacenDatos.getAppDataDir()+"/dependientes/");
    }
}