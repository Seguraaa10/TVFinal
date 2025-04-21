package cua.uam.mx.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cua.uam.mx.bl.Vendedor;
import cua.uam.mx.dal.GerenteDao;

public class GerenteUI extends JFrame {
    private GerenteDao gerenteDao = new GerenteDao();

    public GerenteUI() {
        setTitle("Tienda Virtual - Menú Gerente");
        setSize(400, 400);
        setLayout(null);

        JButton agregarBtn = new JButton("Agregar Vendedor");
        JButton modificarBtn = new JButton("Modificar Vendedor");
        JButton eliminarBtn = new JButton("Eliminar Vendedor");
        JButton mostrarBtn = new JButton("Ver Vendedores");

        agregarBtn.setBounds(100, 30, 200, 30);
        modificarBtn.setBounds(100, 70, 200, 30);
        eliminarBtn.setBounds(100, 110, 200, 30);
        mostrarBtn.setBounds(100, 150, 200, 30);

        add(agregarBtn);
        add(modificarBtn);
        add(eliminarBtn);
        add(mostrarBtn);

        agregarBtn.addActionListener(e -> new AgregarVendedorUI());
        modificarBtn.addActionListener(e -> new ModificarVendedorUI());
        eliminarBtn.addActionListener(e -> new EliminarVendedorUI());
        mostrarBtn.addActionListener(e -> imprimirVendedores());

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void imprimirVendedores() {
        JFrame frame = new JFrame("Lista de Vendedores");
        frame.setSize(400, 400);
        frame.setLayout(null);
    
        JTextArea area = new JTextArea();
        area.setEditable(false); 
    
        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(10, 10, 360, 320);
        frame.add(scroll);
    
        StringBuilder sb = new StringBuilder();
        double totalVentas = 0;
    
       
        for (Vendedor v : gerenteDao.imprimirVendedores()) {
            sb.append("ID: ").append(v.getId_Vendedor()).append("\n");
            sb.append("Nombre: ").append(v.getNombre()).append("\n");
            sb.append("Sueldo: ").append(v.getSueldo()).append("\n");
            sb.append("Total Ventas: ").append(v.getTotal_Ventas()).append("\n");
            sb.append("-------------------------\n");
    
            try {
                totalVentas += Double.parseDouble(v.getTotal_Ventas());
            } catch (NumberFormatException e) {
                System.err.println("Error al convertir total_ventas: " + e.getMessage());
            }
        }
    
        sb.append("TOTAL DE VENTAS DE TODOS LOS VENDEDORES: $").append(totalVentas).append("\n");
    
        area.setText(sb.toString());
    
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    

}
