package ies.sequeros.com.dam.pmdm.cliente.aplicacion.pedidos.activar


import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.CategoriaDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos

import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.ILineaPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.LineaPedido


class CambiarEstadoLineaPedidoUseCase(
    private val repositorio: ILineaPedidoRepositorio
) {

    suspend fun invoke(command: CambiarEstadoLineaPedidoCommand): LineaPedido {
        val linea: LineaPedido? = repositorio.getById(command.id)
        if (linea == null) {
            throw IllegalArgumentException("La línea de pedido no existe.")
        }

        val lineaActualizada = linea.copy(estado = command.estado)
        repositorio.update(lineaActualizada)
        return lineaActualizada
    }
}