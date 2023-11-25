import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Reservation {
    private static int nextId = 1;

    private int id;
    private String guestName;
    private String reservationDate;
    private int numberOfGuests;

    public Reservation(String guestName, String reservationDate, int numberOfGuests) {
        this.id = nextId++;
        this.guestName = guestName;
        this.reservationDate = reservationDate;
        this.numberOfGuests = numberOfGuests;
    }

    public int getId() {
        return id;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }
}

public class Task1_OnlineReservationSystem extends Frame {

    private List<Reservation> reservations = new ArrayList<>();
    private TextArea outputTextArea;

    public Task1_OnlineReservationSystem() {
        setTitle("Online Reservation System");
        setSize(500, 250);
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FFA500")); // Orange color

        outputTextArea = new TextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setBackground(Color.white);
        outputTextArea.setForeground(Color.decode("#000080")); // Blue color
        add(outputTextArea, BorderLayout.CENTER);

        Panel panel = new Panel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.decode("#000080")); // Blue color

        Button makeReservationButton = new Button("Make a Reservation");
        Button viewReservationsButton = new Button("View All Reservations");
        Button cancelReservationButton = new Button("Cancel a Reservation");
        Button exitButton = new Button("Exit");

        makeReservationButton.setBackground(Color.decode("#FFA500")); // Orange color
        viewReservationsButton.setBackground(Color.decode("#FFA500")); // Orange color
        cancelReservationButton.setBackground(Color.decode("#FFA500")); // Orange color
        exitButton.setBackground(Color.decode("#FFA500")); // Orange color

        makeReservationButton.setForeground(Color.decode("#000080")); // Blue color
        viewReservationsButton.setForeground(Color.decode("#000080")); // Blue color
        cancelReservationButton.setForeground(Color.decode("#000080")); // Blue color
        exitButton.setForeground(Color.decode("#000080")); // Blue color

        makeReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReservationDialog();
            }
        });

        viewReservationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllReservations();
            }
        });

        cancelReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCancelDialog();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(makeReservationButton);
        panel.add(viewReservationsButton);
        panel.add(cancelReservationButton);
        panel.add(exitButton);

        add(panel, BorderLayout.SOUTH);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    private void showReservationDialog() {
        Frame frame = new Frame();
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(4, 2));
        frame.setBackground(Color.decode("#000080")); // Blue color

        Label nameLabel = new Label("Guest Name:");
        nameLabel.setForeground(Color.decode("#FFA500")); // Orange color
        TextField nameTextField = new TextField();

        Label dateLabel = new Label("Reservation Date:");
        dateLabel.setForeground(Color.decode("#FFA500")); // Orange color
        TextField dateTextField = new TextField();

        Label guestsLabel = new Label("Number of Guests:");
        guestsLabel.setForeground(Color.decode("#FFA500")); // Orange color
        TextField guestsTextField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setBackground(Color.decode("#FFA500")); // Orange color
        submitButton.setForeground(Color.decode("#000080")); // Blue color

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guestName = nameTextField.getText();
                String reservationDate = dateTextField.getText();
                int numberOfGuests = Integer.parseInt(guestsTextField.getText());

                Reservation reservation = new Reservation(guestName, reservationDate, numberOfGuests);
                reservations.add(reservation);
                outputTextArea.append("Reservation ID " + reservation.getId() + " created.\n");

                frame.dispose(); // Close the dialog
            }
        });

        frame.add(nameLabel);
        frame.add(nameTextField);
        frame.add(dateLabel);
        frame.add(dateTextField);
        frame.add(guestsLabel);
        frame.add(guestsTextField);
        frame.add(submitButton);

        frame.setVisible(true);
    }

    private void displayAllReservations() {
        outputTextArea.setText("All Reservations:\n");

        if (reservations.isEmpty()) {
            outputTextArea.append("No reservations found.\n");
        } else {
            for (Reservation reservation : reservations) {
                outputTextArea.append("Reservation ID: " + reservation.getId() + " | Guest Name: " + reservation.getGuestName() +
                        " | Reservation Date: " + reservation.getReservationDate() + " | Guests: " + reservation.getNumberOfGuests() + "\n");
            }
        }
    }

    private void showCancelDialog() {
        Frame frame = new Frame();
        frame.setSize(400, 100);
        frame.setLayout(new GridLayout(2, 1));
        frame.setBackground(Color.decode("#000080")); // Blue color

        Label idLabel = new Label("Reservation ID to cancel:");
        idLabel.setForeground(Color.decode("#FFA500")); // Orange color
        TextField idTextField = new TextField();

        Button cancelReservationButton = new Button("Cancel Reservation");
        cancelReservationButton.setBackground(Color.decode("#FFA500")); // Orange color
        cancelReservationButton.setForeground(Color.decode("#000080")); // Blue color

        cancelReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reservationId = Integer.parseInt(idTextField.getText());

                if (cancelReservation(reservationId)) {
                    outputTextArea.append("Reservation ID " + reservationId + " cancelled.\n");
                } else {
                    outputTextArea.append("Reservation ID " + reservationId + " not found.\n");
                }

                frame.dispose(); // Close the dialog
            }
        });

        frame.add(idLabel);
        frame.add(idTextField);
        frame.add(cancelReservationButton);

        frame.setVisible(true);
    }

    private boolean cancelReservation(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                reservations.remove(reservation);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Task1_OnlineReservationSystem reservationSystemAWT = new Task1_OnlineReservationSystem();
        reservationSystemAWT.setVisible(true);
    }
}
