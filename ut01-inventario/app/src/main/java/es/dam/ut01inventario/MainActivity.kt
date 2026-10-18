package es.dam.ut01inventario

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.dam.ut01inventario.modelo.Producto
import es.dam.ut01inventario.servicio.Inventario
import es.dam.ut01inventario.ui.theme.UT01InventarioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UT01InventarioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android", modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }


        Log.d("InventarioApp", "===== INICIO DE DEMOSTRACIÓN =====")

        Inventario.vaciarInventario()

        val teclado = Producto(1, "Teclado mecánico", 59.99, 10)
        val raton = Producto(2, "Ratón inalámbrico", 24.50, 15)
        val monitor = Producto(3, "Monitor LED", 199.99, 5)

        Inventario.agregarProducto(teclado)
        Inventario.agregarProducto(raton)
        Inventario.agregarProducto(monitor)

        Log.d("InventarioApp", "--- INVENTARIO INICIAL ---")
        Inventario.listarProductos()

        Log.d("InventarioApp", "--- PRUEBA DE ID DUPLICADO ---")
        Inventario.agregarProducto(teclado)

        Log.d("InventarioApp", "--- BÚSQUEDA DE PRODUCTOS ---")

        val resultados = Inventario.buscarProductoPorNombre("TECLA")

        resultados.forEach { producto ->
            Log.d(
                "InventarioApp", "Encontrado: ${producto.nombre} - Precio: ${producto.precio} €"
            )
        }

        Log.d("InventarioApp", "--- ACTUALIZACIÓN DE STOCK ---")
        Inventario.actualizarStock(2, 25)

        Log.d("InventarioApp", "--- ELIMINACIÓN DE PRODUCTO ---")
        Inventario.eliminarProducto(3)

        Log.d("InventarioApp", "--- INVENTARIO FINAL ---")
        Inventario.listarProductos()

        Log.d("InventarioApp", "===== FIN DE DEMOSTRACIÓN =====")

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UT01InventarioTheme {
        Greeting("Android")
    }
}