import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddressBook {

    //se genera HashMap.
    private Map<String, Contact> contacts = new HashMap<>();
    private String contactos;

    public AddressBook(String archivoContactos) {
        this.contacts = new HashMap<>();
        this.contactos = archivoContactos;
        load();  // Intentar cargar los contactos desde el archivo
    }

    public AddressBook() {
        this("contactos.txt");  // Usar un nombre de archivo por defecto
    }

    //creación de menú de usuario.
    public void menu() {
        Scanner menu = new Scanner(System.in); //creación de Scanner.
        int option;

        do {
            System.out.println("________________________________________");
            System.out.println("\nMenú de opciones:");
            System.out.println("| [1] - Listar contactos");
            System.out.println("| [2] - Crear contacto");
            System.out.println("| [3] - Eliminar contacto");
            System.out.println("| [4] - Salir");
            System.out.print("| [ Ingrese una opción ] = ");
            System.out.println("\n________________________________________");

            option = menu.nextInt();
            menu.nextLine();  // Limpiar el buffer después de nextInt()

            switch (option) {
                case 1:
                    list();
                    break;
                case 2:
                    System.out.println("_______________________________________________");
                        System.out.println("| Ingresa el nombre:");
                    String nombre = menu.nextLine();
                        System.out.println("| Ingresa el número de teléfono:");
                    String numero = menu.nextLine();
                        System.out.println("| Ingresa el email:");
                    String email = menu.nextLine();
                        System.out.println("| Ingresa la dirección:");
                    String direccion = menu.nextLine();
                        System.out.println("| Ingresa la fecha de nacimiento con el siguiente formato: (dd/MM/yyyy) ");
                    String fechaNacimiento = menu.nextLine();

                    create(nombre, numero, email, direccion, fechaNacimiento);
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema ");
                    System.out.println("Guardando los contactos...");
                    save(contactos);
                    break;
                default:
                    System.out.println(" ******OPCIÓN NO VALIDA****** ");
            }
        } while (option != 4);
    } // fin de menu.

    // Método para listar los contactos
    public void list() {
        System.out.println("\n*********Contactos*********");
        for (String phoneNumber : contacts.keySet()) {
            System.out.println("| " + phoneNumber + ": " + contacts.get(phoneNumber));
        }
    }//fin méotodo List.

    // Método para crear un nuevo contacto
    public void create(String nombre, String phoneNumber, String email, String direccion, String fechaNacimientoStr) {
        // Convertir la fecha de String a Date
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaNacimiento = null;

        try {
            fechaNacimiento = formatter.parse(fechaNacimientoStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Contact contact = new Contact(nombre, phoneNumber, email, direccion, fechaNacimiento);
        contacts.put(phoneNumber, contact);
    }

    //Método para eliminar contacto.
    public void delete() {
        Scanner scn = new Scanner(System.in);
        System.out.print("Ingrese el número de teléfono a eliminar: ");
        String phoneNumber = scn.nextLine();
        contacts.remove(phoneNumber);
        System.out.println("Contacto eliminado exitosamente.");
    }//fin metodo delete.

    //creación de metodo save.
    public void save(String contactos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(contactos))) {
            for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
                Contact contact = entry.getValue();
                bw.write("* " + contact.getNombre() + "," + contact.getNumero() + "," + contact.getEmail() + "," + contact.getDireccion() + "," + contact.getFechaNacimiento());
                bw.newLine();
            }
        } catch (IOException exc) {
            System.err.println("Error al guardar los contactos: " + exc.getMessage());
        }
    }//final de metodo save.

    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(contactos))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date fechaNacimiento = null;
                    try {
                        fechaNacimiento = formatter.parse(parts[4]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Contact contact = new Contact(parts[0], parts[1], parts[2], parts[3], fechaNacimiento);
                    contacts.put(contact.getNumero(), contact);
                }
            }
        } catch (IOException exc) {
            System.err.println("* Error al cargar los contactos: " + exc.getMessage());
        }
    }//final de método load.
}//final de clase AddressBook.


