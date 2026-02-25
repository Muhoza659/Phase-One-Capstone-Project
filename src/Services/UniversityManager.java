package Services;
import Exceptions.CourseFullException;
import Exceptions.StudentAlreadyEnrolledException;
import Models.Course;
import Models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UniversityManager {

    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    public void registerStudent(Student student) {
        students.add(student);
    }

    public void createCourse(Course course) {
        courses.add(course);
    }

    public void enrollStudentInCourse(Student student, Course course)
            throws CourseFullException, StudentAlreadyEnrolledException {

        student.enrollCourse(course);
        course.addStudents(student);
    }

    public void enrollStudentInCourse(String studentId, String courseCode) {
        Optional<Student> student = findStudentById(studentId);
        Optional<Course> course = findCourseByCode(courseCode);


        try {
            enrollStudentInCourse(student.get(), course.get());
            System.out.println("Enrollment successful: " +
                    student.get().getName() + " → " + course.get().getCourseCode());
        } catch (CourseFullException | StudentAlreadyEnrolledException e) {
            System.out.println("Enrollment error: " + e.getMessage());
        }
    }

    public double calculateAverageGpaByDepartment(String Department) {
        return students.stream()
                .filter(s -> s.getDepartment().equalsIgnoreCase(Department))
                .mapToDouble(Student::getGPA)
                .average()
                .orElse(0.0);
    }

    public Optional<Student> findTopPerformingStudent() {
        return students.stream()
                .max((s1, s2) -> Double.compare(s1.getGPA(), s2.getGPA()));
    }

    public Optional<Student> findStudentById(String Id) {
        return students.stream()
                .filter(s -> s.getStudentID().equals(Id))
                .findFirst();
    }

    public Optional<Course> findCourseByCode(String Code) {
        return courses.stream()
                .filter(c -> c.getCourseCode().equalsIgnoreCase(Code))
                .findFirst();

    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Student> generateDeansList() {
        List<Student> deansList = new ArrayList<>();

        for (Student s : students) {
            if (s.getGPA() > 3.5) {
                deansList.add(s);
            }
        }
        return deansList;
    }
}
