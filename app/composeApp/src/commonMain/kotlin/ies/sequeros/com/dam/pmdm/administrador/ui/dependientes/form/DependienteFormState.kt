package ies.sequeros.com.dam.pmdm.administrador.ui.dependientes.form

data class DependienteFormState(
    val nombre: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val enabled: Boolean = false,
    val isadmin:Boolean=false,
    val imagePath:String="default",
    // errores (null = sin error)
    val nombreError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val imagePathError:String?=null,
    val confirmPasswordError: String? = null,

    // para controlar si se intentó enviar (mostrar errores globales)
    val submitted: Boolean = false
)