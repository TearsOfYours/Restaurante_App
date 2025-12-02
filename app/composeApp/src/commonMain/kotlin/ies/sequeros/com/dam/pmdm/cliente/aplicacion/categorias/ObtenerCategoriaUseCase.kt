package ies.sequeros.com.dam.pmdm.cliente.aplicacion.categorias

import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio

class ObtenerCategoriasUseCase(private val repo: ICategoriaRepositorio) {
    suspend fun ejecutar(cmd: ObtenerCategoriaCommand) =
        repo.getAll()
}