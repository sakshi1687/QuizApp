
import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class HighScoreStore {
    private static final String FILE_NAME = "scores.csv";
    public static void save(String playerName, int score, int total) {
        String line = String.join(",",
                escape(playerName),
                String.valueOf(score),
                String.valueOf(total),
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("⚠ Could not save score: " + e.getMessage());
        }
    }
    public static List<String[]> top(int limit) {
        List<String[]> rows = new ArrayList<>();
        Path p = Paths.get(FILE_NAME);
        if (!Files.exists(p)) return rows;
        try (BufferedReader br = Files.newBufferedReader(p)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = parse(line);
                if (parts.length >= 4) rows.add(parts);
            }
        } catch (IOException ignored) {}
        rows.sort((a,b) -> {
            double pa = (Double.parseDouble(a[1]) / Double.parseDouble(a[2]));
            double pb = (Double.parseDouble(b[1]) / Double.parseDouble(b[2]));
            int cmp = Double.compare(pb, pa);
            if (cmp != 0) return cmp;
            cmp = Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1]));
            if (cmp != 0) return cmp;
            return b[3].compareTo(a[3]);
        });
        if (rows.size() > limit) return rows.subList(0, limit);
        return rows;
    }
    private static String escape(String s) {
        if (s.contains(",") || s.contains("\"")) {
            s = s.replace("\"", "\"\"");
            return "\"" + s + "\"";
        }
        return s;
    }
    private static String[] parse(String line) {
        List<String> out = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (c == '"' ) {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                out.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        out.add(sb.toString());
        return out.toArray(new String[0]);
    }
}
