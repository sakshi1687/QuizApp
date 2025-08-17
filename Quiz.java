import java.util.*;
public class Quiz {
    private final List<Question> questions;
    private final Scanner scanner;
    private final Map<Integer, Integer> userAnswers = new HashMap<>(); // qIndex -> chosenOptionIndex (after shuffle)
    private int correctCount = 0;
    public Quiz(List<Question> questions, Scanner scanner) {
        this.questions = new ArrayList<>(questions);
        this.scanner = scanner;
    }
    public void run() {
        Collections.shuffle(questions);
        correctCount = 0;
        userAnswers.clear();
        System.out.println("\n--- Quiz Started ---");
        for (int i = 0; i < questions.size(); i++) {
            askQuestion(i, questions.get(i));
        }
        System.out.println("\n--- Quiz Finished ---");
    }
    private void askQuestion(int qNumber, Question q) {
        List<String> options = q.getOptions();
        List<Integer> idx = new ArrayList<>();
        for (int i = 0; i < options.size(); i++) idx.add(i);
        Collections.shuffle(idx);
        int newCorrectPos = -1;
        List<String> shuffled = new ArrayList<>();
        for (int pos = 0; pos < idx.size(); pos++) {
            int originalIndex = idx.get(pos);
            shuffled.add(options.get(originalIndex));
            if (originalIndex == q.getCorrectIndex()) newCorrectPos = pos;
        }
        System.out.println("\nQ" + (qNumber + 1) + ": " + q.getText());
        char label = 'A';
        for (int i = 0; i < shuffled.size(); i++) {
            System.out.println("  " + (char)(label + i) + ") " + shuffled.get(i));
        }
        int choice = readChoice(shuffled.size());
        userAnswers.put(qNumber, choice);
        if (choice == newCorrectPos) {
            correctCount++;
            System.out.println(" Correct!");
        } else {
            System.out.println(" Incorrect.");
        }
    }
    private int readChoice(int optionCount) {
        while (true) {
            System.out.print("Your answer (A-" + (char)('A' + optionCount - 1) + " or 1-" + optionCount + "): ");
            String in = scanner.nextLine().trim().toUpperCase();
            if (in.isEmpty()) continue;
            if (in.length() == 1 && in.charAt(0) >= 'A' && in.charAt(0) < ('A' + optionCount)) {
                return in.charAt(0) - 'A';
            }
            try {
                int num = Integer.parseInt(in);
                if (num >= 1 && num <= optionCount) return num - 1;
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid choice. Please try again.");
        }
    }
    public void showResults() {
        int total = questions.size();
        double pct = total == 0 ? 0.0 : (correctCount * 100.0 / total);
        String grade = (pct >= 90) ? "A+" : (pct >= 80) ? "A" : (pct >= 70) ? "B" : (pct >= 60) ? "C" : "D";
        System.out.printf("\nScore: %d/%d (%.2f%%) | Grade: %s\n", correctCount, total, pct, grade);
        System.out.println("\n--- Review ---");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            List<String> options = q.getOptions();
            int correct = q.getCorrectIndex();
            Integer chosen = userAnswers.get(i);
            System.out.println("Q" + (i + 1) + ": " + q.getText());
            for (int j = 0; j < options.size(); j++) {
                String mark = (j == correct) ? " (✔ correct)" : "";
                System.out.println("   - " + options.get(j) + mark);
            }
            if (chosen == null) {
                System.out.println("   Your answer: (no answer)");
            } else {
                System.out.println("   Your answer: " + options.get(chosen));
            }
            if (!q.getExplanation().isEmpty()) {
                System.out.println("   Why? " + q.getExplanation());
            }
            System.out.println();
        }
    }
    public int getCorrectCount() { return correctCount; }
    public int getTotal() { return questions.size(); }
}