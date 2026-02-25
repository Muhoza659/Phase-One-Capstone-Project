package Models;


import Exceptions.StudentAlreadyEnrolledException;
import java.util.HashMap;
import java.util.Map;

public abstract class Student extends Person {

    private String StudentID;
    private double GPA;
    private String Department;

    private Map<Course, Double> courses;

    public Student( String StudentID,String Name, String Email, String Department,double GPA) {
        super(Name, Email);
        this.StudentID = StudentID;
        this.GPA = GPA;
        this.Department = Department;
        this.courses = new HashMap<>();
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        if (GPA >= 0 && GPA <= 4.0) {
            this.GPA = GPA;
        }
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {

        this.Department = Department;


    }

    public Map<Course, Double> getCourses() {
        return courses;
    }

    public void enrollCourse(Course course) throws StudentAlreadyEnrolledException {
        if (courses.containsKey(course)) {
            throw new StudentAlreadyEnrolledException(
                    "Student already enrolled in " + course.getTitle());
        }
        courses.put(course, 0.0);
    }


    public void addCourses(Course course, Double grade) {

        courses.put(course,grade);
    }


    public abstract double calculateTuition();
    @Override
    public String getDetails() {
        return "StudentID: " + StudentID +
                ", Name: " + getName() +
                ", GPA: " + GPA +
                ", Department: " + Department ;
    }

}
