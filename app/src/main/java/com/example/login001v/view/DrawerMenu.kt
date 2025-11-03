package com.example.login001v.view

import android.net.Uri
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.login001v.R

@Composable
fun DrawerMenu(
    username:String,
    navController: NavController
){
    Column(modifier=Modifier.fillMaxSize() )
    {
        Box(
            modifier=Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(MaterialTheme.colorScheme.primary)
        )
        {
            Text(
                text="Tienda de items de : $username",
                style=MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier=Modifier
                    .align(Alignment.BottomStart)
            )
        }
        LazyColumn (modifier=Modifier.weight(1f) )
        {
            item {
                NavigationDrawerItem(
                    label =  { Text("Juegos de Mesa") } ,
                    selected = false,
                    onClick =  {
                        val nombre= Uri.encode("Juegos de Mesa")
                        val precio="15000"
                        val idImagen = R.drawable.catan
                        navController.navigate("ProductoFormScreen/$nombre/$precio/$idImagen")
                    } ,
                    icon =  { Icon(Icons.Default.Casino  , contentDescription = "Juegos de Mesa"    ) }
                )
            }
            item {
                NavigationDrawerItem(
                    label =  { Text("Accesorios") } ,
                    selected = false,
                    onClick =  {
                        val nombre= Uri.encode("Accesorios")
                        val precio="5000"
                        val idImagen = R.drawable.mause
                        navController.navigate("ProductoFormScreen/$nombre/$precio/$idImagen")
                    } ,
                    icon =  { Icon(Icons.Default.Gamepad  , contentDescription = "Accesorios"    ) }
                )
            }
            item {
                NavigationDrawerItem(
                    label =  { Text("Consolas") } ,
                    selected = false,
                    onClick =  {
                        val nombre= Uri.encode("Consolas")
                        val precio="300000"
                        val idImagen = R.drawable.play
                        navController.navigate("ProductoFormScreen/$nombre/$precio/$idImagen")
                    } ,
                    icon =  { Icon(Icons.Default.VideogameAsset  , contentDescription = "Consolas"    ) }
                )
            }
            item {
                NavigationDrawerItem(
                    label =  { Text("Computadores") } ,
                    selected = false,
                    onClick =  {
                        val nombre= Uri.encode("Computadores")
                        val precio="800000"
                        val idImagen = R.drawable.pc
                        navController.navigate("ProductoFormScreen/$nombre/$precio/$idImagen")
                    } ,
                    icon =  { Icon(Icons.Default.DesktopWindows  , contentDescription = "PCs"    ) }
                )
            }
            item {
                NavigationDrawerItem(
                    label =  { Text("Sillas Gamers") } ,
                    selected = false,
                    onClick =  {
                        val nombre= Uri.encode("Sillas Gamers")
                        val precio="150000"
                        val idImagen = R.drawable.silla
                        navController.navigate("ProductoFormScreen/$nombre/$precio/$idImagen")
                    } ,
                    icon =  { Icon(Icons.Default.Chair  , contentDescription = "Sillas"    ) }
                )
            }
        }
        Text(
            text="@ 2025 Level-UP Gamer",
            style=MaterialTheme.typography.bodySmall,
            modifier=Modifier
                .fillMaxWidth()
                .padding(16.dp),
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
