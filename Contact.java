import java.util.Date;
import java.text.SimpleDateFormat;

public class Contact {

    //atributos privados para encapsulación.
    private String nombre;
    private String numero;
    private String email;
    private String direccion;
    private Date fechaNacimiento;

    // creación de consutrctores.
    public Contact(String nombre, String numero, String email, String direccion, Date fechaNacimiento ) {
        this.nombre = nombre;
        this.numero = numero;
        this.email = email;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    //Getters y Setters.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    //se genera un override
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaNacStr = (fechaNacimiento != null) ? dateFormat.format(fechaNacimiento) : "No disponible";
        return "Nombre: " + nombre + ", Número: " + numero + ", Email: " + email +
                ", Dirección: " + direccion + ", Fecha de nacimiento: " + fechaNacStr;
    }

}// fin de la clase
