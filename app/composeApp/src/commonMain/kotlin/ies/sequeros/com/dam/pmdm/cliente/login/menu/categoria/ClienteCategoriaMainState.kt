package ies.sequeros.com.dam.pmdm.cliente.login.menu.categoria

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ProductoDTO

data class ClienteCategoriaMainState(
    val categoriaId: String? = null,
    val productos: List<ProductoDTO> = emptyList()
)