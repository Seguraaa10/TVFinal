package cua.uam.mx.dal;

import cua.uam.mx.bl.Gerente;
import cua.uam.mx.bl.Vendedor;

public interface EntidadG {
    public void getAll();
    public int save(Vendedor vendedor);
    public int update(Vendedor vendedor);
    public int delete(String id);
    Gerente getById(String id);
}

