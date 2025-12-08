package ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.crear

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.CategoriaDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.generateUUID


class CrearCategoriaUseCase(private val repositorio: ICategoriaRepositorio, private val almacenDatos: AlmacenDatos)  {

    suspend  fun invoke(createUserCommand: CrearCategoriaCommand): CategoriaDTO {
        //this.validateUser(user)
        if (repositorio.findByName(createUserCommand.name)!=null) {
            throw IllegalArgumentException("Ya existe una categoría con este nombre.")
        }
        val id=generateUUID()
        //se almacena el fichero
        val imageName=almacenDatos.copy(createUserCommand.imagePath,id,"/categorias/")
        val item = Categoria(
            id = id,
            name = createUserCommand.name,
            imagePath = imageName,
            enabled = true
        )
        val element=repositorio.findByName(item.name)
        if(element!=null)
            throw IllegalArgumentException("Categoría ya creada");
        repositorio.add(item)
        return item.toDTO( almacenDatos.getAppDataDir()+"/categorias/");
    }
}