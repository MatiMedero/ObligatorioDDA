package Dominio;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private int IdHotel;
    private String Nombre;
    private String Pais;
    private String Ciudad;
    private String Direccion;
    private String Barrio;
    private int CantEstrellas;
    private ArrayList<Habitaciones> habitacionesList;

    public int getIdHotel() {
        return IdHotel;
    }

    public void setIdHotel(int idHotel) {
        IdHotel = idHotel;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getBarrio() {
        return Barrio;
    }

    public void setBarrio(String barrio) {
        Barrio = barrio;
    }

    public int getCantEstrellas() {
        return CantEstrellas;
    }

    public void setCantEstrellas(int cantEstrellas) {
        CantEstrellas = cantEstrellas;
    }

    public List<Habitaciones> getHabitacionesList() {
        return habitacionesList;
    }

    public void setHabitacionesList(ArrayList<Habitaciones> habitacionesList) {
        this.habitacionesList = habitacionesList;
    }


    public void agregarHabitacion(Habitaciones habitacion) {
        if (habitacion != null) {
            this.habitacionesList.add(habitacion);
            System.out.println("Habitación agregada al hotel " + this.Nombre);
        } else {
            System.out.println("No se pudo agregar la habitación. Los datos son inválidos.");
        }
    }

    @Override
    public String toString() {
        return "Hotel ID: " + IdHotel + "Nombre: " + Nombre + "País: " + Pais + "Ciudad: " + Ciudad + "Dirección: " + Direccion + "Barrio: " + Barrio + "Cantidad de Estrellas: " + CantEstrellas;
    }


    public Hotel(int idHotel, String nombre, String pais, String ciudad, String direccion,
                 String barrio, int cantEstrellas) {
        IdHotel = idHotel;
        Nombre = nombre;
        Pais = pais;
        Ciudad = ciudad;
        Direccion = direccion;
        Barrio = barrio;
        CantEstrellas = cantEstrellas;
        this.habitacionesList = new ArrayList<>();
    }
}
