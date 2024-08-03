package helpers;
import static helpers.ReservationFilter.all;
import java.time.LocalDate;
import java.util.List;
import src.ReservationSystem;

public class SummaryGenerator {

    private final ReservationSystem _reservationService;

    public SummaryGenerator(ReservationSystem _reservationService) {
        this._reservationService = _reservationService;
    }

    public  String dailySummary(List<ReservationDto> reservations) {
        List<ReservationDto> filteReservations = _reservationService.viewReservations(all(),LocalDate.now());
        StringBuilder summary = new StringBuilder();
        summary.append("Daily Summary:\n");

        filteReservations.forEach(reservation -> {
            summary.append(reservation.getDetails()).append("\n");
        });

        return summary.toString();
    }

    public  String dateRangeSummary(LocalDate startDate, LocalDate endDate) {
        List<ReservationDto> filteredReservations = _reservationService.viewReservations(all(),startDate,endDate);

        StringBuilder summary = new StringBuilder();
        summary.append("Date Range Summary (").append(startDate).append(" to ").append(endDate).append("):\n");

        filteredReservations.forEach(reservation -> {
            summary.append(reservation.getDetails()).append("\n");
        });

        return summary.toString();
    }
}