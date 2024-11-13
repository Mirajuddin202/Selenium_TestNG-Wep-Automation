package setup;

public class EmployeeModel {
    String firstname;
    String lastName;
    String username;
    String password;


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeModel(String firstName, String lastName, String username, String password){
        this.firstname=firstName;
        this.lastName=lastName;
        this.username=username;
        this.password=password;

    }
    public EmployeeModel(){

    }
}
