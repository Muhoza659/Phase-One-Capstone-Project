package Util;

import Models.*;
import java.io.*;
import java.util.*;

public class FileManager {

    private static final String STUDENT_FILE = "students.csv";
    private static final String COURSE_FILE = "courses.csv";

    // Save students to CSV
    public void saveStudents(List<Student> students) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENT_FILE))) {
            for (Student s : students) {
                String type = s instanceof GraduateStudent ? "Grad" : "Undergrad";
                writer.println(type + "," +
                        s.getStudentID() + "," +
                        s.getName() + "," +
                        s.getEmail() + "," +
                        s.getDepartment() + "," +
                        s.getGPA());
            }
        }
    }

    // Load students from CSV
    public List<Student> loadStudents() throws IOException {
        List<Student> students = new ArrayList<>();
        File file = new File(STUDENT_FILE);
        if (!file.exists()) return students;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Student s;
                if (data.length == 6) {
                    s = data[0].equals("Grad") ?
                            new GraduateStudent(data[1], data[2], data[3], data[4], Double.parseDouble(data[5])) :
                            new UndergraduateStudent(data[1], data[2], data[3], data[4], Double.parseDouble(data[5]));
                } else {
                    s = new UndergraduateStudent(data[0], data[1], data[2], data[3], Double.parseDouble(data[4]));
                }
                students.add(s);
            }
        }
        return students;
    }

    // Save courses to CSV
    public void saveCourses(List<Course> courses) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COURSE_FILE))) {
            for (Course c : courses) {
                writer.println(c.getCourseCode() + "," +
                        c.getTitle() + "," +
                        c.getCapacity() + "," +
                        c.getCredits());
            }
        }
    }

    // Load courses from CSV
    public List<Course> loadCourses() throws IOException {
        List<Course> courses = new ArrayList<>();
        File file = new File(COURSE_FILE);
        if (!file.exists()) return courses;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Course c = new Course(data[0], data[1],
                        Integer.parseInt(data[2]),
                        Integer.parseInt(data[3]));

                courses.add(c);
            }
        }
        return courses;
    }
}