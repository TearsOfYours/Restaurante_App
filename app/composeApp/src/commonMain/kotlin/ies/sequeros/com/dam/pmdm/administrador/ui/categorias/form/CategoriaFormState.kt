package ies.sequeros.com.dam.pmdm.administrador.ui.categorias.form

data class CategoriaFormState(
    val nombre: String = "",
    val imagePath: String = "", //Debe de darle el path intuyo

    val nombreError: String? = null,
    val imagePathError: String? = null,

    val submitted: Boolean = false
)