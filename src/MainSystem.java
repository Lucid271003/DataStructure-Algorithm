import java.util.Scanner;

public class MainSystem {
    private Queue<String> inboxQueue;
    private Queue<String> outboxQueue;
    private Stack<String> processingStack;
    private MainSystem connected;

    public MainSystem() {
        this.inboxQueue = new Queue<>();
        this.outboxQueue = new Queue<>();
        this.processingStack = new Stack<>();
        this.connected = null;
    }

    public void connect(MainSystem system) {
        if (connected == null && system.connected == null) {
            connected = system;
            system.connected = this;
            System.out.println("Both systems are connected successful.");
        } else {
            System.out.println("Both systems are already connected.");
        }
    }

    public void checkConnect(MainSystem system) {
        if (connected != null && system.connected != null) {
            System.out.println("Both systems is connected.");
        } else {
            System.out.println("Both systems not connected.");
        }
    }

    public void disconnect(MainSystem system) {
        if (connected != null && system.connected != null) {
            connected = null;
            system.connected = null;
            System.out.println("Disconnected both systems.");
        } else {
            System.out.println("Both systems are not connected.");
        }
    }

    public void sendMessage(String message) {
        long startTime = System.nanoTime();
        try {
            if (message.length() == 0) {
                throw new NullPointerException("Empty message. Please enter a valid message.");
            }
            if (message.length() > 250) {
                System.out.println("Message length exceeds 250 characters. Truncating...");
                // Truncate the message and send smaller messages
                int startIndex = 0;
                while (startIndex < message.length()) {
                    int endIndex = Math.min(startIndex + 250, message.length());
                    String truncatedMessage = message.substring(startIndex, endIndex);
                    outboxQueue.offer(truncatedMessage);
                    System.out.println("Sending message: " + truncatedMessage);
                    startIndex = endIndex;
                }
            } else {
                // Send the message
                outboxQueue.offer(message);
                System.out.println("Sending message: " + message);
            }
        }catch(NullPointerException e){
                System.out.println("NullPointerException: " + e.getMessage());
        } catch(Exception e){
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Time spent: " + elapsedTime + " nanoseconds");
    }

    public void receiveMessage(MainSystem system) {
        long startTime = System.nanoTime();
        try {
            if (connected != null && system.connected != null) {
                if (!system.outboxQueue.isEmpty()) {
                    while (!system.outboxQueue.isEmpty()) {
                        inboxQueue.offer(system.outboxQueue.poll());
                    }
                    System.out.println("Received messages from connected system and stored in inbox: \n" + inboxQueue);
                } else {
                    System.out.println("Outbox connected system is empty. No messages to receive.");
                }
            } else {
                System.out.println("Error: Both systems are not connected.");
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Time spent: " + elapsedTime + " nanoseconds");
    }

    public void checkInbox() {
        System.out.println("Messages: ");
        processIncomingMessages();
        readIncomingMessages();
    }

    private void processIncomingMessages() {
        while (!inboxQueue.isEmpty()) {
            processingStack.push(inboxQueue.poll());
        }
    }

    private void readIncomingMessages() {
        long startTime = System.nanoTime();
        while (!processingStack.isEmpty()) {
            System.out.println(processingStack.pop());
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Time spent: " + elapsedTime + " nanoseconds");
    }
}