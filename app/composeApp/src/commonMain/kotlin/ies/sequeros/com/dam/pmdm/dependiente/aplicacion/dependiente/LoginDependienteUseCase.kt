package ies.sequeros.com.dam.pmdm.dependiente.aplicacion.dependiente

import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio

class LoginDependienteUseCase(private val repo: IDependienteRepositorio) {
    suspend fun ejecutar(cmd: LoginDependienteCommand) =
        repo.getAll().firstOrNull { it.email == cmd.email && it.password == cmd.password }
}