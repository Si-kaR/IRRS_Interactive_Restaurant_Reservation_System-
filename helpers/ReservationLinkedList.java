package helpers;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import src.Reservation;

public class ReservationLinkedList extends LinkedList<Reservation> {

    @Override
    public boolean add(Reservation reservation) {
        int i = this.getIndex(reservation.getTime());
        switch (i) {
            case -1 -> super.add(reservation);
            case 0 -> super.add(0,reservation);
            default -> super.add(i,reservation);
        }
        
        return true;
    }

    public Reservation search(LocalTime time) {
        List<Reservation> list = new LinkedList<>(this); 
        return binarySearchHelperObject(list, r -> r.getTime().compareTo(time) == 0);
    }

    public List<Reservation> search(Predicate<Reservation> predicate){
        List<Reservation> list = new LinkedList<>(this); 
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    private int getIndex(LocalTime time){
        List<Reservation> list = new LinkedList<>(this);
        return binarySearchHelperIndex(list, r -> r.getTime().compareTo(time) >= 0);
    }

    private int binarySearchHelperIndex(List<Reservation> list, Predicate<Reservation> predicate) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Reservation midVal = list.get(mid);
            boolean result = predicate.test(midVal);

            if (result) {
                return mid; 
            } else if (predicate.test(list.get(low))) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1; 
    }

    private Reservation binarySearchHelperObject(List<Reservation> list, Predicate<Reservation> predicate){
        int index = binarySearchHelperIndex(list, predicate);
        if(index == -1) return null;
        return list.get(binarySearchHelperIndex(list, predicate));
    }

    

    
    

    
    
   

}
