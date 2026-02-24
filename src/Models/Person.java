package Models;

public abstract class Person{

    private String Name;
    private String Email;

    public Person(String Name, String Email){
        this.Name = Name;
        this.Email = Email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    public abstract String getDetails();
}