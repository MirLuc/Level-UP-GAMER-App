package com.example.login001v.ui.login

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import com.example.login001v.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.login001v.data.repository.AuthRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    vm: LoginViewModel = viewModel()
) {
    val state = vm.uiState
    var showPass by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val ColorScheme = darkColorScheme(
        primary = Color(0xFF00BFFF),
        onPrimary = Color.Black,
        onSurface = Color(0xFF00BFFF),
    )

    MaterialTheme(
        colorScheme = ColorScheme
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Level-UP -Gamer",
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color.Black),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Image(
                    painter = painterResource(id = R.drawable.logoduoc),
                    contentDescription = "Logo App",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(66.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Usuario",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                }

                OutlinedTextField(
                    value = state.username,
                    onValueChange = vm::onUsernameChange,
                    label = { Text("Usuario") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.95f)
                )

                Text(
                    "Contraseña",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(end = 8.dp)
                )

                OutlinedTextField(
                    value = state.password,
                    onValueChange = vm::onPasswordChange,
                    label = { Text("Contraseña") },
                    singleLine = true,
                    visualTransformation = if (showPass) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        TextButton(onClick = { showPass = !showPass }) {
                            Text(if (showPass) "Ocultar" else "Ver")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.95f)
                )

                if (state.error != null) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = state.error ?: "",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(66.dp))

                Button(
                    onClick = {
                        vm.submit { user ->
                            navController.navigate("DrawerMenu/$user") {
                                popUpTo("login") { inclusive = true }
                                launchSingleTop = true
                            }
                        }

                        val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                        val savedUser = prefs.getString("username", null)
                        val savedPass = prefs.getString("password", null)

                        if (state.username.trim() == savedUser && state.password == savedPass) {
                            navController.navigate("DrawerMenu/${state.username.trim()}") {
                                popUpTo("login") { inclusive = true }
                                launchSingleTop = true
                            }
                            return@Button
                        }
                    },
                    enabled = !state.isLoading,
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    Text(if (state.isLoading) "Validando" else "Iniciar sesion")
                }

                // BOTÓN DE REGISTRO RESTAURADO
                TextButton(onClick = { navController.navigate("register") }) {
                    Text("Registrarme", color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    val vm = LoginViewModel(AuthRepository())
    LoginScreen(navController = navController, vm = vm)
}