package com.example.login001v.ui.profile

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.content.ContentValues
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.login001v.view.ElectricBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    username: String,
    navController: NavController
) {
    val context = LocalContext.current
    var imageUri by remember {
        mutableStateOf(
            context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                .getString("profile_image_uri", null)
                ?.let { Uri.parse(it) }
        )
    }

    // Selector de galería (existente)
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            imageUri = uri
            context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                .edit()
                .putString("profile_image_uri", uri.toString())
                .apply()
        }
    }

    // NUEVO: Lanzador de cámara que guarda en un URI de MediaStore
    val pendingCameraUri = remember { mutableStateOf<Uri?>(null) }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            pendingCameraUri.value?.let { takenUri ->
                imageUri = takenUri
                context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
                    .edit()
                    .putString("profile_image_uri", takenUri.toString())
                    .apply()
            }
        } else {
            // Limpieza si la captura falla
            pendingCameraUri.value?.let { context.contentResolver.delete(it, null, null) }
        }
        pendingCameraUri.value = null
    }

    fun createImageUri(context: Context): Uri? {
        val resolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "profile_${System.currentTimeMillis()}.jpg")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/LevelUpGamer")
            }
        }
        return resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    }

    val colorScheme = darkColorScheme(
        primary = Color(0xFF00BFFF),
        onPrimary = Color.Black,
        onSurface = Color(0xFF00BFFF),
    )

    MaterialTheme(colorScheme = colorScheme) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Perfil de $username", color = MaterialTheme.colorScheme.primary) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Black,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        ) { inner ->
            Column(
                modifier = Modifier
                    .padding(inner)
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val bitmap = remember(imageUri) {
                    imageUri?.let {
                        try {
                            context.contentResolver.openInputStream(it).use { stream ->
                                stream?.let { BitmapFactory.decodeStream(it) }
                            }
                        } catch (_: Exception) {
                            null
                        }
                    }
                }

                if (bitmap != null) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .clip(CircleShape)
                            .aspectRatio(1f)
                    )
                } else {
                    Text("Sin foto de perfil", color = MaterialTheme.colorScheme.primary)
                }

                // NUEVO: Botón para tomar foto con la cámara
                Button(
                    onClick = {
                        val uri = createImageUri(context)
                        if (uri != null) {
                            pendingCameraUri.value = uri
                            cameraLauncher.launch(uri)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ElectricBlue,
                        contentColor = Color.Black
                    )
                ) {
                    Text("Tomar foto con la cámara")
                }

                // Botón existente: elegir desde la galería
                Button(
                    onClick = { galleryLauncher.launch("image/*") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ElectricBlue,
                        contentColor = Color.Black
                    )
                ) {
                    Text("Cambiar foto de perfil")
                }
            }
        }
    }
}