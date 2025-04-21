package cua.uam.mx.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cua.uam.mx.bl.Producto;
import cua.uam.mx.dal.VendedorDao;

public class AgregarProductoUI extends JFrame{
    public AgregarProductoUI(){
        setTitle("TV-Agregar Producto");
        setSize(300,300);
        setLayout(null);

        JTextField IdField = new JTextField(); IdField.setBounds(120,20,150,25);
        JTextField nombreField = new JTextField();nombreField.setBounds(120,50,150,25);
        JTextField precioField = new JTextField();precioField.setBounds(120,80,150,25);
        JTextField inventarioField = new JTextField();inventarioField.setBounds(120,110,150,25);
        JTextField tipoField = new JTextField();tipoField.setBounds(120,140,150,25);

        add(new JLabel("ID:")).setBounds(20,20,100,25);
        add(IdField);
        add(new JLabel("Nombre:")).setBounds(20,50,100,25);
        add(nombreField);
        add(new JLabel("Precio:")).setBounds(20,80,100,25);
        add(precioField);
        add(new JLabel("Inventario:")).setBounds(20,110,100,25);
        add(inventarioField);
        add(new JLabel("Tipo:")).setBounds(20,140,100,25);
        add(tipoField);

        JButton agregarBtn = new JButton("Agregar Producto");
        agregarBtn.setBounds(120,180,150,30);
        add(agregarBtn);

        agregarBtn.addActionListener(e -> {
            VendedorDao vendedorDao = new VendedorDao();
            Producto nuevoProducto = new Producto(
                IdField.getText(),
                nombreField.getText(),
                precioField.getText(),
                inventarioField.getText(),
                tipoField.getText()
            );

        int resultado = vendedorDao.save(nuevoProducto);
          if (resultado == 1) {
            JOptionPane.showMessageDialog(this, "¡Producto agregado al catalogo");
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
