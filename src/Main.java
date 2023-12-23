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

            System.out.print("Input your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Choosing system to send message...");
                    System.out.println("Choose system to send message:");
                    System.out.println("1. System A");
                    System.out.println("2. System B");
                    int systemChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    System.out.println("Enter number of messages to send:");
                    int numMessages = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    for (int i = 0; i < numMessages; i++) {
                        System.out.println("Enter message(s):");
                        String message = scanner.nextLine();

                        if (systemChoice == 1) {
                            systemA.sendMessageToSystemB(message);
                        } else if (systemChoice == 2) {
                            systemB.sendMessageToSystemA(message);
                        } else {
                            System.out.println("Invalid system choice.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Connecting systems...");
                    systemA.connectToB(systemB);
                    break;
                case 3:
                    System.out.println("Check Connect...");
                    systemA.checkConnect(systemA, systemB);
                    break;
                case 4:
                    System.out.println("Receiving message from connected system...");
                    System.out.println("Choose system to receive message:");
                    System.out.println("1. System A");
                    System.out.println("2. System B");

                    int systemChoiceMessage = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    if (systemChoiceMessage == 1) {
                        systemA.receiveMessageFromB(systemB);
                    } else if (systemChoiceMessage == 2) {
                        systemB.receiveMessageFromA(systemA);
                    } else {
                        System.out.println("Invalid system choice.");
                    }
                    break;
                case 5:
                    systemA.checkInbox(systemA, systemB);
                    break;
                case 6:
                    System.out.println("Disconnecting systems...");
                    systemA.disconnectFromB(systemB);
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
