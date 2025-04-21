package cua.uam.mx.dal;

import cua.uam.mx.bl.Producto;

public interface EntityDao {
    public void getAll();
    public int save(Producto producto);
    public void update(Producto producto);
    public int delete(String id);
}
