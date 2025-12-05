package ies.sequeros.com.dam.pmdm.cliente.login

data class LoginClienteState(
    val nombre: String = "",
    val nombreError: String? = null,
    val submitted: Boolean = false
)
