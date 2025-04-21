package cua.uam.mx.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import cua.uam.mx.dal.*;
import cua.uam.mx.bl.*;


public class LoginUI extends JFrame {
    private JTextField idField;
    private JPasswordField passwordField;
    private JComboBox<String> tipoUsuario;
    private JButton loginButton;
    private JButton registrarButton;

    public LoginUI() {
        setTitle("TV-Inicio sesión.");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(340, 300);
        setLayout(null);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(30, 30, 100, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 30, 150, 25);
        add(idField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(10, 70, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 70, 150, 25);
        add(passwordField);

        JLabel tipoLabel = new JLabel("Usuario:");
        tipoLabel.setBounds(30, 110, 100, 25);
        add(tipoLabel);

        tipoUsuario = new JComboBox<>(new String[] {"Cliente", "Vendedor", "Gerente"});
        tipoUsuario.setBounds(100, 110, 150, 25);
        add(tipoUsuario);

        loginButton = new JButton("Iniciar sesión");
        loginButton.setBounds(70, 160, 150, 30);
        add(loginButton);

        registrarButton = new JButton("Registrarse");
        registrarButton.setBounds(70, 200, 150, 30);
        registrarButton.addActionListener(e -> new RegistroClienteUI());
        add(registrarButton);

        
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void realizarLogin() {
        String id = idField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String tipo = (String) tipoUsuario.getSelectedItem();

        switch (tipo) {
            case "Cliente":
                ClienteDao clienteDao = new ClienteDao();
                Cliente cliente = clienteDao.getById(id);
                if (cliente != null && cliente.getPassword().equals(password)) {
                    JOptionPane.showMessageDialog(this, "Bienvenido Cliente " + cliente.getNombre());
                    new ClienteUI();
                } else {
                    mostrarError();
                }
                break;
            case "Vendedor":
                VendedorDao vendedorDao = new VendedorDao();
                Vendedor vendedor = vendedorDao.getById(id);
                if (vendedor != null && vendedor.getPassword().trim().equals(password.trim())) {
                    JOptionPane.showMessageDialog(this, "Bienvenido Vendedor " + vendedor.getNombre());
                    new VendedorUI();
                } else {
                    mostrarError();
                }
                break;
            case "Gerente":
                GerenteDao gerenteDao = new GerenteDao();
                Gerente gerente = gerenteDao.getById(id);
                if (gerente != null && gerente.getPassword().trim().equals(password.trim())) {
                    
                    new GerenteUI();
                    dispose();
                } else {
                    mostrarError();
                }
                break;
        }
    }

    private void mostrarError() {
        JOptionPane.showMessageDialog(this, "ID o Contraseña incorrectas!!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
