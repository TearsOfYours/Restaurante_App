package ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.actualizar


data class ActualizarCategoriaCommand(
    val name: String,
    val id: String,
    val imagePath: String,
)