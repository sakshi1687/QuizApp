import java.util.*;
public class Question {
    private final String text;
    private final List<String> options; // size >= 2
    private final int correctIndex;     // 0-based index into options
    private final String explanation;
    public Question(String text, List<String> options, int correctIndex, String explanation) {
        if (options == null || options.size() < 2) throw new IllegalArgumentException("Need at least 2 options");
        if (correctIndex < 0 || correctIndex >= options.size()) throw new IllegalArgumentException("Invalid correct index");
        this.text = text;
        this.options = new ArrayList<>(options);
        this.correctIndex = correctIndex;
        this.explanation = explanation == null ? "" : explanation.trim();
    }
    public String getText() { return text; }
    public List<String> getOptions() { return new ArrayList<>(options); }
    public int getCorrectIndex() { return correctIndex; }
    public String getExplanation() { return explanation; }
}