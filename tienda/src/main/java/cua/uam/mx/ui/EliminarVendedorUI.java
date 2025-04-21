package cua.uam.mx.ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cua.uam.mx.dal.GerenteDao;

public class EliminarVendedorUI extends JFrame {
    public EliminarVendedorUI() {
        setTitle("TV- Despedir Vendedor");
        setSize(300, 150);
        setLayout(new GridLayout(4, 1));
        setLocationRelativeTo(null);

        JTextField id = new JTextField();
        JButton eliminar = new JButton("Despedir");

        add(new JLabel("                    ID del vendedor a despedir:"));
        add(id);
        add(new JLabel(""));
        add(eliminar);

        eliminar.addActionListener(e -> {
            GerenteDao dao = new GerenteDao();
            int resultado = dao.delete(id.getText());

            if (resultado == 1) {
                JOptionPane.showMessageDialog(this, "Vendedor despedido exitosamente");
                dispose();
            } else if (resultado == 0) {
                JOptionPane.showMessageDialog(this, "No se encontró ningún vendedor con ese ID");
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el vendedor");
            }
        });

        setVisible(true);
    }
}
