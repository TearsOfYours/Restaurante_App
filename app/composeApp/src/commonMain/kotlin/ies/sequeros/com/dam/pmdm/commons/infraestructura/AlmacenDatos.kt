package ies.sequeros.com.dam.pmdm.commons.infraestructura

expect class AlmacenDatos {
    fun getAppDataDir(): String
     suspend fun readFile(path: String,subdirectory:String=""): String
    suspend fun writeFile(path: String, content: String,subdirectory:String="")
    suspend fun copy(source:String,name:String,subpath:String=""):String
    suspend fun remove(path:String)
    suspend fun getPath(name:String):String

}