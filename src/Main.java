import Models.*;
import Services.UniversityManager;
import Util.FileManager;
import Exceptions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UniversityManager manager = new UniversityManager();
        FileManager fileManager = new FileManager();



        Instructor inst1 = new Instructor("0002", "Dr.Emmy", "emmy@gmail.com", "Computer Science", 2000.0);
        System.out.println("-----Instructor----");
        System.out.println(inst1.getDetails());
        System.out.println();

        Course c1 = new Course("CS101", "Java Programming", 20, 2);
        Course c2 = new Course("MT201", "Calculus", 15, 1);

        System.out.println("-----Courses and Credits------");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println();

        manager.createCourse(c1);
        manager.createCourse(c2);


        Student s1 = new UndergraduateStudent("0011", "Aline", "aline@gmail.com", "Computer Science", 3.8);
        Student s2 = new UndergraduateStudent("0012", "Eric", "eric@gmail.com", "Mathematics", 3.3);
        Student s3 = new GraduateStudent("0013", "Tom", "tom@gmail.com", "Mathematics", 3.5);

        manager.registerStudent(s1);
        manager.registerStudent(s2);
        manager.registerStudent(s3);

        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);

        manager.enrollStudentInCourse("0011", "CS101");
        manager.enrollStudentInCourse("0012", "MT201");
        manager.enrollStudentInCourse("0013", "MT201");

        System.out.println();

        s1.addCourses(c1, 85.0);
        s2.addCourses(c2, 76.0);
        s3.addCourses(c2, 90.0);

        for (Student s : students) {
            System.out.println(s.getDetails());
            for (Map.Entry<Course, Double> entry : s.getCourses().entrySet()) {
                System.out.println("   Course: " + entry.getKey().getTitle()
                        + " | Grade: " + entry.getValue());
            }
            System.out.println("   Tuition: $" + s.calculateTuition());
            System.out.println();
        }


        System.out.println("Average GPA in Computer Science: " +
                manager.calculateAverageGpaByDepartment("Computer Science"));

        System.out.println();

        manager.findTopPerformingStudent()
                .ifPresent(top -> System.out.println("Top Student: " +
                        top.getName() + " (GPA: " + top.getGPA() + ")"));
        System.out.println();

        manager.findStudentById("0012")
                .ifPresent(s -> System.out.println("Found Student: " + s.getDetails()));
        System.out.println();

        manager.findCourseByCode("MT201")
                .ifPresent(c -> System.out.println("Found Course: " + c));
        System.out.println();

        // --- Load previous data ---
        try {
            manager.getStudents().addAll(fileManager.loadStudents());
            manager.getCourses().addAll(fileManager.loadCourses());
            System.out.println("Data loaded successfully!");
        } catch (IOException e) {
            System.out.println("No previous data found. Starting fresh.");
        }

        boolean running = true;

        while (running) {
            System.out.println("----- University Management System -----");
            System.out.println("1. Register Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll in Course");
            System.out.println("4. View Student Record");
            System.out.println("5. Generate Dean's List");
            System.out.println("6. Save and Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                // Register student

                case 1:
                    System.out.print("Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Department: ");
                    String dept = scanner.nextLine();
                    System.out.print("GPA: ");
                    double gpa = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Student Type (1=Undergraduate, 2=Graduate): ");
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    Student student = type == 2 ?
                            new GraduateStudent(id, name, email, dept, gpa) :
                            new UndergraduateStudent(id, name, email, dept, gpa);
                    manager.registerStudent(student);
                    System.out.println("Student registered successfully!");
                    break;


                  // Add course
                case 2:
                    System.out.print("Course Code: ");
                    String code = scanner.nextLine();
                    System.out.print("Course Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Capacity: ");
                    int capacity = scanner.nextInt();
                    System.out.print("Credits: ");
                    int credits = scanner.nextInt();
                    scanner.nextLine();

                    Course course = new Course(code, title, capacity, credits);
                    manager.createCourse(course);
                    System.out.println("Course added successfully!");
                    break;

            // Enroll student
                case 3:
                    System.out.print("Student ID: ");
                    String sid = scanner.nextLine();
                    System.out.print("Course Code: ");
                    String ccode = scanner.nextLine();

                    manager.findStudentById(sid).ifPresentOrElse(
                            s -> manager.findCourseByCode(ccode).ifPresentOrElse(
                                    c -> {
                                        try {
                                            manager.enrollStudentInCourse(s, c);
                                            System.out.println("Enrollment successful!");
                                        } catch (CourseFullException | StudentAlreadyEnrolledException e) {
                                            System.out.println("Enrollment Error: " + e.getMessage());
                                        }
                                    },
                                    () -> System.out.println("Course not found")
                            ),
                            () -> System.out.println("Student not found")
                    );
                    break;

            // View student record
                case 4:
                    System.out.print("Enter Student ID: ");
                    String searchId = scanner.nextLine();
                    manager.findStudentById(searchId).ifPresentOrElse(
                            s -> {
                                System.out.println(s.getDetails());
                                if (s.getCourses().isEmpty()) {
                                    System.out.println("   No enrolled courses.");
                                } else {
                                    for (Map.Entry<Course, Double> entry : s.getCourses().entrySet()) {
                                        System.out.println("   Course: " +
                                                entry.getKey().getTitle() +
                                                " (" + entry.getKey().getCourseCode() + ")" +
                                                " | Grade: " + entry.getValue());
                                    }
                                }
                            }, () -> System.out.println("Student not found"));
                    break;

              // Dean's List
                case 5:
                    System.out.println("\n--- Dean's List ---");
                    for (Student s : manager.generateDeansList()) {
                        System.out.println(s.getName() + " | GPA: " + s.getGPA());
                    }
                    break;

              // Save & exit
                case 6:
                    try {
                        fileManager.saveStudents(manager.getStudents());
                        fileManager.saveCourses(manager.getCourses());
                        System.out.println("Data saved successfully!");
                    } catch (IOException e) {
                        System.out.println("Error saving data!");
                    }
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        scanner.close();
    }
}