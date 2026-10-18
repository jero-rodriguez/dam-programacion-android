package es.dam.ut01inventario.modelo

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    var cantidadEnStock: Int
)