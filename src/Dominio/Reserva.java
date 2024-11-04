package Dominio;

import java.time.LocalDate;

public class Reserva {
    private static int NuevaReserva=1;
    private int IdReserva;
    private int cantPersonas;
    private LocalDate FechaReserva;
    private Huespedes huesped;
    private Hotel hotel;
    private Habitaciones habitacion;
    private LocalDate FecIngreso;
    private LocalDate FecSalida;
    private double Monto;
    private String Observaciones;
    private boolean Pagado;


    public int getIdReserva() {
        return IdReserva;
    }

    public void setIdReserva(int idReserva) {
        IdReserva = idReserva;
    }

    public Huespedes getHuesped() {
        return huesped;
    }

    public void setHuesped(Huespedes huesped) {
        this.huesped = huesped;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getHabitacion() {
        return habitacion.getIdHabitacion();
    }

    public void setHabitacion(Habitaciones habitacion) {
        this.habitacion = habitacion;
    }

    public LocalDate getFecIngreso() {
        return FecIngreso;
    }

    public void setFecIngreso(LocalDate fecIngreso) {
        FecIngreso = fecIngreso;
    }

    public LocalDate getFecSalida() {
        return FecSalida;
    }

    public void setFecSalida(LocalDate fecSalida) {
        FecSalida = fecSalida;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }

    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String observaciones) {
        Observaciones = observaciones;
    }

    public boolean isPagado() {
        return Pagado;
    }

    public void setPagado(boolean pagado) {
        this.Pagado = pagado;
    }

    public LocalDate getFechaReserva() {
        return FechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        FechaReserva = fechaReserva;
    }

    public double getMonto() {
        return Monto;
    }

    public void setMonto(double monto) {
        Monto = monto;
    }


    public Reserva( int cantPersonas, LocalDate fechaReserva, Huespedes huesped, Hotel hotel, Habitaciones habitacion, LocalDate fecIngreso, LocalDate fecSalida, double monto, String observaciones, boolean pagado) {
        IdReserva = NuevaReserva;
        this.cantPersonas = cantPersonas;
        FechaReserva = fechaReserva;
        this.huesped = huesped;
        this.hotel = hotel;
        this.habitacion = habitacion;
        FecIngreso = fecIngreso;
        FecSalida = fecSalida;
        Monto = monto;
        Observaciones = observaciones;
        Pagado = pagado;
        NuevaReserva++;


    }

    @Override
    public String toString() {
        return "Reserva{" +
                "IdReserva=" + IdReserva +
                ", cantPersonas=" + cantPersonas +
                ", FechaReserva=" + FechaReserva +
                ", huesped=" + huesped +
                ", hotel=" + hotel +
                ", habitacion=" + habitacion +
                ", FecIngreso=" + FecIngreso +
                ", FecSalida=" + FecSalida +
                ", Monto=" + Monto +
                ", Observaciones='" + Observaciones + '\'' +
                ", Pagado=" + Pagado +
                '}';
    }
}
