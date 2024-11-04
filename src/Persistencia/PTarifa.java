package Persistencia;

import Dominio.Tarifa;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PTarifa {
    private static Conexion conexion = new Conexion();

    public static boolean agregarTarifa(Tarifa pTarifa) {
        String sql = "INSERT INTO Tarifas(IdTarifa, monto, fechaInicio, fechaFin) VALUES(?,?, ?, ?)";

        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                pTarifa.getIdTarifa(),
                pTarifa.getMonto(),
                pTarifa.getFechaInicio(),
                pTarifa.getFechaFin()
        ));

        try {
            if (conexion.consulta(sql, parametros)) {
                System.out.println("Se realizó el agregado de la tarifa con éxito.");
                return true;
            } else {
                System.out.println("No se pudo agregar la tarifa.");
                return false;
            }
        } catch (AppSQLException ase) {
            System.out.println("Error al agregar la tarifa: " + ase.getMessage());
            return false;
        }
    }

    public static boolean eliminarTarifa(int pIdTarifa) {
        String sql = "DELETE FROM Tarifas WHERE IdTarifa=?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pIdTarifa));

        try {
            if (conexion.consulta(sql, parametros)) {
                System.out.println("Se eliminó con éxito la tarifa.");
                return true;
            } else {
                System.out.println("No se puede eliminar la tarifa.");
                return false;
            }
        } catch (AppSQLException ase) {
            System.out.println("Error al eliminar la tarifa: " + ase.getMessage());
            return false;
        }
    }

    public static boolean modificarTarifa(Tarifa t) {
        String sql = "UPDATE Tarifas SET Monto=?, FechaInicio=?, FechaFin=? WHERE IdTarifa=?";

        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(
                t.getIdTarifa(),
                t.getMonto(),
                t.getFechaInicio(),
                t.getFechaFin()

        ));

        try {
            if (conexion.consulta(sql, parametros)) {
                System.out.println("Se modificó la tarifa con éxito.");
                return true;
            } else {
                System.out.println("No se pudo modificar la tarifa.");
                return false;
            }
        } catch (AppSQLException ase) {
            System.out.println("Error al modificar la tarifa: " + ase.getMessage());
            return false;
        }
    }


    public static Tarifa conseguirTarifa(int pIdTarifa) throws AppSQLException {
        String sql = "SELECT IdTarifa, Monto, FechaInicio, FechaFin FROM Tarifas WHERE IdTarifa=?";
        ArrayList<Object> parametros = new ArrayList<>(Arrays.asList(pIdTarifa));

        List<List<Object>> resultado = conexion.seleccion(sql, parametros);
        if (resultado.isEmpty()) {
            System.out.println("No se encontró ninguna tarifa con el Id: " + pIdTarifa);
            return null;
        }

        int idTarifa = (int) resultado.get(0).get(0);
        double monto = (double) resultado.get(0).get(1);
        LocalDate fechaInicio = LocalDate.parse(String.valueOf(resultado.get(2)));
        LocalDate fechaFin =  LocalDate.parse(String.valueOf(resultado.get(3)));


        return new Tarifa(idTarifa, monto, fechaInicio, fechaFin);

    }

    public static ArrayList<Tarifa> listarTarifas() throws AppSQLException {
        String sql = "SELECT IdTarifa, Monto, FechaInicio, FechaFin FROM Tarifas";
        List<List<Object>> registros = conexion.seleccion(sql, null);
        ArrayList<Tarifa> tarifasList = new ArrayList<>();

        for (List<Object> registro : registros) {
            int idTarifa = (int) registro.get(0);
            double monto = (double) registro.get(1);

            Date fechaInicioSQL = (Date) registro.get(2);
            LocalDate fechaInicio = fechaInicioSQL.toLocalDate();

            Date fechaFinSQL = (Date) registro.get(3);
            LocalDate fechaFin = fechaFinSQL.toLocalDate();

            Tarifa tarifa = new Tarifa(idTarifa,monto,fechaInicio,fechaFin);

            tarifasList.add(tarifa);
        }

        return tarifasList;
    }




}
