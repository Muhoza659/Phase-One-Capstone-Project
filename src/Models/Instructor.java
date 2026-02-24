package Models;

public class Instructor extends Person {

    private String InstructorID;
    private String Department;
    private Double Salary;

    public Instructor( String InstructorID, String Name, String Email, String Department, Double Salary) {
        super(Name, Email);
        this.InstructorID =InstructorID;
        this.Department = Department;
        this.Salary = Salary;
    }

    public String getInstructorID() {
        return InstructorID;
    }

    public void setInstructorID(String instructorID) {
        InstructorID = instructorID;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public Double getSalary() {
        return Salary;
    }

    public void setSalary(Double salary) {
        Salary = salary;
    }
    @Override
    public String getDetails() {
        return "Instructor ID: " + InstructorID +
                ", Name: " + getName() +
                ", Department: " + Department +
                ", Salary: $" + Salary;
    }

}

