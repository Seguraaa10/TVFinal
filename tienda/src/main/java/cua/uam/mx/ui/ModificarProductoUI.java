package cua.uam.mx.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cua.uam.mx.bl.Producto;
import cua.uam.mx.dal.VendedorDao;

public class ModificarProductoUI extends JFrame {
    public ModificarProductoUI() {
        setTitle("TV-Modificar Producto");
        setSize(300, 300);
        setLayout(null);

        JTextField idField = new JTextField(); idField.setBounds(120, 20, 150, 25);
        JTextField nombreField = new JTextField(); nombreField.setBounds(120, 50, 150, 25);
        JTextField precioField = new JTextField(); precioField.setBounds(120, 80, 150, 25);
        JTextField inventarioField = new JTextField(); inventarioField.setBounds(120, 110, 150, 25);
        JTextField tipoField = new JTextField(); tipoField.setBounds(120, 140, 150, 25);

        JLabel idLabel = new JLabel("ID:"); idLabel.setBounds(20, 20, 100, 25);
        JLabel nombreLabel = new JLabel("Nombre:"); nombreLabel.setBounds(20, 50, 100, 25);
        JLabel precioLabel = new JLabel("Precio:"); precioLabel.setBounds(20, 80, 100, 25);
        JLabel inventarioLabel = new JLabel("Inventario:"); inventarioLabel.setBounds(20, 110, 100, 25);
        JLabel tipoLabel = new JLabel("Tipo:"); tipoLabel.setBounds(20, 140, 100, 25);

        add(idLabel); add(idField);
        add(nombreLabel); add(nombreField);
        add(precioLabel); add(precioField);
        add(inventarioLabel); add(inventarioField);
        add(tipoLabel); add(tipoField);

        JButton modificarBtn = new JButton("Modificar");
        modificarBtn.setBounds(100, 190, 100, 30);
        add(modificarBtn);

        modificarBtn.addActionListener(e -> {
            Producto p = new Producto(
                idField.getText(),
                nombreField.getText(),
                precioField.getText(),
                inventarioField.getText(),
                tipoField.getText()
            );
            int resultado = new VendedorDao().update(p);

            if (resultado == 1) {
                JOptionPane.showMessageDialog(this, "Los datos del producto han sido modificados exitosamente.");
                dispose();
            } else if (resultado == 0) {
                JOptionPane.showMessageDialog(this, "No se encontró un producto con ese ID.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar el producto.");
            }
        });

        setVisible(true);
        setLocationRelativeTo(null);
    }
}
