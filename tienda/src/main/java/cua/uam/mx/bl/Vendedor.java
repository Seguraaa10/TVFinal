package cua.uam.mx.bl;

public class Vendedor {
    private String id_Vendedor; 
    private String password; 
    private String nombre; 
    private String sueldo; 
    private String total_Ventas;

    public Vendedor(){

    }
    
    public Vendedor(String id_Vendedor, String password, String nombre, String sueldo, String total_Ventas ){
        this.id_Vendedor = id_Vendedor;
        this.password = password;
        this.nombre = nombre; 
        this.sueldo = sueldo; 
        this.total_Ventas  = total_Ventas;
    }

    public String getId_Vendedor() {
        return id_Vendedor;
    }

    public void setId_Vendedor(String id_Vendedor) {
        this.id_Vendedor = id_Vendedor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    public String getTotal_Ventas() {
        return total_Ventas;
    }

    public void setTotal_Ventas(String total_Ventas) {
        this.total_Ventas = total_Ventas;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
                String sep = "\n";
                str.append("Id de Vendedor: ");
                str.append(id_Vendedor);
                str.append(sep);
                str.append("Nombre: ");
                str.append(nombre);
                str.append(sep);
                str.append("Contraseña: ");
                str.append(password);
                str.append(sep);
                str.append("Sueldo: ");
                str.append(sueldo);
                str.append(sep);
                str.append("Total de ventas: ");
                str.append(total_Ventas);
                str.append(sep);
        return str.toString();
    }
    
}
