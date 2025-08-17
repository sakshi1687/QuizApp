import java.util.*;
public class QuestionBank {
    public static List<Question> javaBasics() {
        List<Question> list = new ArrayList<>();
        list.add(new Question(
            "What are loops in Java?",
            Arrays.asList("Statements that repeat a block of code", "A data type", "A Java package", "Only used in GUI apps"),
            0,
            "Loops (for/while/do-while) repeat code until a condition fails."
        ));
        list.add(new Question(
            "Which loop is also called the enhanced for-loop?",
            Arrays.asList("for-each loop", "do-while loop", "labeled loop", "infinite loop"),
            0,
            "Enhanced for-loop (for-each) iterates directly over arrays/collections."
        ));
        list.add(new Question(
            "Which collection grows dynamically and allows indexed access?",
            Arrays.asList("ArrayList", "Array (fixed size)", "Enum", "Thread"),
            0,
            "ArrayList is a resizable array implementation in java.util."
        ));
        list.add(new Question(
            "What interface does HashMap implement?",
            Arrays.asList("Map", "List", "Set", "Queue"),
            0,
            "HashMap implements the Map interface (key–value pairs)."
        ));
        list.add(new Question(
            "How do you iterate a List using an Iterator?",
            Arrays.asList("while(it.hasNext()) { it.next(); }", "for(int i=0;i<map.size();i++)", "map.get(key)", "Thread.run()"),
            0,
            "Iterator provides hasNext()/next() for safe traversal."
        ));
        list.add(new Question(
            "What is the difference between switch-case and if-else?",
            Arrays.asList(
                "switch compares a single expression to constants; if-else evaluates boolean conditions",
                "No difference",
                "if-else is only for numbers",
                "switch works for all objects and ranges"
            ),
            0,
            "switch matches one value against case labels; if-else handles arbitrary boolean logic."
        ));
        list.add(new Question(
            "Which method shuffles elements in a List?",
            Arrays.asList("Collections.shuffle(list)", "Collections.rotate(list)", "Collections.fill(list, x)", "Arrays.sort(arr)"),
            0,
            "Use Collections.shuffle to randomize order."
        ));
        list.add(new Question(
            "Which method sorts a List in natural order?",
            Arrays.asList("Collections.sort(list)", "Collections.reverse(list)", "list.shuffle()", "list.sorted()"),
            0,
            "Collections.sort(list) sorts in natural order (or with a Comparator)."
        ));
        list.add(new Question(
            "Which Map does NOT maintain any ordering of keys?",
            Arrays.asList("HashMap", "LinkedHashMap", "TreeMap", "SortedMap"),
            0,
            "HashMap provides no ordering guarantees."
        ));
        list.add(new Question(
            "Choose the correct syntax for enhanced for-loop over a List<String> names:",
            Arrays.asList(
                "for (String n : names) { ... }",
                "for (names : String n) { ... }",
                "foreach (String n in names) { ... }",
                "loop (String n : names) { ... }"
            ),
            0,
            "Use `for (Type var : collection)`."
        ));
        list.add(new Question(
            "Which statement safely reads multiple user inputs from console?",
            Arrays.asList(
                "Use Scanner and validate with hasNextXxx() before nextXxx()",
                "Use System.console().readPassword only",
                "Use Thread.sleep",
                "Use Random"
            ),
            0,
            "Scanner offers methods like hasNextInt()/nextInt() for validation."
        ));
        list.add(new Question(
            "Pick the correct description of ArrayList:",
            Arrays.asList(
                "Resizable array; allows duplicates; maintains insertion order",
                "Fixed size; disallows nulls",
                "Unique elements only",
                "Stores key–value pairs"
            ),
            0,
            "ArrayList is a dynamic array that preserves insertion order and allows duplicates."
        ));
        return list;
    }
}