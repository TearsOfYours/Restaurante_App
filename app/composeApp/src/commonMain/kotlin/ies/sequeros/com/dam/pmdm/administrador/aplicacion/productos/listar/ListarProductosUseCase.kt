package ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar


import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos


class ListarProductosUseCase(private val repositorio: IProductoRepositorio, private val almacenDatos: AlmacenDatos) {

    suspend fun invoke( ): List<ProductoDTO> {
        //this.validateUser(user)
        //si tiene imagen
        val items= repositorio.getAll().map {
            it.toDTO(if(it.imagePath.isEmpty()) "" else
             almacenDatos.getAppDataDir()+"/productos/") }
        return items
    }
}