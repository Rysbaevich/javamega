package register;

import java.time.LocalDate;

public class Customer {

    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String email;
    String phoneNumber;

    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
