package pl.workshop1;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileHandler {

    final String DATA_FILE = "tasks.csv";

    public String[][] loadFile() {
        String[][] tasks = new String[0][3];
        Path path = Paths.get(DATA_FILE);

        try {
            List<String> rows = Files.readAllLines(path);

            for (String row : rows) {
                tasks = Arrays.copyOf(tasks, tasks.length + 1);
                tasks[tasks.length - 1] = row.split(", ");
            }

        } catch (IOException e) {
            System.out.println("File doesn't exist or is inaccessible");
        }
        return tasks;
    }

    public void safeToFie(String[][] tasks) {
        try (PrintWriter printWriter = new PrintWriter(DATA_FILE)) {
            for (String[] row : tasks) {
                printWriter.println(String.join(", ", row));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
