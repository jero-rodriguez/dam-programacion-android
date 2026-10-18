package es.dam.ut01inventario.servicio

import android.util.Log
import es.dam.ut01inventario.modelo.Producto

object Inventario {
    private val productos: MutableList<Producto> = mutableListOf()

    fun agregarProducto(producto: Producto) {
        val existe = productos.any { it.id == producto.id }
        if (existe) {
            Log.d("InventarioApp", "ERROR: ya existe un producto con ID ${producto.id}")
        } else {
            productos.add(producto)
            Log.d("InventarioApp", "Producto agregado: ${producto.nombre}")
        }
    }

    fun eliminarProducto(id: Int) {
        val producto = productos.find { it.id == id }
        if (producto != null) {
            productos.remove(producto)
            Log.d("InventarioApp", "Producto eliminado: ${producto.nombre}")
        } else {
            Log.d("InventarioApp", "ERROR: no existe un producto con ID $id")
        }
    }

    fun buscarProductoPorNombre(nombre: String): List<Producto> {
        return productos.filter { it.nombre.contains(nombre, ignoreCase = true) }
    }

    fun listarProductos() {
        Log.d("InventarioApp", "===== INVENTARIO =====")
        if (productos.isEmpty()) {
            Log.d("InventarioApp", "No hay productos en el inventario")
        } else {
            productos.forEach { producto ->
                Log.d(
                    "InventarioApp", "ID: ${producto.id}, " +
                            "Nombre: ${producto.nombre}, " +
                            "Precio: ${producto.precio}, " +
                            "Cantidad en Stock: ${producto.cantidadEnStock}"
                )
            }
        }
        Log.d("InventarioApp", "======================")
    }

    fun actualizarStock(id: Int, nuevaCantidad: Int) {
        val producto = productos.find { it.id == id }
        if(producto != null) {
            producto.cantidadEnStock = nuevaCantidad
            Log.d("InventarioApp", "Stock actualizado: ${producto.nombre} - Nueva cantidad: $nuevaCantidad")
        } else {
            Log.d("InventarioApp", "ERROR: no existe un producto con ID $id")
        }
    }

    fun vaciarInventario() {
        productos.clear()
        Log.d("InventarioApp", "Inventario vaciado correctamente")
    }

}