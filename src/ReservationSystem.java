package src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;


public class ReservationSystem {

    private  ArrayList<Table> tables;
   
    public ReservationSystem() {
        this.tables = new ArrayList<>();
        
        
    }

    public String bookTable(String customerName, LocalDate date, LocalTime time, int numPeople) {
        for (Table table : tables) {
            Reservation reservation = new Reservation(customerName, time, numPeople);
            if (table.reserve(reservation,date)) return reservation.getId();
            
        }
        return null;
    }

    public boolean cancelReservation(String reservationId,LocalDate date,Table table) {
        Reservation reservation = (table.getReservations().get(date).search(r -> r.getId().equals(reservationId))).get(0) ;
        if (reservation != null) {
            reservation.getTable().release(reservation,date);
            return true;
        }
        return false;
    }

    

    public List<Table> checkAvailability(LocalDate date, LocalTime time) {
        List<Table> availableTables = new ArrayList<>();
        for (Table table : tables) {
            if(table.getReservations().containsKey(date)){
                if(table.isAvailable(time, date)) availableTables.add(table);
            }
        }
        return availableTables;
    }

    public void addTable(int id,int Capacity){
        Table table = new Table(id, Capacity);
        tables.add(table);
    }

    public List<Reservation> viewReservations(Predicate<Reservation> predicate,LocalDate date){
        List<Reservation> reservations = new ArrayList<>();
        for (Table table : tables) {
            reservations.addAll(viewReservations(predicate,date,table));
        }
        return reservations;
    }

    public List<Reservation> viewReservations(Predicate<Reservation> predicate,LocalDate date,Table table) {
        return table.getReservations().get(date).search(predicate);
    }

    public List<Reservation> viewReservations(Predicate<Reservation> predicate,Table table){
        Set<LocalDate> dates = table.getReservations().keySet();
        List<Reservation> reservations = new ArrayList<>();
        for (LocalDate date : dates) {
            reservations.addAll(viewReservations(predicate,date, table));
        }
        return reservations;
    }

    public List<Reservation> viewReservations(Predicate<Reservation> predicate,LocalDate startDate, LocalDate endDate){
        List<Reservation> reservations = new ArrayList<>();
        for (Table table : tables) {
            Set<LocalDate> dates = table.getReservations().keySet();
            dates.stream().filter(date -> (date.isEqual(startDate) || date.isAfter(startDate)) &&
                    (date.isEqual(endDate) || date.isBefore(endDate))).forEach(date -> reservations.addAll(viewReservations(predicate, date, table)));
        }
        return reservations;

    }

    public List<Reservation> viewReservations(Predicate<Reservation> predicate){
        List<Reservation> reservations = new ArrayList<>();
        for (Table table : tables) {
            reservations.addAll(viewReservations(predicate, table));
        }
        return reservations;
    }

    public Table getTableById(int id) {
        for (Table table : tables) {
            if (table.getId() == id) {
                return table;
            }
        }
        return null;
    }
    

    


}
