package com.mycompany.gestioninventariofarmaciabenigno;

import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class InventarioFarmacia {
    private ProductoDTO[] productos = new ProductoDTO[100]; // Vector estático
    private int contadorProductos = 0; // Control de productos registrados
    // Agrega un nuevo producto si hay espacio
    public void agregarProducto(ProductoDTO producto) {
        if (contadorProductos < productos.length) {
            productos[contadorProductos] = producto;
            contadorProductos++;
            JOptionPane.showMessageDialog(null, "Producto agregado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Inventario lleno. No se puede agregar más productos.");
        }
    }

    // Busca producto por código de barras
    public ProductoDTO buscarProductoPorCodigo(String codigoBarras) {
        for (int i = 0; i < contadorProductos; i++) {
            if (productos[i].getCodigoBarras().equalsIgnoreCase(codigoBarras)) {
                return productos[i];
            }
        }
        return null;
    }

    // Muestra todos los productos del inventario
    public void mostrarInventario() {
        if (contadorProductos == 0) {
            JOptionPane.showMessageDialog(null, "Inventario vacío.");
            return;
        }

        StringBuilder sb = new StringBuilder("=== Inventario de la Farmacia ===\n\n");
        for (int i = 0; i < contadorProductos; i++) {
            sb.append(productos[i].toString()).append("\n------------------------\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Realiza la venta de un producto
    public boolean venderProducto(String codigoBarras, int cantidadVendida) {
        ProductoDTO p = buscarProductoPorCodigo(codigoBarras);
        if (p != null) {
            if (p.getCantidadStock() >= cantidadVendida) {
                p.setCantidadStock(p.getCantidadStock() - cantidadVendida);
                JOptionPane.showMessageDialog(null, "Venta realizada con éxito.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Stock insuficiente para realizar la venta.");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            return false;
        }
    }

    // Actualiza el precio del producto
    public boolean actualizarPrecio(String codigoBarras, double nuevoPrecio) {
        ProductoDTO p = buscarProductoPorCodigo(codigoBarras);
        if (p != null) {
            p.setPrecioUnitario(nuevoPrecio);
            JOptionPane.showMessageDialog(null, "Precio actualizado exitosamente.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            return false;
        }
    }
    
    
        public void guardarInventarioEnArchivo(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (ProductoDTO producto : this.productos) {
                writer.write(productos); // Escribe cada producto en una nueva línea
                writer.newLine();        // Agrega una nueva línea
            }
            System.out.println("Inventario guardado en " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el inventario: " + e.getMessage());
        }}

/*No se encontró ningún método adecuado para write(ProductoDTO).
El método Writer.write(char[]) no es aplicable.
(Discordancia de argumentos; ProductoDTO no se puede convertir a char[]).
El método Writer.write(String) no es aplicable.
(Discordancia de argumentos; ProductoDTO no se puede convertir a String).
El método BufferedWriter.write(int) no es aplicable.
(Discordancia de argumentos; ProductoDTO no se puede convertir a int).*/
    
    public ProductoDTO[] getProductos() {
    return productos;
}

public int getContadorProductos() {
    return contadorProductos;
}

public boolean eliminarProducto(String codigo) {
    for (int i = 0; i < contadorProductos; i++) {
        if (productos[i].getCodigoBarras().equals(codigo)) {
            // Mover los siguientes elementos una posición hacia atrás
            for (int j = i; j < contadorProductos - 1; j++) {
                productos[j] = productos[j + 1];
            }
            productos[contadorProductos - 1] = null;
            contadorProductos--;
            return true;
        }
    }
    return false;
}


}

