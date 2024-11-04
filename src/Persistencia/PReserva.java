package Persistencia;

import Dominio.Habitaciones;
import Dominio.Hotel;
import Dominio.Huespedes;
import Dominio.Reserva;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PReserva {

    private static Conexion conexion = new Conexion();

    public static boolean agregarReserva(Reserva pReserva) {

        String sql = "INSERT INTO Reservas(IdReserva, CantPersonas, FechaReserva, IdHuesped, IdHotel, IdHabitacion, FecIngreso, FecSalida, Monto, Observaciones, Pagado) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                pReserva.getIdReserva(),
                pReserva.getCantPersonas(),
                pReserva.getFechaReserva(),
                pReserva.getHuesped().getIdhuesped(),
                pReserva.getHotel().getIdHotel(),
                pReserva.getHabitacion(),
                pReserva.getFecIngreso(),
                pReserva.getFecSalida(),
                pReserva.getMonto(),
                pReserva.getObservaciones(),
                pReserva.isPagado()
        ));

        try {
            if (conexion.consulta(sql, parametros)) {
                System.out.println("Se realizó el agregado de la reserva con éxito.");

                if (PHabitaciones.cambiarEstadoOcupada(pReserva.getHabitacion(), true)) {
                    System.out.println("La habitación ha sido marcada como ocupada.");
                } else {
                    System.out.println("No se pudo actualizar el estado de la habitación.");
                }
                return true;
            } else {
                System.out.println("No se pudo agregar la reserva.");
                return false;
            }
        } catch (AppSQLException ase) {
            System.out.println("Error al agregar la reserva: " + ase.getMessage());
            return false;
        }
    }


    public static boolean eliminarReserva(int pIdReserva) throws AppSQLException {

        Reserva reserva = conseguirReserva(pIdReserva);
        if (reserva == null) {
            System.out.println("La reserva no existe.");
            return false;
        }

        int idHabitacion = reserva.getHabitacion();
    LocalDate fechaSalida = reserva.getFecSalida();

        String sql = "DELETE FROM Reservas WHERE Idhuesped=?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pIdReserva));

        try {
            if (conexion.consulta(sql, parametros)) {
                System.out.println("Se elimino con éxito");

                if (PHabitaciones.cambiarEstadoOcupada(idHabitacion, false)) {
                    System.out.println("La habitación ha sido liberada.");
                } else {
                    System.out.println("No se pudo actualizar el estado de la habitación.");
                }
                return true;


            } else {
                System.out.println("No se puede eliminar la reserva");
                return false;
            }
        } catch (AppSQLException ase) {
            System.out.println(ase.getMessage());
            return false;
        }
    }

    public static boolean modificarReserva(Reserva r) {
        String sql = "UPDATE Reservas SET CantPersonas=?, FechaReserva=?, IdHuesped=?, IdHotel=?, IdHabitacion=?, FecIngreso=?, FecSalida=?, Monto=?, Observaciones=?, Pagado=? WHERE IdReserva=?";

        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                r.getCantPersonas(),
                r.getFechaReserva(),
                r.getHuesped().getIdhuesped(),
                r.getHotel().getIdHotel(),
                r.getHabitacion(),
                r.getFecIngreso(),
                r.getFecSalida(),
                r.getMonto(),
                r.getObservaciones(),
                r.isPagado(),
                r.getIdReserva()
        ));

        try {
            if (conexion.consulta(sql, parametros)) {
                System.out.println("Se modificó la reserva con éxito.");
                return true;
            } else {
                System.out.println("No se pudo modificar la reserva.");
                return false;
            }
        } catch (AppSQLException ase) {
            System.out.println("Error al modificar la reserva: " + ase.getMessage());
            return false;
        }
    }

    public static Reserva conseguirReserva(int pIdReserva) throws AppSQLException {
        String sql = "SELECT CantPersonas, FechaReserva, IdHuesped, IdHotel, IdHabitacion, FecIngreso, FecSalida, Monto, Observaciones, Pagado FROM Reservas WHERE IdReserva=?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pIdReserva));


        List<List<Object>> resultado = conexion.seleccion(sql, parametros);
        if (resultado.isEmpty()) {
            System.out.println("No se encontró ninguna reserva con el Id: " + pIdReserva);
            return null;
        }


        int cantPersonas = (int) resultado.get(0).get(0);
        LocalDate fechaReserva = LocalDate.parse(String.valueOf(resultado.get(0).get(1)));
        int idHuesped = (int) resultado.get(0).get(2);
        int idHotel = (int) resultado.get(0).get(3);
        int idHabitacion = (int) resultado.get(0).get(4);
        LocalDate fecIngreso =  LocalDate.parse(String.valueOf(resultado.get(0).get(5)));
        LocalDate fecSalida = LocalDate.parse(String.valueOf(resultado.get(0).get(6)));
        double monto = (double) resultado.get(0).get(7);
        String observaciones = (String) resultado.get(0).get(8);
        boolean pagado = (boolean) resultado.get(0).get(9);


        Huespedes huesped = PHuespedes.conseguirHuesped(idHuesped);
        Hotel hotel = PHotel.conseguirHotel(idHotel);
        Habitaciones habitacion = PHabitaciones.conseguirHabitacion(idHabitacion, idHotel);

        return new Reserva(cantPersonas, fechaReserva, huesped, hotel, habitacion, fecIngreso, fecSalida, monto, observaciones, pagado);
    }

    public static ArrayList<Reserva> listarReservas() throws AppSQLException {
        String sql = "SELECT IdReserva, CantPersonas, FechaReserva, IdHuesped, IdHotel, IdHabitacion, FecIngreso, FecSalida, Monto, Observaciones, Pagado FROM Reservas";
        List<List<Object>> registros = conexion.seleccion(sql, null);
        ArrayList<Reserva> reservasList = new ArrayList<>();
        for (List<Object> registro : registros) {
            int idReserva = (int) registro.get(0);
            int cantPersonas = (int) registro.get(1);
            LocalDate fechaReserva = LocalDate.parse(String.valueOf(registro.get(2)));
            int idHuesped = (int) registro.get(3);
            int idHotel = (int) registro.get(4);
            int idHabitacion = (int) registro.get(5);
            LocalDate fecIngreso =  LocalDate.parse(String.valueOf(registro.get(6)));
            LocalDate fecSalida =LocalDate.parse(String.valueOf(registro.get(7)));
            double monto = (double) registro.get(8);
            String observaciones = (String) registro.get(9);
            boolean pagado = (boolean) registro.get(10);

            Huespedes huesped = PHuespedes.conseguirHuesped(idHuesped);
            Hotel hotel = PHotel.conseguirHotel(idHotel);
            Habitaciones habitacion = PHabitaciones.conseguirHabitacion(idHabitacion, idHotel);


            reservasList.add(new Reserva(cantPersonas, fechaReserva, huesped, hotel, habitacion, fecIngreso, fecSalida, monto, observaciones, pagado));

        }
        return reservasList;
    }




}
