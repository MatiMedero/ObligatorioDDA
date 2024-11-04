package Dominio;

import java.time.LocalDate;

public class Huespedes {
    private int  Idhuesped;
    private String Nombre;
    private String Apaterno;
    private String Amaterno;
    private String TipoDoc;
    private int NumDoc;
    private LocalDate FechaNac;
    private int Telefono;
    private String Pais;

    public int getIdhuesped() {
        return Idhuesped;
    }

    public void setIdhuesped(int idhuesped) {
        Idhuesped = idhuesped;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApaterno() {
        return Apaterno;
    }

    public void setApaterno(String apaterno) {
        Apaterno = apaterno;
    }

    public String getAmaterno() {
        return Amaterno;
    }

    public void setAmaterno(String amaterno) {
        Amaterno = amaterno;
    }

    public String getTipoDoc() {
        return TipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        TipoDoc = tipoDoc;
    }

    public int getNumDoc() {
        return NumDoc;
    }

    public void setNumDoc(int numDoc) {
        NumDoc = numDoc;
    }

    public LocalDate getFechaNac() {
        return FechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        FechaNac = fechaNac;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int telefono) {
        Telefono = telefono;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public Huespedes(int idhuesped, String nombre, String apaterno, String amaterno,
                     String tipoDoc, int numDoc, LocalDate fechaNac, int telefono, String pais) {

        Idhuesped = idhuesped;
        Nombre = nombre;
        Apaterno = apaterno;
        Amaterno = amaterno;
        TipoDoc = tipoDoc;
        NumDoc = numDoc;
        FechaNac = fechaNac;
        Telefono = telefono;
        Pais = pais;


    }

    @Override
    public String toString() {
        return "Huésped{" +
                "ID=" + Idhuesped +
                ", Nombre='" + Nombre + '\'' +
                ", Apaterno='" + Apaterno + '\'' +
                ", Amaterno='" + Amaterno + '\'' +
                ", TipoDoc='" + TipoDoc + '\'' +
                ", NumDoc=" + NumDoc +
                ", FechaNac=" + FechaNac +
                ", Telefono=" + Telefono +
                ", Pais='" + Pais + '\'' +
                '}';
    }
}
