package cua.uam.mx.dal;

import cua.uam.mx.bl.Cliente;

public interface EntidadC {
    public void getAll();
    public int save(Cliente cliente);
    public void update(Cliente cliente);
    public int delete(String id);
    Cliente getById(String id);
 
}
