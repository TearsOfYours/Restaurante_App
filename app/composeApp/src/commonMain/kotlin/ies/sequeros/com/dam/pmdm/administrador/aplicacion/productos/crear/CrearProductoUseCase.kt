package ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.crear

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.CategoriaDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ProductoDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.Producto
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.generateUUID



class CrearProductoUseCase(private val repositorio: IProductoRepositorio, private val categoriarepositorio: ICategoriaRepositorio, private val almacenDatos: AlmacenDatos)  {

    suspend  fun invoke(createProductCommand: CrearProductoCommand): ProductoDTO {
        //this.validateUser(user)
        if (repositorio.findByName(createProductCommand.name)!=null) {
            throw IllegalArgumentException("Ya existe un producto con este nombre.")
        }
        val categoria = categoriarepositorio.getById(createProductCommand.idCategoria)
            ?: throw java.lang.IllegalArgumentException("La categoría no existe")
        val id=generateUUID()
        //se almacena el fichero
        val imageName=almacenDatos.copy(createProductCommand.imagePath,id,"/productos/")
        val item = Producto(
            name = createProductCommand.name,
            idCategoria = categoria.id,
            id = id,
            precio = createProductCommand.precio,
            imagePath = imageName,
            enabled = true,
            descripcion = ""
        )

        val element=repositorio.findByName(item.name)
        if(element!=null)
            throw IllegalArgumentException("Producto ya creado");
        repositorio.add(item)
        return item.toDTO( almacenDatos.getAppDataDir()+"/productos/");
    }
}