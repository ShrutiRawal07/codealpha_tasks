import java.util.*;

class Room {
    int roomNumber;
    String category;
    boolean isAvailable;

    Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
    }
}

class Booking {
    String customerName;
    Room room;
    boolean paymentConfirmed;

    Booking(String customerName, Room room) {
        this.customerName = customerName;
        this.room = room;
        this.paymentConfirmed = false;
    }
}

public class HotelReservationSystem {
    private static final List<Room> rooms = new ArrayList<>();
    private static final List<Booking> bookings = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms();

        int choice;
        do {
            System.out.println("\n=== Hotel Reservation System ===");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> showAvailableRooms();
                case 2 -> makeReservation();
                case 3 -> viewBookings();
                case 4 -> System.out.println("Thank you for using the system!");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 4);
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Deluxe"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(103, "Suite"));
        rooms.add(new Room(104, "Standard"));
        rooms.add(new Room(105, "Deluxe"));
    }

    private static void showAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.printf("Room %d - %s\n", room.roomNumber, room.category);
            }
        }
    }

    private static void makeReservation() {
        showAvailableRooms();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter room number to reserve: ");
        int roomNum = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNum && room.isAvailable) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room not available or does not exist.");
            return;
        }

        Booking booking = new Booking(name, selectedRoom);
        bookings.add(booking);
        selectedRoom.isAvailable = false;

        // Simulate payment
        System.out.print("Enter payment amount to confirm booking: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (amount > 0) {
            booking.paymentConfirmed = true;
            System.out.println("Payment successful! Booking confirmed.");
        } else {
            System.out.println("Payment failed. Booking is not confirmed.");
        }
    }

    private static void viewBookings() {
        System.out.println("\n--- Bookings ---");
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (Booking b : bookings) {
                System.out.printf("Customer: %s | Room: %d (%s) | Payment: %s\n",
                        b.customerName,
                        b.room.roomNumber,
                        b.room.category,
                        b.paymentConfirmed ? "Confirmed" : "Pending");
            }
        }
    }
}
