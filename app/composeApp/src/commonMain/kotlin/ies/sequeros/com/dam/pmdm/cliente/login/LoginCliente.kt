package ies.sequeros.com.dam.pmdm.cliente.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ies.sequeros.com.dam.pmdm.AppViewModel
import vegaburguer.composeapp.generated.resources.Res

@Composable
fun LoginCliente(
    appViewModel: AppViewModel,
    loginClienteViewModel: LoginClienteViewModel,
    onLogin: (String) -> Unit,
    onClose: () -> Unit)
    {
        val state by loginClienteViewModel.uiState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Row centrado y con tamaño fijo
            Row(
                modifier = Modifier
                    .width(300.dp)
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    OutlinedTextField(
                        value = state.nombre,
                        onValueChange = { loginClienteViewModel.onNombreChange(it) },
                        label = { Text("Nombre:", fontSize = 30.sp) },
                        isError = state.nombreError != null,
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                loginClienteViewModel.submit(
                                    onSuccess = { onLogin(state.nombre) },
                                    onFailure = { }
                                )
                            }
                        )
                    )

                    state.nombreError?.let { error ->
                        Text(
                            text = error,
                            color = Color.Red,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    loginClienteViewModel.submit(
                        onSuccess = { onLogin(state.nombre) },
                        onFailure = {  }
                    )
                },
                enabled = state.nombre.isNotBlank() && state.nombreError == null
            ) {
                Text("Iniciar sesión")
            }
        }

    }