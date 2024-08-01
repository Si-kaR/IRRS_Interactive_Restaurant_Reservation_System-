package helpers;

import java.time.LocalDate;
import java.util.HashMap;
import src.Reservation;

public class HashTable extends HashMap<LocalDate,ReservationLinkedList>{
    

    public HashTable() {
        
    }

    public boolean add(Reservation reservation){
        return add(reservation, LocalDate.now());
    }

    public boolean  add(Reservation reservation, LocalDate date){
        if(this.containsKey(date)) this.get(date).add(reservation);
        else {
            ReservationLinkedList reservationList = new ReservationLinkedList();
            reservationList.add(reservation);
            this.put(date, reservationList);
        }
        return true;
    }
    
    public void remove(Reservation reservation, LocalDate date){
        ReservationLinkedList list = this.get(date);
        if(this.containsKey(date)) list.remove(reservation);
    }

   
    

    

    
}
