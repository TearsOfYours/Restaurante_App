package ies.sequeros.com.dam.pmdm.administrador.ui.productos.form

data class ProductoFormState(
    val nombre: String = "",
    val precio: Double = 0.0,
    val idCategoria: String = "",
    val id: String = "",
    val imagePath: String = "",

    // Errores
    val nombreError: String? = null,
    val precioError: String? = null,
    val idCategoriaError: String? = null,
    val imagePathError: String? = null,

    val submitted: Boolean = false
)