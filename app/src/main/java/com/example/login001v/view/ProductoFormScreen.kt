package com.example.login001v.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import com.example.login001v.data.remote.RetrofitClient
import com.example.login001v.data.model.Post
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.login001v.data.model.Producto
import com.example.login001v.viewmodel.ProductoViewModel
import com.example.login001v.viewmodel.ProductoViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductoFormScreen(
    navController: NavController,
    nombre: String,
    precio: String,
    idImagen: Int,
    factory: ProductoViewModelFactory
) {
    val viewModel: ProductoViewModel = viewModel(factory = factory)
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = nombre) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = idImagen),
                contentDescription = nombre,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Nombre: $nombre",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Precio: $$precio",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    val nuevoProducto = Producto(
                        nombre = nombre,
                        precio = precio,
                        idImagen = idImagen
                    )
                    scope.launch {
                        viewModel.insert(nuevoProducto)
                        try {
                            val response = RetrofitClient.apiService.createPost(
                                Post(
                                    userId = 0,
                                    id = 0,
                                    title = "Gracias por su compra",
                                    body = "Compra confirmada"
                                )
                            )
                            Log.d("CompraAPI", "POST /posts -> id=${response.id}, title=${response.title}")
                            snackbarHostState.showSnackbar("API OK (id=${response.id})")
                        } catch (_: Exception) {
                            snackbarHostState.showSnackbar("API falló (se guardó local)")
                        }

                        snackbarHostState.showSnackbar("Gracias por su compra")
                        navController.navigate("MuestraDatosScreen")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar al Carrito")
            }
        }
    }
}