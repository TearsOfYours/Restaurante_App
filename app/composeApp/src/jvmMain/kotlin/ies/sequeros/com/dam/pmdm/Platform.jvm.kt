package ies.sequeros.com.dam.pmdm


import java.util.UUID

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()
actual fun generateUUID(): String {
    return UUID.randomUUID().toString()
}
