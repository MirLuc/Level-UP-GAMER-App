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
import androidx.compose.material.icons.filled.ShoppingCart
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
// imports para la seccion de noticias
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Divider
import coil.compose.AsyncImage
import androidx.compose.ui.graphics.Brush

data class DrawerMenuItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val price: String,
    val idImagen: Int
)

val ElectricBlue = Color(0xFF64B5F6)

val menuItems = listOf(
    DrawerMenuItem("Juegos de Mesa", Icons.Default.Casino, "juegos", "15000", R.drawable.catan),
    DrawerMenuItem("Accesorios", Icons.Default.Gamepad, "accesorios", "5000", R.drawable.mause),
    DrawerMenuItem("Consolas", Icons.Default.VideogameAsset, "consolas", "300000", R.drawable.play),
    DrawerMenuItem("Sillas Gamers", Icons.Default.Chair, "sillas", "150000", R.drawable.silla),
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

                    // INSERCIÓN: SECCIÓN DE NOTICIAS DEBAJO DEL TEXTO EXISTENTE
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(56.dp))
                        NewsSection(
                            title = "Noticias de Gaming",
                            query = "gaming"
                        )
                    }
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
                    label = { Text("Carrito", color = ElectricBlue) },
                    selected = false,
                    onClick = {
                        onItemClick("MuestraDatosScreen", "", "", 0)
                    },
                    icon = {
                        Icon(
                            Icons.Default.ShoppingCart,
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


//Sección de Noticias (API)

data class NewsItem(
    val title: String,
    val url: String,
    val imageUrl: String? = null
)

@Composable
fun NewsSection(
    title: String,
    query: String
) {
    val scope = rememberCoroutineScope()
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    var news by remember { mutableStateOf<List<NewsItem>>(emptyList()) }

    LaunchedEffect(query) {
        loading = true
        error = null
        news = emptyList()
        scope.launch {
            val result = fetchNewsWithImages(query)
            if (result.isSuccess) {
                news = result.getOrNull().orEmpty()
                loading = false
            } else {
                error = result.exceptionOrNull()?.message ?: "Error desconocido"
                loading = false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = ElectricBlue // azul claro para el título de la sección
        )
        Spacer(Modifier.height(8.dp))

        when {
            loading -> {
                CircularProgressIndicator()
            }
            error != null -> {
                Text(
                    text = "No se pudieron cargar las noticias: ${error}",
                    color = ElectricBlue // azul claro en mensajes
                )
            }
            news.isEmpty() -> {
                Text(text = "No hay noticias disponibles.", color = ElectricBlue)
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(news) { item ->
                        NewsCard(item = item)
                        Divider(color = ElectricBlue.copy(alpha = 0.2f))
                    }
                }
            }
        }
    }
}

@Composable
fun NewsCard(item: NewsItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(vertical = 8.dp)
    ) {
        // Imagen de fondo (si existe)
        AsyncImage(
            model = item.imageUrl,
            contentDescription = "Imagen de noticia",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        // Overlay para mejorar legibilidad
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.10f),
                            Color.Black.copy(alpha = 0.35f)
                        )
                    )
                )
        )

        // Título en azul claro
        Text(
            text = item.title.ifBlank { "Sin título" },
            style = MaterialTheme.typography.bodyLarge,
            color = ElectricBlue,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        )

        // URL en azul claro (más tenue)
        Text(
            text = item.url,
            style = MaterialTheme.typography.bodySmall,
            color = ElectricBlue.copy(alpha = 0.85f),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp)
        )
    }
}

 //Lógica de datos: Fetch de noticias y og:image


suspend fun fetchNewsWithImages(query: String): Result<List<NewsItem>> {
    return try {
        // Primero obtenemos las noticias (título + url)
        val baseNews = fetchNews(query).getOrElse { return Result.failure(it) }

        // Limitamos para no disparar demasiadas peticiones (ajusta según necesidad)
        val limited = baseNews.take(10)

        // Obtenemos la imagen og:image por cada noticia en IO
        val withImages = withContext(Dispatchers.IO) {
            limited.map { item ->
                val img = fetchOgImage(item.url)
                item.copy(imageUrl = img)
            }
        }

        Result.success(withImages)
    } catch (e: Exception) {
        Result.failure(e)
    }
}

// Obtiene el HTML y extrae meta og:image o twitter:image
suspend fun fetchOgImage(pageUrl: String): String? {
    return try {
        val html = withContext(Dispatchers.IO) {
            val url = URL(pageUrl)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.connectTimeout = 8000
            conn.readTimeout = 8000
            conn.setRequestProperty("Accept", "text/html")
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Android) LevelUPGamerApp")

            val code = conn.responseCode
            val stream = if (code in 200..299) conn.inputStream else conn.errorStream
            val reader = BufferedReader(InputStreamReader(stream))
            val content = buildString {
                var line: String?
                while (true) {
                    line = reader.readLine()
                    if (line == null) break
                    append(line)
                }
            }
            reader.close()
            conn.disconnect()
            content
        }

        // Búsqueda simple por meta tags
        val ogRegex = Regex("""<meta\s+property=["']og:image["']\s+content=["']([^"']+)["']""", RegexOption.IGNORE_CASE)
        val twRegex = Regex("""<meta\s+name=["']twitter:image["']\s+content=["']([^"']+)["']""", RegexOption.IGNORE_CASE)

        val ogMatch = ogRegex.find(html)?.groupValues?.getOrNull(1)
        val twMatch = twRegex.find(html)?.groupValues?.getOrNull(1)

        ogMatch ?: twMatch
    } catch (_: Exception) {
        null
    }
}

// Mantiene la llamada original a Hacker News Algolia
suspend fun fetchNews(query: String): Result<List<NewsItem>> {
    return try {
        val apiUrl = "https://hn.algolia.com/api/v1/search?query=${Uri.encode(query)}&tags=story"
        val response = withContext(Dispatchers.IO) {
            val url = URL(apiUrl)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.connectTimeout = 8000
            conn.readTimeout = 8000
            conn.setRequestProperty("Accept", "application/json")

            val code = conn.responseCode
            val stream = if (code in 200..299) conn.inputStream else conn.errorStream
            val reader = BufferedReader(InputStreamReader(stream))
            val content = buildString {
                var line: String?
                while (true) {
                    line = reader.readLine()
                    if (line == null) break
                    append(line)
                }
            }
            reader.close()
            conn.disconnect()
            content
        }

        val json = JSONObject(response)
        val hits = json.optJSONArray("hits")
        val resultList = mutableListOf<NewsItem>()
        if (hits != null) {
            for (i in 0 until hits.length()) {
                val obj = hits.optJSONObject(i) ?: continue
                val title = obj.optString("title", obj.optString("story_title", ""))
                val url = obj.optString("url", obj.optString("story_url", ""))
                if (title.isNotBlank() && url.isNotBlank()) {
                    resultList.add(NewsItem(title = title, url = url))
                }
            }
        }

        Result.success(resultList)
    } catch (e: Exception) {
        Result.failure(e)
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