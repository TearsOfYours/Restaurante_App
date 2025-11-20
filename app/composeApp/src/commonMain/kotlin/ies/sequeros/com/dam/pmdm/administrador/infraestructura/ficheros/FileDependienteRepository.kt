package ies.sequeros.com.dam.pmdm.administrador.infraestructura.memoria

import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.administrador.modelo.Dependiente
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio
import java.io.File
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FileDependienteRepository(
    private val almacenDatos: AlmacenDatos,
    private val fileName: String = "dependientes.json"
) : IDependienteRepositorio {

    private val subdirectory="/data/"
    init {

    }

    private suspend fun getDirectoryPath(): String {
        val dir = almacenDatos.getAppDataDir()
        val directory = File(dir, subdirectory)

        return directory.absolutePath
    }

    private suspend fun save(items: List<Dependiente>) {
        if(!File(this.getDirectoryPath()).exists())
            File(this.getDirectoryPath()).mkdirs()
        this.almacenDatos.writeFile(this.getDirectoryPath()+"/"+this.fileName, Json.encodeToString(items))
    }

    override suspend fun add(item: Dependiente) {
        val items = this.getAll().toMutableList()

        if (items.firstOrNull { it.name == item.name } == null) {
            items.add(item)
        } else {
            throw IllegalArgumentException("ALTA:El usuario con id:" + item.id + " ya existe")
        }
        this.save(items)
    }

    override suspend fun remove(item: Dependiente): Boolean {
        return this.remove(item.id!!)
    }

    override suspend fun remove(id: String): Boolean {
        val items = this.getAll().toMutableList()
        var item = items.firstOrNull { it.id == id }
        if (item != null) {
            items.remove((item))
            this.save(items)
            return true
        } else {
            throw IllegalArgumentException(
                "BORRADO:" +
                        " El usuario con id:" + id + " NO  existe"
            )
        }
        return true
    }

    override suspend fun update(item: Dependiente): Boolean {
        val items = this.getAll().toMutableList()
        val newItems= items.map { if (it.id == item.id) item else it }.toMutableList()
        this.save(newItems)
        return true
    }


    override suspend fun getAll(): List<Dependiente> {
        val path = getDirectoryPath()+"/"+this.fileName
        val items= mutableListOf<Dependiente>()
        var json=""
        if(File(path).exists()) {
            json = almacenDatos.readFile(path)
            if (!json.isEmpty())
                items.addAll(Json.decodeFromString<List<Dependiente>>(json))
        }
        return items.toList()
    }

    override suspend fun findByName(name: String): Dependiente? {
        val elements=this.getAll()
        for(element in elements){
            if(element.name==name)
                return element
        }
        return null; //this.items.values.firstOrNull { it.name.equals(name) };
    }

    override suspend fun getById(id: String): Dependiente? {
        val elements=this.getAll()
        for(element in elements){
            if(element.id==id)
                return element
        }
        return null;
    }


}