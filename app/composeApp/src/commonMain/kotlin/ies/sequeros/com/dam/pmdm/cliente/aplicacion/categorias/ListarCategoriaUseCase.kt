package ies.sequeros.com.dam.pmdm.cliente.aplicacion.categorias

import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.cliente.aplicacion.listar.toTPV
import ies.sequeros.com.dam.pmdm.cliente.modelo.CategoriaTPVDTO


class ListarCategoriaUseCase(
    private val categoriaRepo: ICategoriaRepositorio,
    private val productoRepo: IProductoRepositorio,
    private val almacenDatos: AlmacenDatos
) {

    suspend fun invoke(): List<CategoriaTPVDTO> {

        val categoriaPath = almacenDatos.getAppDataDir() + "/categorias/"
        val productoPath = almacenDatos.getAppDataDir() + "/productos/"

        val categorias = categoriaRepo.getAll().map { it.toTPV(categoriaPath) }
        val productos = productoRepo.getAll().map { it.toTPV(productoPath) }

        val categoriasConProductos = categorias.map { categoriaDTO ->
            val productosDeCategoria = productos.filter {
                it.idCategoria == categoriaDTO.id
            }
            categoriaDTO.copy(productos = productosDeCategoria)
        }

        return categoriasConProductos
    }
}
