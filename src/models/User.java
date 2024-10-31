package models;

import java.util.Scanner;;

public class User {
    private String name;
    private String last_name;
    private String email;
    private String country;
    private String city;
    private int dni;

    
    private User(String name, String last_name, String email, String country, String city, int dni) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.country = country;
        this.city = city;
        this.dni = dni;
    }

    public static User getUser(){
        // Crear un objeto Scanner para leer datos desde el teclado
        Scanner scanner = new Scanner(System.in);

        // Pedir datos al usuario
        System.out.print("Ingrese el nombre: ");
        String name = scanner.nextLine();

        System.out.print("Ingrese el apellido: ");
        String last_name = scanner.nextLine();

        System.out.print("Ingrese el email: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese el pais:");
        String country = scanner.nextLine();

        System.out.print("Ingrese su ciudad:");
        String city = scanner.nextLine();

        int dni = 0;
        while (true) {
            System.out.print("Ingrese el DNI: ");
            try {
                dni = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.print("\"Entrada inválida. Por favor, ingresa un número");
                scanner.next(); // Limpia el buffer
            }
        }
        scanner.close();

        User user = new User(name, last_name, email, country, city, dni);
        return user;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public int getDni() {
        return dni;
    }
    
}
