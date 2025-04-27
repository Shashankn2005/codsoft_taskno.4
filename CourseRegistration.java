import java.util.*;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;
    List<Student> registeredStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public boolean registerStudent(Student student) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(student);
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return courseCode + " - " + title + " | Schedule: " + schedule + " | Capacity: " + capacity + " | Enrolled: " + registeredStudents.size();
    }
}

class Student {
    String studentId;
    String name;
    List<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            registeredCourses.add(course);
        }
    }

    public String toString() {
        return studentId + " - " + name;
    }

    public void showCourses() {
        System.out.println("Registered Courses for " + name + ":");
        for (Course c : registeredCourses) {
            System.out.println(" - " + c.courseCode + ": " + c.title);
        }
    }
}

public class CourseRegistration {
    static Map<String, Course> courses = new HashMap<>();
    static Map<String, Student> students = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Course Registration System ---");
            System.out.println("1. Add Course");
            System.out.println("2. Add Student");
            System.out.println("3. Register Student to Course");
            System.out.println("4. View All Courses");
            System.out.println("5. View Student's Registered Courses");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    registerStudentToCourse();
                    break;
                case 4:
                    viewAllCourses();
                    break;
                case 5:
                    viewStudentCourses();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void addCourse() {
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        System.out.print("Enter course title: ");
        String title = scanner.nextLine();
        System.out.print("Enter course description: ");
        String description = scanner.nextLine();
        System.out.print("Enter course capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter course schedule: ");
        String schedule = scanner.nextLine();

        Course course = new Course(code, title, description, capacity, schedule);
        courses.put(code, course);
        System.out.println("Course added successfully.");
    }

    static void addStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        Student student = new Student(id, name);
        students.put(id, student);
        System.out.println("Student added successfully.");
    }

    static void registerStudentToCourse() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = courses.get(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (course.registerStudent(student)) {
            student.registerCourse(course);
            System.out.println("Student registered to course successfully.");
        } else {
            System.out.println("Course is full!");
        }
    }

    static void viewAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            for (Course c : courses.values()) {
                System.out.println(c);
            }
        }
    }

    static void viewStudentCourses() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);

        if (student != null) {
            student.showCourses();
        } else {
            System.out.println("Student not found.");
        }
    }
}
