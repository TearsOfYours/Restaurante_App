package ies.sequeros.com.dam.pmdm.cliente.modelo


data class ProductoTPVDTO (
    val name: String,
    val precio: Double,
    val imagePath: String,
    val idCategoria: String
)
data class CategoriaTPVDTO (
    val name: String,
    val id: String,
    val descripcion: String,
    val imagePath: String,
    val productos: List<ProductoTPVDTO>
)
