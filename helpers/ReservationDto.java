package helpers;

import java.time.LocalDate;
import java.time.LocalTime;
import src.Table;

public class ReservationDto {
    public String id;
    public String customerName;
    public LocalDate date;
    public LocalTime time;
    public int numPeople;
    public int tableId;

    public ReservationDto(String id, String customerName, LocalTime time, LocalDate date, int numPeople,Table table){
        this.id = id;
        this.customerName = customerName;
        this.time = time;
        this.date = date;
        this.numPeople = numPeople;
        this.tableId = table.getId();
    }

    public String getDetails() {
        return String.format("Reservation ID: %s\nCustomer Name: %s\nDate: %s\nTime: %s\nNumber of People: %d\nTable ID: %d\n",
                id, customerName,date,time, numPeople, tableId);
    }
}