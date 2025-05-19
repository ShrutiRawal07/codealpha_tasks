import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class StudentGradeTracker { 
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Student Grade Tracker ===");
        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Input student names and grades
        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter name for student " + (i + 1) + ": ");
            String name = scanner.nextLine();

            int grade;
            while (true) {
                System.out.print("Enter grade for " + name + ": ");
                grade = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (grade >= 0 && grade <= 100) break;
                System.out.println("Please enter a valid grade between 0 and 100.");
            }

            students.add(new Student(name, grade));
        }

        // Initialize tracking variables
        int total = 0;
        Student highest = students.get(0);
        Student lowest = students.get(0);

        // Calculate stats
        for (Student s : students) {
            total += s.grade;
            if (s.grade > highest.grade) highest = s;
            if (s.grade < lowest.grade) lowest = s;
        }

        double average = (double) total / students.size();

        // Display results
        System.out.println("\n=== Grade Summary ===");
        System.out.println("Total Students: " + numStudents);
        System.out.printf("Average Grade: %.2f\n", average);
        System.out.println("Highest Grade: " + highest.grade + " (Student: " + highest.name + ")");
        System.out.println("Lowest Grade: " + lowest.grade + " (Student: " + lowest.name + ")");

        scanner.close();
    }
}
