package cua.uam.mx.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cua.uam.mx.bl.Cliente;
import cua.uam.mx.dal.ClienteDao;

public class RegistroClienteUI extends JFrame {

    private JTextField idField, nombreField, correoField, passwordField;
    private JButton registrarButton;

    public RegistroClienteUI() {
        setTitle("Registro de Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(350, 300);
        setLayout(null);

        JLabel idLabel = new JLabel("ID a 5 dígitos:");
        idLabel.setBounds(20, 20, 100, 25);
        add(idLabel);
        idField = new JTextField();
        idField.setBounds(120, 20, 180, 25);
        add(idField);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(20, 60, 80, 25);
        add(nombreLabel);
        nombreField = new JTextField();
        nombreField.setBounds(120, 60, 180, 25);
        add(nombreField);

        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setBounds(20, 100, 80, 25);
        add(correoLabel);
        correoField = new JTextField();
        correoField.setBounds(120, 100, 180, 25);
        add(correoField);

        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setBounds(20, 140, 100, 25);
        add(passLabel);
        passwordField = new JTextField();
        passwordField.setBounds(120, 140, 180, 25);
        add(passwordField);

        registrarButton = new JButton("Registrar");
        registrarButton.setBounds(100, 190, 120, 30);
        add(registrarButton);

        registrarButton.addActionListener(e -> {
            ClienteDao clienteDao = new ClienteDao();
            Cliente nuevoCliente = new Cliente(
                idField.getText(),
                nombreField.getText(),
                correoField.getText(),
                passwordField.getText()
            );

        int resultado = clienteDao.save(nuevoCliente);
          if (resultado == 1) {
            JOptionPane.showMessageDialog(this, "¡Cliente registrado exitosamente!");
            dispose();
        } else if (resultado == 0) {
             JOptionPane.showMessageDialog(this, "El ID ya está registrado.Prueba con otro.");
        } 
        }
        );

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
