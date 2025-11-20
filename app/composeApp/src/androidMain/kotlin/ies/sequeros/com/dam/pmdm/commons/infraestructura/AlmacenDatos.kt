package ies.sequeros.com.dam.pmdm.commons.infraestructura

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException

actual class AlmacenDatos(private val context: Context) {
    actual fun getAppDataDir(): String {
        return context.filesDir.absolutePath
    }
    actual suspend fun readFile(path: String, subdirectory:String): String {
        return withContext(Dispatchers.IO) {
            File(path + subdirectory).readText()
        }
    }

    actual suspend fun writeFile(path: String, content: String, subdirectory:String) {
        withContext(Dispatchers.IO) {
            File(path + subdirectory).writeText(content)
        }
    }
    private fun getNameForSource(uri: Uri?, originalSource: String): String {
        uri ?: return File(originalSource).name
        return when (uri.scheme) {
            ContentResolverSchemes.CONTENT -> {
                var name: String? = null
                context.contentResolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)
                    ?.use { cursor ->
                        if (cursor.moveToFirst()) {
                            val idx = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                            if (idx != -1) name = cursor.getString(idx)
                        }
                    }
                name ?: File(uri.path ?: originalSource).name
            }
            ContentResolverSchemes.FILE -> File(uri.path ?: originalSource).name
            else -> File(originalSource).name
        }
    }

    private object ContentResolverSchemes {
        const val CONTENT = "content"
        const val FILE = "file"
    }
    private fun getExtension(source:String):String{
        val uri = source.toUri()
        val cursor = context.contentResolver.query(uri, null, null, null, null)

        cursor?.use {
            if (it.moveToFirst()) {
                val index = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (index != -1) {
                    val name = it.getString(index)
                    return name.substringAfterLast('.', "")
                }
            }
        }
        return ""
    }
    actual suspend fun copy(source: String, name: String,subpath:String):String =
        withContext(Dispatchers.IO) {
            val extension = getExtension(source)
            var returnPath = "";
            val srcUri = try {
                Uri.parse(source)
            } catch (e: Exception) {
                null
            }
            val dstFile = File(getAppDataDir() + subpath)
            //en caso de no existir el subpath
            if (!dstFile.exists()) dstFile.mkdirs()
            val targetFile: File = File(dstFile, name + "." + extension)

            // Abre InputStream según esquema
            val input = when {
                srcUri != null && srcUri.scheme == ContentResolverSchemes.CONTENT -> {
                    context.contentResolver.openInputStream(srcUri)
                }

                srcUri != null && srcUri.scheme == ContentResolverSchemes.FILE -> {
                    File(srcUri.path ?: source).inputStream()
                }

                else -> {
                    // asumimos que source es ruta absoluta de fichero
                    File(source).inputStream()
                }
            } ?: throw IOException("No se pudo abrir el stream de origen: $source") as Throwable

            // Copiar a fichero destino de forma segura
            input.use { inputStream ->
                // Escribir en temp y renombrar para atomicidad
                val tmp = File(targetFile.absolutePath + ".tmp")
                tmp.outputStream().use { out ->
                    inputStream.copyTo(out)
                    out.flush()
                }
                // si hay ya un fichero, lo reemplazamos
                if (targetFile.exists()) {
                    if (!targetFile.delete()) {
                        // no fatal: intentamos sobreescribir
                    }
                }
                if (!tmp.renameTo(targetFile)) {
                    // si no puede renombrar, intentamos copiar contenido manual
                    tmp.inputStream().use { tIn ->
                        targetFile.outputStream().use { tOut ->
                            tIn.copyTo(tOut)
                        }
                    }
                    tmp.delete()
                }
            }
            name + "." + extension
        }
        //return target


    actual suspend fun remove(path: String) {
        File(path).delete()
    }

    actual suspend fun getPath(name: String): String {
        return getAppDataDir()+name

    }
}