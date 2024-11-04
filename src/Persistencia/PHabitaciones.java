package Persistencia;
import Dominio.Habitaciones;
import Dominio.Huespedes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PHabitaciones {

    private static Conexion conexion = new Conexion();

    public static boolean agregarHabitaciones(Habitaciones pHabitaciones) {
    String sql = "INSERT INTO Habitaciones(IdHabitacion, CantCamas, CamaMatrimonial, " +
            "CamasIndividuales, AireAcondicionado, Balcon,Amenities, Ocupada, Hotel) VALUES(?, ?, ?, ?, ?, ?,?,?,?)";


    ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
            pHabitaciones.getIdHabitacion(),
            pHabitaciones.getCantCamas(),
            pHabitaciones.isCamaMatrimonial(),
            pHabitaciones.isCamasIndividuales(),
            pHabitaciones.isAireAcondicionado(),
            pHabitaciones.isBalcon(),
            pHabitaciones.getAmenities(),
            pHabitaciones.isOcupada(),
            pHabitaciones.getHotel()
    ));
try {
        if (conexion.consulta(sql, parametros)) {
            System.out.println("Se realizó el agregado con éxito");
            return true;
        } else {

            System.out.println("No se puede agregar la habitación");
            return false;
        }
    } catch (AppSQLException ase) {

        System.out.println("Error al agregar la habitación: " + ase.getMessage());
        return false;
    } catch (Exception e) {
        System.out.println("Error inesperado: " + e.getMessage());
        return false;
    }

}

    public static boolean eliminarHabitacion(int pIdHabitacion) {
        String sql = "DELETE FROM Habitaciones WHERE IdHabitacion=?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pIdHabitacion));

        try {
            if (conexion.consulta(sql, parametros)) {
                System.out.println("Se eliminó con éxito la habitación.");
                return true;
            } else {
                System.out.println("No se puede eliminar la habitación.");
                return false;
            }
        } catch (AppSQLException ase) {
            System.out.println(ase.getMessage());
            return false;
        }
    }

    public static boolean modificarHabitacion(Habitaciones h) {
        String sql = "UPDATE Habitaciones SET CantCamas=?, CamaMatrimonial=?, CamasIndividuales=?, AireAcondicionado=?, Balcon=?, Amenities=?, Ocupada=? WHERE IdHabitacion=? AND Hotel=?";

        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                h.getCantCamas(),
                h.isCamaMatrimonial(),
                h.isCamasIndividuales(),
                h.isAireAcondicionado(),
                h.isBalcon(),
                h.getAmenities(),
                h.isOcupada(),
                h.getIdHabitacion(),
                h.getHotel()
        ));
        try {
            if (conexion.consulta(sql, parametros)) {
                System.out.println("Se modificó la habitación con éxito");
                return true;
            } else {
                System.out.println("No se pudo modificar la habitación");
                return false;
            }
        } catch (AppSQLException ase) {
            System.out.println("Error en modificar habitación: " + ase.getMessage());
            return false;
        }
    }



    public static Habitaciones conseguirHabitacion(int pIdHabitacion, int idHotel) throws AppSQLException {
        String sql = "SELECT * FROM Habitaciones WHERE IdHabitacion = ? AND Hotel = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pIdHabitacion, idHotel));

        List<List<Object>> resultado = conexion.seleccion(sql, parametros);
        if (resultado.isEmpty()) {
            System.out.println("No se encontró ninguna habitación con el Id: " + pIdHabitacion + " en el hotel con ID: " + idHotel);
            return null;
        }


        int idHabitacion = (int) resultado.get(0).get(0);
        int cantCamas = (int) resultado.get(0).get(1);
        boolean camaMatrimonial = (boolean) resultado.get(0).get(2);
        boolean camasIndividuales = (boolean) resultado.get(0).get(3);
        boolean aireAcondicionado = (boolean) resultado.get(0).get(4);
        boolean balcon = (boolean) resultado.get(0).get(5);
        String amenities = (String) resultado.get(0).get(6);
        boolean ocupada = (boolean) resultado.get(0).get(7);
        int hotel = (int) resultado.get(0).get(8);

        return new Habitaciones(idHabitacion, cantCamas, camaMatrimonial, camasIndividuales, aireAcondicionado, balcon, amenities, ocupada, hotel);
    }



    public static ArrayList<Habitaciones> listarHabitaciones(int idHotel) throws AppSQLException {
        String sql = "SELECT * FROM Habitaciones WHERE Hotel = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(idHotel));
        List<List<Object>> registros = conexion.seleccion(sql, parametros);
        ArrayList<Habitaciones> habitacionesList = new ArrayList<>();

        for (List<Object> registro : registros) {
            int idHabitacion = (int) registro.get(0);
            int cantCamas = (int) registro.get(1);
            boolean camaMatrimonial = (boolean) registro.get(2);
            boolean camasIndividuales = (boolean) registro.get(3);
            boolean aireAcondicionado = (boolean) registro.get(4);
            boolean balcon = (boolean) registro.get(5);
            String amenities = (String) registro.get(6);
            boolean ocupada = (boolean) registro.get(7);
            int hotel = (int) registro.get(8);

            habitacionesList.add(new Habitaciones(idHabitacion, cantCamas, camaMatrimonial, camasIndividuales, aireAcondicionado, balcon, amenities, ocupada, hotel));
        }
        return habitacionesList;
    }

    public static boolean cambiarEstadoOcupada(int idHabitacion, boolean ocupada) {
        String sql = "UPDATE Habitaciones SET Ocupada = ? WHERE IdHabitacion = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(ocupada, idHabitacion));

        try {
            return conexion.consulta(sql, parametros);
        } catch (AppSQLException ase) {
            System.out.println("Error al cambiar el estado de ocupación: " + ase.getMessage());
            return false;
        }
    }




}
