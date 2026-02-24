
import Models.*;
import Services.UniversityManager;

public class Main {
    public static void main(String[] args) {

        UniversityManager manager = new UniversityManager();


        Instructor inst1 = new Instructor("0002", "Dr.Emmy", "emmy@gmail.com", "Computer Science", 2000.0);
        System.out.println("-----Instructor----");
        System.out.println(inst1.getDetails());
        System.out.println();

        Course c1 = new Course("CS101", "Java Programming", 20, 2);
        Course c2 = new Course("MT201", "Calculus", 15, 1);

        System.out.println("-----Courses and Credits------");
        System.out.println(c1);
        System.out.println(c2);

        manager.createCourse(c1);
        manager.createCourse(c2);


        Student s1 = new UndergraduateStudent("0011", "Aline", "aline@gmail.com", "Computer Science", 3.8);
        Student s2 = new UndergraduateStudent("0012", "Eric", "eric@gmail.com", "Mathematics", 3.3);
        Student s3 = new GraduateStudent("0013", "Tom", "tom@gmail.com", "Mathematics", 3.5);

        manager.registerStudent(s1);
        manager.registerStudent(s2);
        manager.registerStudent(s3);

        manager.enrollStudentInCourse("0011", "CS101");
        manager.enrollStudentInCourse("0012", "MT201");
        manager.enrollStudentInCourse("0013", "CS101");
        manager.enrollStudentInCourse("0013", "MT201");


        System.out.println("Average GPA in Computer Science: " +
                manager.calculateAverageGpaByDepartment("Computer Science"));


        manager.findTopPerformingStudent()
                .ifPresent(top -> System.out.println("Top Student: " +
                        top.getName() + " (GPA: " + top.getGPA() + ")"));

        manager.findStudentById("0012")
                .ifPresent(s -> System.out.println("Found Student: " + s.getDetails()));


        manager.findCourseByCode("MT201")
                .ifPresent(c -> System.out.println("Found Course: " + c));
    }
}