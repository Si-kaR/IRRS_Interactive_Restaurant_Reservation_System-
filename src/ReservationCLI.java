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
            // int choice = Integer.parseInt(scanner.nextLine());
            int choice = -1;
            boolean validInput = false;

            while (!validInput) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.\n");
                    System.out.print("Enter your choice: ");
                }
            }   switch (choice) {
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
        System.out.println("\nReservation System CLI - - - - - - - - - - - - - - - - - - - -|\n - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -|");
        System.out.println("1. Book a table                                               |");
        System.out.println("2. Cancel a reservation                                       |");
        System.out.println("3. Check table availability                                   |");
        System.out.println("4. View daily summary                                         |");
        System.out.println("5. View date range summary                                    |");
        System.out.println("6. Add a table                                                |");
        System.out.println("0. Exit\n - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -|");

        System.out.print("\nEnter your choice: ");
    }

    private void bookTable() {
        String customerName = null;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter customer name: ");
            try {
                customerName = scanner.nextLine();
                // Check for invalid characters (numbers or symbols)
                if (!customerName.matches("[a-zA-Z ]+")) {
                throw new IllegalArgumentException("Names cannot contain numbers or symbols.");
            }
            validInput = true; // Valid input, exit loop
            } catch (IllegalArgumentException e) { // catch for numbers
                System.out.println("Invalid input: " + e.getMessage() + "\n");
            }
        }
        // Further actions related to booking a table
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Table booked successfully for " + customerName + "                    |");
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

    }

    // private void cancelReservation() {
    //     System.out.print("Enter reservation ID: ");
    //     String reservationId = scanner.nextLine();
    //     System.out.print("Enter date (YYYY-MM-DD): ");
    //     LocalDate date = LocalDate.parse(scanner.nextLine());
    //     System.out.print("Enter table ID: ");
    //     int tableId = Integer.parseInt(scanner.nextLine());

    //     Table table = reservationSystem.getTableById(tableId); // You need to implement this method in ReservationSystem
    //     if (table != null && reservationSystem.cancelReservation(reservationId, date, table)) {
    //         System.out.println("Reservation cancelled successfully.");
    //     } else {
    //         System.out.println("Reservation not found.");
    //     }
    // }
    private void cancelReservation() {
        System.out.print("Enter reservation ID: ");
        String reservationId = scanner.nextLine();
        
        LocalDate date = null;
        boolean validDate = false;
        
        while (!validDate) {
            System.out.print("Enter date (YYYY-MM-DD): ");
            try {
                date = LocalDate.parse(scanner.nextLine());
                validDate = true; // Valid date, exit loop
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter a date in the format YYYY-MM-DD.\n");
            }
        }
        
        int tableId = -1;
        boolean validTableId = false;
        
        while (!validTableId) {
            System.out.print("Enter table ID: ");
            try {
                tableId = Integer.parseInt(scanner.nextLine());
                validTableId = true; // Valid table ID, exit loop
            } catch (NumberFormatException e) {
                System.out.println("Invalid table ID. Please enter a valid number.\n");
            }
        }
        
        Table table = reservationSystem.getTableById(tableId);
        if (table != null && reservationSystem.cancelReservation(reservationId, date, table)) {
            System.out.println("Reservation cancelled successfully.");
        } else {
            System.out.println("Reservation not found.");
        }
    }
    

    // private void checkAvailability() {
    //     System.out.print("Enter date (YYYY-MM-DD): ");
    //     LocalDate date = LocalDate.parse(scanner.nextLine());
    //     System.out.print("Enter time (HH:MM): ");
    //     LocalTime time = LocalTime.parse(scanner.nextLine());

    //     List<Table> availableTables = reservationSystem.checkAvailability(date, time);
    //     if (!availableTables.isEmpty()) {
    //         System.out.println("Available tables:");
    //         for (Table table : availableTables) {
    //             System.out.println("Table ID: " + table.getId() + ", Capacity: " + table.getCapacity());
    //         }
    //     } else {
    //         System.out.println("No available tables for the specified time and date.");
    //     }
    // }
    private void checkAvailability() {
        LocalDate date = null;
        boolean validDate = false;
    
        while (!validDate) {
            System.out.print("Enter date (YYYY-MM-DD): ");
            try {
                date = LocalDate.parse(scanner.nextLine());
                validDate = true; // Valid date, exit loop
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter a date in the format YYYY-MM-DD.\n");
            }
        }
    
        LocalTime time = null;
        boolean validTime = false;
    
        while (!validTime) {
            System.out.print("Enter time (HH:MM): ");
            try {
                time = LocalTime.parse(scanner.nextLine());
                validTime = true; // Valid time, exit loop
            } catch (Exception e) {
                System.out.println("Invalid time format. Please enter time in the format HH:MM.\n");
            }
        }
    
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

    // private void viewDateRangeSummary() {
    //     System.out.print("Enter start date (YYYY-MM-DD): ");
    //     LocalDate startDate = LocalDate.parse(scanner.nextLine());
    //     System.out.print("Enter end date (YYYY-MM-DD): ");
    //     LocalDate endDate = LocalDate.parse(scanner.nextLine());

    //     System.out.println(summaryGenerator.dateRangeSummary(startDate, endDate));
    // }
    private void viewDateRangeSummary() {
        LocalDate startDate = null;
        boolean validStartDate = false;
    
        while (!validStartDate) {
            System.out.print("Enter start date (YYYY-MM-DD): ");
            try {
                startDate = LocalDate.parse(scanner.nextLine());
                validStartDate = true; // Valid start date, exit loop
            } catch (Exception e) {
                System.out.println("Invalid start date format. Please enter a date in the format YYYY-MM-DD.\n");
            }
        }
    
        LocalDate endDate = null;
        boolean validEndDate = false;
    
        while (!validEndDate) {
            System.out.print("Enter end date (YYYY-MM-DD): ");
            try {
                endDate = LocalDate.parse(scanner.nextLine());
                validEndDate = true; // Valid end date, exit loop
            } catch (Exception e) {
                System.out.println("Invalid end date format. Please enter a date in the format YYYY-MM-DD.\n");
            }
        }
    
        System.out.println(summaryGenerator.dateRangeSummary(startDate, endDate));
    }
    

    // private void addTable() {
    //     System.out.print("Enter table ID: ");
    //     int id = Integer.parseInt(scanner.nextLine());
    //     System.out.print("Enter table capacity: ");
    //     int capacity = Integer.parseInt(scanner.nextLine());

    //     reservationSystem.addTable(id, capacity);
    //     System.out.println("Table added successfully.");
    // }
    private void addTable() {
        int id = -1;
        boolean validId = false;
    
        while (!validId) {
            System.out.print("Enter table ID: ");
            try {
                id = Integer.parseInt(scanner.nextLine());
                validId = true; // Valid ID, exit loop
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for the table ID.\n");
            }
        }
    
        int capacity = -1;
        boolean validCapacity = false;
    
        while (!validCapacity) {
            System.out.print("Enter table capacity: ");
            try {
                capacity = Integer.parseInt(scanner.nextLine());
                validCapacity = true; // Valid capacity, exit loop
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for the table capacity.\n");
            }
        }
    
        reservationSystem.addTable(id, capacity);
        System.out.println("Table added successfully.");
    }
    
}
