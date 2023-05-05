package jm.task.core.jdbc.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName", nullable = true, columnDefinition = "VARCHAR(50) DEFAULT ''")
    private String lastName;

    @Column(name = "age")
    private Byte age;

    public User() {
    }

    public User(String firstName, String lastName, byte age) {
        this.name = firstName;
        this.lastName = lastName;
        this.age = age;
    }


    public Long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = (long) id;
    }

    public String getFirstName() {
        return name;
    }

    public void setFirstName(String firstName) {
        this.name = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + name + ", lastName=" + lastName + ", email=" + age + "]";
    }

}