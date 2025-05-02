package com.mycompany.gestioninventariofarmaciabenigno;


public class GestionInventarioFarmaciaBenigno {

 public static void main(String[] args) {
        // Solo esto es necesario: ejecuta el JFrame en el hilo de interfaz gráfica (Swing)
        javax.swing.SwingUtilities.invokeLater(() -> {
            new PrincipalJframe().setVisible(true);
        });
 
    }

}
