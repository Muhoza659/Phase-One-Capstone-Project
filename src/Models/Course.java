package Models;

import Exceptions.CourseFullException;
import Exceptions.StudentAlreadyEnrolledException;
import java.util.ArrayList;
import java.util.List;


public class Course {

    private String CourseCode;
    private String Title;
    private int Credits;
    private int Capacity;

    private List<Student> LStudents;


    public Course(String CourseCode, String Title, int Credits, int Capacity) {
        this.CourseCode = CourseCode;
        this.Title = Title;
        this.Credits = Credits;
        this.Capacity = Capacity;
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

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public void setCredits(int credits) {
        Credits = credits;
    }


    public List<Student> getLStudents() {
        return LStudents;
    }

    public void addStudents(Student student) throws CourseFullException, StudentAlreadyEnrolledException {
        if(LStudents.size() >= Capacity){
            throw new CourseFullException("Course " + Title + " is full.");
        }
        if (LStudents.contains(student)) {
            throw new StudentAlreadyEnrolledException(
                    student.getName() + " is already enrolled in " + Title);
        }
        LStudents.add(student);
    }

    @Override
    public String toString() {
        return CourseCode + " - " + Title + " (" + Credits + " credits)";
    }
}