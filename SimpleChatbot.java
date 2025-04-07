import java.util.*;

public class SimpleChatbot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ChatBot: Hello, I am your assistant. You can start chatting with me!");
        System.out.println("Type 'exit' to end the chat.\n");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().toLowerCase().trim();

            if (userInput.equals("exit")) {
                System.out.println("ChatBot: Goodbye. Have a nice day!");
                break;
            }

            String response = getResponse(userInput);
            System.out.println("ChatBot: " + response);
        }

        scanner.close();
    }

    public static String getResponse(String input) {
        if (input.contains("hi") || input.contains("hello")) {
            return "Hello! How can I help you today?";
        } else if (input.contains("how are you")) {
            return "I'm just a program, but I'm doing great!";
        } else if (input.contains("your name")) {
            return "I am a simple Java ChatBot.";
        } else if (input.contains("what can you do")) {
            return "I can talk with you and answer some simple questions.";
        } else if (input.contains("bye")) {
            return "Goodbye! It was nice talking to you.";
        } else if (input.contains("thank")) {
            return "You're welcome!";
        } else if (input.contains("help")) {
            return "You can ask me questions like 'how are you', 'what is your name', or just say hi!";
        } else {
            return "I'm not sure how to respond to that. Can you ask something else?";
        }
    }
}
