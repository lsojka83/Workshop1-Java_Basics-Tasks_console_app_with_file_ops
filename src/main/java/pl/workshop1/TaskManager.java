package pl.workshop1;

import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {

        boolean wantToExit = false;

        Operations operations = new Operations();
        Scanner scanner = new Scanner(System.in);

        while (!wantToExit) {
            operations.showOptions();
            String input = scanner.next();
            switch (input) {
                case "add": {
                    operations.addTask();
                    break;
                }
                case "remove": {
                    operations.removeTask();
                    break;
                }
                case "list": {
                    operations.listTasks();
                    break;
                }
                case "exit": {
                    System.out.println("Saving to file");
                    System.out.println(ConsoleColors.RED + "Bye, bye!" + ConsoleColors.WHITE_BRIGHT);
                    operations.endProgram();
                    wantToExit = true;
                    break;
                }
                default:
                    System.out.println(ConsoleColors.BLUE + "Please enter a correct option" + ConsoleColors.WHITE_BRIGHT);
            }
        }
    }
}
