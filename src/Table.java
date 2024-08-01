package src;
import helpers.HashTable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Table {
    private int id;
    private int capacity;
    private HashTable reservations;

    public Table(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.reservations = new HashTable();
    }

    public boolean reserve(Reservation reservation,LocalDate date) {
        if (reservation.getNumPeople() > this.capacity) {
            System.out.println("Reservation exceeds table capacity.");
            return false;
        }
        
        if (isAvailable(reservation.getTime(),date)) {
            reservation.setTable(this);
            return reservations.add(reservation,date);
        }
        
        System.out.println("Table is not available at the requested time.");
        return false;
    }

    

   
    public void release(Reservation reservation,LocalDate date) {
        reservations.remove(reservation,date);
    }

    public boolean isAvailable(LocalTime time, LocalDate date) {
        if(! this.reservations.containsKey(date)) return true;
        try {
            for (Reservation reservation : reservations.get(date)) {
                if (reservation.getTime().equals(time)) {
                    return false;
                }
            }
            return true; 
        } catch (Exception e) {
            return false;
        }
        
    }

    public int getId() {
        return id;
    }

    public int getCapacity(){
        return capacity;
    }

    public HashTable getReservations(){
        return reservations;
    }
}
