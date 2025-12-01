package com.example.login001v.view

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Chair
import androidx.compose.material.icons.filled.DesktopWindows
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VideogameAsset
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.login001v.R
import com.example.login001v.data.dao.ProductoDao
import com.example.login001v.data.model.Producto
import com.example.login001v.data.repository.ProductoRepository
import com.example.login001v.viewmodel.ProductoViewModelFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

data class DrawerMenuItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val price: String,
    val idImagen: Int
)

val ElectricBlue = Color(0xFF64B5F6)

val menuItems = listOf(
    DrawerMenuItem("Juegos de Mesa", Icons.Default.Casino, "juegos", "15000", R.drawable.ic_launcher_foreground),
    DrawerMenuItem("Accesorios", Icons.Default.Gamepad, "accesorios", "5000", R.drawable.ic_launcher_foreground),
    DrawerMenuItem("Consolas", Icons.Default.VideogameAsset, "consolas", "300000", R.drawable.ic_launcher_foreground),
    DrawerMenuItem("Sillas Gamers", Icons.Default.Chair, "sillas", "150000", R.drawable.ic_launcher_foreground),
)

@Composable
fun DrawerMenu(
    username: String,
    navController: NavController,
    productoViewModelFactory: ProductoViewModelFactory
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                username = username,
                navController = navController,
                onItemClick = { route, nombre, precio, idImagen ->
                    scope.launch { drawerState.close() }
                    if (nombre.isNotEmpty()) {
                        val encodedNombre = Uri.encode(nombre)
                        navController.navigate("ProductoFormScreen/$encodedNombre/$precio/$idImagen")
                    } else if (route == "MuestraDatosScreen") {
                        navController.navigate(route)
                    } else if (route == "PostScreen") {
                        navController.navigate(route)
                    } else if (route.startsWith("profile")) {
                        navController.navigate(route)
                    }
                }
            )
        },
        content = {
            Scaffold(
                topBar = {
                    CustomTopAppBar(
                        title = "Bienvenido $username",
                        onMenuClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }
                    )
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text(
                        text = "Contenido Principal (Desliza para ver el menú)",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(title: String, onMenuClick: () -> Unit) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary, titleContentColor = MaterialTheme.colorScheme.onPrimary),
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(Icons.Filled.Menu, contentDescription = "Menú", tint = MaterialTheme.colorScheme.onPrimary)
            }
        }
    )
}

@Composable
fun DrawerContent(
    username: String,
    navController: NavController,
    onItemClick: (String, String, String, Int) -> Unit
) {
    ModalDrawerSheet(
        modifier = Modifier.fillMaxWidth(0.7f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(ElectricBlue),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = username,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(menuItems.size) { index ->
                val item = menuItems[index]
                NavigationDrawerItem(
                    label = { Text(item.title, color = ElectricBlue) },
                    selected = false,
                    onClick = {
                        onItemClick(item.route, item.title, item.price, item.idImagen)
                    },
                    icon = {
                        Icon(item.icon, contentDescription = item.title, tint = ElectricBlue)
                    },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
                )
            }

            item {
                HorizontalDivider(color = ElectricBlue.copy(alpha = 0.3f), modifier = Modifier.padding(vertical = 8.dp))
            }

            item {
                NavigationDrawerItem(
                    label = { Text("Posts API Externa", color = ElectricBlue) },
                    selected = false,
                    onClick = {
                        onItemClick("PostScreen", "", "", 0)
                    },
                    icon = {
                        Icon(
                            Icons.Default.DesktopWindows,
                            contentDescription = "Posts API Externa",
                            tint = ElectricBlue
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
                )
            }

            item {
                NavigationDrawerItem(
                    label = { Text("Mostrar Datos Guardados", color = ElectricBlue) },
                    selected = false,
                    onClick = {
                        onItemClick("MuestraDatosScreen", "", "", 0)
                    },
                    icon = {
                        Icon(
                            Icons.Default.Archive,
                            contentDescription = "Datos Guardados",
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
                        onItemClick("profile/$username", "", "", 0)
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
                            Icons.AutoMirrored.Filled.Logout,
                            contentDescription = "Cerrar sesión",
                            tint = ElectricBlue
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
                )
            }
        }

        HorizontalDivider(color = ElectricBlue.copy(alpha = 0.3f))

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
fun DrawerMenuPreview() {
    val navController = rememberNavController()

    val dummyDao = object : ProductoDao {
        override fun getAll(): Flow<List<Producto>> = flowOf(emptyList())
        override suspend fun insert(producto: Producto) {}
        override suspend fun deleteAll() {}
    }

    val dummyRepository = ProductoRepository(dummyDao)

    val dummyFactory = ProductoViewModelFactory(dummyRepository)

    DrawerMenu(username = "Cay Pereira", navController = navController, productoViewModelFactory = dummyFactory)
}