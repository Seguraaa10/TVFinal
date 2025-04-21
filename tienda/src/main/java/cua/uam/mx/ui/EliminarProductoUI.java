package cua.uam.mx.ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cua.uam.mx.dal.ProductoDao;

public class EliminarProductoUI extends JFrame {
    public EliminarProductoUI() {
        setTitle("TV- Eliminar Producto");
        setSize(300, 150);
        setLayout(new GridLayout(4, 1));
        setLocationRelativeTo(null);

        JTextField id = new JTextField();
        JButton eliminar = new JButton("Eliminar");

        add(new JLabel("                    ID del producto a eliminar:"));
        add(id);
        add(new JLabel("")); 
        add(eliminar);

        eliminar.addActionListener(e -> {
            ProductoDao del = new ProductoDao();
            int resultado = del.delete(id.getText());
        
            if (resultado == 1) {
                JOptionPane.showMessageDialog(this, "Producto eliminado del catálogo.");
                dispose();
            } else if (resultado == 0) {
                JOptionPane.showMessageDialog(this, "No se encontró ningún producto con ese ID.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el producto.");
            }
        });
        

        setVisible(true);
    }
}
