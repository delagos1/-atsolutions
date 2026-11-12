package com.autotechsolutions.main;

import com.autotechsolutions.vista.VentanaPrincipal;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public final class Main {
    private Main() {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                System.err.println("No se pudo aplicar el tema del sistema: " + ex.getMessage());
            }
            new VentanaPrincipal().setVisible(true);
        });
    }
}
