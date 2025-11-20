package ies.sequeros.com.dam.pmdm


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.automirrored.filled.ManageSearch
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.PersonPinCircle

import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.TableBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vegaburguer.composeapp.generated.resources.Res
import vegaburguer.composeapp.generated.resources.hombre


@Composable
fun Principal(onAdministrador:()->Unit, onDependiente:()->Unit,onTPV:()->Unit){
    Res.drawable.hombre
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = Icons.Default.Restaurant,
                contentDescription = "Clear",
                modifier = Modifier.fillMaxSize(0.25f)

            )
            Spacer(modifier = Modifier.height(4.dp))

            // Título
            Text(

                text = "VegaBurguer",
                fontSize = 26.sp, // tamaño grande
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedIconButton(onClick = onAdministrador,
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(96.dp)) {
                    Icon(
                        modifier = Modifier.fillMaxSize(0.8f),
                        imageVector = Icons.Default.ManageAccounts,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                OutlinedIconButton(onClick = onDependiente,
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(96.dp)) {
                    Icon(
                        modifier = Modifier.fillMaxSize(0.8f),
                        imageVector = Icons.Default.Fastfood,
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                OutlinedIconButton(
                    onClick = { onTPV },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(96.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(0.8f),
                        imageVector = Icons.Default.TableBar,
                        contentDescription = ""
                    )
                }
            }
        }
    }
}