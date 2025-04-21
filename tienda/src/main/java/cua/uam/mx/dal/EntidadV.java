package cua.uam.mx.dal;

import cua.uam.mx.bl.Producto;
import cua.uam.mx.bl.Vendedor;

public interface EntidadV {
    public void getAll();
    public int save(Producto producto);
    public int update(Producto producto);
    public int delete(String id);
    Vendedor getById(String id);
}
