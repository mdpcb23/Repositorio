package org.example;

import org.example.controllers.ClienteController;
import org.example.persistence.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClienteController controller = new ClienteController();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Bienvenid@ al aplicativo: Gestión de clientes");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Actualizar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Buscar clientes por ciudad");
            System.out.println("0. Salir");
            System.out.println("Selecciona una opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion){
                case 1 -> controller.agregarCliente();
                case 2 -> controller.listarClientes();
                case 3 -> controller.actualizarCliente();
                case 4 -> controller.eliminarCliente();
                case 5 -> controller.buscarPorCiudad();
                case 0 -> System.out.println("Exit");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);

        scanner.close();

    }
}