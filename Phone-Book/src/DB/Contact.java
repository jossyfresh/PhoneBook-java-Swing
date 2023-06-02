package DB;

import java.time.LocalDate;
import java.util.Date;

public class Contact {
    private String name;
    private String email;
    private String address;
    private String phone;
    private LocalDate Date;


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getDate() {
        return Date;
    }
}