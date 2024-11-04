package Dominio;

public class Habitaciones {
private int IdHabitacion;
private int CantCamas;
private boolean CamaMatrimonial;
private boolean CamasIndividuales;
private boolean AireAcondicionado;
private boolean Balcon;
private String Amenities;
private boolean Ocupada;
private int Hotel;

    public int getIdHabitacion() {
        return IdHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        IdHabitacion = idHabitacion;
    }

    public int getCantCamas() {
        return CantCamas;
    }

    public void setCantCamas(int cantCamas) {
        CantCamas = cantCamas;
    }

    public boolean isCamaMatrimonial() {
        return CamaMatrimonial;
    }

    public void setCamaMatrimonial(boolean camaMatrimonial) {
        CamaMatrimonial = camaMatrimonial;
    }

    public boolean isCamasIndividuales() {
        return CamasIndividuales;
    }

    public void setCamasIndividuales(boolean camasIndividuales) {
        CamasIndividuales = camasIndividuales;
    }

    public boolean isAireAcondicionado() {
        return AireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        AireAcondicionado = aireAcondicionado;
    }

    public boolean isBalcon() {
        return Balcon;
    }

    public void setBalcon(boolean balcon) {
        Balcon = balcon;
    }

    public boolean isOcupada() {
        return Ocupada;
    }

    public void setOcupada(boolean ocupada) {
        Ocupada = ocupada;
    }

    public String getAmenities() {
        return Amenities;
    }

    public void setAmenities(String amenities) {
        Amenities = amenities;
    }

    public int getHotel() {
        return Hotel;
    }

    public void setHotel(int hotel) {
        Hotel = hotel;
    }

    public void cambiarEstadoOcupada(boolean estado) {
        this.Ocupada = estado;
    }



    public Habitaciones(int idHabitacion, int cantCamas, boolean camaMatrimonial,
                        boolean camasIndividuales, boolean aireAcondicionado, boolean balcon, String amenities,
                        boolean ocupada, int hotel) {

        IdHabitacion = idHabitacion;
        CantCamas = cantCamas;
        CamaMatrimonial = camaMatrimonial;
        CamasIndividuales = camasIndividuales;
        AireAcondicionado = aireAcondicionado;
        Balcon = balcon;
        Amenities=amenities;
        Ocupada = ocupada;
        Hotel = hotel;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "idHabitacion=" + this.IdHabitacion +
                ", cantCamas=" + this.CantCamas +
                ", camaMatrimonial=" + this.CamaMatrimonial +
                ", camasIndividuales=" + this.CamasIndividuales +
                ", aireAcondicionado=" + this.AireAcondicionado +
                ", balcon=" + this.Balcon +
                ", amenities=" + this.Amenities +
                ", ocupada=" + this.Ocupada +
                ", Hotel=" + this.Hotel +
                '}';
    }

}
