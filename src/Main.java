import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MainSystem systemA = new MainSystem();
        MainSystem systemB = new MainSystem();

        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Choose system to send message");
            System.out.println("2. Connect systems");
            System.out.println("3. Check Connect");
            System.out.println("4. Receive message from connected system");
            System.out.println("5. Check Inbox");
            System.out.println("6. Disconnect systems");
            System.out.println("7. Exit program");

            while (true) {
                System.out.print("Input your choice: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    break; // Break the loop if a valid integer is entered
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine(); // Clear the input buffer
                }
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Choosing system to send message...");
                        System.out.println("Choose system to send message:");
                        System.out.println("1. System A");
                        System.out.println("2. System B");

                        int systemChoice;
                        while (true) {
                            System.out.print("Enter a system to send: ");
                            if (scanner.hasNextInt()) {
                                systemChoice = scanner.nextInt();
                                scanner.nextLine(); // consume newline

                                if (systemChoice >= 1 && systemChoice <= 2) {
                                    break; // Lựa chọn hợp lệ, thoát khỏi vòng lặp
                                } else {
                                    System.out.println("Invalid system choice. Please choose 1 or 2.");
                                }
                            } else {
                                System.out.println("Invalid input. Please enter a valid number.");
                                scanner.nextLine(); // Clear the input buffer
                            }
                        }

                        if (systemChoice == 1 || systemChoice == 2) {
                            int numMessages;
                            while (true) {
                                System.out.print("Enter number of messages to send: ");
                                String input = scanner.nextLine();
                                try {
                                    numMessages = Integer.parseInt(input);
                                    if (numMessages > 0) {
                                        break;
                                    } else {
                                        System.out.println("Invalid input. Please enter a valid positive number.");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input. Please enter a valid number.");
                                }
                            }

                            for (int i = 0; i < numMessages; i++) {
                                System.out.print("Enter message(s): ");
                                String message = scanner.nextLine();

                                if (systemChoice == 1) {
                                    systemA.sendMessage(message);
                                } else {
                                    systemB.sendMessage(message);
                                }
                            }
//                            System.out.println("Successfully sent " + numMessages + " message(s).");
                        }
                    } catch (Exception e) {
                        System.out.println("An unexpected error occurred: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Connecting systems...");
                    systemA.connect(systemB);
                    break;
                case 3:
                    System.out.println("Check Connect...");
                    systemA.checkConnect(systemB);
                    break;
                case 4:
                    System.out.println("Receiving message from connected system...");
                    System.out.println("Choose system to receive message:");
                    System.out.println("1. System A");
                    System.out.println("2. System B");

                    int systemChoiceMessage;
                    while (true) {
                        System.out.print("Enter a system to receive: ");
                        if (scanner.hasNextInt()) {
                            systemChoiceMessage = scanner.nextInt();
                            scanner.nextLine(); // consume newline

                            if (systemChoiceMessage >= 1 && systemChoiceMessage <= 2) {
                                break;
                            } else {
                                System.out.println("Invalid system choice. Please choose 1 or 2.");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a valid number.");
                            scanner.nextLine(); // Clear the input buffer
                        }
                    }

                    if (systemChoiceMessage == 1) {
                        systemA.receiveMessage(systemB);
                    } else {
                        systemB.receiveMessage(systemA);
                    }
                    break;
                case 5:
                    System.out.println("Checking Inbox...");
                    System.out.println("Choose system to check inbox:");
                    System.out.println("1. System A");
                    System.out.println("2. System B");

                    int systemChoiceInbox;
                    while (true) {
                        System.out.print("Enter a system to check inbox: ");
                        if (scanner.hasNextInt()) {
                            systemChoiceInbox = scanner.nextInt();
                            scanner.nextLine(); // consume newline

                            if (systemChoiceInbox >= 1 && systemChoiceInbox <= 2) {
                                break; // Lựa chọn hợp lệ, thoát khỏi vòng lặp
                            } else {
                                System.out.println("Invalid system choice. Please choose 1 or 2.");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a valid number.");
                            scanner.nextLine(); // Clear the input buffer
                        }
                    }

                    if (systemChoiceInbox == 1) {
                        systemA.checkInbox();
                    } else {
                        systemB.checkInbox();
                    }
                    break;
                case 6:
                    System.out.println("Disconnecting systems...");
                    systemA.disconnect(systemB);
                    break;
                case 7:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
                    break;
            }
        } while (choice != 7);
    }
}
