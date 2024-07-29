import java.time.LocalDateTime;

public class Reservation {
    private String id;
    private String customerName;
    private LocalDateTime dateTime;
    private int numPeople;
    private Table table;
    private NotificationType notificationPreference;

    // Constructor, getters and setters

    public Reservation(String id, String customerName, LocalDateTime dateTime, int numPeople, Table table, NotificationType notificationPreference) {
        this.id = id;
        this.customerName = customerName;
        this.dateTime = dateTime;
        this.numPeople = numPeople;
        this.table = table;
        this.notificationPreference = notificationPreference;
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public Table getTable() {
        return table;
    }

    public NotificationType getNotificationPreference() {
        return notificationPreference;
    }

    public String getDetails() {
        return "Reservation ID: " + id + ", Customer: " + customerName + ", DateTime: " + dateTime + ", People: " + numPeople + ", Table: " + table.getId();
    }

    public void cancel() {
        // Cancellation logic
    }

    public void updateNotificationPreference(NotificationType notificationPreference) {
        this.notificationPreference = notificationPreference;
    }
}