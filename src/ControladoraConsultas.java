import Dominio.Habitaciones;
import Dominio.Hotel;
import Dominio.Reserva;
import Persistencia.AppSQLException;
import Persistencia.PHabitaciones;
import Persistencia.PHotel;
import Persistencia.PReserva;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControladoraConsultas {
    private static Scanner escaner = new Scanner(System.in);


    public void ConsultaHoteles() throws AppSQLException {
        List<Hotel> listaHoteles = PHotel.listarHoteles();
        System.out.println("1. Por Ciudad");
        System.out.println("2. Por Nombre");
        System.out.println("3. Por Fecha (NO ES REQUERIDO PARA LA LETRA)");
        System.out.println("4. Por Categoría (Cantidad de Estrellas)");
        System.out.print("Ingrese la opción: ");

        int opcion = Integer.parseInt(escaner.nextLine());
        List<Hotel> hotelesFiltrados = new ArrayList<>();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese la ciudad: ");
                String ciudad = escaner.nextLine();
                for (Hotel hotel : listaHoteles) {
                    if (hotel.getCiudad().equalsIgnoreCase(ciudad)) {
                        hotelesFiltrados.add(hotel);
                    }
                }
                break;
            case 2:
                System.out.print("Ingrese el nombre del hotel: ");
                String nombre = escaner.nextLine();
                for (Hotel hotel : listaHoteles) {
                    if (hotel.getNombre().equalsIgnoreCase(nombre)) {
                        hotelesFiltrados.add(hotel);
                    }
                }
                break;

            case 3:

                break;
            case 4:
                System.out.print("Ingrese la cantidad de estrellas: ");
                int estrellas = Integer.parseInt(escaner.nextLine());
                for (Hotel hotel : listaHoteles) {
                    if (hotel.getCantEstrellas() == estrellas) {
                        hotelesFiltrados.add(hotel);
                    }
                }
                break;
            default:
                System.out.println("Opción inválida. Intente nuevamente.");
                return;
        }
        if (hotelesFiltrados.isEmpty()) {
            System.out.println("No se encontraron hoteles que coincidan con los criterios.");
        } else {
            System.out.println("Hoteles encontrados:");
            for (Hotel hotel : hotelesFiltrados) {
                System.out.println(hotel);
            }
        }
    }


















    public  void reservaFecha() throws AppSQLException {
        System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
        LocalDate fechaInicio = LocalDate.parse(escaner.nextLine());

        System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
        LocalDate fechaFin = LocalDate.parse(escaner.nextLine());

        List<Reserva> reservas = reservaPorFecha(fechaInicio, fechaFin);

        if (reservas.isEmpty()) {
            System.out.println("No hay reservas en el rango de fechas especificado.");
        } else {
            System.out.println("Reservas encontradas:");
            for (Reserva reserva : reservas) {
                System.out.println(reserva);
            }
        }
    }



    public static List<Reserva> reservaPorFecha(LocalDate fechaInicio, LocalDate fechaFin) throws AppSQLException {

        List<Reserva> todasLasReservas = PReserva.listarReservas();
        List<Reserva> reservasFecha = new ArrayList<>();

        for (Reserva reserva : todasLasReservas) {
            if ((reserva.getFecIngreso().isBefore(fechaFin) || reserva.getFecIngreso().isEqual(fechaFin)) &&
                    (reserva.getFecSalida().isAfter(fechaInicio) || reserva.getFecSalida().isEqual(fechaInicio))) {
                reservasFecha.add(reserva);
            }
        }
        return reservasFecha;
    }




    public void FiltrarHabitacionPorReserva() throws AppSQLException {
        System.out.print("Ingrese el ID del hotel: ");
        int idHotel = Integer.parseInt(escaner.nextLine());

        List<Reserva> todasLasReservas = PReserva.listarReservas();
        List<Habitaciones> listHabi = PHabitaciones.listarHabitaciones(idHotel);
        List<Habitaciones> conReserva = new ArrayList<>();
        List<Habitaciones> sinReserva = new ArrayList<>();
        List<Integer> idsHabitacionesReservadas = new ArrayList<>();

        for (Reserva r : todasLasReservas) {
            idsHabitacionesReservadas.add(r.getHabitacion());
        }

        for (Habitaciones ha : listHabi) {
            if (idsHabitacionesReservadas.contains(ha.getIdHabitacion()) || ha.isOcupada()) {
                conReserva.add(ha);
            } else {
                sinReserva.add(ha);
            }
        }
        System.out.println("Habitaciones con reserva:");
        for (Habitaciones h : conReserva) {
            System.out.println("Habitación ID: " + h.getIdHabitacion());
        }

        System.out.println("Habitaciones sin reserva:");
        for (Habitaciones h : sinReserva) {
            System.out.println("Habitación ID: " + h.getIdHabitacion());
        }
    }






}
