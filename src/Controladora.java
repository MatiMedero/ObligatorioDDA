import Dominio.*;
import Persistencia.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controladora {
    private static Scanner escaner = new Scanner(System.in);
    private static ArrayList<Hotel> listaHoteles = new ArrayList<>();



    public void agregarHuesped() {
        System.out.println("Agregar huésped");

        int idHuesped;
        do {
            System.out.println("Ingrese ID del huésped:");
            try {
                idHuesped = Integer.parseInt(escaner.nextLine());
            } catch (Exception e) {
                idHuesped = 0;
            }
        } while (idHuesped == 0);

        String nombre;
        do {
            System.out.println("Ingrese nombre del huésped:");
            nombre = escaner.nextLine();
        } while (nombre.isEmpty());

        String apaterno;
        do {
            System.out.println("Ingrese apellido paterno del huésped:");
            apaterno = escaner.nextLine();
        } while (apaterno.isEmpty());

        String amaterno;
        do {
            System.out.println("Ingrese apellido materno del huésped (opcional):");
            amaterno = escaner.nextLine();
        } while (amaterno.isEmpty());

        String tipoDoc;
        do {
            System.out.println("Ingrese tipo de documento del huésped:");
            tipoDoc = escaner.nextLine();
        } while (tipoDoc.isEmpty());

        int numDoc;
        do {
            System.out.println("Ingrese número de documento del huésped:");
            try {
                numDoc = Integer.parseInt(escaner.nextLine());
            } catch (Exception e) {
                numDoc = 0;
            }
        } while (numDoc == 0);

        LocalDate fechaNac;
        do {
            System.out.println("Ingrese fecha de nacimiento del huésped (YYYY-MM-DD):");
            try {
                fechaNac = LocalDate.parse(escaner.nextLine());
            } catch (Exception e) {
                fechaNac = null;
            }
        } while (fechaNac == null);

        int telefono;
        do {
            System.out.println("Ingrese el teléfono del huésped:");
            try {
                telefono = Integer.parseInt(escaner.nextLine());
            } catch (Exception e) {
                telefono = 0;
            }
        } while (telefono == 0);

        String pais;
        do {
            System.out.println("Ingrese el país del huésped:");
            pais = escaner.nextLine();
        } while (pais.isEmpty());

        Huespedes unHuesped = new Huespedes(idHuesped, nombre, apaterno, amaterno, tipoDoc, numDoc, fechaNac, telefono, pais);
        if (PHuespedes.agregarHuesped(unHuesped)) {
            System.out.println("Se agregó el huésped con éxito");
        } else {
            System.out.println("Hubo un problema al agregar el huésped");
        }
    }

    public void eliminarHuesped() {
        System.out.println("Eliminar huésped");

        System.out.println("Ingrese el ID del huésped:");
        int idHuesped = Integer.parseInt(escaner.nextLine());

        if (PHuespedes.eliminarHuesped(idHuesped)) {
            System.out.println("Se eliminó con éxito el huésped.");
        } else {
            System.out.println("No se pudo eliminar el huésped.");
        }
    }

    public void modificarHuesped() throws AppSQLException {
        System.out.println("Modificar huésped");

        System.out.println("Ingrese el ID del huésped a modificar:");
        int idHuesped = Integer.parseInt(escaner.nextLine());


        Huespedes h = buscarHuesped(idHuesped);

        if (h == null) {
            System.out.println("No se encontró ningún huésped con ese ID.");
            return;
        }

        System.out.println("Ingrese el nombre del huésped (" + h.getNombre() + "):");
        String nombre = escaner.nextLine();
        if (!nombre.isEmpty())
            h.setNombre(nombre);

        System.out.println("Ingrese el apellido paterno del huésped (" + h.getApaterno() + "):");
        String apaterno = escaner.nextLine();
        if (!apaterno.isEmpty())
            h.setApaterno(apaterno);

        System.out.println("Ingrese el apellido materno del huésped (" + h.getAmaterno() + "):");
        String amaterno = escaner.nextLine();
        if (!amaterno.isEmpty())
            h.setAmaterno(amaterno);

        System.out.println("Ingrese el tipo de documento del huésped (" + h.getTipoDoc() + "):");
        String tipoDoc = escaner.nextLine();
        if (!tipoDoc.isEmpty())
            h.setTipoDoc(tipoDoc);

        System.out.println("Ingrese el número de documento del huésped (" + h.getNumDoc() + "):");
        try {
            int numDoc = Integer.parseInt(escaner.nextLine());
            h.setNumDoc(numDoc);
        } catch (Exception e) {
            System.out.println("Número de documento no válido. Se mantendrá el valor anterior.");
        }

        System.out.println("Ingrese la fecha de nacimiento del huésped (" + h.getFechaNac() + ") en formato YYYY-MM-DD:");
        try {
            LocalDate fechaNac = LocalDate.parse(escaner.nextLine());
            h.setFechaNac(fechaNac);
        } catch (Exception e) {
            System.out.println("Fecha no válida. Se mantendrá el valor anterior.");
        }

        System.out.println("Ingrese el teléfono del huésped (" + h.getTelefono() + "):");
        try {
            int telefono = Integer.parseInt(escaner.nextLine());
            h.setTelefono(telefono);
        } catch (Exception e) {
            System.out.println("Teléfono no válido. Se mantendrá el valor anterior.");
        }

        System.out.println("Ingrese el país del huésped (" + h.getPais() + "):");
        String pais = escaner.nextLine();
        if (!pais.isEmpty())
            h.setPais(pais);

        if (PHuespedes.modificarHuesped(h)) {
            System.out.println("Se modificó el huésped con éxito.");
        } else {
            System.out.println("No se pudo modificar el huésped.");
        }
    }

    private Huespedes buscarHuesped(int pIdHuesped) throws AppSQLException {
        return PHuespedes.conseguirHuesped(pIdHuesped);
    }

    public void conseguirHuesped() throws AppSQLException {
        System.out.println("Ingrese el ID del huésped:");
        int idHuesped = Integer.parseInt(escaner.nextLine());

        Huespedes huesped = buscarHuesped(idHuesped);

        if (huesped != null) {
            System.out.println(huesped.toString());
        } else {
            System.out.println("No se encontró ningún huésped con ese ID.");
        }
    }

    public void listarHuespedes() throws AppSQLException {
        System.out.println("Listado de huéspedes");

        for (Huespedes h : PHuespedes.listarHuespedes()) {
            System.out.println(h.toString());
        }
    }


    public void agregarHabitacion() {
        if (listaHoteles.isEmpty()) {
            try {
                cargarHoteles();
            } catch (AppSQLException e) {
                System.out.println("Error al cargar hoteles: " + e.getMessage());
                return;
            }
        }

        int idHotel;
        do {
            System.out.println("Ingrese el ID del hotel al cual desea agregar la habitación:");
            try {
                idHotel = Integer.parseInt(escaner.nextLine());
            } catch (Exception e) {
                idHotel = 0;
                System.out.println("ID no válido. Intente de nuevo.");
            }
        } while (idHotel <= 0);


        Hotel hotel = buscarHotelPorId(idHotel, listaHoteles);

        if (hotel == null) {
            System.out.println("No se puede agregar la habitación porque el hotel no fue encontrado.");
            return;
        }




        System.out.println("Agregar habitación");
        int idHabitacion;
        do {
            System.out.println("Ingrese ID de la habitación:");
            try {
                idHabitacion = Integer.parseInt(escaner.nextLine());
            } catch (Exception e) {
                idHabitacion = 0;
            }
        } while (idHabitacion <= 0);

        int cantCamas;
        do {
            System.out.println("Ingrese la cantidad de camas:");
            try {
                cantCamas = Integer.parseInt(escaner.nextLine());
            } catch (Exception e) {
                cantCamas = 0;
            }
        } while (cantCamas < 0);

        boolean camaMatrimonial;
        String respuesta;
        do {
            System.out.print("¿La habitación tiene cama matrimonial? (si/no): ");
            respuesta = escaner.nextLine().trim().toLowerCase();
        } while (!respuesta.equals("si") && !respuesta.equals("no"));
        camaMatrimonial = respuesta.equals("si");

        boolean camasIndividuales;
        do {
            System.out.print("¿La habitación tiene camas individuales? (si/no): ");
            respuesta = escaner.nextLine().trim().toLowerCase();
        } while (!respuesta.equals("si") && !respuesta.equals("no"));
        camasIndividuales = respuesta.equals("si");

        boolean aireAcondicionado;
        do {
            System.out.print("¿La habitación tiene aire acondicionado? (si/no): ");
            respuesta = escaner.nextLine().trim().toLowerCase();
        } while (!respuesta.equals("si") && !respuesta.equals("no"));
        aireAcondicionado = respuesta.equals("si");

        boolean balcon;
        do {
            System.out.print("¿La habitación tiene balcón? (si/no): ");
            respuesta = escaner.nextLine().trim().toLowerCase();
        } while (!respuesta.equals("si") && !respuesta.equals("no"));
        balcon = respuesta.equals("si");


        String amenities;
        do {
            System.out.println("Ingrese Amenities:");
            amenities = escaner.nextLine();
        } while (amenities.isEmpty());

        boolean ocupada;
        do {
            System.out.print("¿La habitación esta ocupada? (si/no): ");
            respuesta = escaner.nextLine().trim().toLowerCase();
        } while (!respuesta.equals("si") && !respuesta.equals("no"));
        ocupada = respuesta.equals("si");


        Habitaciones Habi = new Habitaciones(idHabitacion, cantCamas, camaMatrimonial, camasIndividuales, aireAcondicionado, balcon,amenities,ocupada, idHotel);
        hotel.agregarHabitacion(Habi);
        System.out.println("Se agregó la habitación con éxito al hotel " + hotel.getNombre());

        if (PHabitaciones.agregarHabitaciones(Habi)) {
            System.out.println("Se agregó la habitación con éxito");
        } else {
            System.out.println("Hubo un problema al agregar la habitación");
        }
    }

    public void eliminarHabitacion() {
        System.out.println("Eliminar habitacion");

        System.out.println("Ingrese el ID de la habitacion:");
        int IdHabitacion = Integer.parseInt(escaner.nextLine());

        if (PHabitaciones.eliminarHabitacion(IdHabitacion)) {
            System.out.println("Se eliminó con éxito el habitacion.");
        } else {
            System.out.println("No se pudo eliminar el habitacion.");
        }
    }

    public void modificarHabitacion() throws AppSQLException {
        System.out.println("Modificar habitación");
        System.out.println("Ingrese el ID del hotel al cual pertenece la habitación:");
        int idHotel = Integer.parseInt(escaner.nextLine());

        System.out.println("Ingrese el ID de la habitación a modificar:");
        int idHabitacion = Integer.parseInt(escaner.nextLine());
        Habitaciones habitacion = PHabitaciones.conseguirHabitacion(idHabitacion, idHotel);

        if (habitacion == null) {
            System.out.println("No se encontró ninguna habitación con ese ID en el hotel.");
            return;
        }



        System.out.println("Ingrese la cantidad de camas (" + habitacion.getCantCamas() + "):");
        String input = escaner.nextLine();
        if (!input.isEmpty()) {
            habitacion.setCantCamas(Integer.parseInt(input));
        }

        System.out.println("¿La habitación tiene cama matrimonial? (actual: " + (habitacion.isCamaMatrimonial() ? "si" : "no") + "):");
        String respuesta = escaner.nextLine().trim().toLowerCase();
        if (!respuesta.isEmpty()) {
            habitacion.setCamaMatrimonial(respuesta.equals("si"));
        }

        System.out.println("¿La habitación tiene camas individuales? (actual: " + (habitacion.isCamasIndividuales() ? "si" : "no") + "):");
        respuesta = escaner.nextLine().trim().toLowerCase();
        if (!respuesta.isEmpty()) {
            habitacion.setCamasIndividuales(respuesta.equals("si"));
        }

        System.out.println("¿La habitación tiene aire acondicionado? (actual: " + (habitacion.isAireAcondicionado() ? "si" : "no") + "):");
        respuesta = escaner.nextLine().trim().toLowerCase();
        if (!respuesta.isEmpty()) {
            habitacion.setAireAcondicionado(respuesta.equals("si"));
        }

        System.out.println("¿La habitación tiene balcón? (actual: " + (habitacion.isBalcon() ? "si" : "no") + "):");
        respuesta = escaner.nextLine().trim().toLowerCase();
        if (!respuesta.isEmpty()) {
            habitacion.setBalcon(respuesta.equals("si"));
        }

        System.out.println("Ingrese Amenities (actual: " + habitacion.getAmenities() + "):");
        String amenities = escaner.nextLine();
        if (!amenities.isEmpty()) {
            habitacion.setAmenities(amenities);
        }

        System.out.println("¿La habitación está ocupada? (actual: " + (habitacion.isOcupada() ? "si" : "no") + "):");
        respuesta = escaner.nextLine().trim().toLowerCase();
        if (!respuesta.isEmpty()) {
            habitacion.setOcupada(respuesta.equals("si"));
        }

        if (PHabitaciones.modificarHabitacion(habitacion)) {
            System.out.println("Se modificó la habitación con éxito.");
        } else {
            System.out.println("No se pudo modificar la habitación.");
        }
    }

    private Habitaciones buscarHabitacion(int pIdHabitacion, int idHotel) throws AppSQLException {
        return PHabitaciones.conseguirHabitacion(pIdHabitacion, idHotel);
    }

    public void conseguirHabitacion() throws AppSQLException {
        System.out.println("Ingrese el ID del hotel al cual pertenece la habitación:");
        int idHotel = Integer.parseInt(escaner.nextLine());

        System.out.println("Ingrese el ID de la habitación:");
        int idHabitacion = Integer.parseInt(escaner.nextLine());

        Habitaciones habitacion = buscarHabitacion(idHabitacion, idHotel);

        if (habitacion != null) {
            System.out.println(habitacion.toString());
        } else {
            System.out.println("No se encontró ninguna habitación con ese ID en el hotel especificado.");
        }
    }

    public void listarHabitaciones() throws AppSQLException {
        System.out.println("Ingrese el ID del hotel para listar las habitaciones:");
        int idHotel = Integer.parseInt(escaner.nextLine());

        System.out.println("Listado de habitaciones para el hotel con ID: " + idHotel);
        ArrayList<Habitaciones> habitaciones = PHabitaciones.listarHabitaciones(idHotel);

        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones registradas para este hotel.");
        } else {
            for (Habitaciones h : habitaciones) {
                System.out.println(h.toString());
            }
        }
    }




    public Hotel buscarHotelPorId(int idHotel, ArrayList<Hotel> listaHoteles) {
        for (Hotel hotel : listaHoteles) {
            if (hotel.getIdHotel() == idHotel) {
                return hotel;
            }
        }
        System.out.println("Hotel con ID " + idHotel + " no encontrado.");
        return null;
    }

    public void agregarHotel() {
        System.out.println("Agregar Hotel");

        int IdHotel;
        do {
            System.out.println("Ingrese ID del hotel:");
            try {
                IdHotel = Integer.parseInt(escaner.nextLine());
            } catch (Exception e) {
                IdHotel = 0;
            }
        } while (IdHotel <= 0);
    String Nombre;
        do {
            System.out.println("Ingrese Nombre del hotel:");
            Nombre = escaner.nextLine();
        } while (Nombre.isEmpty());
    String Pais;
        do {
            System.out.println("Ingrese Pais donde se ubica el hotel:");
            Pais = escaner.nextLine();
        } while (Pais.isEmpty());
String Ciudad;
        do {
            System.out.println("Ingrese Ciudad:");
            Ciudad = escaner.nextLine();
        } while (Ciudad.isEmpty());
String Direccion;
        do {
            System.out.println("Ingrese Direccion:");
            Direccion = escaner.nextLine();
        } while (Direccion.isEmpty());
        String Barrio;
        do {
            System.out.println("Ingrese barrio:");
            Barrio = escaner.nextLine();
        } while (Barrio.isEmpty());
        int CantEstrellas;
        do {
            System.out.println("Ingrese Cantidad de estrellas:");
            try {
                CantEstrellas = Integer.parseInt(escaner.nextLine());
                if (CantEstrellas < 1 || CantEstrellas > 5) {
                    System.out.println("ingrese un número entre 1 y 5.");
                }
            } catch (Exception e) {
                System.out.println("Ingrese un valor valido");
                CantEstrellas = 0;
            }
        } while (CantEstrellas < 1 || CantEstrellas > 5);
                Hotel hotel = new Hotel(IdHotel, Nombre, Pais, Ciudad, Direccion, Barrio, CantEstrellas);
                listaHoteles.add(hotel);

                if (PHotel.agregarHoteles(hotel)) {
                    System.out.println("Se agregó Hotel con éxito");
                } else {
                    System.out.println("Hubo un problema al agregar el hotel");
                }
            }

    public void eliminarHotel() {
        System.out.println("Eliminar hotel");

        System.out.println("Ingrese el ID del hotel:");
        int IdHotel = Integer.parseInt(escaner.nextLine());

        if (PHotel.eliminarHotel(IdHotel)) {
            System.out.println("Se eliminó con éxito el hotel.");
        } else {
            System.out.println("No se pudo eliminar el hotel.");
        }
    }

    private Hotel buscarHotel(int pIdHotel) throws AppSQLException {
        return PHotel.conseguirHotel(pIdHotel);
    }

    public void conseguirHotel() throws AppSQLException {
        System.out.println("Ingrese el ID del hotel:");
        int idHotel = Integer.parseInt(escaner.nextLine());

        Hotel hotel = buscarHotel(idHotel);

        if (hotel != null) {
            System.out.println(hotel.toString());
        } else {
            System.out.println("No se encontró ningún hotel con ese ID.");
        }
    }

    public void modificarHotel() throws AppSQLException{
    System.out.println("Modificar hotel");

    System.out.println("Ingrese el ID del hotel a modificar:");
    int idHotel = Integer.parseInt(escaner.nextLine());

    Hotel hotel = buscarHotel(idHotel);

    if (hotel == null) {
        System.out.println("No se encontró ningún hotel con ese ID.");
        return;
    }

    System.out.println("Ingrese el nombre del hotel (" + hotel.getNombre() + "):");
    String nombre = escaner.nextLine();
    if (!nombre.isEmpty())
        hotel.setNombre(nombre);

    System.out.println("Ingrese el país del hotel (" + hotel.getPais() + "):");
    String pais = escaner.nextLine();
    if (!pais.isEmpty())
        hotel.setPais(pais);

    System.out.println("Ingrese la ciudad del hotel (" + hotel.getCiudad() + "):");
    String ciudad = escaner.nextLine();
    if (!ciudad.isEmpty())
        hotel.setCiudad(ciudad);

    System.out.println("Ingrese la dirección del hotel (" + hotel.getDireccion() + "):");
    String direccion = escaner.nextLine();
    if (!direccion.isEmpty())
        hotel.setDireccion(direccion);

    System.out.println("Ingrese el barrio del hotel (" + hotel.getBarrio() + "):");
    String barrio = escaner.nextLine();
    if (!barrio.isEmpty())
        hotel.setBarrio(barrio);

    System.out.println("Ingrese la cantidad de estrellas del hotel (" + hotel.getCantEstrellas() + "):");
    try {
        int cantEstrellas = Integer.parseInt(escaner.nextLine());
        hotel.setCantEstrellas(cantEstrellas);
    } catch (Exception e) {
        System.out.println("Cantidad de estrellas no válida. Se mantendrá el valor anterior.");
    }


    if (PHotel.modificarHotel(hotel)) {
        System.out.println("Se modificó el hotel con éxito.");
    } else {
        System.out.println("No se pudo modificar el hotel.");
    }
}

    public void listarHoteles() throws AppSQLException {
       System.out.println("Listado de hoteles:");
    for (Hotel ho : PHotel.listarHoteles()) {
        System.out.println(ho.toString());
    }
    }

    public void listarHabitacionesDeHotel() throws AppSQLException {
        System.out.println("Ingrese el ID del hotel para listar sus habitaciones:");
        int idHotel = Integer.parseInt(escaner.nextLine());

        Hotel hotel = buscarHotel(idHotel);

        if (hotel != null) {
            System.out.println("Listado de habitaciones para el hotel: " + hotel.getNombre());
            List<Habitaciones> habitaciones = hotel.getHabitacionesList();
            if (habitaciones.isEmpty()) {
                System.out.println("Este hotel no tiene habitaciones registradas.");
            } else {
                for (Habitaciones h : habitaciones) {
                    System.out.println(h.toString());
                }
            }
        } else {
            System.out.println("No se encontró ningún hotel con ese ID.");
        }
    }

    public void cargarHoteles() throws AppSQLException {
        listaHoteles = PHotel.listarHoteles();
    }



    public void agregarReserva() throws AppSQLException {
        System.out.println("Agregar Reserva");

        int cantPersonas;
        do {
            System.out.println("Ingrese la cantidad de personas:");
            try {
                cantPersonas = Integer.parseInt(escaner.nextLine());
            } catch (Exception e) {
                cantPersonas = 0;
            }
        } while (cantPersonas <= 0);

        LocalDate fechaReserva = LocalDate.now();

        Huespedes huesped;
        do {
            System.out.println("Ingrese el ID del huésped para la reserva:");
            int pIdHuesped = Integer.parseInt(escaner.nextLine());
            huesped = PHuespedes.conseguirHuesped(pIdHuesped);
            if (huesped == null) {
                System.out.println("Huésped no encontrado.");
            }
        } while (huesped == null);

        Hotel hotel;
        do {
            System.out.println("Ingrese el ID del hotel:");
            int idHotel = Integer.parseInt(escaner.nextLine());
            hotel = PHotel.conseguirHotel(idHotel);
            if (hotel == null) {
                System.out.println("Hotel no encontrado.");
            }
        } while (hotel == null);

        Habitaciones habitacion;
        do {
            System.out.println("Ingrese el ID de la habitación:");
            int idHabitacion = Integer.parseInt(escaner.nextLine());
            habitacion = PHabitaciones.conseguirHabitacion(idHabitacion, hotel.getIdHotel());
            if (habitacion == null) {
                System.out.println("Habitación no encontrada en el hotel con ID: " + hotel.getIdHotel());
            }
        } while (habitacion == null);
        if (habitacion.isOcupada()) {
            System.out.println("La habitación está ocupada y no puede ser reservada.");
            return;
        }
        LocalDate fecIngreso;
        do {
            System.out.println("Ingrese la fecha de ingreso (YYYY-MM-DD):");
            try {
                fecIngreso = LocalDate.parse(escaner.nextLine());
            } catch (Exception e) {
                fecIngreso = null;
            }
        } while (fecIngreso == null);

        LocalDate fecSalida;
        do {
            System.out.println("Ingrese la fecha de salida (YYYY-MM-DD):");
            try {
                fecSalida = LocalDate.parse(escaner.nextLine());
            } catch (Exception e) {
                fecSalida = null;
            }
        } while (fecSalida == null || fecSalida.isBefore(fecIngreso));

        double monto = Tarifa.obtenerMonto(fecIngreso);
        System.out.println("El monto a pagar es "+monto);

        double montoReserva = monto*0.2;
        System.out.println("Lo que hay que pagar para asegurar la reserva es  "+montoReserva);

        System.out.println("Ingrese observaciones (opcional):");
        String observaciones = escaner.nextLine();

        boolean pagado;
        String respuesta;
        do {
            System.out.print("¿La reserva esta pagada? (si/no): ");
            respuesta = escaner.nextLine().trim().toLowerCase();
        } while (!respuesta.equals("si") && !respuesta.equals("no"));

        pagado = respuesta.equals("si");
        double montoPagado = pagado ? monto : montoReserva;

        Reserva nReserva = new Reserva(cantPersonas,fechaReserva,huesped,hotel,habitacion,fecIngreso,fecSalida,montoPagado,observaciones,pagado);
        if (PReserva.agregarReserva(nReserva)) {
            System.out.println("Reserva agregada con éxito.");
           if(PHabitaciones.cambiarEstadoOcupada(habitacion.getIdHabitacion(),true)){
               System.out.println("La habitación ha sido marcada como ocupada.");
           }
           else{
               System.out.println("No se pudo actualizar el estado de la habitacion");
           }
        } else {
            System.out.println("Hubo un problema al agregar la reserva.");
        }
    }

    public void eliminarReserva() throws AppSQLException {
        System.out.println("Eliminar reserva");

        System.out.println("Ingrese el ID de la reserva:");
        int idReserva = Integer.parseInt(escaner.nextLine());

        if (PReserva.eliminarReserva(idReserva)) {
            System.out.println("Se eliminó con éxito la reserva.");
        } else {
            System.out.println("No se pudo eliminar la reserva.");
        }
    }

    public void modificarReserva() throws AppSQLException {
        System.out.println("Modificar reserva");

        System.out.println("Ingrese el ID de la reserva a modificar:");
        int idReserva = Integer.parseInt(escaner.nextLine());

        Reserva reserva = PReserva.conseguirReserva(idReserva);

        if (reserva == null) {
            System.out.println("No se encontró ninguna reserva con ese ID.");
            return;
        }

        System.out.println("Ingrese la cantidad de personas (" + reserva.getCantPersonas() + "):");
        try {
            int cantPersonas = Integer.parseInt(escaner.nextLine());
            reserva.setCantPersonas(cantPersonas);
        } catch (Exception e) {
            System.out.println("Cantidad no válida. Se mantendrá el valor anterior.");
        }

        System.out.println("Ingrese la fecha de reserva (" + reserva.getFechaReserva() + ") en formato YYYY-MM-DD:");
        try {
            LocalDate fechaReserva = LocalDate.parse(escaner.nextLine());
            reserva.setFechaReserva(fechaReserva);
        } catch (Exception e) {
            System.out.println("Fecha no válida. Se mantendrá el valor anterior.");
        }


        System.out.println("Ingrese el ID del huésped para la reserva (" + reserva.getHuesped().getIdhuesped() + "):");
        int idHuesped = Integer.parseInt(escaner.nextLine());
        Huespedes huesped = PHuespedes.conseguirHuesped(idHuesped);
        if (huesped != null) {
            reserva.setHuesped(huesped);
        } else {
            System.out.println("No se encontró el huésped. Se mantendrá el valor anterior.");
        }

        System.out.println("Ingrese el ID del hotel para la reserva (" + reserva.getHotel().getIdHotel() + "):");
        int idHotel = Integer.parseInt(escaner.nextLine());
        Hotel hotel = PHotel.conseguirHotel(idHotel);
        if (hotel != null) {
            reserva.setHotel(hotel);
        } else {
            System.out.println("No se encontró el hotel. Se mantendrá el valor anterior.");
        }

        System.out.println("Ingrese el ID de la habitación para la reserva (" + reserva.getHabitacion() + "):");
        int idHabitacion = Integer.parseInt(escaner.nextLine());
        Habitaciones habitacion = PHabitaciones.conseguirHabitacion(idHabitacion, hotel.getIdHotel());
        if (habitacion != null) {
            reserva.setHabitacion(habitacion);
        } else {
            System.out.println("No se encontró la habitación. Se mantendrá el valor anterior.");
        }

        System.out.println("Ingrese la fecha de ingreso (" + reserva.getFecIngreso() + ") en formato YYYY-MM-DD:");
        try {
            LocalDate fecIngreso = LocalDate.parse(escaner.nextLine());
            reserva.setFecIngreso(fecIngreso);
        } catch (Exception e) {
            System.out.println("Fecha no válida. Se mantendrá el valor anterior.");
        }

        System.out.println("Ingrese la fecha de salida (" + reserva.getFecSalida() + ") en formato YYYY-MM-DD:");
        try {
            LocalDate fecSalida = LocalDate.parse(escaner.nextLine());
            reserva.setFecSalida(fecSalida);
        } catch (Exception e) {
            System.out.println("Fecha no válida. Se mantendrá el valor anterior.");
        }

        System.out.println("Ingrese el monto de la reserva (" + reserva.getMonto() + "):");
        try {
            int monto = Integer.parseInt(escaner.nextLine());
            reserva.setMonto(monto);
        } catch (Exception e) {
            System.out.println("Monto no válido. Se mantendrá el valor anterior.");
        }

        System.out.println("Ingrese las observaciones de la reserva (" + reserva.getObservaciones() + "):");
        String observaciones = escaner.nextLine();
        if (!observaciones.isEmpty()) {
            reserva.setObservaciones(observaciones);
        }

        System.out.println("¿La reserva está pagada? (si/no):");
        String respuesta = escaner.nextLine().trim().toLowerCase();
        if (respuesta.equals("si")) {
            reserva.setPagado(true);
        } else if (respuesta.equals("no")) {
            reserva.setPagado(false);
        }

        if (PReserva.modificarReserva(reserva)) {
            System.out.println("Se modificó la reserva con éxito.");
        } else {
            System.out.println("No se pudo modificar la reserva.");
        }
    }



    private Reserva buscarReserva(int pIdReserva) throws AppSQLException {
        return PReserva.conseguirReserva(pIdReserva);
    }

    public void conseguirReserva() throws AppSQLException {
        System.out.println("Ingrese el ID de la reserva:");
        int idReserva = Integer.parseInt(escaner.nextLine());

        Reserva reserva = PReserva.conseguirReserva(idReserva);

        if (reserva != null) {
            System.out.println(reserva.toString());
        } else {
            System.out.println("No se encontró ninguna reserva con ese ID.");
        }
    }

    public void listarReservas() throws AppSQLException {
        System.out.println("Listado de reservas");

        for (Reserva r : PReserva.listarReservas()) {
            System.out.println(r.toString());
        }
    }





    public void agregarTarifa() {
        System.out.println("Agregar tarifa");

        int idTarifa;
        do {
            System.out.println("Ingrese el ID de la tarifa:");
            try {
                idTarifa = Integer.parseInt(escaner.nextLine());
            } catch (Exception e) {
                idTarifa = -1;
            }
        } while (idTarifa < 0);

        double monto;
        do {
            System.out.println("Ingrese el monto de la tarifa:");
            try {
                monto = Double.parseDouble(escaner.nextLine());
            } catch (Exception e) {
                monto = -1;
            }
        } while (monto < 0);

        LocalDate fechaInicio;
        do {
            System.out.println("Ingrese fecha de inicio (YYYY-MM-DD):");
            try {
                fechaInicio = LocalDate.parse(escaner.nextLine());
            } catch (Exception e) {
                fechaInicio = null;
            }
        } while (fechaInicio == null);

        LocalDate fechaFin;
        do {
            System.out.println("Ingrese fecha de fin (YYYY-MM-DD):");
            try {
                fechaFin = LocalDate.parse(escaner.nextLine());
                if (fechaFin.isBefore(fechaInicio)) {
                    System.out.println("La fecha de fin debe ser posterior a la fecha de inicio.");
                    fechaFin = null;
                }
            } catch (Exception e) {
                fechaFin = null;
            }
        } while (fechaFin == null);

        Tarifa nuevaTarifa = new Tarifa(idTarifa,monto,fechaInicio,fechaFin);
        if (PTarifa.agregarTarifa(nuevaTarifa)) {
            System.out.println("Se agregó la tarifa con éxito");
        } else {
            System.out.println("Hubo un problema al agregar la tarifa");
        }
    }

    public void eliminarTarifa() {
        System.out.println("Eliminar tarifa");

        System.out.println("Ingrese el ID de la tarifa:");
        int idTarifa = Integer.parseInt(escaner.nextLine());

        if (PTarifa.eliminarTarifa(idTarifa)) {
            System.out.println("Se eliminó con éxito la tarifa.");
        } else {
            System.out.println("No se pudo eliminar la tarifa.");
        }
    }

    public void modificarTarifa() throws AppSQLException {
        System.out.println("Modificar tarifa");

        System.out.println("Ingrese el ID de la tarifa a modificar:");
        int idTarifa = Integer.parseInt(escaner.nextLine());

        Tarifa tarifa = PTarifa.conseguirTarifa(idTarifa);

        if (tarifa == null) {
            System.out.println("No se encontró ninguna tarifa con ese ID.");
            return;
        }

        System.out.println("Ingrese el monto de la tarifa (" + tarifa.getMonto() + "):");
        double monto;
        try {
            monto = Double.parseDouble(escaner.nextLine());
            tarifa.setMonto(monto);
        } catch (NumberFormatException e) {
            System.out.println("Monto no válido. Se mantendrá el valor anterior.");
        }

        System.out.println("Ingrese la fecha de inicio de la tarifa (" + tarifa.getFechaInicio() + ") en formato YYYY-MM-DD:");
        LocalDate fechaInicio;
        try {
            fechaInicio = LocalDate.parse(escaner.nextLine());
            tarifa.setFechaInicio(fechaInicio);
        } catch (Exception e) {
            System.out.println("Fecha no válida. Se mantendrá el valor anterior.");
        }

        System.out.println("Ingrese la fecha de fin de la tarifa (" + tarifa.getFechaFin() + ") en formato YYYY-MM-DD:");
        LocalDate fechaFin;
        try {
            fechaFin = LocalDate.parse(escaner.nextLine());
            tarifa.setFechaFin(fechaFin);
        } catch (Exception e) {
            System.out.println("Fecha no válida. Se mantendrá el valor anterior.");
        }


        if (PTarifa.modificarTarifa(tarifa)) {
            System.out.println("Se modificó la tarifa con éxito.");
        } else {
            System.out.println("No se pudo modificar la tarifa.");
        }
    }

    private Tarifa buscarTarifa(int pIdTarifa) throws AppSQLException {
        return PTarifa.conseguirTarifa(pIdTarifa);
    }

    public void conseguirTarifa() throws AppSQLException {
        System.out.println("Ingrese el ID de la tarifa:");
        int idTarifa = Integer.parseInt(escaner.nextLine());

        Tarifa tarifa = buscarTarifa(idTarifa);

        if (tarifa != null) {
            System.out.println(tarifa.toString());
        } else {
            System.out.println("No se encontró ninguna tarifa con ese ID.");
        }
    }

    public void listarTarifas() throws AppSQLException {
        System.out.println("Listado de tarifas");

        for (Tarifa t : PTarifa.listarTarifas()) {
            System.out.println(t.toString());
        }
    }


}








