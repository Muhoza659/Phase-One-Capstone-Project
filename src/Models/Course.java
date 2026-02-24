package Models;

import java.util.ArrayList;
import java.util.List;


public class Course {

    private String CourseCode;
    private String Title;
    private int Credits;

    private List<Student> LStudents;


    public Course(String CourseCode, String Title, int Credits) {
        this.CourseCode = CourseCode;
        this.Title = Title;
        this.Credits = Credits;
        this.LStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getCredits() {
        return Credits;
    }

    public void setCredits(int credits) {
        Credits = credits;
    }

    public List<Student> getLStudents() {
        return LStudents;
    }

    public void addStudents(Student student) {
        LStudents.add(student);
    }

    @Override
    public String toString() {
        return CourseCode + " - " + Title + " (" + Credits + " credits)";
    }
}