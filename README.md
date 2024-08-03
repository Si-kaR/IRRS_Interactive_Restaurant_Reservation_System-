# IRRS_Interactive_Restaurant_Reservation_System-

The IRRS Interactive Restaurant Reservation System is a Java-based simulation designed for a Data Structures and Algorithms class. It models a restaurant reservation system that allows users to book tables, cancel reservations, and view reservation statuses. The system uses in-memory data structures to manage operations and state, and it operates through a command-line interface.

**Key Features:**
- **Reservation Management**: Book tables, cancel reservations, and view reservations with various filters.
- **Table Availability**: Check table availability for specific dates and times.
- **Reservation Summaries**: Generate text-based summaries for specified dates or ranges.

**Components**
**HashTable**
A custom implementation of a hash table that maps dates to ReservationLinkedList objects.

*Methods:*
add(Reservation reservation): Adds a reservation for the current date.
add(Reservation reservation, LocalDate date): Adds a reservation for a specific date.
remove(Reservation reservation, LocalDate date): Removes a reservation for a specific date.

**ReservationDto**
Data Transfer Object (DTO) is used to encapsulate reservation details.

*Constructor:*
ReservationDto(String id, String customerName, LocalTime time, LocalDate date, int numPeople, Table table): Initializes a reservation DTO.

*Methods:*
getDetails(): Returns a formatted string of reservation details.

**ReservationFilter**
Provides utility methods to filter reservations based on different criteria.

*Static Methods:*
all(): Returns a predicate that matches all reservations.
byCustomerName(String customerName): Returns a predicate that matches reservations by customer name.
byTimeRange(List<Reservation> reservations, LocalTime startTime, LocalTime endTime): Filters reservations by a time range.

*Instance Methods:*
byCustomerName(String customerName): Filters the current list of reservations by customer name.
ReservationLinkedList
A custom implementation of a linked list for managing reservations with additional functionalities.

*Methods:*
add(Reservation reservation): Adds a reservation in sorted order based on time.
search(LocalTime time): Searches for a reservation by time.
search(Predicate<Reservation> predicate): Searches for reservations based on a predicate.

**SummaryGenerator**
Generates summaries of reservations.

*Constructor:*
SummaryGenerator(ReservationSystem reservationService): Initializes the summary generator with a reservation service.

*Methods:*
dailySummary(List<ReservationDto> reservations): Generates a daily summary of reservations.
dateRangeSummary(LocalDate startDate, LocalDate endDate): Generates a summary of reservations for a date range.
summary(List<ReservationDto> reservations): Generates a generic summary of reservations.


Contact
For any questions or issues, please get in touch with kwame.wilson@ashesi.edu.gh,rahman.abubakar@ashesi.edu.gh,isabel.herraiz@ashesi.edu.gh, robert.sika@ashesi.edu.gh


