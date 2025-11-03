package com.example.login001v.navigation
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.login001v.ui.home.MuestraDatosScreen
import com.example.login001v.ui.login.LoginScreen
import com.example.login001v.view.DrawerMenu
import com.example.login001v.view.ProductoFormScreen
@Composable
fun AppNav(){
    val navController = rememberNavController()
    NavHost(navController=navController, startDestination = "login") {
        composable ("login") {
            LoginScreen(navController = navController)
        }
        composable (
            route="DrawerMenu/{username}",
            arguments =  listOf (
                navArgument ("username") {
                    type = NavType.StringType
                }
            )
        )
        {
                backStackEntry ->
            val username = backStackEntry.arguments?.getString("username"). orEmpty ()
            DrawerMenu(username=username, navController = navController)
        }
        composable (
            route="ProductoFormScreen/{nombre}/{precio}/{idImagen}",
            arguments =  listOf (
                navArgument ("nombre") {  type = NavType.StringType  } ,
                navArgument ("precio") {  type = NavType.StringType  } ,
                navArgument ("idImagen") {  type = NavType.IntType  } ,
            )
        )
        {
                backStackEntry ->
            val nombre = Uri.encode(backStackEntry.arguments?.getString("nombre") ?:"")
            val precio = backStackEntry.arguments?.getString("precio") ?:""
            val idImagen = backStackEntry.arguments?.getInt("idImagen") ?: android.R.drawable.btn_default
            ProductoFormScreen( navController = navController,  nombre=nombre, precio=precio, idImagen = idImagen)
        }
    }
}
