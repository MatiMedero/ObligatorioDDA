import Persistencia.AppSQLException;
import java.util.Scanner;

public class Main {
    private static Scanner escaner = new Scanner(System.in);
    private static Controladora controladora = new Controladora();
private static ControladoraConsultas controladoraC = new ControladoraConsultas();
    public static void main(String[] args) throws AppSQLException {
        int opcion;
        do {
            menuPrincipal();
            System.out.println("Ingrese la opción del menú");
            opcion = Integer.parseInt(escaner.nextLine());

            switch(opcion) {
                case 1: menuHuesped(); break;
                case 2: menuHabitacion(); break;
                case 3: menuHotel(); break;
                case 4: menuReserva(); break;
                case 5: menuTarifa(); break;
                case 6: menuConsultas(); break;
                case -1: break;
                default: System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != -1);
    }

    private static void menuPrincipal() {
        System.out.println("\nMENU");
        System.out.println("1. Gestionar Huespedes");
        System.out.println("2. Gestionar Habitaciones");
        System.out.println("3. Gestionar Hoteles");
        System.out.println("4. Gestionar Reservas");
        System.out.println("5. Gestionar Tarifas");
        System.out.println("6. Consultas");
        System.out.println("-1. Salir");
    }

    private static void menuHuesped() throws AppSQLException {
        int opcion;
        do {
            System.out.println("\nMENU HUESPED");
            System.out.println("1. Agregar Huesped");
            System.out.println("2. Eliminar Huesped");
            System.out.println("3. Modificar Huesped");
            System.out.println("4. Listar Huespedes");
            System.out.println("5. Buscar Huesped");
            System.out.println("-1. Volver al menu principal");
            System.out.print("Ingrese la opción: ");
            opcion = Integer.parseInt(escaner.nextLine());

            switch(opcion) {
                case 1: controladora.agregarHuesped(); break;
                case 2: controladora.eliminarHuesped(); break;
                case 3: controladora.modificarHuesped(); break;
                case 4: controladora.listarHuespedes(); break;
                case 5: controladora.conseguirHuesped(); break;
                case -1:  break;
                default: System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != -1);
    }

    private static void menuHabitacion() throws AppSQLException {
        int opcion;
        do {
            System.out.println("\nMENU HABITACION");
            System.out.println("1. Agregar Habitacion");
            System.out.println("2. Eliminar Habitacion");
            System.out.println("3. Modificar Habitacion");
            System.out.println("4. Listar Habitaciones");
            System.out.println("5. Buscar Habitacion");
            System.out.println("-1. Volver al menu principal");
            System.out.print("Ingrese la opción: ");
            opcion = Integer.parseInt(escaner.nextLine());

            switch(opcion) {
                case 1: controladora.agregarHabitacion(); break;
                case 2: controladora.eliminarHabitacion(); break;
                case 3: controladora.modificarHabitacion(); break;
                case 4: controladora.listarHabitaciones(); break;
                case 5: controladora.conseguirHabitacion(); break;
                case -1: break;
                default: System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != -1);
    }

    private static void menuHotel() throws AppSQLException {
        int opcion;
        do {
            System.out.println("\nMENU HOTEL");
            System.out.println("1. Agregar Hotel");
            System.out.println("2. Eliminar Hotel");
            System.out.println("3. Modificar Hotel");
            System.out.println("4. Listar Hoteles");
            System.out.println("5. Buscar Hotel");
            System.out.println("-1. Volver al menu principal");
            System.out.print("Ingrese la opción: ");
            opcion = Integer.parseInt(escaner.nextLine());

            switch(opcion) {
                case 1: controladora.agregarHotel(); break;
                case 2: controladora.eliminarHotel(); break;
                case 3: controladora.modificarHotel(); break;
                case 4: controladora.listarHoteles(); break;
                case 5: controladora.conseguirHotel(); break;
                case -1: break;
                default: System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != -1);
    }

    private static void menuReserva() throws AppSQLException {
        int opcion;
        do {
            System.out.println("\nMENU RESERVA");
            System.out.println("1. Agregar Reserva");
            System.out.println("2. Eliminar Reserva");
            System.out.println("3. Modificar Reserva");
            System.out.println("4. Listar Reservas");
            System.out.println("5. Buscar Reserva");
            System.out.println("-1. Volver al menu principal");
            System.out.print("Ingrese la opción: ");
            opcion = Integer.parseInt(escaner.nextLine());

            switch(opcion) {
                case 1: controladora.agregarReserva(); break;
                case 2: controladora.eliminarReserva(); break;
                case 3: controladora.modificarReserva(); break;
                case 4: controladora.listarReservas(); break;
                case 5: controladora.conseguirReserva(); break;
                case -1:  break;
                default: System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != -1);
    }

    private static void menuTarifa() throws AppSQLException {
        int opcion;
        do {
            System.out.println("\nMENU TARIFA");
            System.out.println("1. Agregar Tarifa");
            System.out.println("2. Eliminar Tarifa");
            System.out.println("3. Modificar Tarifa");
            System.out.println("4. Listar Tarifas");
            System.out.println("5. Buscar Tarifa");
            System.out.println("-1. Volver al menu principal");
            System.out.print("Ingrese la opción: ");
            opcion = Integer.parseInt(escaner.nextLine());

            switch(opcion) {
                case 1: controladora.agregarTarifa(); break;
                case 2: controladora.eliminarTarifa(); break;
                case 3: controladora.modificarTarifa(); break;
                case 4: controladora.listarTarifas(); break;
                case 5: controladora.conseguirTarifa(); break;
                case -1:  break;
                default: System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != -1);
    }


    private static void menuConsultas() throws AppSQLException {
        int opcion;
        do {
            System.out.println("\nMENU Consultas");
           System.out.println("1. Consulta Hoteles(Ciudad,Nombre o Categoria)");
            System.out.println("2. Reservas por Fecha");
            System.out.println("3. Filtrar habitaciones con o sin reserva previa");
            System.out.println("-1. Volver al menu principal");
            System.out.print("Ingrese la opción: ");
            opcion = Integer.parseInt(escaner.nextLine());

            switch(opcion) {
                case 1: controladoraC.ConsultaHoteles(); break;
                case 2: controladoraC.reservaFecha(); break;
                case 3: controladoraC.FiltrarHabitacionPorReserva(); break;

                case -1:  break;
                default: System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != -1);
    }
}
