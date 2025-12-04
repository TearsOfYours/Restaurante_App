package ies.sequeros.com.dam.pmdm.administrador.infraestructura.ficheros

import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.Pedido
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import kotlinx.serialization.json.Json
import java.io.File

class FilePedidoRepository(
    private val almacenDatos: AlmacenDatos,
    private val fileName: String = "pedidos.json"
) : IPedidoRepositorio {

    private val subdirectory = "/pedidos/"

    private suspend fun getDirectoryPath(): String {
        val dir = almacenDatos.getAppDataDir()
        val directory = File(dir, subdirectory)
        return directory.absolutePath
    }

    private suspend fun save(items: List<Pedido>) {
        if (!File(getDirectoryPath()).exists())
            File(getDirectoryPath()).mkdirs()

        almacenDatos.writeFile(
            "${getDirectoryPath()}/$fileName",
            Json.encodeToString(items)
        )
    }

    override suspend fun getAll(): List<Pedido> {
        val path = "${getDirectoryPath()}/$fileName"
        if (!File(path).exists()) return emptyList()

        val json = almacenDatos.readFile(path)
        return if (json.isNotEmpty())
            Json.decodeFromString(json)
        else
            emptyList()
    }

    override suspend fun getById(id: String) =
        getAll().firstOrNull { it.id == id }

    override suspend fun getByCliente(clienteName: String): List<Pedido> =
        getAll().filter { it.name == clienteName }

    override suspend fun create(pedido: Pedido) {
        val current = getAll().toMutableList()
        current.add(pedido)
        save(current)
    }

    override suspend fun update(pedido: Pedido) {
        val current = getAll().map { if (it.id == pedido.id) pedido else it }
        save(current)
    }

    suspend fun findByName(name: String): Pedido? =
        getAll().firstOrNull { it.name == name }
}