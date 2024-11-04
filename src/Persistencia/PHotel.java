package Persistencia;

import Dominio.Habitaciones;
import Dominio.Hotel;
import Dominio.Huespedes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PHotel {
    private static Conexion conexion = new Conexion();

    public static boolean agregarHoteles(Hotel pHotel) {
        String sql = "INSERT INTO Hoteles(IdHotel, Nombre, Pais, " +
                "Ciudad, Direccion, Barrio,CantEstrellas) VALUES(?, ?, ?, ?, ?, ?,?)";


        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                pHotel.getIdHotel(),
                pHotel.getNombre(),
                pHotel.getPais(),
                pHotel.getCiudad(),
                pHotel.getDireccion(),
                pHotel.getBarrio(),
                pHotel.getCantEstrellas()

        ));
        try {
            if (conexion.consulta(sql, parametros)) {
                System.out.println("Se realizó el agregado con éxito");
                return true;
            } else {

                System.out.println("No se puede agregar hotel");
                return false;
            }
        } catch (AppSQLException ase) {

            System.out.println("Error al agregar el hotel: " + ase.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            return false;
        }

    }





    public static ArrayList<Hotel> listarHoteles() throws AppSQLException {
        String sql = "SELECT * FROM Hoteles";
        List<List<Object>> registros = conexion.seleccion(sql, null);
        ArrayList<Hotel> hotelList = new ArrayList<>();

        for (List<Object> registro : registros) {
            int IdHotel = (int) registro.get(0);
            String Nombre = String.valueOf(registro.get(1));
            String Pais = String.valueOf(registro.get(2));
            String Ciudad = String.valueOf(registro.get(3));
            String Direccion = String.valueOf(registro.get(4));
            String Barrio = String.valueOf(registro.get(5));
            int CantEstrellas = (int) registro.get(6);


            hotelList.add(new Hotel(IdHotel, Nombre, Pais, Ciudad, Direccion, Barrio, CantEstrellas));
        }
        return hotelList;
    }


    public static boolean eliminarHotel(int pIdHotel) {
        String sql = "DELETE FROM Hoteles WHERE IdHotel=?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pIdHotel));

        try {
            if (conexion.consulta(sql, parametros)) {
                System.out.println("Se elimino con éxito");
                return true;

            } else {
                System.out.println("No se puede eliminar el huesped");
                return false;
            }
        } catch (AppSQLException ase) {
            System.out.println(ase.getMessage());
            return false;
        }
    }
    public static boolean modificarHotel(Hotel ho){
        String sql = "UPDATE Hoteles SET Nombre=?, Pais=?, Ciudad=?, Direccion=?, Barrio=?, CantEstrellas=? WHERE IdHotel=?";

        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                ho.getNombre(),
                ho.getPais(),
                ho.getCiudad(),
                ho.getDireccion(),
                ho.getBarrio(),
                ho.getCantEstrellas(),
                ho.getIdHotel()

        ));
        try {
            if (conexion.consulta(sql, parametros)) {
                System.out.println("Se elimino con éxito");
                return true;

            } else {
                System.out.println("No se puede modificar el huesped");
                return false;
            }
        } catch (AppSQLException ase) {
            System.out.println(ase.getMessage());
            return false;
        }
    }

    public static Hotel conseguirHotel(int pIdHotel) throws AppSQLException {
        String sql = "SELECT * FROM Hoteles WHERE IdHotel = ?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pIdHotel));

        List<List<Object>> resultado = conexion.seleccion(sql, parametros);
        if (resultado.isEmpty()) {
            System.out.println("No se encontró ningún hotel con el Id: " + pIdHotel);
            return null;
        }
        int IdHotel = (int) resultado.get(0).get(0);
        String nombre = String.valueOf(resultado.get(0).get(1));
        String pais = String.valueOf(resultado.get(0).get(2));
        String ciudad = String.valueOf(resultado.get(0).get(3));
        String direccion = String.valueOf(resultado.get(0).get(4));
        String barrio = String.valueOf(resultado.get(0).get(5));
        int cantEstrellas = (int) resultado.get(0).get(6);


        return new Hotel(IdHotel, nombre, pais, ciudad, direccion,
                barrio, cantEstrellas);
    }

}
