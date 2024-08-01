package helpers;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import src.Reservation;

public class ReservationFilter extends ArrayList<Reservation> {



    public static Predicate<Reservation> all(){
        return r -> true;
    }

    public static Predicate<Reservation> ByCustomerName(String customerName){
        return r -> r.getCustomerName().equalsIgnoreCase(customerName);
    }
   

    public List<Reservation> byCustomerName(String customerName) {
        return this.stream()
                .filter(reservation -> reservation.getCustomerName().equalsIgnoreCase(customerName))
                .collect(Collectors.toList());
    }

    public static List<Reservation> byTimeRange(List<Reservation> reservations, LocalTime startTime, LocalTime endTime) {
        return reservations.stream()
                .filter(reservation -> {
                    LocalTime reservationTime = reservation.getTime();
                    return !reservationTime.isBefore(startTime) && !reservationTime.isAfter(endTime);
                })
                .collect(Collectors.toList());
    }
    
    

    

    
}