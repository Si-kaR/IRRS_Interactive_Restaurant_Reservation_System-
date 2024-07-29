import java.time.LocalDateTime;
import java.util.TreeSet;

public class Table {
    private int id;
    private int capacity;
    private TreeSet<Reservation> reservations;

    // Constructor, getters and setters

    public Table(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.reservations = new TreeSet<>();
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean reserve(Reservation reservation) {
        return reservations.add(reservation);
    }

    public void release(Reservation reservation) {
        reservations.remove(reservation);
    }

    public boolean isAvailable(LocalDateTime dateTime) {
        return reservations.stream().noneMatch(reservation -> reservation.getDateTime().isEqual(dateTime));
    }
}