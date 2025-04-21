package cua.uam.mx.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cua.uam.mx.bl.Vendedor;
import cua.uam.mx.dal.GerenteDao;

public class ModificarVendedorUI extends JFrame {
    public ModificarVendedorUI() {
        setTitle("TV-Modificar Vendedor");
        setSize(300, 300);
        setLayout(null);

        JTextField idField = new JTextField(); idField.setBounds(120, 20, 150, 25);
        JTextField nombreField = new JTextField(); nombreField.setBounds(120, 50, 150, 25);
        JTextField passField = new JTextField(); passField.setBounds(120, 80, 150, 25);
        JTextField sueldoField = new JTextField(); sueldoField.setBounds(120, 110, 150, 25);
        JTextField ventasField = new JTextField(); ventasField.setBounds(120, 140, 150, 25);

        JLabel idLabel = new JLabel("ID:"); idLabel.setBounds(20, 20, 100, 25);
        JLabel nombreLabel = new JLabel("Nombre:"); nombreLabel.setBounds(20, 50, 100, 25);
        JLabel passLabel = new JLabel("Contraseña:"); passLabel.setBounds(20, 80, 100, 25);
        JLabel sueldoLabel = new JLabel("Sueldo:"); sueldoLabel.setBounds(20, 110, 100, 25);
        JLabel ventasLabel = new JLabel("Ventas:"); ventasLabel.setBounds(20, 140, 100, 25);

        add(idLabel); add(idField);
        add(nombreLabel); add(nombreField);
        add(passLabel); add(passField);
        add(sueldoLabel); add(sueldoField);
        add(ventasLabel); add(ventasField);

        JButton modificarBtn = new JButton("Modificar");
        modificarBtn.setBounds(100, 190, 100, 30);
        add(modificarBtn);

        modificarBtn.addActionListener(e -> {
            Vendedor v = new Vendedor(
                idField.getText(),
                passField.getText(),
                nombreField.getText(),
                sueldoField.getText(),
                ventasField.getText()
            );
            GerenteDao dao = new GerenteDao();
            int resultado = dao.update(v);

            if (resultado == 1) {
                JOptionPane.showMessageDialog(this, "Vendedor modificado exitosamente");
                dispose();
            } else if (resultado == 0) {
                JOptionPane.showMessageDialog(this, "No se encontró ningún vendedor con ese ID");
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar el vendedor");
            }
        });

        setVisible(true);
        setLocationRelativeTo(null);
    }
}
