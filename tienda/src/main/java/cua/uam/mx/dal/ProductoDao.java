package cua.uam.mx.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cua.uam.mx.bl.Producto;

public class ProductoDao implements EntityDao {
   private Connection connection;

   public ProductoDao() {
      DbConnection db = new DbConnection();
      db.connect();
      this.connection = db.getConnection();
   }
   
   @Override
   public void getAll() {
      try {
         List<Producto> productos = new ArrayList<>();
         String sql = "SELECT * FROM producto";
         Statement stmt = this.connection.createStatement();
         ResultSet rs = stmt.executeQuery(sql);

         while(rs.next()) {
            Producto producto = new Producto();
            producto.setId(rs.getString(1));
            producto.setNombre(rs.getString(2));
            producto.setPrecio(rs.getString(3));
            producto.setInventario(rs.getString(4));
            producto.setTipo(rs.getString(5));
            productos.add(producto);
            System.out.println(producto.toString());
         }
      } catch (SQLException ex) {
         System.err.println(ex.getStackTrace());
      }

   }
  
  

   public int save(Producto producto) {
      try {
         String sql = "INSERT INTO producto VALUES(?,?,?,?,?)";
         PreparedStatement stmt = this.connection.prepareStatement(sql);
         stmt.setString(1, producto.getId());
         stmt.setString(2, producto.getNombre());
         stmt.setString(3, producto.getPrecio());
         stmt.setString(4, producto.getInventario());
         stmt.setString(5, producto.getTipo());
         stmt.execute();

         return -1;
      } catch (SQLException ex) {
         System.err.println(ex.getStackTrace());
         return -1;
      }
   }


   @Override
public int delete(String id) {
    try {
        String sql = "DELETE FROM producto WHERE id = ?";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        stmt.setString(1, id);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected;
    } catch (SQLException ex) {
        return -1;
    }
}


public void update(Producto producto) {
    try {
        String sql = "UPDATE producto SET nombre = ?, precio = ?, inventario = ?, tipo = ? WHERE id = ?";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        stmt.setString(1, producto.getNombre());
        stmt.setString(2, producto.getPrecio());
        stmt.setString(3, producto.getInventario());
        stmt.setString(4, producto.getTipo());
        stmt.setString(5, producto.getId());

        int rowsAffected = stmt.executeUpdate();
    } catch (SQLException ex) {
        System.err.println("Error al actualizar producto: " + ex.getMessage());
    }
}

}


    


