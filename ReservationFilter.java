import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationFilter {

    public static List<Reservation> byDate(List<Reservation> reservations, LocalDate date) {
        return reservations.stream()
                .filter(reservation -> reservation.getDateTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    public static List<Reservation> byCustomerName(List<Reservation> reservations, String customerName) {
        return reservations.stream()
                .filter(reservation -> reservation.getCustomerName().equalsIgnoreCase(customerName))
                .collect(Collectors.toList());
    }

    public static List<Reservation> byTimeRange(List<Reservation> reservations, LocalTime startTime, LocalTime endTime) {
        return reservations.stream()
                .filter(reservation -> {
                    LocalTime reservationTime = reservation.getDateTime().toLocalTime();
                    return !reservationTime.isBefore(startTime) && !reservationTime.isAfter(endTime);
                })
                .collect(Collectors.toList());
    }
}