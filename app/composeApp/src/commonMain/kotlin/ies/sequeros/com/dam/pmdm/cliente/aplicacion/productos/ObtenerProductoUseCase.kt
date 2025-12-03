package ies.sequeros.com.dam.pmdm.cliente.aplicacion.productos

import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio

class ObtenerProductosUseCase(private val repo: IProductoRepositorio) {
    suspend fun ejecutar(cmd: ObtenerProductosCommand) =
        repo.getAll().filter { it.idCategoria == cmd.idCategoria }
}