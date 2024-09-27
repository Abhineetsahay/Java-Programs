import java.util.ArrayList;
import java.util.Scanner;

class Tasks {
    private final String Task;
    private final String Status;

    public Tasks(String T) {
        this.Task = T;
        this.Status = "Not Completed";
    }

    public Tasks(String T, String st) {
        this.Task = T;
        this.Status = st;
    }

    public String getTask() {
        return Task;
    }

    public String getStatus() {
        return Status;
    }
}

class TaskManager {
    private final ArrayList<Tasks> Task;
    private static Scanner scanner;

    public TaskManager() {
        Task = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addTask() {
        System.out.println("Enter the task you want to add");
        String task = scanner.nextLine();
        if (task.isEmpty()) {
            System.out.println("Enter a valid task!!!!!!!!!!");
            return;
        }
        Task.add(new Tasks(task));
    }

    public void displayTask() {
        if (Task.isEmpty()) {
            System.out.println("Task is Empty");
        } else {
            int i=0;
            for (Tasks t : Task) {
                System.out.println(((i++)+1)+" "+t.getTask() + ", " + t.getStatus());
            }
        }
    }

    public void updateTask() {
        displayTask();
        System.out.println("Enter the number of task you want to update");
        int taskNumber = scanner.nextInt();
        if (taskNumber < 1 || taskNumber > Task.size()) {
            System.out.println("Invalid task Number");
            return;
        }
        System.out.println("Current status: " + Task.get(taskNumber - 1).getStatus());
        System.out.println("Enter new status(Completed/Not-Completed):");
        scanner.nextLine();
        String newStatus = scanner.nextLine();
        Task.set(taskNumber - 1, new Tasks(Task.get(taskNumber - 1).getTask(), newStatus));
        System.out.println("Task updated successfully.");
    }
    public  void searchTask(){
        System.out.println("Enter the Task to find");
        String taskToFind=scanner.nextLine();
        boolean found=false;
        for (Tasks contact:Task){
            if (contact.getTask().equals(taskToFind)){
                System.out.println("Contact found: " + contact.getTask() +"," + contact.getStatus());
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Contact Not found");
        }
    }
    public void deleteTask(){
        System.out.println("Enter the Task to Delete");
        String taskToDelete=scanner.nextLine();
        boolean removed= Task.removeIf(contact -> contact.getTask().equals(taskToDelete));
        if (removed){
            System.out.println("Number deleted Successfully");
        }
        else {
            System.out.println("Number not found");
        }
    }
    void menu() {
        int choice;
        do {
            System.out.println("\nTask Manager");
            System.out.println("1. Add Tasks");
            System.out.println("2. Display Tasks");
            System.out.println("3. Update Tasks");
            System.out.println("4. Search Tasks");
            System.out.println("5. Delete Tasks");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    displayTask();
                    break;
                case 3:
                    updateTask();
                    break;
                case 4:
                    searchTask();
                    break;
                case 5:
                    deleteTask();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }
}

public class Main {
    public static void main(String[] args) {
        TaskManager Task = new TaskManager();
        Task.menu();
    }
}
