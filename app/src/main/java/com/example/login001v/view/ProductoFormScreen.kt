package com.example.login001v.view
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.login001v.data.model.Producto
import com.example.login001v.viewmodel.ProductoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductoFormScreen(
    navController: NavController,
    nombre:String,
    precio:String,
    idImagen: Int
){
    var cantidad by remember {  mutableStateOf (TextFieldValue(""))  }
    var direccion by remember {  mutableStateOf (TextFieldValue(""))  }
    var conPapas  by remember {  mutableStateOf (false)  }
    var agrandarBebida  by remember {  mutableStateOf (false)  }
    val viewModel: ProductoViewModel =viewModel()
    val productos : List<Producto> by viewModel.productos.collectAsState()

    Scaffold (
        bottomBar =  {
            BottomAppBar  {
            }
        }
    )
    { innerPadding  ->
        Column(
            modifier = Modifier
                . padding (innerPadding)
                . padding (16. dp )
                . fillMaxSize (),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter= painterResource(id= idImagen ),
                contentDescription = "Imagen de Ítem Gamer",
                modifier=Modifier
                    . height (150. dp )
                    . fillMaxWidth ()
                    .border(2.dp, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.medium)
                    .padding(8.dp)
            )
            Spacer(modifier =Modifier. height (16. dp ))

            Text(text="Ítem: $nombre", style= MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
            Text(text="Costo: $precio Monedas", style= MaterialTheme.typography.bodyLarge)

            Spacer(modifier =Modifier. height (16. dp ))

            OutlinedTextField(
                value=cantidad,
                onValueChange =  { cantidad =  it} ,
                label = { Text("Cantidad (Unidades de Inventario)") } ,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                modifier = Modifier. fillMaxWidth ()
            )
            OutlinedTextField(
                value=direccion,
                onValueChange =  { direccion =  it} ,
                label = { Text("Dirección de Envío (Zona de Entrega)") } ,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                modifier = Modifier. fillMaxWidth ()
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked =conPapas,
                    onCheckedChange =  { conPapas =  it} ,
                )
                Text("Añadir Power-Up (Envio Rapido)")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked =agrandarBebida,
                    onCheckedChange =  { agrandarBebida =  it}
                )
                Text("Añadir Paquete de EXP (Garantia por 1 año)")
            }

            Spacer(modifier =Modifier. height (16. dp ))

            Button(
                onClick =  {
                    val producto =Producto(
                        nombre = nombre,
                        precio= precio,
                        cantidad = cantidad.text,
                        direccion=direccion.text,
                        conPapas = conPapas,
                        agrandarBebida = agrandarBebida
                    )
                    viewModel.guardarProducto(producto)
                } ,
                enabled=cantidad.text. isNotBlank () && direccion.text. isNotBlank (),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth().height(56.dp)
            )
            {
                Text("CONFIRMAR COMPRA")
            }

            Spacer(modifier =Modifier. height (16. dp ))

            Text("Inventario Actual / Compras Anteriores :", style=MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary )

            if (productos. isNotEmpty ()){
                LazyColumn(modifier=Modifier. weight (1f)) {
                    items (productos) {  producto  ->
                        Card(
                            modifier = Modifier
                                . fillMaxWidth ()
                                . padding (4. dp ),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        )
                        {
                            Column(modifier=Modifier. padding (8. dp )  )
                            {
                                Text(
                                    text ="${producto.nombre} - ${producto.precio} Monedas",
                                    style=MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text ="Cantidad ${producto.cantidad}",
                                    style=MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text ="Direccion ${producto.direccion}",
                                    style=MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
            else{
                Text(
                    text = "No hay pedidos realizados",
                    modifier=Modifier. weight (1f),
                    style=MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductoFormScreen() {
    // Definimos valores de prueba para todos los parámetros requeridos
    ProductoFormScreen(
        navController = rememberNavController(),
        nombre = "Juegos de Mesa",
        precio = "15000",
        idImagen = android.R.drawable.btn_star
    )
}
