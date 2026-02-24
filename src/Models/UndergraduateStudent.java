package Models;

public class UndergraduateStudent extends Student {

    private static final double flatRate = 1800.0;

    public UndergraduateStudent(String StudentID, String Name, String Email, String Department, double GPA) {
        super(StudentID, Name, Email, Department, GPA);
    }

    @Override
    public double calculateTuition() {
        return flatRate;
    }
}