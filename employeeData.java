/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package suparmarket;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class employeeData {
    private String employeeId;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
   

    public employeeData(String employeeId, String password, String firstName, String lastName, String gender) {
        this.employeeId = employeeId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }


            
            
    
    
    
}
