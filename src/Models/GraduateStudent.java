package Models;

public class GraduateStudent extends Student {


    private static final double PerCreditRate = 500.0;
    private static final double ResearchFees = 1600.0;

    public GraduateStudent( String StudentID,String Name, String Email, String Department, double GPA) {
        super( StudentID,Name, Email, Department, GPA);
    }

    @Override
    public double calculateTuition() {
        int totalCredits = 0;
        for (Course course : getCourses().keySet()) {
            totalCredits += course.getCredits();
        }
        return (totalCredits * PerCreditRate) + ResearchFees;
    }
}

