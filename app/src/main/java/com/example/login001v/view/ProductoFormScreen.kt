package com.example.login001v.view
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import com.example.login001v.R
import com.example.login001v.data.model.Producto
import com.example.login001v.viewmodel.ProductoViewModel

// Color Gris Oscuro para los inputs (mejora el contraste sobre negro)
val DarkInputGray = Color(0xFF1F1F1F)

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
        // Aplicamos el color de fondo negro al Scaffold
        containerColor = Color.Black,
        bottomBar =  {
            // BottomAppBar color oscuro
            BottomAppBar (containerColor = Color.Black) {
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
            // Imagen del Ítem
            Image(
                painter= painterResource(id= idImagen ),
                contentDescription = "Imagen de Ítem Gamer",
                modifier=Modifier
                    . height (150. dp )
                    . fillMaxWidth ()
                    .border(2.dp, ElectricBlue, MaterialTheme.shapes.medium)
                    .padding(8.dp)
            )
            Spacer(modifier =Modifier. height (16. dp ))

            // Título y Costo
            Text(text="Ítem: $nombre", style= MaterialTheme.typography.headlineSmall, color = ElectricBlue)
            Text(text="Costo: $$precio CLP", style= MaterialTheme.typography.bodyLarge, color = Color.White)

            Spacer(modifier =Modifier. height (16. dp ))

            // Campo Cantidad
            OutlinedTextField(
                value=cantidad,
                onValueChange =  { cantidad =  it} ,
                label = { Text("Cantidad (Unidades de Inventario)", color = ElectricBlue) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = DarkInputGray,
                    unfocusedContainerColor = DarkInputGray,
                    focusedBorderColor = ElectricBlue,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = ElectricBlue,
                    focusedLabelColor = ElectricBlue,
                    unfocusedLabelColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier. fillMaxWidth ()
            )
            // Campo Dirección
            OutlinedTextField(
                value=direccion,
                onValueChange =  { direccion =  it} ,
                label = { Text("Dirección de Envío (Zona de Entrega)", color = ElectricBlue) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = DarkInputGray,
                    unfocusedContainerColor = DarkInputGray,
                    focusedBorderColor = ElectricBlue,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = ElectricBlue,
                    focusedLabelColor = ElectricBlue,
                    unfocusedLabelColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier. fillMaxWidth ()
            )

            // Checkbox 1
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked =conPapas,
                    onCheckedChange =  { conPapas =  it} ,
                    colors = CheckboxDefaults.colors(
                        checkedColor = ElectricBlue,
                        uncheckedColor = Color.Gray
                    )
                )
                Text("Añadir Power-Up (Envio Rapido)", color = Color.White)
            }
            // Checkbox 2
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked =agrandarBebida,
                    onCheckedChange =  { agrandarBebida =  it} ,
                    colors = CheckboxDefaults.colors(
                        checkedColor = ElectricBlue,
                        uncheckedColor = Color.Gray
                    )
                )
                Text("Añadir Paquete de EXP (Garantia por 1 año)", color = Color.White)
            }

            Spacer(modifier =Modifier. height (16. dp ))

            // Botón CONFIRMAR COMPRA
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
                    containerColor = ElectricBlue, // Fondo del botón Azul Eléctrico
                    contentColor = Color.Black // Texto del botón Negro (máximo contraste)
                ),
                modifier = Modifier.fillMaxWidth().height(56.dp)
            )
            {
                Text("CONFIRMAR COMPRA")
            }

            Spacer(modifier =Modifier. height (16. dp ))

            // Título de Inventario/Compras
            Text("Inventario Actual / Compras Anteriores :", style=MaterialTheme.typography.headlineSmall, color = ElectricBlue ) // Azul Eléctrico

            if (productos. isNotEmpty ()){
                LazyColumn(modifier=Modifier. weight (1f)) {
                    items (productos) {  producto  ->
                        Card(
                            modifier = Modifier
                                . fillMaxWidth ()
                                . padding (4. dp ),
                            colors = CardDefaults.cardColors(
                                // Fondo de las tarjetas de compra en un gris oscuro
                                containerColor = DarkInputGray
                            )
                        )
                        {
                            Column(modifier=Modifier. padding (8. dp )  )
                            {
                                Text(
                                    text ="${producto.nombre} - ${producto.precio} Monedas",
                                    style=MaterialTheme.typography.bodyLarge,
                                    color = ElectricBlue // Azul Eléctrico
                                )
                                Text(
                                    text ="Cantidad ${producto.cantidad}",
                                    style=MaterialTheme.typography.bodyMedium,
                                    color = Color.White // Blanco
                                )
                                Text(
                                    text ="Direccion ${producto.direccion}",
                                    style=MaterialTheme.typography.bodyMedium,
                                    color = Color.White // Blanco
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
                    style=MaterialTheme.typography.bodyMedium,
                    color = Color.Gray // Gris
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductoFormScreen() {

    ProductoFormScreen(
        navController = rememberNavController(),
        nombre = "Juegos de Mesa",
        precio = "15000",
        idImagen = android.R.drawable.btn_star
    )
}