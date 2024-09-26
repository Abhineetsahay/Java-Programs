import java.util.ArrayList;
import java.util.Scanner;

class StudentGrader {
    private String name;
    private int number;
    private int subject;

    public StudentGrader(String name, int number, int subject) {
        this.name = name;
        this.number = number;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public int getSubject() {
        return subject;
    }
}

class StudentGradeManager {
    private ArrayList<StudentGrader> students;
    private static Scanner scanner;

    public StudentGradeManager() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addStudent(String name, int number, int subject) {
        students.add(new StudentGrader(name, number, subject));
    }

    public void displayStudents() {
        for (StudentGrader student : students) {
            System.out.println("Name: " + student.getName() + ", Number: " + student.getNumber() + ", Subject: " + student.getSubject());
        }
    }

    public static void main(String[] args) {
        StudentGradeManager manager = new StudentGradeManager();
        int n = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            String name = scanner.next();
            int number = scanner.nextInt();
            int subject = scanner.nextInt();
            manager.addStudent(name, number, subject);
        }
        manager.displayStudents();
    }
}
