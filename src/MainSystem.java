import java.util.Scanner;

public class MainSystem {
    private Queue<String> inboxQueueA;
    private Queue<String> outboxQueueA;
    private Stack<String> processingStackA;
    private Queue<String> inboxQueueB;
    private Queue<String> outboxQueueB;
    private Stack<String> processingStackB;
    private MainSystem connectedToA; // B
    private MainSystem connectedToB; // A

    public MainSystem() {
        this.inboxQueueA = new Queue<>();
        this.outboxQueueA = new Queue<>();
        this.processingStackA = new Stack<>();
        this.inboxQueueB = new Queue<>();
        this.outboxQueueB = new Queue<>();
        this.processingStackB = new Stack<>();
        this.connectedToA = null;
        this.connectedToB = null;
    }

    public void connectToA(MainSystem systemA) { // B
        if (connectedToA == null) {
            connectedToA = systemA;
            System.out.println("System B Connected to System A.");
            systemA.connectToB(this);
        }
    }

    public void connectToB(MainSystem systemB) { // A
        if (connectedToB == null) {
            connectedToB = systemB;
            System.out.println("System A Connected to System B.");
            systemB.connectToA(this);
        }
    }

    public void checkConnect(MainSystem systemA, MainSystem systemB) {
        if (systemB.connectedToA == systemA && systemA.connectedToB == systemB) {
            System.out.println("System A is connected to System B");
            System.out.println("System B is connected to System A");
        } else {
            System.out.println("Systems are not connected.");
        }
    }
    public void disconnectFromA(MainSystem systemA) { // B
        if (connectedToA != null) {
            connectedToA = null;
            System.out.println("Disconnected from System A.");
            systemA.disconnectFromB(this);
        }
    }

    public void disconnectFromB(MainSystem systemB) { // A
        if (connectedToB != null) {
            connectedToB = null;
            System.out.println("Disconnected from System B.");
            systemB.disconnectFromA(this);
        }
    }

        public void sendMessageToSystemB(String message) {
            long startTime = System.nanoTime();
        if (message.isEmpty()) {
            System.out.println("Error: Empty message. Please enter a valid message.");
            return;
        }
        if (message.length() > 10) {
            System.out.println("Message length exceeds 10 characters. Truncating...");
            // Truncate the message and send smaller messages
            int startIndex = 0;
            while (startIndex < message.length()) {
                int endIndex = Math.min(startIndex + 10, message.length());
                String truncatedMessage = message.substring(startIndex, endIndex);
                outboxQueueA.offer(truncatedMessage);
                System.out.println("Sending message: " + truncatedMessage);
                startIndex = endIndex;
            }
        } else {
            // Send the message
            outboxQueueA.offer(message);
            System.out.println("Sending message: " + message);
        }
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            System.out.println("Time spent: " + elapsedTime + " nanoseconds");
    }

    public void sendMessageToSystemA(String message) {
        long startTime = System.nanoTime();
        if (message.length() == 0) {
            System.out.println("Error: Empty message. Please enter a valid message.");
            return;
        }
        if (message.length() > 10) {
            System.out.println("Message length exceeds 10 characters. Truncating...");
            // Truncate the message and send smaller messages
            int startIndex = 0;
            while (startIndex < message.length()) {
                int endIndex = Math.min(startIndex + 10, message.length());
                String truncatedMessage = message.substring(startIndex, endIndex);
                outboxQueueB.offer(truncatedMessage);
                System.out.println("Sending message: " + truncatedMessage);
                startIndex = endIndex;
            }
        } else {
            // Send the message
            outboxQueueB.offer(message);
            System.out.println("Sending message: " + message);
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Time spent: " + elapsedTime + " nanoseconds");
    }

    public void receiveMessageFromB(MainSystem systemB) {
        long startTime = System.nanoTime();
        if (connectedToB != null) {
            if (!connectedToB.outboxQueueB.isEmpty()) {
                while (!connectedToB.outboxQueueB.isEmpty()) {
                    inboxQueueA.offer(connectedToB.outboxQueueB.poll());
                }
                System.out.println("Received messages from System B and stored in System A inbox: " + inboxQueueA);
            } else {
                System.out.println("System B outbox is empty. No messages to receive.");
            }
        } else {
            System.out.println("Error: System A is not connected to System B.");
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Time spent: " + elapsedTime + " nanoseconds");
    }

    public void receiveMessageFromA(MainSystem systemA) {
        long startTime = System.nanoTime();
        if (connectedToA != null) {
            if (!connectedToA.outboxQueueA.isEmpty()) {
                while (!connectedToA.outboxQueueA.isEmpty()) {
                    inboxQueueB.offer(connectedToA.outboxQueueA.poll());
                }
                System.out.println("Received messages from System A and stored in System B inbox: " + inboxQueueB);
            } else {
                System.out.println("System A outbox is empty. No messages to receive.");
            }
        } else {
            System.out.println("Error: System B is not connected to System A.");
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Time spent: " + elapsedTime + " nanoseconds");
    }

    public void checkInbox(MainSystem systemA, MainSystem systemB) {
        System.out.println("Incoming Messages (SystemA): ");
        processIncomingMessages(systemA.inboxQueueA, systemA.processingStackA);
        readIncomingMessages(systemA.processingStackA);

        System.out.println("Incoming Messages (SystemB): ");
        processIncomingMessages(systemB.inboxQueueB, systemB.processingStackB);
        readIncomingMessages(systemB.processingStackB);
    }

    private void processIncomingMessages(Queue<String> inboxQueue, Stack<String> processingStack) {
        while (!inboxQueue.isEmpty()) {
            processingStack.push(inboxQueue.poll());
        }
    }

    private void readIncomingMessages(Stack<String> processingStack) {
        long startTime = System.nanoTime();
        while (!processingStack.isEmpty()) {
            System.out.println(processingStack.pop());
        }
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Time spent: " + elapsedTime + " nanoseconds");
    }
}