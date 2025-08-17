import java.util.*;
public class QuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printHeader();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Player";
        while (true) {
            int choice = menu(scanner);
            switch (choice) {
                case 1:
                    startJavaBasicsQuiz(scanner, name);
                    break;
                case 2:
                    showLeaderboard();
                    break;
                case 3:
                    System.out.println("Thanks for playing. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    private static void printHeader() {
        System.out.println("=======================================");
        System.out.println("        Online Quiz App (Console)      ");
        System.out.println("=======================================");
    }
    private static int menu(Scanner scanner) {
        System.out.println("\nMain Menu");
        System.out.println("1) Start Quiz: Java Basics");
        System.out.println("2) View Leaderboard (Top Scores)");
        System.out.println("3) Exit");
        System.out.print("Select an option: ");
        String in = scanner.nextLine().trim();
        try { return Integer.parseInt(in); } catch (NumberFormatException e) { return -1; }
    }
    private static void startJavaBasicsQuiz(Scanner scanner, String playerName) {
        List<Question> bank = QuestionBank.javaBasics();
        int max = bank.size();
        int count = askCount(scanner, 5, max);
        Collections.shuffle(bank);
        List<Question> subset = bank.subList(0, count);
        Quiz quiz = new Quiz(subset, scanner);
        quiz.run();
        quiz.showResults();
        System.out.print("Save your score to leaderboard? (Y/N): ");
        String yn = scanner.nextLine().trim().toUpperCase();
        if (yn.startsWith("Y")) {
            HighScoreStore.save(playerName, quiz.getCorrectCount(), quiz.getTotal());
            System.out.println("✅ Score saved!");
        }
    }
    private static int askCount(Scanner scanner, int min, int max) {
        while (true) {
            System.out.print("How many questions do you want? (" + min + "-" + max + "): ");
            String in = scanner.nextLine().trim();
            try {
                int n = Integer.parseInt(in);
                if (n >= min && n <= max) return n;
            } catch (NumberFormatException ignored) {}
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }
    private static void showLeaderboard() {
        List<String[]> top = HighScoreStore.top(5);
        if (top.isEmpty()) {
            System.out.println("\nNo scores saved yet. Play a quiz first!");
            return;
        }
        System.out.println("\n--- Leaderboard (Top 5 by % score) ---");
        int rank = 1;
        for (String[] row : top) {
            String player = row[0];
            int score = Integer.parseInt(row[1]);
            int total = Integer.parseInt(row[2]);
            double pct = total == 0 ? 0.0 : (score * 100.0 / total);
            String time = row[3].replace('T', ' ');
            System.out.printf("%d) %s - %d/%d (%.2f%%) at %s\n", rank++, player, score, total, pct, time);
        }
    }
}