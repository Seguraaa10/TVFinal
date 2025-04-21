package cua.uam.mx.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cua.uam.mx.bl.Producto;
import cua.uam.mx.bl.Vendedor;

public class VendedorDao implements EntidadV {

    private Connection connection;

    public VendedorDao() {
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
            
            String checkSql = "SELECT COUNT(*) FROM producto WHERE Id = ?";
            PreparedStatement checkStmt = this.connection.prepareStatement(checkSql);
            checkStmt.setString(1, producto.getId());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return 0; 
            }
    
            
            String sql = "INSERT INTO producto VALUES(?,?,?,?,?)";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, producto.getId());
            stmt.setString(2, producto.getNombre());
            stmt.setString(3, producto.getPrecio());
            stmt.setString(4, producto.getInventario());
            stmt.setString(5, producto.getTipo());
            stmt.execute();
    
            return 1; 
        } catch (SQLException ex) {
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
 
 public int update(Producto producto) {
    try {
        String sql = "UPDATE producto SET nombre = ?, precio = ?, inventario = ?, tipo = ? WHERE id = ?";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        stmt.setString(1, producto.getNombre());
        stmt.setString(2, producto.getPrecio());
        stmt.setString(3, producto.getInventario());
        stmt.setString(4, producto.getTipo());
        stmt.setString(5, producto.getId());

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected;
    } catch (SQLException ex) {
        System.err.println("Error al actualizar producto: " + ex.getMessage());
        return -1;
    }
}


 public Vendedor getById(String id) {
   try {
       String sql = "SELECT * FROM vendedor WHERE id_Vendedor = ?";
       PreparedStatement stmt = this.connection.prepareStatement(sql);
       stmt.setString(1, id);
       ResultSet rs = stmt.executeQuery();

       if (rs.next()) {
           Vendedor vendedor = new Vendedor();
           vendedor.setId_Vendedor(rs.getString(1));
           vendedor.setPassword(rs.getString(2));
           vendedor.setNombre(rs.getString(3));
           vendedor.setSueldo(rs.getString(4));
           vendedor.setTotal_Ventas(rs.getString(5));
           return vendedor;
       }
   } catch (SQLException ex) {
       ex.printStackTrace();
   }
   return null;
}




public List<Producto> imprimirProductos() {
    List<Producto> productos = new ArrayList<>();
    try {
        
        String sql = "SELECT Id, Nombre, Precio, Inventario, Tipo FROM producto";
        Statement stmt = this.connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Producto producto = new Producto();
            producto.setId(rs.getString(1));
            producto.setNombre(rs.getString(2));
            producto.setPrecio(rs.getString(3));
            producto.setInventario(rs.getString(4));
            producto.setTipo(rs.getString(5));
            productos.add(producto);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return productos;
}

}
  
