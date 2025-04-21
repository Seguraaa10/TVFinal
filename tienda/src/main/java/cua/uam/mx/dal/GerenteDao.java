package cua.uam.mx.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cua.uam.mx.bl.Gerente;
import cua.uam.mx.bl.Vendedor;

public class GerenteDao implements EntidadG {
    private Connection connection;

    public GerenteDao() {
        DbConnection db = new DbConnection();
        db.connect();
        this.connection = db.getConnection();
    }

    @Override
    public void getAll() {
        try {
            List<Vendedor> vendedores = new ArrayList<>();
            String sql = "SELECT * FROM vendedor";
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setId_Vendedor(rs.getString(1));
                vendedor.setNombre(rs.getString(2));
                vendedor.setPassword(rs.getString(3));
                vendedor.setSueldo(rs.getString(4));
                vendedor.setTotal_Ventas(rs.getString(5));
                vendedores.add(vendedor);
                System.out.println(vendedor.toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int save(Vendedor vendedor) {
        try {
            String checkSql = "SELECT COUNT(*) FROM vendedor WHERE id_Vendedor = ?";
            PreparedStatement checkStmt = this.connection.prepareStatement(checkSql);
            checkStmt.setString(1, vendedor.getId_Vendedor());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                return 0;
            }

            String sql = "INSERT INTO vendedor VALUES(?,?,?,?,?)";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, vendedor.getId_Vendedor());
            stmt.setString(2, vendedor.getPassword());
            stmt.setString(3, vendedor.getNombre());
            stmt.setString(4, vendedor.getSueldo());
            stmt.setString(5, vendedor.getTotal_Ventas());
            stmt.execute();

            return 1;
        } catch (SQLException ex) {
            return -1;
        }
    }

    public int update(Vendedor vendedor) {
        try {
            String sql = "UPDATE vendedor SET nombre = ?, password = ?, sueldo = ?, total_ventas = ? WHERE id_Vendedor = ?";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, vendedor.getNombre());
            stmt.setString(2, vendedor.getPassword());
            stmt.setString(3, vendedor.getSueldo());
            stmt.setString(4, vendedor.getTotal_Ventas());
            stmt.setString(5, vendedor.getId_Vendedor());

            return stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(String id) {
        try {
            String sql = "DELETE FROM vendedor WHERE id_Vendedor = ?";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, id);
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public Gerente getById(String id) {
        try {
            String sql = "SELECT * FROM gerente WHERE id_Admin = ?";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Gerente gerente = new Gerente();
                gerente.setId_admin(rs.getString(1));
                gerente.setPassword(rs.getString(2));
                gerente.setNombre(rs.getString(3));
                return gerente;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Vendedor> imprimirVendedores() {
        List<Vendedor> vendedores = new ArrayList<>();
        try {
            String sql = "SELECT id_Vendedor, nombre, password, sueldo, total_ventas FROM vendedor";
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setId_Vendedor(rs.getString(1));
                vendedor.setNombre(rs.getString(2));
                vendedor.setPassword(rs.getString(3));
                vendedor.setSueldo(rs.getString(4));
                vendedor.setTotal_Ventas(rs.getString(5));
                vendedores.add(vendedor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vendedores;
    }
}
