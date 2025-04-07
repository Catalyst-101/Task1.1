import java.util.*;

public class StudentGradeManager {
    private static class Student {
        String name;
        double grade;

        Student(String name, double grade) {
            this.name = name;
            this.grade = grade;
        }
    }

    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addStudent();
                case "2" -> viewStudents();
                case "3" -> showStatistics();
                case "4" -> updateGrade();
                case "5" -> deleteStudent();
                case "6" -> {
                    System.out.println("Exiting the program. Thank you.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("----- Student Grade Manager -----");
        System.out.println("1. Add Student and Grade");
        System.out.println("2. View All Students");
        System.out.println("3. View Statistics (Average, Highest, Lowest)");
        System.out.println("4. Update a Student's Grade");
        System.out.println("5. Delete a Student");
        System.out.println("6. Exit");
        System.out.print("Enter your choice (1-6): ");
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter grade (0-100): ");
        double grade = readGrade();

        students.add(new Student(name, grade));
        System.out.println("Student added successfully.");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println();
        System.out.println("List of Students:");
        int i = 1;
        for (Student s : students) {
            System.out.printf("%d. %s - %.2f%n", i++, s.name, s.grade);
        }
    }

    private static void showStatistics() {
        if (students.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        double sum = 0, max = Double.MIN_VALUE, min = Double.MAX_VALUE;
        String topStudent = "", lowStudent = "";

        for (Student s : students) {
            sum += s.grade;
            if (s.grade > max) {
                max = s.grade;
                topStudent = s.name;
            }
            if (s.grade < min) {
                min = s.grade;
                lowStudent = s.name;
            }
        }

        double average = sum / students.size();

        System.out.printf("Average Grade: %.2f%n", average);
        System.out.printf("Highest Grade: %.2f (%s)%n", max, topStudent);
        System.out.printf("Lowest Grade: %.2f (%s)%n", min, lowStudent);
    }

    private static void updateGrade() {
        if (students.isEmpty()) {
            System.out.println("No students to update.");
            return;
        }

        viewStudents();
        System.out.print("Enter student number to update: ");
        int index = readIndex();

        System.out.print("Enter new grade: ");
        double newGrade = readGrade();

        students.get(index).grade = newGrade;
        System.out.println("Grade updated successfully.");
    }

    private static void deleteStudent() {
        if (students.isEmpty()) {
            System.out.println("No students to delete.");
            return;
        }

        viewStudents();
        System.out.print("Enter student number to delete: ");
        int index = readIndex();

        String name = students.remove(index).name;
        System.out.println("Student '" + name + "' removed successfully.");
    }

    private static double readGrade() {
        while (true) {
            try {
                double grade = Double.parseDouble(scanner.nextLine().trim());
                if (grade < 0 || grade > 100) throw new NumberFormatException();
                return grade;
            } catch (NumberFormatException e) {
                System.out.print("Invalid grade. Please enter a number between 0 and 100: ");
            }
        }
    }

    private static int readIndex() {
        while (true) {
            try {
                int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
                if (index < 0 || index >= students.size()) throw new IndexOutOfBoundsException();
                return index;
            } catch (Exception e) {
                System.out.print("Invalid student number. Try again: ");
            }
        }
    }
}
