package cua.uam.mx.bl;

public class Cliente {
    private String id_cliente;
    private String nombre;
    private String password; 
    private String correo; 
    private String total; 


public Cliente(){

}

public Cliente(String id_cliente, String nombre, String correo, String password) {
    this.id_cliente = id_cliente;
    this.nombre = nombre;
    this.correo = correo;
    this.password = password;
}

public Cliente(String id_cliente, String nombre, String password, String correo,String total){
    this.id_cliente = id_cliente;
    this.nombre = nombre; 
    this.password = password; 
    this.correo = correo; 
    this.total = total; 
}

    public String getId_Cliente() {
        return id_cliente;
    }

    public void setId_Cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password; 
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
                String sep = "\n";
                str.append("Numero de Usuario: ");
                str.append(id_cliente);
                str.append(sep);
                str.append("Nombre: ");
                str.append(nombre);
                str.append(sep);
                str.append("Contraseña: ");
                str.append(password);
                str.append(sep);
                str.append("Correo: ");
                str.append(correo);
                str.append(sep);
                str.append("Total de Sus Compras: ");
                str.append(total);
                str.append(sep);
        return str.toString();
    }

} 
