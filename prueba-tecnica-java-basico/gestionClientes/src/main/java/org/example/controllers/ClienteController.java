package org.example.controllers;

import org.example.entities.Cliente;
import org.example.entities.Sexo;
import org.example.persistence.ClienteJPA;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ClienteController {

    private final ClienteJPA clienteJPA = new ClienteJPA();
    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void agregarCliente(){
        Cliente cliente = new Cliente();

        System.out.print("Nombre: ");
        cliente.setNombre(leerTextoObligatorio());

        System.out.print("Primer apellido: ");
        cliente.setPrimerApellido(leerTextoObligatorio());

        System.out.print("Segundo apellido: ");
        cliente.setSegundoApellido(leerTextoObligatorio());

        System.out.print("Sexo (HOMBRE/MUJER/OTRO): ");
        cliente.setSexo(Sexo.valueOf(leerTextoObligatorio().toUpperCase()));

        System.out.print("Ciudad: ");
        cliente.setCiudad(leerTextoObligatorio());

        LocalDate fechaNacimiento = null;
        while (fechaNacimiento == null) {
            System.out.println("Fecha de nacimiento (dd/MM/yyyy): ");
            String entradaFecha = leerTextoObligatorio();
            try{
                fechaNacimiento = LocalDate.parse(entradaFecha, formatoFecha);
            } catch (DateTimeParseException e){
                System.out.println("Fecha inválida. Usa el formato dd/MM/yyyy");
            }
        }

        cliente.setFechaNacimiento(fechaNacimiento);

        System.out.print("Teléfono: ");
        cliente.setTelefono(leerTextoObligatorio());

        System.out.println("Correo: ");
        cliente.setCorreo(leerTextoObligatorio());

        clienteJPA.guardar(cliente);
        System.out.println("Cliente agregado exitosamente.");
    }

    public void listarClientes(){
        List<Cliente> clientes = clienteJPA.listarTodos();
        if (clientes.isEmpty()){
        System.out.println("No hay clientes registrados.");
        } else {
        clientes.forEach(System.out::println);
        }

    }

    public void actualizarCliente() {
        System.out.print("ID del cliente a actualizar: ");
        Long id = Long.parseLong(scanner.nextLine());
        Cliente cliente = clienteJPA.buscarPorId(id);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.print("Nuevo nombre (" + cliente.getNombre() + "): ");
        cliente.setNombre(leerTextoOpcional(cliente.getNombre()));

        System.out.print("Nuevo primer apellido (" + cliente.getPrimerApellido() + "): ");
        cliente.setPrimerApellido(leerTextoOpcional(cliente.getPrimerApellido()));

        System.out.print("Nuevo segundo apellido (" + cliente.getSegundoApellido() + "): ");
        cliente.setSegundoApellido(leerTextoOpcional(cliente.getSegundoApellido()));

        System.out.print("Nuevo sexo (" + cliente.getSexo() + "): ");
        cliente.setSexo(Sexo.valueOf(leerTextoOpcional(cliente.getSexo().name())));

        System.out.print("Nueva ciudad (" + cliente.getCiudad() + "): ");
        cliente.setCiudad(leerTextoOpcional(cliente.getCiudad()));

        LocalDate fechaNacimiento = null;
        while (fechaNacimiento == null) {
            System.out.println("Nueva fecha de nacimiento (" + cliente.getFechaNacimiento().format(formatoFecha) + "): ");
            String entradafecha = leerTextoOpcional(cliente.getFechaNacimiento().format(formatoFecha));
            try {
                fechaNacimiento = LocalDate.parse(entradafecha, formatoFecha);
            }catch (DateTimeParseException e) {
                System.out.println("Fecha inválida. Usa el formato dd/MM/yyyy");
            }
        }

        cliente.setFechaNacimiento(fechaNacimiento);

        System.out.print("Nuevo teléfono (" + cliente.getTelefono() + "): ");
        cliente.setTelefono(leerTextoOpcional(cliente.getTelefono()));

        System.out.print("Nuevo correo (" + cliente.getCorreo() + "): ");
        cliente.setCorreo(leerTextoOpcional(cliente.getNombre()));

        clienteJPA.actualizar(cliente);
        System.out.println("Cliente actualizado.");
    }

    public void eliminarCliente() {
        System.out.print("ID del cliente a eliminar: ");
        Long id = Long.parseLong(scanner.nextLine());
        clienteJPA.eliminar(id);
        System.out.println("Cliente eliminado");
    }

    public void buscarPorCiudad() {
        System.out.print("Ciudad: ");
        String ciudad = leerTextoObligatorio();
        List<Cliente> clientes = clienteJPA.buscarPorCiudad(ciudad);

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes en esa ciudad: ");
        } else {
            clientes.forEach(System.out::println);
        }
    }

    private String leerTextoObligatorio() {
        String input;

        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.print("Este campo es obligatorio. Intenta de nuevo: ");
            }
        } while (input.isEmpty());
        return input;
    }

    private String leerTextoOpcional(String valorActual){
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? valorActual : input;
    }

    public DateTimeFormatter getFormatoFecha() {
        return formatoFecha;
    }
}
