package com.example.login001v.view

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Chair
import androidx.compose.material.icons.filled.DesktopWindows
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.VideogameAsset
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.login001v.R
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Divider


val ElectricBlue = Color(0xFF00FFFF)

@Composable
fun DrawerMenu(
    username:String,
    navController: NavController
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Fondo negro
    )
    {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoduoc),
                contentDescription = "Level-up Gamer",
                contentScale = ContentScale.Crop, // Para que cubra el área sin deformarse
                modifier = Modifier.matchParentSize() // Asegura que la imagen llene el Box
            )

        }

        // Items de Navegación
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        )
        {
            item {// juegos de mesa
                NavigationDrawerItem(
                    label = {
                        Text(
                            "Juegos de Mesa",
                            color = ElectricBlue
                        )
                    }, // Texto Azul Eléctrico
                    selected = false,
                    onClick = {
                        val nombre = Uri.encode("Juegos de Mesa")
                        val precio = "15000"
                        val idImagen = R.drawable.catan
                        navController.navigate("ProductoFormScreen/$nombre/$precio/$idImagen")
                    },
                    icon = {
                        Icon(
                            Icons.Default.Casino,
                            contentDescription = "Juegos de Mesa",
                            tint = ElectricBlue
                        )
                    }, // Ícono Azul Eléctrico
                    colors = NavigationDrawerItemDefaults.colors(
                        // Establece el fondo de los ítems a transparente o negro (heredado del padre)
                        unselectedContainerColor = Color.Transparent,
                    )
                )
            }
            item { //accesorios
                NavigationDrawerItem(
                    label = { Text("Accesorios", color = ElectricBlue) },
                    selected = false,
                    onClick = {
                        val nombre = Uri.encode("Accesorios")
                        val precio = "5000"
                        val idImagen = R.drawable.mause
                        navController.navigate("ProductoFormScreen/$nombre/$precio/$idImagen")
                    },
                    icon = {
                        Icon(
                            Icons.Default.Gamepad,
                            contentDescription = "Accesorios",
                            tint = ElectricBlue
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
                )
            }
            item {// Consolas
                NavigationDrawerItem(
                    label = { Text("Consolas", color = ElectricBlue) },
                    selected = false,
                    onClick = {
                        val nombre = Uri.encode("Consolas")
                        val precio = "300000"
                        val idImagen = R.drawable.play
                        navController.navigate("ProductoFormScreen/$nombre/$precio/$idImagen")
                    },
                    icon = {
                        Icon(
                            Icons.Default.VideogameAsset,
                            contentDescription = "Consolas",
                            tint = ElectricBlue
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
                )
            }
            item {// Computadores
                NavigationDrawerItem(
                    label = { Text("Computadores", color = ElectricBlue) },
                    selected = false,
                    onClick = {
                        val nombre = Uri.encode("Computadores")
                        val precio = "800000"
                        val idImagen = R.drawable.pc
                        navController.navigate("ProductoFormScreen/$nombre/$precio/$idImagen")
                    },
                    icon = {
                        Icon(
                            Icons.Default.DesktopWindows,
                            contentDescription = "PCs",
                            tint = ElectricBlue
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
                )
            }
            item {
                NavigationDrawerItem(
                    label = { Text("Sillas Gamers", color = ElectricBlue) },
                    selected = false,
                    onClick = {
                        val nombre = Uri.encode("Sillas Gamers")
                        val precio = "150000"
                        val idImagen = R.drawable.silla
                        navController.navigate("ProductoFormScreen/$nombre/$precio/$idImagen")
                    },
                    icon = {
                        Icon(
                            Icons.Default.Chair,
                            contentDescription = "Sillas",
                            tint = ElectricBlue
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
                )
            }
            item {
                NavigationDrawerItem(
                    label = { Text("Perfil", color = ElectricBlue) },
                    selected = false,
                    onClick = {
                        val user = Uri.encode(username)
                        navController.navigate("profile/$user")
                    },
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Perfil",
                            tint = ElectricBlue
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
                )
            }
            item {
                NavigationDrawerItem(
                    label = { Text("Cerrar sesión", color = ElectricBlue) },
                    selected = false,
                    onClick = {
                        navController.navigate("login") {
                            popUpTo("login") { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            Icons.Default.Logout,
                            contentDescription = "Cerrar sesión",
                            tint = ElectricBlue
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
                )
            }
        }
        // Footer
        Divider(color = ElectricBlue.copy(alpha = 0.3f))
        // Texto "Tienda de items de : Cay Pereira"
        Text(
            text = "Tienda de items de : $username",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = ElectricBlue,
            textAlign = TextAlign.Center
        )
        Text(
            text = "@ 2025 Level-UP Gamer",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = ElectricBlue,
            textAlign = TextAlign.Center
        )
    }
}
@Preview(showBackground = true)
@Composable
fun DrawerMenuPreview(){
    val navController = androidx.navigation.compose.rememberNavController()
    DrawerMenu(username = "Cay Pereira", navController = navController)
}