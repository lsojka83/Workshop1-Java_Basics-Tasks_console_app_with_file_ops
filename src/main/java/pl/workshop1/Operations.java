package pl.workshop1;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Scanner;

public class Operations {

    private String[][] tasks;
    InputValidator inputValidator = new InputValidator();
    FileHandler fileHandler = new FileHandler();

    public Operations() {
        tasks = fileHandler.loadFile();
    }

    public void addTask() {

        Scanner scanner = new Scanner(System.in);
        String[] newTask = new String[3];
        System.out.println("Please enter new task description");
        System.out.print("> ");
        newTask[TaskData.TASK_DESCRIPTION] = scanner.nextLine();
        do {
            System.out.println("Please enter new task due date: YYYY-MM-DD");
            System.out.print("> ");
            newTask[TaskData.TASK_DUE_DATE] = scanner.nextLine();
        } while (!inputValidator.validateTaskDate(newTask[TaskData.TASK_DUE_DATE]));
        do {
            System.out.println("Please enter if the task is important: true/false");
            System.out.print("> ");
            newTask[TaskData.TASK_IMPORTANCE] = scanner.nextLine();
        } while (!inputValidator.validateImportanceInput(newTask[TaskData.TASK_IMPORTANCE]));

        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = newTask;
    }

    public void listTasks() {
        int index = 0;
        if (!ArrayUtils.isEmpty(tasks)) {
            for (String[] row : tasks) {
                System.out.print(String.format("%s : ", index));
                for (String token : row) {
                    System.out.print(token + " ");
                }
                System.out.println();
                index++;
            }
        } else {
            System.out.println("List is empty.");
        }
    }

    public void removeTask() {
        Scanner scanner = new Scanner(System.in);
        listTasks();
        int index = 0;
        System.out.println("Enter a number of the task to remove");
        System.out.print("> ");
        boolean badInput = true;
        if (!ArrayUtils.isEmpty(tasks)) {
            while (badInput) {
                try {
                    index = Integer.parseInt(scanner.nextLine());
                    if (index < 0 || index > tasks.length - 1) {
                        System.out.println("Entered incorrect value. Please enter number from 0 to " + (tasks.length - 1));
                        System.out.print("> ");
                        continue;
                    }
                    badInput = false;
                } catch (Exception e) {
                    System.out.println("Wrong input");
                    System.out.println("Entered incorrect value. Please enter number from 0 to " + (tasks.length - 1));
                    System.out.print("> ");
                    badInput = true;

                }
            }
            tasks = ArrayUtils.remove(tasks, index);
            System.out.println("Task successfully removed");
        } else {
            System.out.println("List is empty.");
        }
    }


    public void showOptions() {
        String[] options = {"add", "remove", "list", "exit"};
        System.out.println(ConsoleColors.BLUE + "Please enter an option:" + ConsoleColors.WHITE_BRIGHT);
        for (String o : options) {
            System.out.println(o);
        }
        System.out.print("> ");
    }

    public void endProgram() {
        fileHandler.safeToFie(tasks);
    }
}
