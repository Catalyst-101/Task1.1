import java.util.*;

class Room {
    int roomNumber;
    String category;
    boolean isBooked;

    Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isBooked = false;
    }
}

class Booking {
    String guestName;
    int roomNumber;
    String category;
    String paymentStatus;

    Booking(String guestName, int roomNumber, String category, String paymentStatus) {
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.category = category;
        this.paymentStatus = paymentStatus;
    }

    void display() {
        System.out.println("Guest: " + guestName + " | Room: " + roomNumber + " | Category: " + category + " | Payment: " + paymentStatus);
    }
}

public class HotelReservationSystem {
    static List<Room> rooms = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initRooms();

        while (true) {
            showMenu();
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    searchAvailableRooms();
                    break;
                case "2":
                    makeReservation();
                    break;
                case "3":
                    viewBookings();
                    break;
                case "4":
                    System.out.println("Exiting system. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static void showMenu() {
        System.out.println("\n=== Hotel Reservation System ===");
        System.out.println("1. Search Available Rooms");
        System.out.println("2. Make a Reservation");
        System.out.println("3. View Bookings");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    static void initRooms() {
        for (int i = 1; i <= 10; i++) {
            if (i <= 4)
                rooms.add(new Room(i, "Single"));
            else if (i <= 7)
                rooms.add(new Room(i, "Double"));
            else
                rooms.add(new Room(i, "Suite"));
        }
    }

    static void searchAvailableRooms() {
        System.out.print("Enter room category (Single, Double, Suite): ");
        String category = sc.nextLine();
        boolean found = false;

        System.out.println("\nAvailable " + category + " Rooms:");
        for (Room room : rooms) {
            if (room.category.equalsIgnoreCase(category) && !room.isBooked) {
                System.out.println("Room " + room.roomNumber);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available rooms in this category.");
        }
    }

    static void makeReservation() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter room category to book (Single, Double, Suite): ");
        String category = sc.nextLine();

        Room roomToBook = null;
        for (Room room : rooms) {
            if (room.category.equalsIgnoreCase(category) && !room.isBooked) {
                roomToBook = room;
                break;
            }
        }

        if (roomToBook == null) {
            System.out.println("No rooms available in this category.");
            return;
        }

        System.out.print("Proceed to payment (yes or no): ");
        String paymentInput = sc.nextLine();
        String paymentStatus = paymentInput.equalsIgnoreCase("yes") ? "Paid" : "Pending";

        roomToBook.isBooked = true;
        Booking booking = new Booking(name, roomToBook.roomNumber, roomToBook.category, paymentStatus);
        bookings.add(booking);

        System.out.println("Reservation successful! Room number: " + roomToBook.roomNumber);
    }

    static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }

        System.out.println("\n--- All Bookings ---");
        for (Booking b : bookings) {
            b.display();
        }
    }
}
