package models;

import java.util.Scanner;

import utils.HashUtils;;

public class User {
    private String name;
    private String last_name;
    private String email;
    private String country;
    private String city;
    private int dni;
    private String password;

    
    private User(String name, String last_name, String email, String country, String city, int dni, String password) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.country = country;
        this.city = city;
        this.dni = dni;
        this.password = password;
    }

    public static User createUser(){
        User user;
        while (true) {
            try {
                // Crear un objeto Scanner para leer datos desde el teclado
                Scanner scanner = new Scanner(System.in);
                // Pedir datos al usuario
                System.out.print("Ingrese el nombre: ");
                String name = scanner.nextLine();

                System.out.print("Ingrese el apellido: ");
                String last_name = scanner.nextLine();

                System.out.print("Ingrese el email: ");
                String email = scanner.nextLine();

                System.out.print("Ingrese el pais: ");
                String country = scanner.nextLine();

                System.out.print("Ingrese su ciudad: ");
                String city = scanner.nextLine();

                System.out.print("Ingrese el DNI: ");
                int dni = scanner.nextInt();
                scanner.nextLine();

                String password;
                while (true) {
                    System.out.print("Ingrese una contraseña: ");
                    String psw = scanner.nextLine();
                    
                    System.out.print("Repita la contraseña: ");
                    String psw2 = scanner.nextLine();
                
                    if(psw.equals(psw2)){
                        password = HashUtils.hashPassword(psw);
                        break;
                    } else {
                        System.out.println("No coinciden, ingrese nuevamente una contraseña:\n");
                    }
                }
                scanner.close();
                user = new User(name, last_name, email, country, city, dni, password);
                break;
            } catch (Exception e) {
                System.out.print("\"Entrada no valida. ingrese nuevamente los datos: ");
            }
        }
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

    public String getPassword() {
        return password;
    }
}
