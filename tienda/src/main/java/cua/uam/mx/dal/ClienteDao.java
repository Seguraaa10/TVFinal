package cua.uam.mx.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cua.uam.mx.bl.Cliente;

public class ClienteDao implements EntidadC {
    private Connection connection;

   public ClienteDao() {
      DbConnection db = new DbConnection();
      db.connect();
      this.connection = db.getConnection();
   }

   @Override
   public void getAll() {
      try {
         List<Cliente> clientes = new ArrayList<>();
         String sql = "SELECT * FROM cliente";
         Statement stmt = this.connection.createStatement();
         ResultSet rs = stmt.executeQuery(sql);

         while(rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setId_Cliente(rs.getString(1));
            cliente.setNombre(rs.getString(2));
            cliente.setPassword(rs.getString(3));
            cliente.setCorreo(rs.getString(4));
            cliente.setTotal(rs.getString(5));
            clientes.add(cliente);
            System.out.println(cliente.toString());
         }
      } catch (SQLException ex) {
         System.err.println(ex.getStackTrace());
      }
   }

   public int save(Cliente cliente) {
    try {
        
        String checkSql = "SELECT COUNT(*) FROM cliente WHERE id_cliente = ?";
        PreparedStatement checkStmt = this.connection.prepareStatement(checkSql);
        checkStmt.setString(1, cliente.getId_Cliente());
        ResultSet rs = checkStmt.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            System.out.println("Ya existe un cliente con este ID.Pruebe otro");
            return 0; 
        }

        
        String sql = "INSERT INTO cliente VALUES(?,?,?,?,?)";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        stmt.setString(1, cliente.getId_Cliente());
        stmt.setString(2, cliente.getNombre());
        stmt.setString(3, cliente.getPassword());
        stmt.setString(4, cliente.getCorreo());
        stmt.setString(5, cliente.getTotal());
        stmt.execute();

        return 1; // Éxito
    } catch (SQLException ex) {
        return -1; 
    }
}


   public void update(Cliente cliente) {
    try {
        String sql = "UPDATE cliente SET nombre = ?, password = ?, correo = ?, total_de_Compras = ? WHERE id_cliente = ?";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        stmt.setString(1, cliente.getNombre());
        stmt.setString(2, cliente.getPassword());
        stmt.setString(3, cliente.getCorreo());
        stmt.setString(4, cliente.getTotal());
        stmt.setString(5, cliente.getId_Cliente());

        int rowsAffected = stmt.executeUpdate();
    } catch (SQLException ex) {
        System.err.println("Error al actualizar producto: " + ex.getMessage());
    }
}

@Override
public int delete(String id) {
    try {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        stmt.setString(1, id);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected;
    } catch (SQLException ex) {
        return -1;
    }
}

public Cliente getById(String id) {
   try {
       String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
       PreparedStatement stmt = this.connection.prepareStatement(sql);
       stmt.setString(1, id);
       ResultSet rs = stmt.executeQuery();

       if (rs.next()) {
           Cliente cliente = new Cliente();
           cliente.setId_Cliente(rs.getString(1));
           cliente.setNombre(rs.getString(2));
           cliente.setPassword(rs.getString(3));
           cliente.setCorreo(rs.getString(4));
           cliente.setTotal(rs.getString(5));
           return cliente;
       }
   } catch (SQLException ex) {
       ex.printStackTrace();
   }
   return null;
}

}
