package com.example.login001v

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.login001v.data.App
import com.example.login001v.navigation.AppNav
import com.example.login001v.ui.theme.Login001VTheme
import com.example.login001v.viewmodel.ProductoViewModelFactory

class MainActivity : ComponentActivity() {

    private val productoViewModelFactory: ProductoViewModelFactory by lazy {
        val repository = (application as App).container.productoRepository
        ProductoViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Login001VTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNav(
                        padding = innerPadding,
                        productoViewModelFactory = productoViewModelFactory
                    )
                }
            }
        }
    }
}