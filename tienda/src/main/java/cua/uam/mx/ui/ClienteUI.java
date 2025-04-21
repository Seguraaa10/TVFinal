package cua.uam.mx.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cua.uam.mx.bl.Producto;
import cua.uam.mx.dal.VendedorDao;

public class ClienteUI extends JFrame {

    private VendedorDao vendedorDao = new VendedorDao();
    private List<CarritoItem> carrito = new ArrayList<>();
    private JTextArea carritoArea;

    public ClienteUI() {
        setTitle("Carrito de Compras - Cliente");
        setSize(600, 600);
        setLayout(new BorderLayout());

        JPanel productosPanel = new JPanel();
        productosPanel.setLayout(new BoxLayout(productosPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(productosPanel);

        List<Producto> productos = vendedorDao.imprimirProductos();

        for (Producto producto : productos) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel nombre = new JLabel(producto.getNombre() + " - Precio: $" + producto.getPrecio() +
                    " - Stock: " + producto.getInventario());
            JTextField cantidadField = new JTextField("1", 3);
            JButton agregarBtn = new JButton("Agregar");

            agregarBtn.addActionListener(e -> {
                int cantidad;
                try {
                    cantidad = Integer.parseInt(cantidadField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Cantidad inválida");
                    return;
                }

                int stock = Integer.parseInt(producto.getInventario());
                if (cantidad <= 0 || cantidad > stock) {
                    JOptionPane.showMessageDialog(this, "Cantidad fuera de rango");
                    return;
                }

                carrito.add(new CarritoItem(producto, cantidad));
                actualizarCarritoArea();
            });

            panel.add(nombre);
            panel.add(new JLabel("Cantidad: "));
            panel.add(cantidadField);
            panel.add(agregarBtn);
            productosPanel.add(panel);
        }

        carritoArea = new JTextArea(10, 50);
        carritoArea.setEditable(false);
        JScrollPane carritoScroll = new JScrollPane(carritoArea);

        JButton confirmarBtn = new JButton("Confirmar Compra");
        confirmarBtn.addActionListener(e -> confirmarCompra());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(carritoScroll, BorderLayout.CENTER);
        bottomPanel.add(confirmarBtn, BorderLayout.SOUTH);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void actualizarCarritoArea() {
        StringBuilder sb = new StringBuilder();
        double total = 0;
        for (CarritoItem item : carrito) {
            Producto p = item.getProducto();
            int cantidad = item.getCantidad();
            double subtotal = Double.parseDouble(p.getPrecio()) * cantidad;
            total += subtotal;
            sb.append(p.getNombre()).append(" x ").append(cantidad)
              .append(" = $").append(subtotal).append("\n");
        }
        sb.append("\nTOTAL: $").append(total);
        carritoArea.setText(sb.toString());
    }

    private void confirmarCompra() {
        for (CarritoItem item : carrito) {
            Producto p = item.getProducto();
            int cantidad = item.getCantidad();
            int nuevoInventario = Integer.parseInt(p.getInventario()) - cantidad;
            p.setInventario(String.valueOf(nuevoInventario));
            vendedorDao.update(p);
        }

        double total = carrito.stream()
                .mapToDouble(item -> Double.parseDouble(item.getProducto().getPrecio()) * item.getCantidad())
                .sum();

        carrito.clear();
        actualizarCarritoArea();
        JOptionPane.showMessageDialog(this, "Compra realizada. Total pagado: $" + total);
    }

    public static void main(String[] args) {
        new ClienteUI();
    }
}

class CarritoItem {
    private Producto producto;
    private int cantidad;

    public CarritoItem(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }
}

