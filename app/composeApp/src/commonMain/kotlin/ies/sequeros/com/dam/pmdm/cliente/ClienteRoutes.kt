package ies.sequeros.com.dam.pmdm.cliente


object ClienteRoutes {

    const val Login = "login"
    var Menu = "menu"
    var Pedido = "pedido"
    var Producto = "productos/{categoriaId}"

    fun productoConId(id: String) = "producto/$id"
}