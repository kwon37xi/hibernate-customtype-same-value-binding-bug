package kr.pe.kwonnam.hibernatesutomtime;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 *
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 20)
    private String name;

    @Type(type = "true_false")
    @Column(name = "employee", columnDefinition = "varchar(5)")
    private Boolean employee;

    @Type(type = "yes_no")
    @Column(name = "male", columnDefinition = "varchar(5)")
    private Boolean male;

    @Column(name = "old")
    private Boolean old;

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

    public Boolean getEmployee() {
        return employee;
    }

    public void setEmployee(Boolean employee) {
        this.employee = employee;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public Boolean getOld() {
        return old;
    }

    public void setOld(Boolean old) {
        this.old = old;
    }
}
