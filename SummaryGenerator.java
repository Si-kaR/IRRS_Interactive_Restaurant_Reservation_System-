import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SummaryGenerator {

    public static String dailySummary(List<Reservation> reservations) {
        StringBuilder summary = new StringBuilder();
        summary.append("Daily Summary:\n");

        reservations.forEach(reservation -> {
            summary.append(reservation.getDetails()).append("\n");
        });

        return summary.toString();
    }

    public static String dateRangeSummary(List<Reservation> reservations, LocalDate startDate, LocalDate endDate) {
        List<Reservation> filteredReservations = reservations.stream()
                .filter(reservation -> {
                    LocalDate reservationDate = reservation.getDateTime().toLocalDate();
                    return (reservationDate.isEqual(startDate) || reservationDate.isAfter(startDate)) &&
                           (reservationDate.isEqual(endDate) || reservationDate.isBefore(endDate));
                })
                .collect(Collectors.toList());

        StringBuilder summary = new StringBuilder();
        summary.append("Date Range Summary (").append(startDate).append(" to ").append(endDate).append("):\n");

        filteredReservations.forEach(reservation -> {
            summary.append(reservation.getDetails()).append("\n");
        });

        return summary.toString();
    }
}