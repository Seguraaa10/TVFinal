package cua.uam.mx.bl;

public class Producto {
   private String id; 
   private String nombre; 
   private String precio; 
   private String inventario; 
   private String tipo; 

   public Producto(){

   }

   public Producto (String id, String nombre, String precio, String inventario, String tipo){
      this.id = id; 
      this.nombre = nombre; 
      this.precio = precio;
      this.inventario = inventario;
      this.tipo = tipo; 
   }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getInventario() {
        return inventario;
    }

    public void setInventario(String inventario) {
        this.inventario = inventario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String toString(){
      StringBuilder str = new StringBuilder();
              String sep = "\n";
              str.append("ID: ");
              str.append(id);
              str.append(sep);
              str.append("Nombre del Producto: ");
              str.append(nombre);
              str.append(sep);
              str.append("Precio: ");
              str.append(precio);
              str.append(sep);
              str.append("Inventario: ");
              str.append(inventario);
              str.append(sep);
              str.append("Tipo: ");
              str.append(tipo);
              str.append(sep);
      return str.toString();
  }
}