package ies.sequeros.com.dam.pmdm

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ies.sequeros.com.dam.pmdm.administrador.infraestructura.BBDDDependienteRepository
import ies.sequeros.com.dam.pmdm.administrador.infraestructura.dependientes.BBDDRepositorioDependientesJava
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import java.io.FileInputStream
import java.util.logging.LogManager
fun main() = application {
    val dependienteRepositorioJava=BBDDRepositorioDependientesJava("/app.properties")
    val dependienteRepositorio: IDependienteRepositorio = BBDDDependienteRepository(dependienteRepositorioJava )
    configureExternalLogging("./logging.properties")
    Window(
        onCloseRequest = {
            //se cierra la conexion
            dependienteRepositorioJava.close()
            exitApplication()},
        title = "VegaBurguer",
    ) {
        //se envuelve el repositorio en java en uno que exista en Kotlin
        App(dependienteRepositorio,AlmacenDatos())
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