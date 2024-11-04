package Dominio;

import Persistencia.AppSQLException;
import Persistencia.PTarifa;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tarifa {
    public int IdTarifa;
    public double monto;
    public LocalDate fechaInicio;
    public LocalDate fechaFin;

    public int getIdTarifa() {
        return IdTarifa;
    }

    public void setIdTarifa(int idTarifa) {
        IdTarifa = idTarifa;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Tarifa(int idTarifa, double monto, LocalDate fechaInicio, LocalDate fechaFin) {
        IdTarifa = idTarifa;
        this.monto = monto;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "Tarifa{" +
                "IdTarifa=" + IdTarifa +
                ", monto=" + monto +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }

    public static double obtenerMonto(LocalDate Fecha) throws AppSQLException {
        ArrayList<Tarifa> listTarifa = PTarifa.listarTarifas();
        for(Tarifa t : listTarifa){
            if(t.fechaInicio.isBefore(Fecha) && t.fechaFin.isAfter(Fecha)){
                return t.getMonto();
            }
        }
        return 100;
    }
}
