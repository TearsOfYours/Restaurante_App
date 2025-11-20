package ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar


import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio




class ListarDependientesUseCase(private val repositorio: IDependienteRepositorio,private val almacenDatos: AlmacenDatos) {

    suspend fun invoke( ): List<DependienteDTO> {
        //this.validateUser(user)
        //si tiene imagen
        val items= repositorio.getAll().map { it.toDTO(if(it.imagePath.isEmpty()) "" else almacenDatos.getAppDataDir()+"/dependientes/") }
        return items
    }
}