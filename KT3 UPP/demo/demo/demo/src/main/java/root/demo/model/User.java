package root.demo.model;

import javax.persistence.*;
import java.net.UnknownServiceException;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true,nullable = false)
    private Long id;

    @Column(name="name",unique = false,nullable = false)
    String name;

    @Column(name="lastname",unique = false,nullable = false)
    String lastname;

    @Column(name="city",unique = false,nullable = false)
    String city;

    @Column(name = "country",unique = false,nullable = false)
    String country;

    @Column(name="tittle",unique = false,nullable = true)
    String tittle;

    @Column(name = "email",unique = false,nullable = false)
    String email;

    @Column(name="scienceFields",unique = false,nullable = true)
    String scienceFields;

    @Column(name="username",unique = true,nullable = false)
    String username;

    @Column(name="password",unique = false,nullable = false)
    String password;

    @Column(name="role",unique = false,nullable = true)
    String role;

    @Column(name="hash",unique = false,nullable = true)
    String hash;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getScienceFields() {
        return scienceFields;
    }

    public void setScienceFields(String scienceFields) {
        this.scienceFields = scienceFields;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public User(){}

    public User(String name,String lastname,String city,String state,String tittle,String email,String username,String password,String fields,String role)
    {
        this.name = name;
        this.lastname = lastname;
        this.city = city;
        this.country = state;
        this.tittle = tittle;
        this.email = email;
        this.scienceFields = fields;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
