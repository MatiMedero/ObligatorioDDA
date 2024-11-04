package Persistencia;

import Dominio.Huespedes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PHuespedes {

    private static Conexion conexion = new Conexion();

    public static boolean agregarHuesped(Huespedes pHuespedes) {

        String sql = "INSERT INTO Huespedes(Idhuesped, Nombre, Apaterno, Amaterno, TipoDoc, NumDoc, FechaNac, Telefono, Pais) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                pHuespedes.getIdhuesped(),
                pHuespedes.getNombre(),
                pHuespedes.getApaterno(),
                pHuespedes.getAmaterno(),
                pHuespedes.getTipoDoc(),
                pHuespedes.getNumDoc(),
                pHuespedes.getFechaNac(),
                pHuespedes.getTelefono(),
                pHuespedes.getPais()
        ));
        try {
            if (conexion.consulta(sql, parametros)) {
                System.out.println("Se realizó el agregado con éxito");
                return true;

            }
            else{
                System.out.println("No se puede agregar el huesped");
                return false;
            }
        } catch (AppSQLException ase) {
            System.out.println(ase.getMessage());
            return false;
        }

    }

    public static boolean eliminarHuesped(int pIdHuesped) {
        String sql = "DELETE FROM Huespedes WHERE Idhuesped=?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pIdHuesped));

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

        public static boolean modificarHuesped(Huespedes h){
            String sql = "UPDATE Huespedes SET Nombre=?, Apaterno=?, Amaterno=?, TipoDoc=?, NumDoc=?, FechaNac=?, Telefono=?, Pais=? WHERE Idhuesped=?";

            ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                    h.getNombre(),
                    h.getApaterno(),
                    h.getAmaterno(),
                    h.getTipoDoc(),
                    h.getNumDoc(),
                    h.getFechaNac(),
                    h.getTelefono(),
                    h.getPais(),
                    h.getIdhuesped()
            ));
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

    public static Huespedes conseguirHuesped(int pIdHuesped) throws AppSQLException {
        String sql = "SELECT Idhuesped, Nombre, Apaterno, Amaterno, TipoDoc, NumDoc, FechaNac, Telefono, Pais FROM huespedes WHERE Idhuesped=?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pIdHuesped));

        List<List<Object>> resultado = conexion.seleccion(sql, parametros);
        if (resultado.isEmpty()) {
            System.out.println("No se encontró ningún huésped con el Id: " + pIdHuesped);
            return null;
        }
        int idHuesped = (int) resultado.get(0).get(0);
        String nombre = String.valueOf(resultado.get(0).get(1));
        String apaterno = String.valueOf(resultado.get(0).get(2));
        String amaterno = String.valueOf(resultado.get(0).get(3));
        String tipoDoc = String.valueOf(resultado.get(0).get(4));
        int numDoc = (int) resultado.get(0).get(5);
        LocalDate fechaNac = LocalDate.parse(String.valueOf(resultado.get(0).get(6)));
        int telefono = (int) resultado.get(0).get(7);
        String pais = String.valueOf(resultado.get(0).get(8));


        return new Huespedes(idHuesped, nombre, apaterno, amaterno, tipoDoc,
                numDoc, fechaNac, telefono, pais);
    }

    public static ArrayList<Huespedes> listarHuespedes() throws AppSQLException {
        String sql = "SELECT * FROM Huespedes";
        List<List<Object>> registros = conexion.seleccion(sql, null);
        ArrayList<Huespedes> huespedesList = new ArrayList<>();

        for (List<Object> registro : registros) {
            int idHuesped = (int) registro.get(0);
            String nombre = String.valueOf(registro.get(1));
            String apaterno = String.valueOf(registro.get(2));
            String amaterno = String.valueOf(registro.get(3));
            String tipoDoc = String.valueOf(registro.get(4));
            int numDoc = (int) registro.get(5);
            LocalDate fechaNac = LocalDate.parse(String.valueOf(registro.get(6)));
            int telefono = (int) registro.get(7);
            String pais = String.valueOf(registro.get(8));

            huespedesList.add(new Huespedes(idHuesped, nombre, apaterno, amaterno, tipoDoc, numDoc, fechaNac, telefono, pais));
        }
        return huespedesList;
    }


    }


