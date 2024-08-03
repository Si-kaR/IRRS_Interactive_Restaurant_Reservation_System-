package src;

import helpers.ReservationDto;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Reservation implements Comparable<Reservation> {
    private String id;
    private String customerName;
    private LocalTime time;
    private int numPeople;
    private Table table;
   
    public Reservation( String customerName, LocalTime time, int numPeople) {
        this.id = UUID.randomUUID().toString();
        this.customerName = customerName;
        this.time = time;
        this.numPeople = numPeople;
    }

    public String getId(){
        return this.id;
    }

    public String getDetails() {
        return String.format("Reservation ID: %s\nCustomer Name: %s\nDateTime: %s\nNumber of People: %d\nTable ID: %d\n",
                id, customerName, time, numPeople, table.getId());
    }

    public int getNumPeople() {
        return numPeople;
    }

    public Table getTable() {
        return table;
    }

    public void cancel(LocalDate date) {
        table.release(this,date);
    }


    public LocalTime getTime() {
        return time;
    }

    @Override
    public int compareTo(Reservation other) {
        return this.time.compareTo(other.time);
    }

    public String getCustomerName(){
        return this.customerName;
    }

    public void setTable(Table table){
        this.table = table;
    }

    public ReservationDto ConvertToReservationDto(LocalDate date){
        return new ReservationDto(
            this.id,
            this.customerName,
            this.time,
            date,
            this.numPeople,
            this.table
        );
    }

    public static List<ReservationDto> ConvertToReservationDto(List<Reservation> list,LocalDate date){
        List<ReservationDto> listDto = new ArrayList<>();
        for (Reservation reservation : list) {
            listDto.add(reservation.ConvertToReservationDto(date));
        }
        return listDto;
    }


    
}
