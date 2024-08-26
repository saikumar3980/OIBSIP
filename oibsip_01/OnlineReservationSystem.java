import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class OnlineReservationSystem {
    private final Map<String, String> users;
    private final Map<String, String> reservations;
    private int nextPNR;

    public OnlineReservationSystem() {
        users = new HashMap<>();
        reservations = new HashMap<>();
        nextPNR = 1001;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("--------------- ONLINE RESERVATION SYSTEM ---------------");
            System.out.println("Please Select One Option...\n");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("------------------------------------------------");
            System.out.print("Enter your choice: ");

            int choice = getValidIntInput(sc);
            sc.nextLine(); 

            switch (choice) {
                case 1 -> register(sc);
                case 2 -> login(sc);
                case 3 -> {
                    System.out.println("------------------------------------------------");
                    System.out.println("\nExiting...");
                    System.out.println("\nThank You! Please Visit Again...");
                    System.out.println("\n------------------------------------------------");
                    sc.close();
                    return;
                }
                default -> {
                    System.out.println("------------------------------------------------");
                    System.out.println("\nInvalid choice. Please try again...");
                }
            }

            System.out.println();
        }
    }

    private void register(Scanner sc) {
        System.out.println("------------------------------------------------");
        System.out.println("--------------- REGISTRATION PAGE --------------");
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        if (users.containsKey(username)) {
            System.out.println("\nUsername already exists... Try again...");
            return;
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();
        users.put(username, password);
        System.out.println("\nRegistration successful... You can now log in...");
    }

    private void login(Scanner sc) {
        System.out.println("------------------------------------------------");
        System.out.println("--------------- LOGIN FORM ---------------");
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("\nLogin successful...");
            reservationMenu(sc, username);
        } else {
            System.out.println("\nInvalid username or password...");
        }
    }

    private void reservationMenu(Scanner sc, String username) {
        while (true) {
            System.out.println("------------------------------------------------");
            System.out.println("--------------- RESERVATION MENU ---------------");
            System.out.println("------------------------------------------------");
            System.out.println("Please Select One Option...\n");
            System.out.println("1. Reservation Form");
            System.out.println("2. Cancellation Form");
            System.out.println("3. Logout");
            System.out.println("------------------------------------------------");
            System.out.print("Enter your choice: ");

            int choice = getValidIntInput(sc);
            sc.nextLine();  

            switch (choice) {
                case 1 -> reservationForm(sc, username);
                case 2 -> cancellationForm(sc);
                case 3 -> {
                    System.out.println("------------------------------------------------");
                    System.out.println("\nLogging out...");
                    System.out.println("\n------------------------------------------------");
                    return;
                }
                default -> {
                    System.out.println("------------------------------------------------");
                    System.out.println("\nInvalid choice. Please try again...");
                }
            }

            System.out.println();
        }
    }

    private void reservationForm(Scanner sc, String username) {
        System.out.println("------------------------------------------------");
        System.out.println("--------------- RESERVATION FORM ---------------");
        System.out.print("Enter your basic details by the user "+username+":");
        String basicDetails = sc.nextLine();
        System.out.print("Enter train number: ");
        String trainNumber = sc.nextLine();
        System.out.print("Enter class type: ");
        String classType = sc.nextLine();
        System.out.print("Enter date of journey (DD-MM-YYYY): ");
        String dateOfJourney = sc.nextLine();
        
        if (!isValidDate(dateOfJourney)) {
            System.out.println("Invalid date format. Please use DD-MM-YYYY.");
            return;
        }

        System.out.print("Enter from (place): ");
        String fromPlace = sc.nextLine();
        System.out.print("Enter destination: ");
        String destination = sc.nextLine();

        String reservationDetails = "Basic Details: " + basicDetails + ", Train Number: " + trainNumber +
                                    ", Class Type: " + classType + ", Date of Journey: " + dateOfJourney +
                                    ", From: " + fromPlace + ", To: " + destination;

        String pnrNumber = generatePNR();
        reservations.put(pnrNumber, reservationDetails);
        System.out.println("\nReservation created successfully. Your PNR number is: " + pnrNumber);
    }

    private void cancellationForm(Scanner sc) {
        System.out.println("------------------------------------------------");
        System.out.println("--------------- CANCELLATION FORM ---------------");
        System.out.print("Enter your PNR number to cancel the reservation: ");
        String pnrNumber = sc.nextLine();

        if (reservations.containsKey(pnrNumber)) {
            System.out.println("Reservation Details: " + reservations.get(pnrNumber));
            System.out.print("Do you want to cancel this reservation? (Y/N): ");
            String confirmation = sc.nextLine();

            if (confirmation.equalsIgnoreCase("Y")) {
                reservations.remove(pnrNumber);
                System.out.println("\nReservation with PNR " + pnrNumber + " cancelled successfully...");
            } else {
                System.out.println("\nReservation not cancelled...");
            }
        } else {
            System.out.println("\nNo reservation found with PNR " + pnrNumber);
        }
    }

    private String generatePNR() {
        return "PNR" + nextPNR++;
    }

    private int getValidIntInput(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            sc.next();
        }
        return sc.nextInt();
    }

    private boolean isValidDate(String date) {
        String datePattern = "\\d{2}-\\d{2}-\\d{4}";
        return Pattern.matches(datePattern, date);
    }

    public static void main(String[] args) {
        OnlineReservationSystem system = new OnlineReservationSystem();
        system.run();
}
}
