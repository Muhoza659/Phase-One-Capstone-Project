import Models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Instructor inst1 = new Instructor("0002", "Dr.Emmy", "emmy@gmail.com", "Computer Science", 2000.0);

        System.out.println("-----Instructor----");
        System.out.println(inst1.getDetails());
        System.out.println();


        System.out.println("-----Courses and its Credits------");

        Course c1 = new Course("CS101", "Java Programming", 20);
        Course c2 = new Course("MT201", "Calculus", 15);

        System.out.println(c1);
        System.out.println(c2);

        UndergraduateStudent u1 = new UndergraduateStudent("0011", "Aline", "aline@gmail.com", "Computer Science", 3.8);
        UndergraduateStudent u2 = new UndergraduateStudent("0012", "Eric", "eric@gmail.com", "Mathematics", 3.8);
        GraduateStudent g1 = new GraduateStudent("0013", "Tom", "tom@gmail.com", "Mathematics", 3.5);


        List<Student> students = new ArrayList<>();
        students.add(u1);
        students.add(u2);
        students.add(g1);

        u1.addCourses(c1, 85.0);
        g1.addCourses(c1, 76.0);
        g1.addCourses(c2, 90.0);
        u2.addCourses(c2, 80.0);

        System.out.println();

        System.out.println("----- Students -----");

        for (Student s : students) {
            System.out.println(s.getDetails());
            for (Map.Entry<Course, Double> entry : s.getCourses().entrySet()) {
                System.out.println("   Course: " + entry.getKey().getTitle()
                        + " | Grade: " + entry.getValue());
            }
            System.out.println("   Tuition: $" + s.calculateTuition());
            System.out.println();
        }
    }
}


