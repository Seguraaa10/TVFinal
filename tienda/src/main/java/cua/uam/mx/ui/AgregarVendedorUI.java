package cua.uam.mx.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cua.uam.mx.bl.Vendedor;
import cua.uam.mx.dal.GerenteDao;

public class AgregarVendedorUI extends JFrame {
    public AgregarVendedorUI() {
        setTitle("TV-Contratar Vendedor");
        setSize(300, 300);
        setLayout(null);

        JTextField idField = new JTextField(); idField.setBounds(120, 20, 150, 25);
        JTextField nombreField = new JTextField(); nombreField.setBounds(120, 50, 150, 25);
        JTextField passField = new JTextField(); passField.setBounds(120, 80, 150, 25);
        JTextField sueldoField = new JTextField(); sueldoField.setBounds(120, 110, 150, 25);
        JTextField ventasField = new JTextField(); ventasField.setBounds(120, 140, 150, 25);

        add(new JLabel("ID:")).setBounds(20, 20, 100, 25); add(idField);
        add(new JLabel("Nombre:")).setBounds(20, 50, 100, 25); add(nombreField);
        add(new JLabel("Contraseña:")).setBounds(20, 80, 100, 25); add(passField);
        add(new JLabel("Sueldo:")).setBounds(20, 110, 100, 25); add(sueldoField);
        add(new JLabel("Ventas:")).setBounds(20, 140, 100, 25); add(ventasField);

        JButton agregarBtn = new JButton("Contratar vendedor");
        agregarBtn.setBounds(100, 180, 100, 30);
        add(agregarBtn);

        agregarBtn.addActionListener(e -> {
            GerenteDao gerenteDao = new GerenteDao();
            Vendedor nuevoVendedor = new Vendedor(
                idField.getText(),
                passField.getText(),
                nombreField.getText(),
                sueldoField.getText(),
                ventasField.getText()
            );

        int resultado = gerenteDao.save(nuevoVendedor);
          if (resultado == 1) {
            JOptionPane.showMessageDialog(this, "¡Vendedor Contratado");
            dispose();
        } else if (resultado == 0) {
             JOptionPane.showMessageDialog(this, "El ID ya está registrado.Prueba con otro.");
        } else {
            JOptionPane.showMessageDialog(this, "error base de datos");
        }
        }
        );

        setVisible(true);
        setLayout(null);
        setLocationRelativeTo(null);
    }
}
