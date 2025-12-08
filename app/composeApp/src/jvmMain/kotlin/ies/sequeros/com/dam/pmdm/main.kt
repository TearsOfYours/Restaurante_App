package ies.sequeros.com.dam.pmdm

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ies.sequeros.com.dam.pmdm.administrador.infraestructura.BBDDCategoriaRepository

import ies.sequeros.com.dam.pmdm.administrador.infraestructura.BBDDDependienteRepository
import ies.sequeros.com.dam.pmdm.administrador.infraestructura.BBDDLineaPedidoRepository
import ies.sequeros.com.dam.pmdm.administrador.infraestructura.BBDDPedidoRepository
import ies.sequeros.com.dam.pmdm.administrador.infraestructura.BBDDProductoRepository
import ies.sequeros.com.dam.pmdm.administrador.infraestructura.categorias.BBDDRepositorioCategoriasJava
import ies.sequeros.com.dam.pmdm.administrador.infraestructura.dependientes.BBDDRepositorioDependientesJava
import ies.sequeros.com.dam.pmdm.administrador.infraestructura.pedidos.BBDDRepositorioPedidosJava
import ies.sequeros.com.dam.pmdm.administrador.infraestructura.productos.BBDDRepositorioProductosJava
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.ILineaPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.cliente.infraestructura.pedidos.BBDDRepositorioLineaPedidosJava
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.commons.infraestructura.DataBaseConnection

import java.io.FileInputStream
import java.util.logging.LogManager
fun main() = application {
    val connection = DataBaseConnection()
    connection.setConfig_path("./app.properties")
    connection.open()













    val dependienteRepositorioJava=BBDDRepositorioDependientesJava(connection)
    val dependienteRepositorio: IDependienteRepositorio = BBDDDependienteRepository(dependienteRepositorioJava )

    val categoriaRepositorioJava = BBDDRepositorioCategoriasJava(connection)
    val categoriaRepositorio: ICategoriaRepositorio =
        BBDDCategoriaRepository(categoriaRepositorioJava)

    val productoRepositorioJava = BBDDRepositorioProductosJava(connection)
    val productoRepositorio: BBDDProductoRepository = BBDDProductoRepository(productoRepositorioJava)

    val pedidoRepositorioJava = BBDDRepositorioPedidosJava(connection)
    val pedidoRepositorio: IPedidoRepositorio = BBDDPedidoRepository(pedidoRepositorioJava)

    val lineaPedidoRepositorioJava = BBDDRepositorioLineaPedidosJava(connection)
    val lineaPedidoRepositorio: ILineaPedidoRepositorio =
        BBDDLineaPedidoRepository(lineaPedidoRepositorioJava)
    configureExternalLogging("./logging.properties")
    Window(
        onCloseRequest = {
            //se cierra la conexion
           // dependienteRepositorioJava.close()
            exitApplication()},
        title = "VegaBurguer",
    ) {
        //se envuelve el repositorio en java en uno que exista en Kotlin
        App(pedidoRepositorio,productoRepositorio,categoriaRepositorio,dependienteRepositorio,AlmacenDatos())
    }
}
fun configureExternalLogging(path: String) {
    try {
        FileInputStream(path).use { fis ->
            LogManager.getLogManager().readConfiguration(fis)
            println("Logging configurado desde: $path")
        }
    } catch (e: Exception) {
        println("⚠️ No se pudo cargar logging.properties externo: $path")
        e.printStackTrace()
    }
}