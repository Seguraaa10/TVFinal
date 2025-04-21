package cua.uam.mx.ui;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cua.uam.mx.bl.Producto;
import cua.uam.mx.dal.VendedorDao;

public class VendedorUI extends JFrame {
    private VendedorDao vendedorDao = new VendedorDao();

    public VendedorUI(){
        setTitle("TV- Ménu vendedor");
        setSize(400,400);
        setLayout(null);

        JButton agregarBtn = new JButton("Agregar Producto");
        JButton modificarBtn = new JButton("Modificar Producto");
        JButton eliminarBtn = new JButton("Eliminar Producto");
        JButton mostrarBtn = new JButton("Ver Productos");


        agregarBtn.setBounds(100,30,200,30);
        modificarBtn.setBounds(100,70,200,30);
        eliminarBtn.setBounds(100,110,200,30);
        mostrarBtn.setBounds(100,150,200,30);

        add(agregarBtn);
        add(modificarBtn);
        add(eliminarBtn);
        add(mostrarBtn);

        modificarBtn.addActionListener(e-> new ModificarProductoUI());
        agregarBtn.addActionListener(e -> new AgregarProductoUI());
        eliminarBtn.addActionListener(e -> new EliminarProductoUI());
        mostrarBtn.addActionListener(e -> imprimirProductos());

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void imprimirProductos() {
    JFrame frame = new JFrame("Lista de Productos");
    frame.setSize(400, 400);
    frame.setLayout(null);

    JTextArea area = new JTextArea();
    area.setEditable(false); // para que no se pueda modificar
    JScrollPane scroll = new JScrollPane(area);
    scroll.setBounds(10, 10, 360, 320);
    frame.add(scroll);

    StringBuilder sb = new StringBuilder();

    // Obtener productos de la base de datos
    List<Producto> productos = vendedorDao.imprimirProductos();

    for (Producto producto : productos) {
        sb.append("ID: ").append(producto.getId()).append("\n");
        sb.append("Nombre: ").append(producto.getNombre()).append("\n");
        sb.append("Precio: ").append(producto.getPrecio()).append("\n");
        sb.append("Inventario: ").append(producto.getInventario()).append("\n");
        sb.append("Tipo: ").append(producto.getTipo()).append("\n");
        sb.append("-----------------------------\n");
    }

    area.setText(sb.toString());

    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
}


}
