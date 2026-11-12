package com.autotechsolutions.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaPrincipal extends JFrame {
    private static final Color AZUL = new Color(23, 38, 58);
    private static final Color AZUL_ACTIVO = new Color(43, 68, 96);
    private final CardLayout tarjetas = new CardLayout();
    private final JPanel contenido = new JPanel(tarjetas);

    public VentanaPrincipal() {
        setTitle("AutoTech Solutions | Gestión de Taller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1050, 680));
        setSize(1200, 760);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(crearMenu(), BorderLayout.WEST);
        add(contenido, BorderLayout.CENTER);
        agregarPantalla("Dashboard", "Bienvenido a AutoTech Solutions");
        for (String modulo : new String[]{"Clientes", "Vehículos", "Servicios", "Repuestos",
                "Órdenes de trabajo", "Facturación", "Reportes"}) {
            agregarPantalla(modulo, modulo + " | Módulo en construcción");
        }
        tarjetas.show(contenido, "Dashboard");
    }

    private JPanel crearMenu() {
        JPanel menu = new JPanel();
        menu.setBackground(AZUL);
        menu.setPreferredSize(new Dimension(245, 0));
        menu.setBorder(BorderFactory.createEmptyBorder(22, 14, 18, 14));
        menu.setLayout(new javax.swing.BoxLayout(menu, javax.swing.BoxLayout.Y_AXIS));

        JLabel marca = new JLabel("  AutoTech Solutions");
        marca.setForeground(Color.WHITE);
        marca.setFont(new Font("SansSerif", Font.BOLD, 18));
        marca.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        menu.add(marca);

        JLabel subtitulo = new JLabel("  GESTIÓN AUTOMOTRIZ");
        subtitulo.setForeground(new Color(174, 193, 213));
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 10));
        subtitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        menu.add(subtitulo);

        String[] opciones = {"Dashboard", "Clientes", "Vehículos", "Servicios", "Repuestos",
                "Órdenes de trabajo", "Facturación", "Reportes"};
        for (String opcion : opciones) {
            JButton boton = new JButton(opcion);
            boton.setAlignmentX(LEFT_ALIGNMENT);
            boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 43));
            boton.setPreferredSize(new Dimension(215, 43));
            boton.setHorizontalAlignment(SwingConstants.LEFT);
            boton.setMargin(new Insets(0, 14, 0, 8));
            boton.setForeground(Color.WHITE);
            boton.setBackground(opcion.equals("Dashboard") ? AZUL_ACTIVO : AZUL);
            boton.setFont(new Font("SansSerif", Font.PLAIN, 13));
            boton.setFocusPainted(false);
            boton.setBorderPainted(false);
            boton.addActionListener(e -> tarjetas.show(contenido, opcion));
            menu.add(boton);
        }
        return menu;
    }

    private void agregarPantalla(String clave, String titulo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(246, 248, 251));
        panel.setBorder(BorderFactory.createEmptyBorder(35, 40, 35, 40));
        JLabel etiqueta = new JLabel(titulo);
        etiqueta.setFont(new Font("SansSerif", Font.BOLD, 25));
        etiqueta.setForeground(new Color(35, 48, 65));
        panel.add(etiqueta, BorderLayout.NORTH);
        contenido.add(panel, clave);
    }
}
