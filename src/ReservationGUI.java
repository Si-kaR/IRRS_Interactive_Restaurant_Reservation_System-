package src;

import helpers.ReservationDto;
import static helpers.ReservationFilter.all;
import helpers.SummaryGenerator;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ReservationGUI extends JFrame {

    private final ReservationSystem reservationSystem;
    private final SummaryGenerator summaryGenerator;
    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    public ReservationGUI() {
        this.reservationSystem = new ReservationSystem();
        this.summaryGenerator = new SummaryGenerator(reservationSystem);

        setTitle("Reservation System GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLookAndFeel();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        add(mainPanel);
        mainPanel.add(createMenuPanel(), "Menu");
        mainPanel.add(createBookTablePanel(), "BookTable");
        mainPanel.add(createCancelReservationPanel(), "CancelReservation");
        mainPanel.add(createCheckAvailabilityPanel(), "CheckAvailability");
        mainPanel.add(createViewDailySummaryPanel(), "ViewDailySummary");
        mainPanel.add(createViewDateRangeSummaryPanel(), "ViewDateRangeSummary");
        mainPanel.add(createAddTablePanel(), "AddTable");
        mainPanel.add(createViewReservationsPanel(), "ViewReservations");

        cardLayout.show(mainPanel, "Menu");
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton[] buttons = {
            new JButton("Book a table"),
            new JButton("Cancel a reservation"),
            new JButton("Check table availability"),
            new JButton("View daily summary"),
            new JButton("View date range summary"),
            new JButton("Add a table"),
            new JButton("View Reservations"),
            new JButton("Exit")
        };

        for (JButton button : buttons) {
            button.setBackground(new Color(100, 149, 237)); // Cornflower Blue
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setPreferredSize(new Dimension(200, 50));
            panel.add(button);
        }

        buttons[0].addActionListener(e -> cardLayout.show(mainPanel, "BookTable"));
        buttons[1].addActionListener(e -> cardLayout.show(mainPanel, "CancelReservation"));
        buttons[2].addActionListener(e -> cardLayout.show(mainPanel, "CheckAvailability"));
        buttons[3].addActionListener(e -> cardLayout.show(mainPanel, "ViewDailySummary"));
        buttons[4].addActionListener(e -> cardLayout.show(mainPanel, "ViewDateRangeSummary"));
        buttons[5].addActionListener(e -> cardLayout.show(mainPanel, "AddTable"));
        buttons[6].addActionListener(e -> cardLayout.show(mainPanel, "ViewReservations"));
        buttons[7].addActionListener(e -> System.exit(0));

        return panel;
    }

    private JPanel createBookTablePanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel[] labels = {
            new JLabel("Customer Name:"),
            new JLabel("Date (YYYY-MM-DD):"),
            new JLabel("Time (HH:MM):"),
            new JLabel("Number of People:")
        };

        JTextField[] fields = {
            new JTextField(),
            new JTextField(),
            new JTextField(),
            new JTextField()
        };

        for (JLabel label : labels) {
            label.setFont(new Font("Arial", Font.PLAIN, 14));
        }

        for (JTextField field : fields) {
            field.setFont(new Font("Arial", Font.PLAIN, 14));
        }

        JButton bookButton = new JButton("Book");
        JButton backButton = new JButton("Back");
        bookButton.setBackground(new Color(34, 139, 34)); // Forest Green
        backButton.setBackground(new Color(255, 69, 0)); // Red-Orange

        bookButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.WHITE);

        bookButton.addActionListener(e -> {
            String customerName = fields[0].getText();
            LocalDate date = LocalDate.parse(fields[1].getText());
            LocalTime time = LocalTime.parse(fields[2].getText());
            int numPeople = Integer.parseInt(fields[3].getText());

            String reservationId = reservationSystem.bookTable(customerName, date, time, numPeople);
            if (reservationId != null) {
                JOptionPane.showMessageDialog(this, "Reservation successful! Your reservation ID is: " + reservationId);
            } else {
                JOptionPane.showMessageDialog(this, "No available tables for the specified time and date.");
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        panel.add(labels[0]);
        panel.add(fields[0]);
        panel.add(labels[1]);
        panel.add(fields[1]);
        panel.add(labels[2]);
        panel.add(fields[2]);
        panel.add(labels[3]);
        panel.add(fields[3]);
        panel.add(bookButton);
        panel.add(backButton);

        return panel;
    }

    private JPanel createCancelReservationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
    
        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        inputPanel.setBackground(new Color(255, 255, 255));
    
        JLabel reservationIdLabel = new JLabel("Reservation ID:");
        JTextField reservationIdField = new JTextField();
        JButton cancelButton = new JButton("Cancel");
        JButton backButton = new JButton("Back");
    
        reservationIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        reservationIdField.setFont(new Font("Arial", Font.PLAIN, 14));
        cancelButton.setBackground(new Color(34, 139, 34));
        backButton.setBackground(new Color(255, 69, 0));
        cancelButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.WHITE);
    
        cancelButton.addActionListener(e -> {
            String reservationId = reservationIdField.getText();
            
            if (reservationId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a reservation ID.");
                return;
            }
    
            // Assuming reservationSystem has a method to cancel by reservation ID directly
            boolean success = reservationSystem.cancelReservationById(reservationId);
            
            if (success) {
                JOptionPane.showMessageDialog(this, "Reservation cancelled successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Reservation not found or cannot be cancelled.");
            }
        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
    
        inputPanel.add(reservationIdLabel);
        inputPanel.add(reservationIdField);
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cancelButton);
        buttonPanel.add(backButton);
    
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
    
        return panel;
    }
    

    private JPanel createCheckAvailabilityPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        JTextField dateField = new JTextField();
        JLabel timeLabel = new JLabel("Time (HH:MM):");
        JTextField timeField = new JTextField();
        JButton checkButton = new JButton("Check");
        JButton backButton = new JButton("Back");

        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dateField.setFont(new Font("Arial", Font.PLAIN, 14));
        timeField.setFont(new Font("Arial", Font.PLAIN, 14));
        checkButton.setBackground(new Color(100, 149, 237));
        backButton.setBackground(new Color(255, 69, 0));
        checkButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.WHITE);

        checkButton.addActionListener(e -> {
            LocalDate date = LocalDate.parse(dateField.getText());
            LocalTime time = LocalTime.parse(timeField.getText());

            List<Table> availableTables = reservationSystem.checkAvailability(date, time);
            if (!availableTables.isEmpty()) {
                StringBuilder sb = new StringBuilder("Available tables:\n");
                for (Table table : availableTables) {
                    sb.append("Table ID: ").append(table.getId()).append(", Capacity: ").append(table.getCapacity()).append("\n");
                }
                JOptionPane.showMessageDialog(this, sb.toString());
            } else {
                JOptionPane.showMessageDialog(this, "No available tables for the specified time and date.");
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(timeLabel);
        panel.add(timeField);
        panel.add(checkButton);
        panel.add(backButton);

        return panel;
    }

    private JPanel createViewDailySummaryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton viewButton = new JButton("View");
        JButton backButton = new JButton("Back");
        JTextArea textArea = new JTextArea();

        viewButton.setBackground(new Color(100, 149, 237));
        backButton.setBackground(new Color(255, 69, 0));
        viewButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.WHITE);

        viewButton.addActionListener(e -> {
            String summary = summaryGenerator.dailySummary(reservationSystem.viewReservations(all(), LocalDate.now()));
            textArea.setText(summary);
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        panel.add(viewButton, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createViewDateRangeSummaryPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        JTextField startDateField = new JTextField();
        JLabel endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
        JTextField endDateField = new JTextField();
        JButton viewButton = new JButton("View");
        JButton backButton = new JButton("Back");
        JTextArea textArea = new JTextArea();

        startDateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        endDateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        startDateField.setFont(new Font("Arial", Font.PLAIN, 14));
        endDateField.setFont(new Font("Arial", Font.PLAIN, 14));
        viewButton.setBackground(new Color(100, 149, 237));
        backButton.setBackground(new Color(255, 69, 0));
        viewButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.WHITE);

        viewButton.addActionListener(e -> {
            LocalDate startDate = LocalDate.parse(startDateField.getText());
            LocalDate endDate = LocalDate.parse(endDateField.getText());

            String summary = summaryGenerator.dateRangeSummary(startDate, endDate);
            textArea.setText(summary);
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        panel.add(startDateLabel);
        panel.add(startDateField);
        panel.add(endDateLabel);
        panel.add(endDateField);
        panel.add(viewButton);
        panel.add(backButton);
        panel.add(new JScrollPane(textArea));

        return panel;
    }

    private JPanel createAddTablePanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel idLabel = new JLabel("Table ID:");
        JTextField idField = new JTextField();
        JLabel capacityLabel = new JLabel("Table Capacity:");
        JTextField capacityField = new JTextField();
        JButton addButton = new JButton("Add");
        JButton backButton = new JButton("Back");

        idLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        capacityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        idField.setFont(new Font("Arial", Font.PLAIN, 14));
        capacityField.setFont(new Font("Arial", Font.PLAIN, 14));
        addButton.setBackground(new Color(34, 139, 34));
        backButton.setBackground(new Color(255, 69, 0));
        addButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.WHITE);

        addButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            int capacity = Integer.parseInt(capacityField.getText());

            reservationSystem.addTable(id, capacity);
            JOptionPane.showMessageDialog(this, "Table added successfully.");
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        panel.add(idLabel);
        panel.add(idField);
        panel.add(capacityLabel);
        panel.add(capacityField);
        panel.add(addButton);
        panel.add(backButton);

        return panel;
    }

    private JPanel createViewReservationsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        inputPanel.setBackground(new Color(255, 255, 255));

        JLabel customerNameLabel = new JLabel("Customer Name:");
        JTextField customerNameField = new JTextField();
        JLabel tableIdLabel = new JLabel("Table ID:");
        JTextField tableIdField = new JTextField();
        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        JTextField dateField = new JTextField();
        JButton viewButton = new JButton("View");
        JButton backButton = new JButton("Back");
        JTextArea textArea = new JTextArea(20, 50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        customerNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        tableIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        customerNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        tableIdField.setFont(new Font("Arial", Font.PLAIN, 14));
        dateField.setFont(new Font("Arial", Font.PLAIN, 14));
        viewButton.setBackground(new Color(100, 149, 237));
        backButton.setBackground(new Color(255, 69, 0));
        viewButton.setForeground(Color.WHITE);
        backButton.setForeground(Color.WHITE);

        viewButton.addActionListener(e -> {
            String customerName = customerNameField.getText();
            String tableIdStr = tableIdField.getText();
            String dateStr = dateField.getText();

            List<ReservationDto> reservations = null;

            if (!customerName.isEmpty() && tableIdStr.isEmpty() && dateStr.isEmpty()) {
                reservations = reservationSystem.viewReservations(r -> r.getCustomerName().equals(customerName));
            } else if (!customerName.isEmpty() && !tableIdStr.isEmpty() && dateStr.isEmpty()) {
                int tableId = Integer.parseInt(tableIdStr);
                Table table = reservationSystem.getTableById(tableId);
                reservations = reservationSystem.viewReservations(r -> r.getCustomerName().equals(customerName), table);
            } else if (!customerName.isEmpty() && !tableIdStr.isEmpty() && !dateStr.isEmpty()) {
                int tableId = Integer.parseInt(tableIdStr);
                Table table = reservationSystem.getTableById(tableId);
                LocalDate date = LocalDate.parse(dateStr);
                reservations = reservationSystem.viewReservations(r -> r.getCustomerName().equals(customerName), date, table);
            } else if (!customerName.isEmpty() && dateStr.isEmpty()) {
                reservations = reservationSystem.viewReservations(r -> r.getCustomerName().equals(customerName));
            } else if (!tableIdStr.isEmpty() && customerName.isEmpty() && dateStr.isEmpty()) {
                int tableId = Integer.parseInt(tableIdStr);
                Table table = reservationSystem.getTableById(tableId);
                reservations = reservationSystem.viewReservations(all(), table);
            } else if (!dateStr.isEmpty() && customerName.isEmpty() && tableIdStr.isEmpty()) {
                LocalDate date = LocalDate.parse(dateStr);
                reservations = reservationSystem.viewReservations(all(), date);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter valid search criteria.");
            }

            if (reservations != null && !reservations.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (ReservationDto reservation : reservations) {
                    sb.append(reservation.getDetails()).append("\n");
                }
                textArea.setText(sb.toString());
            } else {
                textArea.setText("No reservations found.");
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        inputPanel.add(customerNameLabel);
        inputPanel.add(customerNameField);
        inputPanel.add(tableIdLabel);
        inputPanel.add(tableIdField);
        inputPanel.add(dateLabel);
        inputPanel.add(dateField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewButton);
        buttonPanel.add(backButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(new JScrollPane(textArea), BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReservationGUI gui = new ReservationGUI();
            gui.setVisible(true);
        });
    }
}
