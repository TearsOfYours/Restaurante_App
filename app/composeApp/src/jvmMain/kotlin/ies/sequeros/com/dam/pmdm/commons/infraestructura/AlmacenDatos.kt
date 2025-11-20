package ies.sequeros.com.dam.pmdm.commons.infraestructura

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption

actual class AlmacenDatos {
    private val baseDir = File(System.getProperty("user.dir"))

    init {
        if (!baseDir.exists()) {
            baseDir.mkdirs()
        }
    }

    actual fun getAppDataDir(): String {
        return baseDir.absolutePath
    }
    actual suspend fun readFile(path: String, subdirectory:String): String {
        return withContext(Dispatchers.IO) {
            File(path).readText()
        }
    }

    actual suspend fun writeFile(path: String, content: String,subdirectory:String) {
        withContext(Dispatchers.IO) {
            File(path).writeText(content)
        }
    }

    actual suspend fun copy(source:String,name:String,subpath:String):String =

        withContext(Dispatchers.IO) {
            var dst = File(baseDir.toString() + subpath)
            var src = File(source)
            val extension = src.extension
            if (!dst.exists()) dst.mkdirs()
            Files.copy(
                Path.of(source), Path.of(dst.toString() + "/" + name + "." + extension),
                StandardCopyOption.REPLACE_EXISTING
            )
            name + "." + extension
        }


    actual suspend fun remove(path: String) {
        withContext(Dispatchers.IO) {
            File(path).delete()
        }
    }

    actual suspend fun getPath(name: String): String {
        return this.baseDir.toString()+"/"+name;
    }
}