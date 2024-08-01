package src;

import static helpers.ReservationFilter.all;
import helpers.SummaryGenerator;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class ReservationCLI {

    private final ReservationSystem reservationSystem;
    private final SummaryGenerator summaryGenerator;
    private final Scanner scanner;

    public ReservationCLI() {
        this.reservationSystem = new ReservationSystem();
        this.summaryGenerator = new SummaryGenerator(reservationSystem);
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ReservationCLI cli = new ReservationCLI();
        cli.run();
    }

    private void run() {
        while (true) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    bookTable();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    checkAvailability();
                    break;
                case 4:
                    viewDailySummary();
                    break;
                case 5:
                    viewDateRangeSummary();
                    break;
                case 6:
                    addTable();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("Reservation System CLI");
        System.out.println("1. Book a table");
        System.out.println("2. Cancel a reservation");
        System.out.println("3. Check table availability");
        System.out.println("4. View daily summary");
        System.out.println("5. View date range summary");
        System.out.println("6. Add a table");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private void bookTable() {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter time (HH:MM): ");
        LocalTime time = LocalTime.parse(scanner.nextLine());
        System.out.print("Enter number of people: ");
        int numPeople = Integer.parseInt(scanner.nextLine());

        String reservationId = reservationSystem.bookTable(customerName, date, time, numPeople);
        if (reservationId != null) {
            System.out.println("Reservation successful! Your reservation ID is: " + reservationId);
        } else {
            System.out.println("No available tables for the specified time and date.");
        }
    }

    private void cancelReservation() {
        System.out.print("Enter reservation ID: ");
        String reservationId = scanner.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter table ID: ");
        int tableId = Integer.parseInt(scanner.nextLine());

        Table table = reservationSystem.getTableById(tableId); // You need to implement this method in ReservationSystem
        if (table != null && reservationSystem.cancelReservation(reservationId, date, table)) {
            System.out.println("Reservation cancelled successfully.");
        } else {
            System.out.println("Reservation not found.");
        }
    }

    private void checkAvailability() {
        System.out.print("Enter date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter time (HH:MM): ");
        LocalTime time = LocalTime.parse(scanner.nextLine());

        List<Table> availableTables = reservationSystem.checkAvailability(date, time);
        if (!availableTables.isEmpty()) {
            System.out.println("Available tables:");
            for (Table table : availableTables) {
                System.out.println("Table ID: " + table.getId() + ", Capacity: " + table.getCapacity());
            }
        } else {
            System.out.println("No available tables for the specified time and date.");
        }
    }

    private void viewDailySummary() {
        System.out.println(summaryGenerator.dailySummary(reservationSystem.viewReservations(all(), LocalDate.now())));
    }

    private void viewDateRangeSummary() {
        System.out.print("Enter start date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter end date (YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        System.out.println(summaryGenerator.dateRangeSummary(startDate, endDate));
    }

    private void addTable() {
        System.out.print("Enter table ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter table capacity: ");
        int capacity = Integer.parseInt(scanner.nextLine());

        reservationSystem.addTable(id, capacity);
        System.out.println("Table added successfully.");
    }
}
