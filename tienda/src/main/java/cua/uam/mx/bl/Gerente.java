package cua.uam.mx.bl;

public class Gerente {
    private String id_admin;
    private String password; 
    private String nombre; 
    private String apellido; 
    private String correo; 

    public Gerente(){
  
    }

    public Gerente(String id_admin,String password, String nombre, String apellido, String correo){
        this.id_admin = id_admin;
        this.password = password;
        this.nombre = nombre; 
        this.apellido = apellido;
        this.correo = correo;
    }

    public String getId_admin() {
        return id_admin;
    }

    public void setId_admin(String id_admin) {
        this.id_admin = id_admin;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
                String sep = "\n";
                str.append("ID de gerente: ");
                str.append(id_admin);
                str.append(sep);
                str.append("Contraseña: ");
                str.append(password);
                str.append(sep);
                str.append("Nombre: ");
                str.append(nombre);
                str.append(sep);
                str.append("Apellido: ");
                str.append(apellido);
                str.append(sep);
                str.append("Correo: ");
                str.append(correo);
                str.append(sep);
        return str.toString();
    }
}
