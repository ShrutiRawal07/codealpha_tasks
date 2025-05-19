import java.util.*;

public class SimpleChatbot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🤖 Chatbot: Hello! I'm your virtual assistant. Type 'bye' to exit.");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().toLowerCase();

            if (userInput.contains("bye")) {
                System.out.println("🤖 Chatbot: Goodbye!");
                break;
            } else if (userInput.contains("hello") || userInput.contains("hi")) {
                System.out.println("🤖 Chatbot: Hello there! How can I help you?");
            } else if (userInput.contains("how are you")) {
                System.out.println("🤖 Chatbot: I'm doing great! Thanks for asking.");
            } else if (userInput.contains("your name")) {
                System.out.println("🤖 Chatbot: I'm ChatBot 1.0. Your virtual helper.");
            } else if (userInput.contains("help")) {
                System.out.println("🤖 Chatbot: You can ask me about the weather, your schedule, or just chat!");
            } else {
                System.out.println("🤖 Chatbot: I'm not sure I understand. Can you rephrase?");
            }
        }

        scanner.close();
    }
}
