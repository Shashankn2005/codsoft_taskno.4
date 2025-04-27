import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of subjects: ");
        int numSubjects = s.nextInt();
        double totalMarks = 0;
        for (int i = 1; i <= numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + i + " (out of 100): ");
            double marks = s.nextDouble();

            // Validate input
            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks! Please enter a value between 0 and 100.");
                return;
            }

            totalMarks += marks;
        }
        double average = totalMarks / numSubjects;
        double percentage = (totalMarks / (numSubjects * 100)) * 100;
        String grade;
        if (percentage >= 90) {
            grade = "A+";
        } else if (percentage >= 80) {
            grade = "A";
        } else if (percentage >= 70) {
            grade = "B";
        } else if (percentage >= 60) {
            grade = "C";
        } else if (percentage >= 50) {
            grade = "D";
        } else {
            grade = "F (Fail)";
        }
        System.out.println("\n--- Result ---");
        System.out.println("Total Marks: " + totalMarks + " out of " + (numSubjects * 100));
        System.out.println("Average Marks: " + String.format("%.2f", average));
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        System.out.println("Grade: " + grade);
        s.close();
    }
}
